//Contains boilerplate code for books class - D.Mullen EE417_Group_Project

package ie.dcu.library.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="books") // This tells Jakarta JPA to make a table out of this class
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  private Integer isbn;
  private String title;
  private String author;
  @JsonFormat(pattern="yyyy-MM-dd")	//Tell Jackson to format the date to only provide date info
  private Date publish_date;
  private String image_path;
  private String description;
  
  public Book() {  
  }
  
  public Book(Integer id, String title, String author, Date date, String image, String description) {
	  this.isbn = id;
	  this.title = title;
	  this.author = author;
	  this.publish_date = date;
	  this.image_path = image;
	  this.description = description;
  }
  
  //Getters and Setters  
  public Integer getId() {
	return id;
  }

  public void setId(Integer id) {
	  this.id = id;
	}

	  public Integer getISBN() {
	    return isbn;
	  }
	
	  public void setISBN(Integer id) {
	    this.isbn = id;
	  }
	
	  public String getTitle() {
	    return title;
	  }
	
	  public void setTitle(String title) {
	    this.title = title;
	  }
	
	  public String getAuthor() {
	    return author;
	  }
	
	  public void setAuthor(String author) {
	    this.author = author;
	  }
  
    public Date getPublish_date() {
		return publish_date;
	}
	
	public void setPublish_date(Date publish_date) {
		this.publish_date = publish_date;
	}
	
	public String getImage_path() {
		return image_path;
	}
	
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}