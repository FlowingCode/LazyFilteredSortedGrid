package com.flowingcode.examples.lazygrid;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class represents an external service, that could be a REST endpoint, or a Database-backed service.
 * 
 * @author Martin Lopez / Flowing Code S.A.
 *
 */
public class PersonService {
	
	private String[] names = new String[] {"John","Henry","Marc","Vincent","Donald","Anne","Grace","Emma","Olivia","Emily","Michael","Ethan","Joseph","Charles"};
	private String[] lastNames = new String[] {"Smith","Jones","Williams","Brown","Wilson"};
	private int[] ages = new int[] {30,35,40,45,50,55};
	
	private ArrayList<Person> internalPersonsFullList = new ArrayList<>();
	
	/**
	 * The contents of this method are just for demo purposes and irrelevant for the features that want to be presented.
	 */
	public PersonService() {
		for(int i = 0;i<10000;i++) {
			internalPersonsFullList.add(new Person(
					names[((int)(Math.random()*names.length))], 
					lastNames[((int)(Math.random()*lastNames.length))], 
					ages[((int)(Math.random()*ages.length))]));
		}
	}

	/**
	 * The contents of this method are just for demo purposes and irrelevant for the features that want to be presented.
	 * @param offset
	 * @param limit
	 * @param nameFilter
	 * @param lastNameFilter
	 * @param sortOrders
	 * @return
	 */
	public Stream<Person> fetchPersons(int offset, int limit, String nameFilter, String lastNameFilter, List<PersonSort> sortOrders) {
		System.out.println("Offset: " + offset + ", limit: " + limit + ", nameFilter='" + nameFilter + "', lastNameFilter = '" + lastNameFilter + "', sortOrders = " + sortOrders);
		Comparator<Person> comparator = (o1,o2)->0;
		for (PersonSort personSort : sortOrders) {
			switch (personSort.getPropertyName()) {
				case PersonSort.NAME:
					comparator = comparator.thenComparing(Person::getName);
					break;
				case PersonSort.LAST_NAME:
					comparator = comparator.thenComparing(Person::getLastName);
					break;
				case PersonSort.AGE:
					comparator = comparator.thenComparing(Person::getAge);
					break;
			}
			if (!personSort.isDescending()) comparator = comparator.reversed();
		}
		List<Person> sortedList = new LinkedList<>(internalPersonsFullList);
		sortedList.sort(comparator);
		List<Person> result = sortedList.stream()
				.filter(person->person.getName().contains(nameFilter==null?"":nameFilter))
				.filter(person->person.getLastName().contains(lastNameFilter==null?"":lastNameFilter))
				.skip(offset)
				.limit(limit).collect(Collectors.toList());
		System.out.println("Size: " + result.size());
		return result.stream();
	}

	/**
	 * The contents of this method are just for demo purposes and irrelevant for the features that want to be presented.
	 * @param nameFilter
	 * @param lastNameFilter
	 * @return
	 */
	public int getPersonCount(String nameFilter, String lastNameFilter) {
		List<Person> result = internalPersonsFullList.stream()
				.filter(person->person.getName().contains(nameFilter==null?"":nameFilter))
				.filter(person->person.getLastName().contains(lastNameFilter==null?"":lastNameFilter))
				.collect(Collectors.toList());
		return result.size();
	}

}
