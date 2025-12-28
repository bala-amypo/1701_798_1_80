// package com.example.demo.service;

// import com.example.demo.entity.UserAccount;

// public interface UserAccountService {

//     // Register user and return the UserAccount object
//     UserAccount registerUser(UserAccount user);

//     // Authenticate user and return the UserAccount object
//     UserAccount authenticate(String email, String password);
// }

package com.example.demo.service;

import com.example.demo.entity.UserAccount;
import java.util.List;

public interface UserAccountService {
    UserAccount register(UserAccount user);
    UserAccount authenticate(String email, String password);
    UserAccount getUser(Long id);
    
}
