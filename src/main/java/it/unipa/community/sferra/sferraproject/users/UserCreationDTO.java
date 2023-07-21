package it.unipa.community.sferra.sferraproject.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserCreationDTO {
    
    @Size(min = 6, max = 45, message = "Your username must contain between 6 and 45 characters.")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-_]{5,44}$", message = "Your username can only contain letters, digits, '-' and '_'. It can only start with a letter.")
    @NotEmpty(message = "A username is required to continue.")
    private String username;

    @Size(max = 320, message = "Your email is too long. Try with a smaller one.")
    @NotEmpty(message = "An email is required to continue.")
    @Email
    private String email;

    @Size(min = 8, max = 20, message = "Your password must contain between 8 and 20 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9-_+*/$()]{8,20}$", message = "Your password can only contain letters, digits and the following characters: '-', '_', '+', '*', '/', '$', '(', ')'.")
    @NotEmpty(message = "A password is required to continue.")
    private String password;
    
    @Pattern(regexp = "^merchant$", message = "You can only enter 'merchant' as a role.")
    private String role;

    private boolean blocked;

}
