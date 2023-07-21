package it.unipa.community.sferra.sferraproject.users;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.unipa.community.sferra.sferraproject.roles.Role;
import it.unipa.community.sferra.sferraproject.roles.RoleRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDTO mapToUserDTO(User user) {
        String username = user.getUsername();
        boolean blocked = user.isBlocked();

        return new UserDTO(username, blocked);
    }

    @Override
    public UserDTO mapToUserDTO(UserCreationDTO user) {
        String username = user.getUsername();
        boolean blocked = user.isBlocked();

        return new UserDTO(username, blocked);
    }

    @Override
    public void createUser(UserCreationDTO user) {
        User newUser = new User();
        newUser.setUsername( user.getUsername() );
        newUser.setEmail( user.getEmail() );
        newUser.setPassword( passwordEncoder.encode( user.getPassword() ) );
        newUser.setBlocked( user.isBlocked() );

        Role role = roleRepository.findByName( user.getRole() );
        newUser.setRoles( Collections.singleton( role ) );

        userRepository.save( newUser );
    }

    @Override
    public void blockOrUnblockMerchantByUsername(String username) {
        User user = userRepository.findByUsername(username);
        boolean currentState = user.isBlocked();
        user.setBlocked( !currentState );
        userRepository.save(user);
    }

}
