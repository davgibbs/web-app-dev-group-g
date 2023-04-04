package com.G_Database.G_Database;

//Crud Repository interface for obtaining login details from database - includes methods for finding by id, returning all logins and delete by id

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LoginRepository
  extends CrudRepository<login,
                         Integer> {

  login findById(int id);
  List<login> findAll();
  void deleteById(int id);
  
}