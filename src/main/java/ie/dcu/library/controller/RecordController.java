package ie.dcu.library.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ie.dcu.library.model.LibraryRecord;
import ie.dcu.library.repository.RecordsRepository;

//**********Mappings for the library_records Queries***********

@RestController
@RequestMapping(path="/library")
public class RecordController {

	  @Autowired
	  private RecordsRepository libRepository;

	  @CrossOrigin(origins = "*")
	  @PostMapping("/addrecord") // POST to add to all fields within books database (Insert new Book)
	  public void add(@RequestBody LibraryRecord record) {
		  libRepository.save(record);
	  }

	  //Returns record by entered record id (isbn number)
	  @CrossOrigin(origins = "*")
	  @GetMapping("/getrecord/{id}") 
	  public LibraryRecord getRecordById(
	      @PathVariable(value="id") int id)
	  {
	      return libRepository.findById(id);
	  }

	  //Returns all books
	  @GetMapping(path="/getallrecords")		
	  public @ResponseBody Iterable<LibraryRecord> getAllRecords() {
	    return libRepository.findAll();
	  }

	  @CrossOrigin(origins = "*")
	  @DeleteMapping("/deleterecord/{id}")
	  public void deleterecord(
	      @PathVariable(value = "id") int id)
	  {
	      libRepository.deleteById(id);
	  }

	  
}
