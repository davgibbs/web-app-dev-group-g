//Contains boilerplate code for library_records class - D.Mullen EE417_Group_Project
package ie.dcu.library.model;
	
import java.time.LocalDate;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

	@Entity(name="library_records") // This tells Hibernate to make a table out of this class
	public class LibraryRecord  {
	  @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Integer id;
	  
	  @Column(name="member_id")
	  private Integer memberid;
	  private Integer book_id;
	  @JsonFormat(pattern="yyyy-MM-dd")	//Tell Jackson to format the date to only provide date info
	  private LocalDate borrowed_date;
	  @JsonFormat(pattern="yyyy-MM-dd")
	  private LocalDate due_date;
	  private Boolean is_returned;
	  
	  public LibraryRecord() {
		  
	  }
	 
	  public LibraryRecord(Integer id, Integer memberid, Integer book_id, LocalDate borrowed_date, LocalDate due_date, Boolean is_returned) {
		  this.id = id;
		  this.memberid = memberid;
		  this.book_id = book_id;
		  this.borrowed_date = borrowed_date;
		  this.due_date = due_date;
		  this.is_returned = is_returned;
	  }

	  //Getters and Setters	  
	  public Integer getBookId() {
	    return book_id;
	  }

	  public void setBookId(Integer book_id) {
	    this.book_id = book_id;
	  }

	  public Integer getMemberid() {
	    return memberid;
	  }

	  public void setMemberid(Integer member) {
	    this.memberid = member;
	  }
	  
	  public Integer getId() {
		    return id;
	  }

	  public void setRecordid(Integer id) {
		    this.id = id;
	  }
	
	  public LocalDate getBorrowed_date() {
	    return borrowed_date;
	  }

	  public void setBorrowed_date(LocalDate borrowed_date) {
	    this.borrowed_date = borrowed_date;
	  }
	  
	  public LocalDate getDue_date() {
	     return due_date;
	  }

	  public void setDue_date(LocalDate due_date) {
		this.due_date = due_date;
		  } 
	  
	  public void setIsReturned(Boolean is_returned) {
		this.is_returned = is_returned;
	  } 
	  
	  public Boolean getIsReturned() {
	    return is_returned;
	  }

}
