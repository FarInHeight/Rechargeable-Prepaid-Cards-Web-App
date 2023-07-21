package it.unipa.community.sferra.sferraproject.transactions;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.unipa.community.sferra.sferraproject.roles.Role;
import it.unipa.community.sferra.sferraproject.roles.RoleRepository;
import it.unipa.community.sferra.sferraproject.users.UserRepository;


@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/show-transactions")
    public String showPageTransactions(@AuthenticationPrincipal User user) {
        return "/transactions/show-transactions";
    }

    @GetMapping("/get-transactions")
    @ResponseBody
    public List<TransactionDTO> showTransactionsByUser(@AuthenticationPrincipal User user) {
        Set<Role> userRoles = userRepository.findByUsername( user.getUsername() ).getRoles();
        Role merchantRole = roleRepository.findByName("merchant");

        if( userRoles.contains(merchantRole) ) {
            return transactionService.findTransactionsByUser(user);
        }

        return transactionService.findAllTransactions();
    }
    
}
