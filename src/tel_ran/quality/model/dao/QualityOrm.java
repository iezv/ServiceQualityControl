package tel_ran.quality.model.dao;
import java.util.*;
import javax.persistence.*;
import org.springframework.transaction.annotation.Transactional;
import tel_ran.qualityControl.entities.*;

public class QualityOrm {
	
	@PersistenceContext(unitName = "springHibernate", type = PersistenceContextType.EXTENDED)
	EntityManager em;
	
	@Transactional
	public boolean addEmployee(Employee employee) {
		if (em.find(Employee.class, employee.getTz()) != null)
			return false;
		em.persist(employee);
		return true;
	}
	
	@Transactional
	public boolean addService(Service service, long managerTz,String namecompany) {
		if (em.find(Service.class, service.getServicename()) != null)
			return false;
		Employee manager = getEmployee(managerTz);
		Company company = getCompany(namecompany);
		service.setResponsibleperson(manager);
		service.setCompany(company);
		em.persist(service);
		return true;
	}
	
	private Company getCompany(String namecompany) {
		Company comp = em.find(Company.class, namecompany);
		return comp;
	}

	@Transactional
	public boolean addCompany(Company company, long genManagerTz) {
		if (em.find(Company.class, company.getNamecompany()) != null)
			return false;
		Employee genmanager = getEmployee(genManagerTz);
		company.setGenmanager(genmanager);
		em.persist(company);
		return true;
	}
			
	private Employee getEmployee(long genManagerTz) {
		Employee emp = em.find(Employee.class, genManagerTz);
		return emp;
	}

	
   
	

	
}
