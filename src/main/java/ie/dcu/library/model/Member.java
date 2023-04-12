package ie.dcu.library.model;

//Contains boilerplate code for members class - D.Mullen EE417_Group_Project

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity // This tells Jakarta JPA to make a table out of this class
public class Member {
	@Id
	private Long memberid;
	private String uname;
	private String pwdhash;
	private String firstname;
	private String surname;
	private String email;
	private String roleid;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Long getMemberid() {
		return memberid;
	}
	
	public void setMemberid(Long memberid) {
		this.memberid = memberid;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
