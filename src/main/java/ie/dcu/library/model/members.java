package ie.dcu.library.model;

//Contains boilerplate code for members class - D.Mullen EE417_Group_Project

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity // This tells Jakarta JPA to make a table out of this class
public class members {
@Id
private Integer memberid;
private String Name;
private String Surname;
private String Email;

public members() {
	  
}

public members(Integer membid, String name, String surname, String email) {
	  this.memberid = membid;
	  this.Name = name;
	  this.Surname = surname;
	  this.Email = email;
}

public Integer getMemberID() {
  return memberid;
}

public void setMemberID(Integer membid) {
  this.memberid = membid;
}

public String getName() {
  return Name;
}

public void setName(String name) {
  this.Name = name;
}

public String getSurname() {
  return Surname;
}

public void setSurname(String sname) {
  this.Surname = sname;
}

public String getEmail() {
	    return Email;
	  }

public void setEmail(String email) {
	this.Email = email;
	  } 
}


