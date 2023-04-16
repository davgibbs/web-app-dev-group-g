package ie.dcu.library.controller;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ie.dcu.library.model.Book;
import ie.dcu.library.model.LibraryRecord;
import ie.dcu.library.service.BookService;
import ie.dcu.library.service.RecordService;
import ie.dcu.library.service.UserService;
import ie.dcu.library.util.JwtTokenUtil;

//**********Mappings for the library_records Queries***********

@RestController
@RequestMapping(path="/library")
public class RecordController {

	  private final RecordService recordService;
	  private final BookService bookService;
	  private final UserService userService;
      private final JwtTokenUtil tokenUtil;


	  @Autowired
	  public RecordController(RecordService recordService, BookService bookService, JwtTokenUtil tokenUtil,UserService userService){
		  this.recordService = recordService;
		  this.bookService = bookService;
		  this.tokenUtil = tokenUtil;
		  this.userService = userService;
	  }
	  
	  @CrossOrigin(origins = "*")
	  @GetMapping("/borrow/{id}") // POST to add to all fields within libraryrecords database
	  public LibraryRecord add(
			  @RequestHeader (value="Authorization") String authorizationHeader,
			  @PathVariable(value = "id") int id){

	      String token = authorizationHeader.substring(7);
	      //System.out.println(token);
	      String email = tokenUtil.getUsernameFromToken(token);
	      //System.out.println(email);
	      
		  LibraryRecord record = new LibraryRecord();
		  var borrow_date = LocalDate.now();
	      var due_date = borrow_date.plusWeeks(2);
	      
	      Long memberid = userService.getId(email);
	      record.setMemberid(memberid.intValue());
	      record.setBookId(id);
	      record.setBorrowed_date(borrow_date);
	      record.setDue_date(due_date);
	      
		  recordService.add(record);
		  Book b = bookService.getBookById(id);
		  // b.setAvailable(false);
		  bookService.modifyBook(b);
		  return record;
	  }

	  //Returns record by entered record id (isbn number)
	  @CrossOrigin(origins = "*")
	  @GetMapping("/getrecord/{id}") 
	  public LibraryRecord getRecordById(
	      @PathVariable(value="id") int id)
	  {
	      return recordService.getRecordById(id);
	  }

	  //Returns all books
	  @GetMapping(path="/getallrecords")
	  @PreAuthorize("hasRole('ADMIN')")
	  public @ResponseBody Iterable<LibraryRecord> getAllRecords() {
	    return recordService.getAllRecords();
	  }

	  //Returns books borrowed by a user
	  @GetMapping(path="/getrecordsByuser")
	  public @ResponseBody Iterable<LibraryRecord> getRecordsbyUser(
			  @RequestHeader (value="Authorization") String authorizationHeader
			  ){
	      String token = authorizationHeader.substring(7);
	      String email = tokenUtil.getUsernameFromToken(token);
	      
	      Long memberid = userService.getId(email);		  
		  return recordService.getRecordsbyUser(memberid.intValue());
		  
	  }

	  @CrossOrigin(origins = "*")
	  @PreAuthorize("hasRole('ADMIN')")	  
	  @DeleteMapping("/return/{bookid}")
	  public void deleterecord(
	      @PathVariable(value = "bookid") int isbn)
	  {              
	  Book b = bookService.getBookByIsbn(isbn);
	  // b.setAvailable(true); todo fix
	  var book = bookService.modifyBook(b);	  
	  recordService.deleterecord(book.getISBN());
	  }
	  
}
