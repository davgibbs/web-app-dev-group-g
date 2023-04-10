//RestController for Library interface - includes endpoints for books, members, library records and login tables D.Mullen Group G 20/03/2023

package com.G_Database.G_Database;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/library") // URL format is http://localhost:8080/library/

public class BookController {
	
  @Autowired 
  private LibraryRepository bookRepository;
  @Autowired
  private RecordsRepository libRepository;
  @Autowired
  private MembersRepository membRepository;
  @Autowired
  private LoginRepository loginRepository;
  
  
//**********Mappings for the books Queries***********
  
  @CrossOrigin(origins = "*")
  @PostMapping("/addbook") // POST to add to all fields within books database (Insert new Book)
  public void add(@RequestBody books book) {
	  bookRepository.save(book);
  }

//Returns book by entered book id (isbn number)
  @CrossOrigin(origins = "*")
  @GetMapping("/getbook/{id}") 
  public books getBookById(
      @PathVariable(value="id") int id)
  {
      return bookRepository.findById(id);
  }
  
//Returns all books
  @GetMapping(path="/getallbooks")		
  public @ResponseBody Iterable<books> getAllUsers() {
    return bookRepository.findAll();
  }

//This is a delete request which deletes the entry under specified id value
  @CrossOrigin(origins = "*")
  @DeleteMapping("/deletebook/{id}")
  public void deletebook(
      @PathVariable(value = "id") int id)
  {
      bookRepository.deleteById(id);
  }
  
  @GetMapping(path="/getauthor")
  public ResponseEntity<List<books>> getBooksByAuthor(@RequestParam String author){
	  return new ResponseEntity<List<books>>(bookRepository.findByAuthor(author), HttpStatus.OK);
  }
  
  @GetMapping(path="/gettitle")
  public ResponseEntity<List<books>> getBooksByTitle(@RequestParam String title){
	  return new ResponseEntity<List<books>>(bookRepository.findByTitle(title), HttpStatus.OK);
  }
   
  //**********Mappings for the library_records Queries***********
  
  @CrossOrigin(origins = "*")
  @PostMapping("/addrecord") // POST to add to all fields within books database (Insert new Book)
  public void add(@RequestBody library_records record) {
	  libRepository.save(record);
  }
  
//Returns record by entered record id (isbn number)
  @CrossOrigin(origins = "*")
  @GetMapping("/getrecord/{id}") 
  public library_records getRecordById(
      @PathVariable(value="id") int id)
  {
      return libRepository.findById(id);
  }
  
//Returns all books
  @GetMapping(path="/getallrecords")		
  public @ResponseBody Iterable<library_records> getAllRecords() {
    return libRepository.findAll();
  }

//This is a delete request which deletes the entry under specified id value
  @CrossOrigin(origins = "*")
  @DeleteMapping("/deleterecord/{id}")
  public void deleterecord(
      @PathVariable(value = "id") int id)
  {
      libRepository.deleteById(id);
  }
  
 //**********Mappings for the members Queries***********
  
  @CrossOrigin(origins = "*")
  @PostMapping("/addmember") // POST to add to all fields within members table (Insert new Member)
  public void add(@RequestBody members member) {
	  membRepository.save(member);
  }
  
//Returns record by entered id (memberid number)
  @CrossOrigin(origins = "*")
  @GetMapping("/getmember/{id}") 
  public members getMemberById(
      @PathVariable(value="id") int id)
  {
      return membRepository.findById(id);
  }
  
//Returns all members
  @GetMapping(path="/getallmembers")		
  public @ResponseBody Iterable<members> getAllMembers() {
    return membRepository.findAll();
  }

//This is a delete request which deletes the entry under specified id value
  @CrossOrigin(origins = "*")
  @DeleteMapping("/deletemember/{id}")
  public void deletemember(
      @PathVariable(value = "id") int id)
  {
      membRepository.deleteById(id);
  }
  
//**********Mappings for the login Queries***********
  
  @CrossOrigin(origins = "*")
  @PostMapping("/addlogin") // POST to add to all fields within books database (Insert new Book)
  public void add(@RequestBody login login) {
	  loginRepository.save(login);
  }

//Returns book by entered book id (isbn number)
  @CrossOrigin(origins = "*")
  @GetMapping("/getlogin/{id}") 
  public login getLoginById(
      @PathVariable(value="id") int id)
  {
      return loginRepository.findById(id);
  }
  
//Returns all books
  @GetMapping(path="/getalllogins")		
  public @ResponseBody Iterable<login> getAllLogins() {
    return loginRepository.findAll();
  }

//This is a delete request which deletes the entry under specified id value
  @CrossOrigin(origins = "*")
  @DeleteMapping("/deletelogin/{id}")
  public void deletelogin(
      @PathVariable(value = "id") int id)
  {
      loginRepository.deleteById(id);
  }
  
}

