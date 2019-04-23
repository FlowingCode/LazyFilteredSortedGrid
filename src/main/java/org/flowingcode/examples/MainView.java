package org.flowingcode.examples;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.router.Route;

/**
 * The main view contains this special Grid
 */
@SuppressWarnings("serial")
@Route("")
public class MainView extends VerticalLayout {
	
	PersonService personService = new PersonService();

    public MainView() {
    	
        PersonFilter gridFilter = new PersonFilter();
        
        DataProvider<Person, PersonFilter> dataProvider =
      		  DataProvider.fromFilteringCallbacks(
      		  query -> {
      		    Optional<PersonFilter> filter = query.getFilter();
      		    List<PersonSort> sortOrders = query.getSortOrders().stream().map(sortOrder->new PersonSort(sortOrder.getSorted(),sortOrder.getDirection().equals(SortDirection.ASCENDING))).collect(Collectors.toList());
      		    return getPersonService().fetchPersons(
      		      query.getOffset(),
      		      query.getLimit(),
      		      filter.map(f -> f.getNameFilter()).orElse(null),
      		      filter.map(f -> f.getLastNameFilter()).orElse(null),
      		      sortOrders
      		    );
      		  },
      		  query -> {
      		    Optional<PersonFilter> filter = query.getFilter();
      		    return getPersonService().getPersonCount(
      		      filter.map(f -> f.getNameFilter()).orElse(null),
      		      filter.map(f -> f.getLastNameFilter()).orElse(null)
      		    );
      		  }
      		);
        ConfigurableFilterDataProvider<Person,Void,PersonFilter> dp = dataProvider.withConfigurableFilter();
        dp.setFilter(gridFilter);
        Grid<Person> personsGrid = new Grid<>(Person.class);
        personsGrid.setDataProvider(dp);
        personsGrid.setMultiSort(true);
        HeaderRow hr = personsGrid.prependHeaderRow();
        TextField nameFilterTF = new TextField();
        nameFilterTF.addValueChangeListener(ev->{
        	gridFilter.setNameFilter(ev.getValue());
        	dp.refreshAll();
        });
        hr.getCell(personsGrid.getColumnByKey("name")).setComponent(nameFilterTF);
        TextField lastNameFilterTF = new TextField();
        lastNameFilterTF.addValueChangeListener(ev->{
        	gridFilter.setLastNameFilter(ev.getValue());
        	dp.refreshAll();
        });
        hr.getCell(personsGrid.getColumnByKey("lastName")).setComponent(lastNameFilterTF);        
        add(personsGrid);
    }
    
	private PersonService getPersonService() {
		return personService;
	}
}
