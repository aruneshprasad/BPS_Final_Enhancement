package bps.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name="admindetails")
@Table(uniqueConstraints=@UniqueConstraint(columnNames="username"))
public class AdminDetails {
	
	@Id
	@Column(unique=true)
	private String username;
	
	@Basic
	private String password;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

}
