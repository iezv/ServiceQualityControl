package tel_ran.quality.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ticketId")
	int ticketId;
	Date startDate;
	Date closeDate;
		
	@ManyToOne
	Service service;
	
	@ManyToOne 
	Question question;
	
	public Ticket(Date startDate) {
		super();
		this.startDate = startDate;
	}

	public Ticket() {
		super();
	}

	
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getTicketId() {
		return ticketId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public Service getService() {
		return service;
	}

	public Question getQuestion() {
		return question;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ticketId;
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
		Ticket other = (Ticket) obj;
		if (ticketId != other.ticketId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", startDate=" + startDate + ", closeDate=" + closeDate + ", service="
				+ service + ", question=" + question + "]";
	}

	

}
