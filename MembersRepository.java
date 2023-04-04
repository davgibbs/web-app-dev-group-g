package com.G_Database.G_Database;

//Crud Repository interface for obtaining members from database - includes methods for finding by id, returning all members and delete by id

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MembersRepository
  extends CrudRepository<members,
                         Integer> {

  members findById(int id);
  List<members> findAll();
  void deleteById(int id);
  
}
