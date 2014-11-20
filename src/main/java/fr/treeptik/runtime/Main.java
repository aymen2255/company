package fr.treeptik.runtime;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.treeptik.model.Department;
import fr.treeptik.model.Employee;
import fr.treeptik.model.Project;

public class Main {

	public static void main(String[] args) {

		// getAllEmployee();
		// getAllEmployeeWithDepartement();
		// getAllEmployeeOfProject();
		// getProjectsAndEmployees();
		// getEmployeeWithOutManagerWorkOnCloud();
		// getAllDepartmentInOrder();
		// getEmployeeMaxSalaryWithOutManager();
		// getEmployee();
		// getEmployeeNameA();
		// /getEmployeeHabitantADetroit();
		// getEmployeeSalaryInf3000();
		// getProjectsManagedByJhon();
		// getEmployee3();
		//getManager();
		getAjusterSalary();
	}

	public static List<Employee> getAllEmployee() {
		EntityManager entityManager = Persistence.createEntityManagerFactory(
				"sample").createEntityManager();
		Query query = entityManager.createQuery("select e from Employee e");
		List resultList = query.getResultList();
		for (int i = 0; i < resultList.size(); i++) {
			System.out.println(resultList.get(1).toString());

		}

		return resultList;

	}

	public static List<Employee> getAllEmployeeWithDepartement() {
		EntityManager entityManager = Persistence.createEntityManagerFactory(
				"sample").createEntityManager();
		TypedQuery<Employee> query = entityManager.createQuery(
				"select e from Employee e left join e.department",
				Employee.class);
		List<Employee> resultList = query.getResultList();
		for (Employee object : resultList) {
			System.out.println(object.getFirstName() + " "
					+ object.getDepartment().getName());
			;
		}

		return resultList;

	}

	public static List<Employee> getAllEmployeeOfProject() {
		EntityManager entityManager = Persistence.createEntityManagerFactory(
				"sample").createEntityManager();
		TypedQuery<Employee> query = entityManager
				.createQuery("select e from Employee e left join e.projects",
						Employee.class);

		List<Employee> Result = query.getResultList();
		for (Employee employee : Result) {
			System.out.println(employee.getFirstName());
		}

		return null;

	}

	public static List<Employee> getProjectsAndEmployees() {
		EntityManager entityManager = Persistence.createEntityManagerFactory(
				"sample").createEntityManager();
		TypedQuery<Project> query = entityManager.createQuery(
				"select p from Project p left join p.employees", Project.class);
		List<Project> Result = query.getResultList();
		for (Project project : Result) {
			System.out.println(project.getEmployees());
		}

		return null;

	}

	public static List<Employee> getEmployeeWithOutManagerWorkOnCloud() {
		EntityManager entityManager = Persistence.createEntityManagerFactory(
				"sample").createEntityManager();
		TypedQuery<Employee> query = entityManager
				.createQuery(
						"select e from Employee e left join e.manager where e.manager is null and e in (select e from Employee e left join e.projects p where p.name = 'Cloudunit')",
						// "select e from Employee e left join e.projects p where p.name = 'Cloudunit'",
						Employee.class);
		List<Employee> resultList = query.getResultList();
		for (Employee employee : resultList) {
			System.out.println(employee.getId());
		}
		return null;

	}

	public static List<Department> getAllDepartmentInOrder() {
		EntityManager entityManager = Persistence.createEntityManagerFactory(
				"sample").createEntityManager();
		TypedQuery<Department> query = entityManager.createQuery(
				"select d from Department d order by name", Department.class);
		List<Department> resultList = query.getResultList();
		for (Department department : resultList) {
			System.out.println(department.getName());
		}
		return null;

	}

	// 9- Récupérer l’employé le mieux payé qui n’a pas de manager mais qui
	// n’est pas lui-même manager
	public static Employee getEmployeeMaxSalaryWithOutManager() {

		EntityManager entityManager = Persistence.createEntityManagerFactory(
				"sample").createEntityManager();
		TypedQuery<Employee> query = entityManager
				.createQuery(
						"select e from Employee e where e.salary = (select  max(e.salary) from Employee e where e.manager is null and e.statut!='Manager') ",
						Employee.class);
		Employee result = query.getSingleResult();
		System.out.println(result);

		return null;

	}

	// 10- Récupérer les deux employés les moins bien payés

	public static List<Employee> getEmployee() {
		EntityManager entityManager = Persistence.createEntityManagerFactory(
				"sample").createEntityManager();
		TypedQuery<Employee> query = entityManager.createQuery(
				"select e from Employee e order by e.salary asc",
				Employee.class);
		query.setFirstResult(0).setMaxResults(2);
		List<Employee> resultList = query.getResultList();
		for (Employee employee : resultList) {
			System.out.println(employee.getFirstName());
		}

		return null;
	}

