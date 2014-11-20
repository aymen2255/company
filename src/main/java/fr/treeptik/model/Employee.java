package fr.treeptik.model;

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Employee {
	
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String statut;
	private String firstName;
	private String lastName;
	private Integer salary;
	private Date startDate;
	
	@ManyToOne
	private Department department;
	
	@OneToOne
	private Address address;
	
	@ManyToOne
	private Employee manager;
	
	@ManyToMany
	private List<Project>projects;
	
	@ElementCollection
	private List<String>phoneNumbers;
	

	public Employee(Integer id, String statut, String firstName,
			String lastName, Integer salary) {
		super();
		this.id = id;
		this.statut = statut;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		//this.startDate = startDate;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "nom: " + firstName;
	}
	

}
