package tel_ran.quality.model.dao;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.persistence.*;
import org.springframework.transaction.annotation.Transactional;
import tel_ran.quality.entities.*;

public class QualityOrmCreate {
		
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
		addTicket(feedback);
		return true;
	}
   
	public void addTicket(ReceivedFeedback feedback) {
		Date date = feedback.getFeedbackdate();
		Service service = feedback.getService();
		Result result = feedback.getResultQuestion();
		List<Integer> recivedDBres = getReceivedFeedbackByMonseAndByService(date, service.getServicename(),result);
		
		for(int i=0; i<recivedDBres.size(); i++){
			if ((recivedDBres.get(i)%10)==0 && recivedDBres.get(i)==0){
				//System.out.println(recivedDBres.get(i));
				long query=(long)em.createQuery(String.format ("select COUNT (t) from Ticket t where t.closeDate is NULL and t.service.servicename='%s' and t.question.questionId=%d",service.getServicename(),i+1)).getSingleResult();
			//	System.out.println(String.format ("select COUNT (t) from Ticket t where t.closeDate is NULL and t.service.servicename='%s' and t.question.questionId=%d",service.getServicename(),i+1));
				  if((int)query<1){
				  	createNewTicket(new Ticket(date), service.getServicename(), i+1);
				 }
			}
		}  
	} 
	
	@Transactional
	private boolean createNewTicket(Ticket ticket, String servicename, int i) {
		if (em.find(Ticket.class, ticket.getTicketId()) != null)
			return false;
		Question question = em.find(Question.class, i);
		Service service = em.find(Service.class, servicename);
		ticket.setQuestion(question);
		ticket.setService(service);
		em.persist(ticket);
		return true;
		
	}
	
	private List<Integer> getReceivedFeedbackByMonseAndByService(Date date, String servicename, Result result) {
		List<Integer> reslist = new ArrayList<>();
		SimpleDateFormat dateForm = new SimpleDateFormat("%yyyy-MM%");
		String dateStr = dateForm.format(date);
		long query1=(long)em.createQuery(String.format ("select COUNT (r) from ReceivedFeedback r where r.feedbackdate like '%s' and service='%s' and resultQuestion.ques1 < 3",
				dateStr, servicename)).getSingleResult();
		reslist.add((int)query1);
		long query2=(long)em.createQuery(String.format ("select COUNT (r) from ReceivedFeedback r where r.feedbackdate like '%s' and service='%s' and resultQuestion.ques2 < 3",
				dateStr, servicename)).getSingleResult();
		reslist.add((int)query2);
		long query3=(long)em.createQuery(String.format ("select COUNT (r) from ReceivedFeedback r where r.feedbackdate like '%s' and service='%s' and resultQuestion.ques3 < 3",
				dateStr, servicename)).getSingleResult();
		reslist.add((int)query3);
		long query4=(long)em.createQuery(String.format ("select COUNT (r) from ReceivedFeedback r where r.feedbackdate like '%s' and service='%s' and resultQuestion.ques4 < 3",
				dateStr, servicename)).getSingleResult();
		reslist.add((int)query4);
		long query5=(long)em.createQuery(String.format ("select COUNT (r) from ReceivedFeedback r where r.feedbackdate like '%s' and service='%s' and resultQuestion.ques5 < 3",
				dateStr, servicename)).getSingleResult();
		reslist.add((int)query5);
		return reslist;
	}

	private Service getService(String serviceName) {
		return em.find(Service.class, serviceName);
	}

	private Client getClient(int clientId) {
		return em.find(Client.class, clientId);
	}
   
		
}
