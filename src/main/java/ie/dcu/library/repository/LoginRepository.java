/*package ie.dcu.library.repository;

//Crud Repository interface for obtaining login details from database - includes methods for finding by id, returning all logins and delete by id

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.dcu.library.entity.Login;

@Repository

public interface LoginRepository
  extends CrudRepository<Login,
                         Integer> {

  Login findById(int id);
  List<Login> findAll();
  void deleteById(int id);
  
}*/