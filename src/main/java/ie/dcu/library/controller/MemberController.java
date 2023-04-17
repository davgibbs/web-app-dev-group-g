package ie.dcu.library.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ie.dcu.library.entity.MemberEntity;
import ie.dcu.library.model.Member;
import ie.dcu.library.repository.MembersRepository;
import ie.dcu.library.service.BookService;
import ie.dcu.library.service.RecordService;
import ie.dcu.library.service.UserService;
import ie.dcu.library.util.JwtTokenUtil;

@RestController
@RequestMapping(path="/library")
public class MemberController {

	  private final MembersRepository membRepository;
      private final JwtTokenUtil tokenUtil;
      private final UserService userService;

	  	@Autowired
	  	public MemberController(MembersRepository membRepository, JwtTokenUtil tokenUtil, UserService userServce){
	  		this.tokenUtil = tokenUtil;
			this.membRepository = membRepository;
			this.userService = userServce;
	  	}

	  @CrossOrigin(origins = "*")
	  @PostMapping("/addmember") // POST to add to all fields within members table (Insert new Member)
	  public void add(@RequestBody MemberEntity member) {
		  membRepository.save(member);
	  }
	  
	  @CrossOrigin(origins = "*")
	  @GetMapping("/getMemberId") // POST to add to all fields within members table (Insert new Member)
	  public int getId(@RequestParam String uname) {
		  return getId(uname);
	  }
	  
	  //Returns member details of currently logged in
	  @CrossOrigin(origins = "*")
	  @GetMapping("/getmember") 
	  public Member getMember(@RequestHeader (value="Authorization") String authorizationHeader){
		  System.out.println("Woo");
	    String token = authorizationHeader.substring(7);
	    String email = tokenUtil.getUsernameFromToken(token);  
	    Long memberid = userService.getId(email);
	    
	    return userService.getUserById(memberid);	  
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