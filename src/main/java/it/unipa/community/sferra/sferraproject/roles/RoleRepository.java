package it.unipa.community.sferra.sferraproject.roles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    
    Role findByName(String name);

}
