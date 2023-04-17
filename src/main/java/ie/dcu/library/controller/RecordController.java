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
	  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	  @GetMapping("/borrow/{bookid}") // POST to add to all fields within libraryrecords database
	  public LibraryRecord add(@RequestHeader (value="Authorization") String authorizationHeader, @PathVariable(value = "bookid") int bookid){

		  LibraryRecord record = new LibraryRecord();

		  if(recordService.getBookAvailability(id)==false) {
			  System.out.println("Already taken");
			  return record;
		  }
		  
	      String token = authorizationHeader.substring(7);
	      //System.out.println(token);
	      String email = tokenUtil.getUsernameFromToken(token);
	      //System.out.println(email);
	      
		  var borrow_date = LocalDate.now();
	      var due_date = borrow_date.plusWeeks(2);
	      
	      Long memberid = userService.getId(email);
	      record.setMemberid(memberid.intValue());
	      record.setBookId(bookid);
	      record.setBorrowed_date(borrow_date);
	      record.setDue_date(due_date);
	      record.setIsReturned(false); // Set false as just borrowing book
	      
		  recordService.add(record);

		  return record;
	  }

	  //Returns record by entered record id
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
	  
	  //Returns books borrowed by a user
	  @GetMapping(path="/getrecordsBybook/{bookid}")
	  public @ResponseBody Iterable<LibraryRecord> getRecordsbyBook(
			  @RequestHeader (value="Authorization") String authorizationHeader,
			  @PathVariable(value = "bookid") int bookid){
		  
		  return recordService.getRecordsbyBook(bookid);		  
	  }
	  
	  @CrossOrigin(origins = "*")
	  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	  @GetMapping("/return/{recordid}") // GET to update libraryrecords database
	  public LibraryRecord update(@RequestHeader (value="Authorization") String authorizationHeader, @PathVariable(value = "recordid") int recordid){
		  LibraryRecord record = recordService.getRecordById(recordid);
	      record.setIsReturned(true); // Set true as returning book
	      recordService.save(record);
		  return record;
	  }	  
	  
	  @CrossOrigin(origins = "*")
	  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	  @GetMapping("/renew/{recordid}") // GET to update libraryrecords database
	  public LibraryRecord updateByDueDate(@RequestHeader (value="Authorization") String authorizationHeader, @PathVariable(value = "recordid") int recordid){
		  LibraryRecord record = recordService.getRecordById(recordid);
		  LocalDate now = LocalDate.now();
		  LocalDate due_date = now.plusWeeks(2);
	      record.setDue_date(due_date); // Set due date to renew for two weeks
	      recordService.save(record);
		  return record;
	  }	  
}
