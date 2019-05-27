package com.chernyak.fapi.validators;

import com.chernyak.fapi.models.User;
import com.chernyak.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserValid implements Validator {

    private final UserService userService;

    @Autowired
    public UserValid(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        User user = (User) o;
        Matcher matcher = Pattern.compile("^(.+)@(.+)$").matcher(user.getEmail());
        if (!matcher.matches()) {
            errors.rejectValue("email", "inv_email is invalid");
        }
        User usercheck = userService.getUserByUsername(user.getUsername());
        if (!(usercheck == null)) {
            errors.rejectValue("username", "inv_" + user.getUsername() + " already exists");
        }
        if (user.getPassword().length() < 6) {
            errors.rejectValue("password", "inv_password length should be > 6 ");
        }
        String userRole = user.getRole();
        if (!(userRole.equals("ADMIN")
                || userRole.equals("PROJECT_MANAGER")
                || userRole.equals("DEVELOPER")
                || userRole.equals("TESTER"))) {
            errors.rejectValue("role", "role is invalid");
        }
    }
}
