package ie.dcu.library.entity;

import ie.dcu.library.model.RoleName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Collection;

@Entity(name = "roles")
public class RoleEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        private RoleName rolename;

		@ManyToMany(mappedBy = "roles")
        private Collection<MemberEntity> users;

        public RoleEntity() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public RoleName getRolename() {
			return rolename;
		}

		public void setRolename(RoleName rolename) {
			this.rolename = rolename;
		}
        public Collection<MemberEntity> getUsers() {
            return users;
        }

        public void setUsers(Collection<MemberEntity> users) {
            this.users = users;
        }
}
