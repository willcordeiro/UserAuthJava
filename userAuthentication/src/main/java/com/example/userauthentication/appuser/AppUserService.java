package com.example.userauthentication.appuser;


import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        {
            return appUserRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException(String.format((USER_NOT_FOUND_MSG), email)));
        }

    }

    public String singUpUser(AppUser appUser){
        boolean userExists =  appUserRepository
        .findByEmail(appUser.getUsername())
        .isPresent();
        
        if(userExists){
            throw new IllegalAccessError("This Email already Exists");
        }

       String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

       appUser.setPassword(encodedPassword);

       appUserRepository.save(appUser);

        return "password encoded";
    }

}
