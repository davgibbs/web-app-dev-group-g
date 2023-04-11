package ie.dcu.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ie.dcu.library.entity.MemberEntity;
import ie.dcu.library.model.Members;
import ie.dcu.library.repository.MembersRepository;

public class MemberController {

	  @Autowired
	  private MembersRepository membRepository;

	  @CrossOrigin(origins = "*")
	  @PostMapping("/addmember") // POST to add to all fields within members table (Insert new Member)
	  public void add(@RequestBody MemberEntity member) {
		  membRepository.save(member);
	  }
	/*
	  //Returns record by entered id (memberid number)
	  @CrossOrigin(origins = "*")
	  @GetMapping("/getmember/{id}") 
	  public Members getMemberById(
	      @PathVariable(value="id") int id)
	  {
	    return membRepository.findById(id);
	  } 
	
	  //Returns all members
	  @GetMapping(path="/getallmembers")		
	  public @ResponseBody Iterable<Members> getAllMembers() {
	    return membRepository.findAll();
	  }
	  
	  //This is a delete request which deletes the entry under specified id value
	  @CrossOrigin(origins = "*")
	  @DeleteMapping("/deletemember/{id}")
	  public void deletemember(
	      @PathVariable(value = "id") int id)
	  {
	      membRepository.deleteById(id);
	  }*/

}