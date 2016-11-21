package tel_ran.quality.entities;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name="company")
public class Company {
	
	@Id
	@Column(name = "namecompany", nullable = false, insertable = true, updatable = true)
	String namecompany;
	String city;
	
	@OneToOne
	Employee genmanager;
	
	public Company(String namecompany, String city, Employee genmanager) {
		super();
		this.namecompany = namecompany;
		this.city = city;
		
	}

	public Company() {
		super();
	}

	public String getNamecompany() {
		return namecompany;
	}

	public String getCity() {
		return city;
	}

	public Employee getGenmanager() {
		return genmanager;
	}
	
	public void setGenmanager(Employee genmanager) {
		this.genmanager = genmanager;
	}

	@Override
	public String toString() {
		return "Company [namecompany=" + namecompany + ", city=" + city + ", genmanager=" + genmanager + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((namecompany == null) ? 0 : namecompany.hashCode());
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
		Company other = (Company) obj;
		if (namecompany == null) {
			if (other.namecompany != null)
				return false;
		} else if (!namecompany.equals(other.namecompany))
			return false;
		return true;
	}
	
		
}
