package tel_ran.quality.controller;
import java.util.*;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import tel_ran.quality.model.dao.QualityOrm;
import tel_ran.qualityControl.entities.*;

public class RandomEntityCreation {
	private static Employee genManager ;
	private static final Company COMPANY = new Company ("TOYOTA", "Tel Aviv", genManager);
	private static final int N_EMPLOYEES = 5;
	private static final int N_RANDOM = 100;
	private static final int N_CLIENTS = 100;
	private static final Set <String> servises = new HashSet<>();
	static QualityOrm qualityOrm;
	static Random gen = new Random();
	
	static{
		servises.add("repair"); servises.add("tire_mounting"); servises.add("washing");
	}
			
	public static void main(String[] args) throws Exception {
		AbstractApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");
		qualityOrm = ctx.getBean(QualityOrm.class);
		createRandonEmployees();
		createCompany();
		createRandomClients();
		createServices();
		createQuestions();
		ctx.close();
	}

	private static void createQuestions() {
		qualityOrm.addQuestion(new Question(1,"Are you satisfied with the quality of services?", 5, 1.0f), servises);
		qualityOrm.addQuestion(new Question(2,"Are you satisfied with timelines?", 5, 0.9f), servises);
		qualityOrm.addQuestion(new Question(3,"Our employee was polite to you?", 5, 0.8f), servises);
		qualityOrm.addQuestion(new Question(4,"Overall rating of our service", 5, 1.0f), servises);
		qualityOrm.addQuestion(new Question(5,"Do you like our advertising?", 5, 0.0f), servises);
	}

	private static void createRandomClients() {
		for (int i=0; i<N_CLIENTS; i++){
			qualityOrm.addClient(new Client(10000000+i, "name"+gen.nextInt(N_RANDOM), "phone"+gen.nextInt(N_RANDOM), 
					"email"+gen.nextInt(N_RANDOM), genRandomAddress()),COMPANY.getNamecompany());
		}
	}

	private static void createCompany() {
		genManager = new Employee(100000001, "Ivan", 1975, genRandomAddress(), "555-55-55");
		qualityOrm.addEmployee(genManager);
		qualityOrm.addCompany(COMPANY, genManager.getTz());
	}

	private static void createServices() {
		qualityOrm.addService(new Service("repair"), 100000002, COMPANY.getNamecompany());
		qualityOrm.addService(new Service("tire_mounting"), 100000003, COMPANY.getNamecompany());
		qualityOrm.addService(new Service("washing"), 100000004, COMPANY.getNamecompany());	
	}

	private static void createRandonEmployees() {
		for (int i=2; i<N_EMPLOYEES; i++){
			Employee employee = new Employee(100000000+i, "employee"+i, 1950+gen.nextInt(60), 
					                                                      genRandomAddress(), "phone"+i);
			qualityOrm.addEmployee(employee);
		}
	}

	private static Address genRandomAddress() {
		Address address = new Address("city"+gen.nextInt(N_RANDOM), "street"+gen.nextInt(N_RANDOM), 
				                                                      gen.nextInt(N_RANDOM), gen.nextInt(N_RANDOM));
		return address;
	}

		
}
  
