package ie.dcu.library.service;
import ie.dcu.library.entity.RoleEntity;
import ie.dcu.library.util.LibraryServiceException;
import ie.dcu.library.entity.MemberEntity;
import ie.dcu.library.mapper.MemberMapper;
import ie.dcu.library.model.ErrorCode;
import ie.dcu.library.model.RoleName;
import ie.dcu.library.model.Members;
import ie.dcu.library.repository.MembersRepository;
import ie.dcu.library.util.LibraryServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ie.dcu.library.model.ErrorCode.USER_NOT_FOUND;

@Service
public class UserService implements UserDetailsService {

    private final MembersRepository userEntityRepository;
    private final MemberMapper mapper;

    private final RoleService roleService;

    public UserService(MembersRepository userEntityRepository, MemberMapper mapper, RoleService roleService) {
        this.userEntityRepository = userEntityRepository;
        this.mapper = mapper;
        this.roleService = roleService;
    }

    public Members findByEmail(String email) {
        var user = userEntityRepository.findMemberEntityByUsername(email).orElseThrow(() ->
                new LibraryServiceException("User not found", USER_NOT_FOUND));
        return mapper.entityToDto(user);
    }

    public List<Members> listUsersByRole(RoleName roleName) {
        List<MemberEntity> entitiesByRolesName = userEntityRepository.findMemberEntitiesByRoles_Name(roleName);
        return mapper.mapList(entitiesByRolesName);
    }

    @Transactional
    public Members signUpUser(String username, String email, String password) {
        userEntityRepository.findMemberEntityByUsername(username).ifPresent(u -> {
            throw new LibraryServiceException("Username taken", ErrorCode.USER_ALREADY_EXISTS);
        });
        userEntityRepository.findMemberEntityByEmail(email).ifPresent(u -> {
                throw new LibraryServiceException("Email taken", ErrorCode.USER_ALREADY_EXISTS);
        });

        MemberEntity user = new MemberEntity();
        user.setPassword(password);
        user.setUsername(username);
        user.setEmail(email);
        user.addRole(roleService.findRoleByName(RoleName.USER));
        MemberEntity created = userEntityRepository.save(user);
        return mapper.entityToDto(created);

    }

    public Members getUserById(long id) {
        var userEntitybyId = userEntityRepository.findById(id).orElseThrow(
                () -> new LibraryServiceException("User not found", USER_NOT_FOUND));
        return mapper.entityToDto(userEntitybyId);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userEntityRepository.findMemberEntityByUsernameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), getAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<RoleEntity> roles) {
        List<GrantedAuthority> authorities
                = new ArrayList<>();
        for (RoleEntity role: roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().name()));
        }

        return authorities;
    }

    public Members updateUser(Members user) {
        var userEntity = userEntityRepository.findById(user.getMemberid()).orElseThrow(
                () -> new LibraryServiceException("User not found", USER_NOT_FOUND));        		
        userEntity.setUsername(user.getUname());
        userEntity.setFirstname(user.getFirstname());
        userEntity.setSurname(user.getSurname());
        MemberEntity saved = userEntityRepository.save(userEntity);
        return mapper.entityToDto(saved);
    }
}
