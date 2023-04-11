package ie.dcu.library.repository;
//Crud Repository interface for obtaining members from database - includes methods for finding by id, returning all members and delete by id
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.dcu.library.entity.MemberEntity;
import ie.dcu.library.model.RoleName;
import ie.dcu.library.model.Members;

@Repository
public interface MembersRepository
	extends JpaRepository<MemberEntity, Long>
	{

   //MemberEntity findById(Long id);
  //List<Members> findAll();
  //void deleteById(int id);
  
  List<MemberEntity> findMemberEntitiesByRoles_Name(RoleName name);
  Optional<MemberEntity> findMemberEntityByUsernameOrEmail(String username, String email);
  Optional<MemberEntity> findMemberEntityByUsername(String username);
  Optional<MemberEntity> findMemberEntityByEmail(String email);
}
