package it.unipa.community.sferra.sferraproject.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.unipa.community.sferra.sferraproject.config.SessionUtils;
import it.unipa.community.sferra.sferraproject.roles.Role;
import it.unipa.community.sferra.sferraproject.roles.RoleRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SessionUtils sessionUtils;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/blocked")
    public String loginBlocked() {
        return "redirect:/users/login?blocked";
    }

    @GetMapping("/admin/create-merchant")
    public String createMerchant() {
        return "/users/admin/create-merchant";
    }

    @PostMapping("/admin/create-merchant")
    @ResponseBody
    public UserDTO createMerchant(@Valid UserCreationDTO user, HttpServletResponse response) {
        User newUser = userRepository.findByUsername(user.getUsername());

        if (newUser != null) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return null;
        }

        userService.createUser(user);
        return userService.mapToUserDTO(user);
    }

    @GetMapping("/admin/unblock-block-merchant")
    public String flipMerchantState() {
        return "/users/admin/unblock-block-merchant";
    }

    @PostMapping("/admin/unblock-block-merchant")
    @ResponseBody
    public UserDTO flipMerchantState(@RequestParam(value = "username") String username, HttpServletResponse response) {
        User user = userRepository.findByUsername(username);
        Role merchant = roleRepository.findByName("merchant");

        if (user == null || !user.getRoles().contains(merchant)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        userService.blockOrUnblockMerchantByUsername(username);
        sessionUtils.expireUserSessions(username);

        return userService.mapToUserDTO(user);
    }
}
