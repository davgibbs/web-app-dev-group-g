package ie.dcu.library.repository;

//Crud Repository interface for obtaining members from database - includes methods for finding by id, returning all members and delete by id

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.dcu.library.model.members;

@Repository

public interface MembersRepository
  extends CrudRepository<members,
                         Integer> {

  members findById(int id);
  List<members> findAll();
  void deleteById(int id);
  
}
