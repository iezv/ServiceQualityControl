package tel_ran.quality.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ticketId")
	int ticketId;
	Date startDate;
	Date closeDate;
	Date progressDate;
	
	public Ticket(Date startDate, Date closeDate, Date progressDate) {
		super();
		this.startDate = startDate;
		this.closeDate = closeDate;
		this.progressDate = progressDate;
	}

	public Ticket() {
		super();
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

	public Date getProgressDate() {
		return progressDate;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", startDate=" + startDate + ", closeDate=" + closeDate
				+ ", progressDate=" + progressDate + "]";
	}
	
	

}
