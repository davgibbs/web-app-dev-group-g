//RestController for Library interface - includes endpoints for books, members, library records and login tables D.Mullen Group G 20/03/2023

package ie.dcu.library.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ie.dcu.library.model.Book;
import ie.dcu.library.model.LibraryRecord;
import ie.dcu.library.model.Member;
import ie.dcu.library.repository.LibraryRepository;
import ie.dcu.library.repository.MembersRepository;
import ie.dcu.library.repository.RecordsRepository;
import ie.dcu.library.service.BookService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path="/library") // URL format is http://localhost:8080/library/
public class BookController {
	
    private final BookService bookService;

	@Autowired
	public BookController(BookService bookService){
		this.bookService = bookService;
	}
	  
  //**********Controller for the books Queries***********
  
  @CrossOrigin(origins = "*")
  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/addbook") // POST to add to all fields within books database (Insert new Book)
  public Book add(@RequestBody Book book) {
	  return bookService.addBook(book);
  }

  //This is a delete request which deletes the entry under specified id value
  @CrossOrigin(origins = "*")
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/deletebook/{isbn}")
  public void deletebook(
      @PathVariable(value = "isbn") int id)
  {
      bookService.deleteBook(id);
  }

  //This is a put request which modifies the book entry
  @CrossOrigin(origins = "*")
  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/modifybook/{isbn}")
  public ResponseEntity<Book> modifybook(@RequestBody @Valid Book book, 
		  @PathVariable(value="isbn") int isbn)
  {	
	  var bookid = bookService.getBookByIsbn(isbn).getId();
	  book.setId(bookid);

	  return new ResponseEntity<Book>(bookService.modifyBook(book),HttpStatus.OK);
  }

  //Returns book by entered book id (isbn number)
  @CrossOrigin(origins = "*")
  @GetMapping("/getbook/{id}") 
  public ResponseEntity<Book> getBookById(@PathVariable(value="id") int id){
      return new ResponseEntity<Book>(bookService.getBookById(id), HttpStatus.OK);
  }
  
  //Returns all books
  @GetMapping(path="/getallbooks")		
  public @ResponseBody Iterable<Book> getAllBooks() {
    return bookService.getAllBooks();
  }
  
  @GetMapping(path="/getbooksByAuthor")
  public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam String author){
	  return new ResponseEntity<List<Book>>(bookService.getBooksByAuthor(author), HttpStatus.OK);
  }
  
  @GetMapping(path="/getbooksByTitle")
  public ResponseEntity<List<Book>> getBooksByTitle(@RequestParam String title){
	  return new ResponseEntity<List<Book>>(bookService.getBooksByTitle(title), HttpStatus.OK);
  }
      
}