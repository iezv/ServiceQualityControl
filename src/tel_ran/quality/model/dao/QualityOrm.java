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
	
	private Set<Question> getQuestions(Set<Integer> questionsId) {
		Set<Question> questions = new HashSet<>();
		for(Integer id: questionsId){
			questions.add(em.find(Question.class, id));
		}
		return questions;
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

	@Transactional
	public boolean addClient(Client client, String companyName) {
		if (em.find(Client.class, client.getClientId()) != null)
			return false;
		Company company = getCompany(companyName);
		client.setCompany(company);
		em.persist(client);
		return true;
	}
		
	@Transactional
	public boolean addQuestion(Question question, Set<String> serviceNames) {
		if (em.find(Question.class, question.getQuestionId()) != null)
			return false;
		Set<Service> services = getServices(serviceNames);
		question.setServices(services);
		em.persist(question);
		return true;
	}
	
	private Set<Service> getServices(Set<String> serviceNames) {
		Set<Service> services = new HashSet<>();
		for(String name: serviceNames){
			services.add(em.find(Service.class, name));
		}
		return services;
	}
	
	@Transactional
	public boolean addReceivedFeedback(ReceivedFeedback feedback, int ClientId,String serviceName) {
		if (em.find(ReceivedFeedback.class, feedback.getFeedbackid()) != null)
			return false;
		Client client = getClient(ClientId);
		Service service = getService(serviceName);
		feedback.setClient(client);
		feedback.setService(service);
		em.persist(feedback);
		return true;
	}

	private Service getService(String serviceName) {
		return em.find(Service.class, serviceName);
	}

	private Client getClient(int clientId) {
		return em.find(Client.class, clientId);
	}
   
		
}
