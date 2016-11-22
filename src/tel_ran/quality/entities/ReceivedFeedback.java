package tel_ran.quality.entities;

import java.util.*;
import javax.persistence.*;

@Entity
public class ReceivedFeedback {
	@Id
	@Column(name = "feedbackid", nullable = false, insertable = true, updatable = true)
	
	int feedbackid;
	Date feedbackdate;
	String comment;
	@Embedded
	Result resultQuestion;
	
	@OneToOne
	Client client;
	
	@OneToOne
	Service service;
		
	public ReceivedFeedback(int feedbackid, Date feedbackdate, String comment, Result resultQuestion) {
		super();
		this.feedbackid = feedbackid;
		this.feedbackdate = feedbackdate;
		this.comment = comment;
		this.resultQuestion = resultQuestion;
	}
	
	public ReceivedFeedback() {
		super();
	}

	public int getFeedbackid() {
		return feedbackid;
	}

	public Date getFeedbackdate() {
		return feedbackdate;
	}

	public String getComment() {
		return comment;
	}

	public Client getClient() {
		return client;
	}

	public Result getResultQuestion() {
		return resultQuestion;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + feedbackid;
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
		ReceivedFeedback other = (ReceivedFeedback) obj;
		if (feedbackid != other.feedbackid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReceivedFeedback [feedbackid=" + feedbackid + ", feedbackdate=" + feedbackdate + ", comment=" + comment
				+ ", resultQuestion=" + resultQuestion + ", client=" + client + ", service=" + service + "]";
	}
	
	

}
