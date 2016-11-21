package tel_ran.quality.entities;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="service")
public class Service {
	@Id
	@Column(name = "servicename", nullable = false, insertable = true, updatable = true)
	String servicename;
	
	@ManyToOne 
	Company company;
	
	@OneToOne
    Employee responsibleperson;
	
	@ManyToMany (mappedBy="services")
	Set<Question> questions;
	
	public Service(String servicename) {
		super();
		this.servicename = servicename;
		questions = new HashSet<>();
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Service() {
		super();
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
  
	public String getServicename() {
		return servicename;
	}

	public Employee getResponsibleperson() {
		return responsibleperson;
	}
	
	public void setResponsibleperson(Employee responsibleperson) {
		this.responsibleperson = responsibleperson;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((servicename == null) ? 0 : servicename.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Service other = (Service) obj;
		if (servicename == null) {
			if (other.servicename != null)
				return false;
		} else if (!servicename.equals(other.servicename))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Service [servicename=" + servicename + ", company=" + company + ", responsibleperson="
				+ responsibleperson + "]";
	}
	
	
}
