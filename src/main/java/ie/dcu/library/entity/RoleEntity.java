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
        private RoleName name;

		@ManyToMany(mappedBy = "roles")
        private Collection<MemberEntity> members;

        public RoleEntity() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public RoleName getName() {
			return name;
		}

		public void setName(RoleName name) {
			this.name = name;
		}
        public Collection<MemberEntity> getUsers() {
            return members;
        }

        public void setMembers(Collection<MemberEntity> members) {
            this.members = members;
        }
}
