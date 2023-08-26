package com.cydeo.converter;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter implements Converter<String, UserDTO> {

    UserService userService;

    public UserDtoConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDTO convert(String source) {

        //I did this for dropdown button. If the user choose select it return null and because of @Notnull validation it gives warning at the screen.
        if (source == null || source.equals("")) {
            return null;
        }

        return userService.findById(source);
    }
}
