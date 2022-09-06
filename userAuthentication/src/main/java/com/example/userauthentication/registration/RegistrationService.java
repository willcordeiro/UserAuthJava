package com.example.userauthentication.registration;

import org.springframework.stereotype.Service;

import com.example.userauthentication.appuser.AppUser;
import com.example.userauthentication.appuser.AppUserRole;
import com.example.userauthentication.appuser.AppUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {

    private EmailValidator emailValidator;
    private AppUserService appUserService;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalAccessError("this email is not valid");
        }
        return  appUserService.singUpUser(
            new AppUser(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getPassword(),
                    AppUserRole.USER

            )
        );
    }

}
