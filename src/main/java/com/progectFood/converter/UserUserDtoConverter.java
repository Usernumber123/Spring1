package com.progectFood.converter;



import com.progectFood.domian.dto.UserDto;
import com.progectFood.domian.entity.User;
import org.springframework.core.convert.converter.Converter;

public class UserUserDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setPhone(user.getPhone());
        return userDto;
    }

}