package com.G_Database.G_Database;
	//Crud Repository interface for obtaining books from database - includes methods for finding by id, returning all books and delete by id

	import java.util.List;
	import org.springframework.data.repository.CrudRepository;
	import org.springframework.stereotype.Repository;

	@Repository

	public interface RecordsRepository
	    extends CrudRepository<library_records,
	                           Integer> {
	  
		library_records findById(int id);
	    List<library_records> findAll();
	    void deleteById(int id);
	    
	}	
	
	
	
	
	
	

