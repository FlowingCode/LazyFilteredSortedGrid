package org.flowingcode.examples;

/**
 * This class represents a given entity. In this case just a Person.
 * 
 * @author Martin Lopez / Flowing Code S.A.
 *
 */
public class Person {

	private String name;
	private String lastName;
	private Integer age;

	public Person(String name, String lastName, Integer age) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}
