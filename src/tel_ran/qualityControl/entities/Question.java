package tel_ran.qualityControl.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Question {
	@Id
	@Column(name = "questionid", nullable = false, insertable = true, updatable = true)
	int questionId;
	String questionBody;
	int maxRating;
	float wightRating;
	
	@ManyToMany 
	Set<Service> services;

	public Question(int questionId, String questionBody, int maxRating, float wightRating) {
		super();
		this.questionId = questionId;
		this.questionBody = questionBody;
		this.maxRating = maxRating;
		this.wightRating = wightRating;
		services = new HashSet<>();
	}

	public Question() {
		super();
	}

	public int getQuestionId() {
		return questionId;
	}

	public String getQuestionBody() {
		return questionBody;
	}

	public int getMaxRating() {
		return maxRating;
	}

	public float getWightRating() {
		return wightRating;
	}

	public Set<Service> getServices() {
		return services;
	}

	public void setServices(Set<Service> services) {
		this.services = services;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + questionId;
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
		Question other = (Question) obj;
		if (questionId != other.questionId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", questionBody=" + questionBody + ", maxRating=" + maxRating
				+ ", wightRating=" + wightRating + ", services=" + services + "]";
	}
	
	
}
