//Crud Repository interface for obtaining books from database - includes methods for finding by id, returning all books and delete by id

package ie.dcu.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.dcu.library.model.Books;

@Repository

public interface LibraryRepository extends CrudRepository<Books,Integer> {
  
    Books findById(int id);
    List<Books> findAll();
    void deleteById(int id);
    List<Books> findByAuthor(String author); //Added DM 22/03/2023 search by author
    List<Books> findByTitle(String title);	//Added DM 08/04/2023 for search-bar on front-end function search by book title
     
}


