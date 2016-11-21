package tel_ran.quality.entities;

import javax.persistence.*;

@Entity
@Table(name="employee")
public class Employee {
	@Id
	@Column(name = "tz", nullable = false, insertable = true, updatable = true)
	long tz;
	String name;
	int birthyear;
	@Embedded
	Address address;
	String phone;
	
   public Employee(long tz, String name, int birthyear, Address address, String phone) {
		super();
		this.tz = tz;
		this.name = name;
		this.birthyear = birthyear;
		this.address = address;
		this.phone = phone;
	}

	public Employee() {
		super();
	}

	public long getTz() {
		return tz;
	}

	public String getName() {
		return name;
	}

	public int getBirthyear() {
		return birthyear;
	}

	public Address getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (tz ^ (tz >>> 32));
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
		Employee other = (Employee) obj;
		if (tz != other.tz)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [tz=" + tz + ", name=" + name + ", birthyear=" + birthyear + ", address=" + address
				+ ", phone=" + phone + "]";
	}
		
			
}
