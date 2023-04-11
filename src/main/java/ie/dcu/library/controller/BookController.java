//RestController for Library interface - includes endpoints for books, members, library records and login tables D.Mullen Group G 20/03/2023

package ie.dcu.library.controller;
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

import ie.dcu.library.entity.Login;
import ie.dcu.library.model.Books;
import ie.dcu.library.model.Library_records;
import ie.dcu.library.model.Members;
import ie.dcu.library.repository.LibraryRepository;
import ie.dcu.library.repository.LoginRepository;
import ie.dcu.library.repository.MembersRepository;
import ie.dcu.library.repository.RecordsRepository;

@RestController
@RequestMapping(path="/library") // URL format is http://localhost:8080/library/
public class BookController {
	
  @Autowired 
  private LibraryRepository bookRepository;

  @Autowired
  private LoginRepository loginRepository;
  
//**********Mappings for the books Queries***********
  
  @CrossOrigin(origins = "*")
  @PostMapping("/addbook") // POST to add to all fields within books database (Insert new Book)
  public void add(@RequestBody Books book) {
	  bookRepository.save(book);
  }

//Returns book by entered book id (isbn number)
  @CrossOrigin(origins = "*")
  @GetMapping("/getbook/{id}") 
  public Books getBookById(
      @PathVariable(value="id") int id)
  {
      return bookRepository.findById(id);
  }
  
//Returns all books
  @GetMapping(path="/getallbooks")		
  public @ResponseBody Iterable<Books> getAllUsers() {
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
  public ResponseEntity<List<Books>> getBooksByAuthor(@RequestParam String author){
	  return new ResponseEntity<List<Books>>(bookRepository.findByAuthor(author), HttpStatus.OK);
  }
  
  @GetMapping(path="/gettitle")
  public ResponseEntity<List<Books>> getBooksByTitle(@RequestParam String title){
	  return new ResponseEntity<List<Books>>(bookRepository.findByTitle(title), HttpStatus.OK);
  }
     
  
//**********Mappings for the login Queries***********
  
  @CrossOrigin(origins = "*")
  @PostMapping("/addlogin") // POST to add to all fields within books database (Insert new Book)
  public void add(@RequestBody Login login) {
	  loginRepository.save(login);
  }

//Returns book by entered book id (isbn number)
  @CrossOrigin(origins = "*")
  @GetMapping("/getlogin/{id}") 
  public Login getLoginById(
      @PathVariable(value="id") int id)
  {
      return loginRepository.findById(id);
  }
  
//Returns all books
  @GetMapping(path="/getalllogins")		
  public @ResponseBody Iterable<Login> getAllLogins() {
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