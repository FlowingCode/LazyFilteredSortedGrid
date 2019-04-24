package com.flowingcode.examples.lazygrid;

/**
 * This class represents the filters that can be applied to a collection of Persons, to filter them out.
 * 
 * @author Martin Lopez / Flowing Code S.A.
 *
 */
public class PersonFilter {
	
	private String nameFilter = null;
	private String lastNameFilter = null;

	public PersonFilter() {	
	}
	
	public PersonFilter(String nameFilter, String lastNameFilter) {
		this.setNameFilter(nameFilter);
		this.setLastNameFilter(lastNameFilter);
	}

	public String getNameFilter() {
		return nameFilter;
	}

	public String getLastNameFilter() {
		return lastNameFilter;
	}

	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;
	}

	public void setLastNameFilter(String lastNameFilter) {
		this.lastNameFilter = lastNameFilter;
	}
}