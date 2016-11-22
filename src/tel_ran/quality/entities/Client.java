package tel_ran.quality.entities;

import javax.persistence.*;

@Entity
public class Client {
	@Id
	@Column(name = "clientid", nullable = false, insertable = true, updatable = true)
	int clientId;
	String name;
	String phone;
	String email;
	@Embedded
	Address address;
	
	@ManyToOne
	Company company;
	
	public Client(int clientId, String name, String phone, String email, Address address) {
		super();
		this.clientId = clientId;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}
	
	public Client() {
		super();
	}

	public int getClientId() {
		return clientId;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public Address getAddress() {
		return address;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + clientId;
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
		Client other = (Client) obj;
		if (clientId != other.clientId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", name=" + name + ", phone=" + phone + ", email=" + email
				+ ", address=" + address + "]";
	}
		
}
