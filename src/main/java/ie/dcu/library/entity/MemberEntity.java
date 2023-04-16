package ie.dcu.library.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "members")
public class MemberEntity {

	    public MemberEntity() {
	    }

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

		private String email;
	    private String username;
	    private String password;
	    private String firstname;
	    private String surname;

	    @ManyToMany(cascade = {
	            CascadeType.PERSIST,
	            CascadeType.MERGE
	    })
	    @JoinTable(
	            name = "member_role",
	            joinColumns = @JoinColumn(
	                    name = "member_id", referencedColumnName = "id"),
	            inverseJoinColumns = @JoinColumn(
	                    name = "role_id", referencedColumnName = "id"))
	    private Collection<RoleEntity> roles = new ArrayList<>();

	    public Long getId() {
			return id;
		}

	    public String getEmail() {
	        return this.email;
	    }

	    public String getUsername() {
	        return this.username;
	    }

	    public String getPassword() {
	        return this.password;
	    }

	    public String getFirstname() {
	        return this.firstname;
	    }

	    public String getSurname() {
	        return this.surname;
	    }

	    public Collection<RoleEntity> getRoles() {
	        return this.roles;
	    }

		public void setId(Long id) {
			this.id = id;
		}

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public void setFirstname(String firstname) {
	        this.firstname = firstname;
	    }

	    public void setSurname(String surname) {
	        this.surname = surname;
	    }

	    public void setRoles(Collection<RoleEntity> roles) {
	        this.roles = roles;
	    }

	    public void addRole(RoleEntity role) {
	        this.roles.add(role);
	    }

	    public boolean equals(final Object o) {
	        if (o == this) return true;
	        if (!(o instanceof MemberEntity)) return false;
	        final MemberEntity other = (MemberEntity) o;
	        if (!other.canEqual((Object) this)) return false;
	        final Object this$id = this.getId();
	        final Object other$id = other.getId();
	        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
	        final Object this$email = this.getEmail();
	        final Object other$email = other.getEmail();
	        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
	        final Object this$username = this.getUsername();
	        final Object other$username = other.getUsername();
	        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
	        final Object this$password = this.getPassword();
	        final Object other$password = other.getPassword();
	        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
	        final Object this$firstname = this.getFirstname();
	        final Object other$firstname = other.getFirstname();
	        if (this$firstname == null ? other$firstname != null : !this$firstname.equals(other$firstname)) return false;
	        final Object this$surname = this.getSurname();
	        final Object other$surname = other.getSurname();
	        if (this$surname == null ? other$surname != null : !this$surname.equals(other$surname)) return false;
	        final Object this$roles = this.getRoles();
	        final Object other$roles = other.getRoles();
	        if (this$roles == null ? other$roles != null : !this$roles.equals(other$roles)) return false;
	        return true;
	    }

	    protected boolean canEqual(final Object other) {
	        return other instanceof MemberEntity;
	    }

	    public int hashCode() {
	        final int PRIME = 59;
	        int result = 1;
	        final Object $id = this.getId();
	        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
	        final Object $email = this.getEmail();
	        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
	        final Object $username = this.getUsername();
	        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
	        final Object $password = this.getPassword();
	        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
	        final Object $firstname = this.getFirstname();
	        result = result * PRIME + ($firstname == null ? 43 : $firstname.hashCode());
	        final Object $surname = this.getSurname();
	        result = result * PRIME + ($surname == null ? 43 : $surname.hashCode());
	        final Object $roles = this.getRoles();
	        result = result * PRIME + ($roles == null ? 43 : $roles.hashCode());
	        return result;
	    }

	    public String toString() {
	        return "User(id=" + this.getId() + ", email=" + this.getEmail() + ", username=" + this.getUsername() + ", password=" + this.getPassword() + ", firstname=" + this.getFirstname() + ", surname=" + this.getSurname() + ", roles=" + this.getRoles() + ")";
	    }
	}