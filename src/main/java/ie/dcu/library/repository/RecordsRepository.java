package ie.dcu.library.repository;

//Crud Repository interface for obtaining books from database - includes methods for finding by id, returning all books and delete by id

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.dcu.library.model.Library_records;

@Repository
public interface RecordsRepository
    extends CrudRepository<Library_records,
                           Integer> {
  
	Library_records findById(int id);
    List<Library_records> findAll();
    void deleteById(int id);    
}	
	
	
	
	
	
	

