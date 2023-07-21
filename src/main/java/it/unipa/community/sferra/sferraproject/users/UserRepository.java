package it.unipa.community.sferra.sferraproject.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);

    User findByUsername(String username);
    
    User findByEmail(String email);

}
