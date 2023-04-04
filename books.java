//Contains boilerplate code for books class - D.Mullen EE417_Group_Project

package com.G_Database.G_Database;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity // This tells Jakarta JPA to make a table out of this class
public class books {
  @Id
  private Integer isbn;
  private String title;
  private String author;
  private String available;
  @JsonFormat(pattern="yyyy-MM-dd")	//Tell Jackson to format the date to only provide date info
  private Date date;
//  private String Customer;
  private String image;
  
 public books() {
	  
  }
  
  public books(Integer id, String title, String author, String avail, Date date, String image) {
	  this.isbn = id;
	  this.title = title;
	  this.author = author;
	  this.available = avail;
	  this.date = date;
//	  this.Customer = customer;
	  this.image = image;
  }
  
//Getters and Setters  
  
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
  
  public String getAvailable() {
	    return available;
	  }

  public void setAvailable(String available) {
	this.available = available;
	  } 
  
  public Date getDate() {
	    return date;
	  }

  public void setDate(Date date) {
	this.date = date;
	  } 
  
/*  public String getCustomer() {
	    return Customer;
	  }

public void setCustomer(String customer) {
	this.Customer = customer;
	  } */

public String getImage() {
    return image;
  }

public void setImage(String image) {
this.image = image;
  } 
}