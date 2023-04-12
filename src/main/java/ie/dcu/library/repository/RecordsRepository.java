package ie.dcu.library.repository;

//Crud Repository interface for obtaining books from database - includes methods for finding by id, returning all books and delete by id

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.dcu.library.model.LibraryRecord;

@Repository
public interface RecordsRepository
    extends CrudRepository<LibraryRecord, Integer> {
  
	LibraryRecord findById(int id);
    List<LibraryRecord> findAll();
    void deleteById(int id);    
}	
	
	
	
	
	
	

