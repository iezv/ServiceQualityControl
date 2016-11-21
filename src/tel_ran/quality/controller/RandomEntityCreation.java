package tel_ran.quality.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import tel_ran.quality.entities.*;
import tel_ran.quality.model.dao.QualityOrmCreate;

public class RandomEntityCreation {
	private static Employee genManager ;
	private static final Company COMPANY = new Company ("TOYOTA", "Tel Aviv", genManager);
	private static final int N_EMPLOYEES = 5;
	private static final int N_RANDOM = 100;
	private static final int N_CLIENTS = 100;
	private static final Set <String> servises = new HashSet<>();
	static QualityOrmCreate qualityOrm;
	static Random gen = new Random();
	
	static{
		servises.add("repair"); servises.add("tire_mounting"); servises.add("washing");
	}
			
	public static void main(String[] args) throws Exception {
		AbstractApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");
		qualityOrm = ctx.getBean(QualityOrmCreate.class);
		createRandonEmployees();
		createCompany();
		createRandomClients();
		createServices();
		createQuestions();
		createRandomReceivedFeedbacks();
		ctx.close();
	}

	private static void createRandomReceivedFeedbacks() {
		for(int i=0; i<N_CLIENTS; i++){
			qualityOrm.addReceivedFeedback(genReceivedFeedback(), 10000000+i, "repair");
			qualityOrm.addReceivedFeedback(genReceivedFeedback(), 10000000+i, "tire_mounting");
			qualityOrm.addReceivedFeedback(genReceivedFeedback(), 10000000+i, "washing");
		}
		
	}

	private static ReceivedFeedback genReceivedFeedback() {
		return new ReceivedFeedback(gen.nextInt(100000000), genNewDate(), "comment"+gen.nextInt(N_RANDOM),genRandomResult());
	}

	private static Result genRandomResult() {
		return new Result(1+gen.nextInt(5), 1+gen.nextInt(5), 1+gen.nextInt(5), 1+gen.nextInt(5), 1+gen.nextInt(5));
	}

	private static void createQuestions() {
		qualityOrm.addQuestion(new Question(1,"Are you satisfied with the quality of services?", 5), servises);
		qualityOrm.addQuestion(new Question(2,"Are you satisfied with timelines?", 5), servises);
		qualityOrm.addQuestion(new Question(3,"Our employee was polite to you?", 5), servises);
		qualityOrm.addQuestion(new Question(4,"Overall rating of our service", 5), servises);
		qualityOrm.addQuestion(new Question(5,"Do you like our advertising?", 5), servises);
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
	
	private static Date genNewDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.parse(2016 +"-"+ gen.nextInt(11)+"-"+ gen.nextInt(28));
		} catch (ParseException e) {
			return new Date();
		}
	}

		
}
  
