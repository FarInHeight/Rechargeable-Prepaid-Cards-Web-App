package it.unipa.community.sferra.sferraproject.users;

public interface UserService {

    void createUser(UserCreationDTO user);

    UserDTO mapToUserDTO(User user);

    UserDTO mapToUserDTO(UserCreationDTO user);

    void blockOrUnblockMerchantByUsername(String username);

}