	// 11- Lister les employés ayant été embauché entre le 13 avril 2013 et
	// aujourd’hui

	public static List<Employee> getEmployee2013AndNow() {
		EntityManager entityManager = Persistence.createEntityManagerFactory(
				"sample").createEntityManager();
		entityManager.createQuery("", Employee.class);
		return null;

	}

	// 12- Lister les noms des employés commençant par A
	public static List<Employee> getEmployeeNameA() {
		EntityManager entityManager = Persistence.createEntityManagerFactory(
				"sample").createEntityManager();
		TypedQuery<Employee> query = entityManager.createQuery(
				"select e from Employee e where e.firstName like 'K%'",
				Employee.class);
		List<Employee> resultList = query.getResultList();
		for (Employee employee : resultList) {
			System.out.println(employee);
		}
		return null;

	}

	// 13- Lister les employés attachés à leurs projets et à leurs numéros de
	// téléphone

	// 14- Lister les employés habitants à Détroit

	public static List<Employee> getEmployeeHabitantADetroit() {
		EntityManager entityManager = Persistence.createEntityManagerFactory(
				"sample").createEntityManager();
		TypedQuery<Employee> query = entityManager
				.createQuery(
						"select e from Employee e left join e.address a where a.town='Detroit'",
						Employee.class);
		List<Employee> resultList = query.getResultList();
		for (Employee employee : resultList) {
			System.out.println(employee);
		}
		return null;

	}

	// 15- Compter le nombre d’employés ayant un salaire inférieur à 30000 $

	public static List<Employee> getEmployeeSalaryInf3000() {
		EntityManager entityManager = Persistence.createEntityManagerFactory(
				"sample").createEntityManager();
		TypedQuery<Employee> query = entityManager.createQuery(
				"select e from Employee e where e.salary < 30000",
				Employee.class);
		List<Employee> resultList = query.getResultList();
		for (Employee employee : resultList) {
			System.out.println(employee.getFirstName());
		}
		return null;

	}

	// 16- Récupérer les projets non managés par John Harper
	public static List<Project> getProjectsManagedByJhon() {
		EntityManager entityManager = Persistence.createEntityManagerFactory(
				"sample").createEntityManager();
		TypedQuery<Project> query = entityManager
				.createQuery(
						"select p from Project p left join p.employees e where e.firstName='Harper'",
						Project.class);
		List<Project> resultList = query.getResultList();
		for (Project project : resultList) {
			System.out.println(project.getId());
		}
		return null;

	}

	// 17- Récupérer les trois salariés les mieux payés qui ne sont pas managers
	// et qui ont fourni leur numéro de téléphone
	public static List<Employee> getEmployee3() {
		EntityManager entityManager = Persistence.createEntityManagerFactory(
				"sample").createEntityManager();
		TypedQuery<Employee> query = entityManager
				.createQuery(
						"select e from Employee e left join e.phoneNumbers t where e.statut!='Manager' and t is not null order by e.salary asc ",
						Employee.class);
		List<Employee> resultList = query.setMaxResults(3).getResultList();
		for (Employee employee : resultList) {
			System.out.println(employee.getId());
		}
		return null;

	}

	// 22- Compter le nombre de managers ayant au moins deux projets

	public static List<Employee> getManager() {
		EntityManager entityManager = Persistence.createEntityManagerFactory(
				"sample").createEntityManager();
		Query query = entityManager
				.createQuery("select count(e) from Employee e where e.statut='Manager' and e in (select e from Employee e join e.projects p group by e.id having count(p)>1)");
		Long singleResult = (Long) query.getSingleResult();
		System.out.println(singleResult);
		return null;

	}
	
//23- Ajuster le salaire des deux salariés les moins payés à 26000 $
	public static List<Employee> getAjusterSalary() {
		EntityManager entityManager = Persistence.createEntityManagerFactory(
				"sample").createEntityManager();
		TypedQuery<Employee> query = entityManager.createQuery(
				"select e from Employee e order by e.salary asc",
				Employee.class);
		query.setFirstResult(0).setMaxResults(2);
		List<Employee> resultList = query.getResultList();
		entityManager.getTransaction().begin();
		for (Employee employee : resultList) {
			employee.setSalary(27000);
			entityManager.merge(employee);
			entityManager.flush();
			
			//System.out.println(employee.getFirstName());
		}
		entityManager.getTransaction().commit();
		return null;

	}
}
