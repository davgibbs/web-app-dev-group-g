//Contains boilerplate code for library_records class - D.Mullen EE417_Group_Project
package ie.dcu.library.model;
	
import java.time.LocalDate;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

	@Entity(name="library_records") // This tells Hibernate to make a table out of this class
	public class LibraryRecord  {
	  @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Integer recordid;
	  private Integer memberid;
	  private Integer isbn;
	  @JsonFormat(pattern="yyyy-MM-dd")	//Tell Jackson to format the date to only provide date info
	  private LocalDate borrowed_date;
	  @JsonFormat(pattern="yyyy-MM-dd")
	  private LocalDate due_date;
	  private String overdue;
	  
	  public LibraryRecord() {
		  
	  }
	 
	  public LibraryRecord(Integer membid, Integer isbn, LocalDate borrowed_date, LocalDate due_date, String overdue) {
		//  this.recordid = recid;
		  this.memberid = membid;
		  this.isbn = isbn;
		  this.borrowed_date = borrowed_date;
		  this.due_date = due_date;
		  this.overdue = overdue;
	  }

	  //Getters and Setters	  
	  public Integer getISBN() {
	    return isbn;
	  }

	  public void setISBN(Integer id) {
	    this.isbn = id;
	  }

	  public Integer getMemberid() {
	    return memberid;
	  }

	  public void setMemberid(Integer member) {
	    this.memberid = member;
	  }
	  
	  public Integer getRecordid() {
		    return recordid;
		  }

		  public void setRecordid(Integer recid) {
		    this.recordid = recid;
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
	  
	  public void setOverdue(String overdue) {
		this.overdue = overdue;
		  } 
	  
	  public String getOverdue() {
		    return overdue;
		  }

}
