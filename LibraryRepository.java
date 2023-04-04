//Crud Repository interface for obtaining books from database - includes methods for finding by id, returning all books and delete by id

package com.G_Database.G_Database;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LibraryRepository extends CrudRepository<books,Integer> {
  
    books findById(int id);
    List<books> findAll();
    void deleteById(int id);
    List<books> findByAuthor(String author);
      
}


