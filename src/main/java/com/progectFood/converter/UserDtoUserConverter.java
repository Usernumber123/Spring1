package com.progectFood.converter;

import com.progectFood.domian.dto.UserDto;
import com.progectFood.domian.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoUserConverter implements Converter<UserDto, User> {
    @Override
    public User convert(UserDto userDto) {
       User user = new User();
        //user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        return user;
    }

}
