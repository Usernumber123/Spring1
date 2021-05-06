package com.progectFood.service.impl;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.RestaurantDto;
import com.progectFood.domian.dto.UserDto;
import com.progectFood.domian.entity.Restaurant;
import com.progectFood.domian.entity.Role;
import com.progectFood.domian.entity.Status;
import com.progectFood.domian.entity.User;
import com.progectFood.repository.RoleRepository;
import com.progectFood.repository.StatusRepository;
import com.progectFood.repository.UserRepository;
import com.progectFood.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    StatusRepository statusRepository;
    private final ConversionService conversionService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserDto> getByLogin(String login) {
        Optional<User> optionalUser = userRepository.findOneByLogin(login);
        User user = optionalUser.isPresent() ? optionalUser.get() : new User();
        UserDto userDto = conversionService.convert(user, UserDto.class);
        Optional<UserDto> userDtoOpt = Optional.of(userDto);
        return userDtoOpt;
    }

    @Override
    public List<UserDto> getCouriers() throws ResourceNotFoundException {
        Role role = roleRepository.findById(2)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found for this id = " + 2));
        Status status1 = statusRepository.findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 1));
        Status status2 = statusRepository.findById(2)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 2));


        ArrayList<UserDto> users = new ArrayList<>();
        for (User user : userRepository.getActiveUserById(role, status1, status2)) {
            UserDto userDto = conversionService.convert(user, UserDto.class);
            users.add(userDto);
        }
        return users;
    }

    @Override
    public List<UserDto> getCustomers() throws ResourceNotFoundException {
        Role role = roleRepository.findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found for this id = " + 1));
        Status status1 = statusRepository.findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 1));
        ArrayList<UserDto> users = new ArrayList<>();
        for (User user : userRepository.getActiveUserById(role, status1, status1)) {
            UserDto userDto = conversionService.convert(user, UserDto.class);
            users.add(userDto);
        }
        return users;
    }

    @Override
    public List<UserDto> getActiveCouriers() throws ResourceNotFoundException {
        Role role = roleRepository.findById(2)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found for this id = " + 2));
        Status status1 = statusRepository.findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 1));
        ArrayList<UserDto> users = new ArrayList<>();
        for (User user : userRepository.getCourierByStatus(role, status1)) {
            UserDto userDto = conversionService.convert(user, UserDto.class);
            users.add(userDto);
        }
        return users;
    }

    @Override
    public List<UserDto> getInactiveCouriers() throws ResourceNotFoundException {
        Role role = roleRepository.findById(2)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found for this id = " + 2));
        Status status1 = statusRepository.findById(2)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 2));
        ArrayList<UserDto> users = new ArrayList<>();
        for (User user : userRepository.getCourierByStatus(role, status1)) {
            UserDto userDto = conversionService.convert(user, UserDto.class);
            users.add(userDto);
        }
        return users;
    }

    @Override
    public List<UserDto> getCouriersByLastname(String lastName) throws ResourceNotFoundException {
        Role role = roleRepository.findById(2)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found for this id = " + 2));
        Status status = statusRepository.findById(3)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 3));
        ArrayList<UserDto> users = new ArrayList<>();
        for (User user : userRepository.findByLastName(lastName, role, status)) {
            UserDto userDto = conversionService.convert(user, UserDto.class);
            users.add(userDto);
        }
        return users;
    }

    @Override
    public List<UserDto> getCustomersByLastname(String lastName) throws ResourceNotFoundException {
        Role role = roleRepository.findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found for this id = " + 1));
        Status status = statusRepository.findById(3)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 3));
        ArrayList<UserDto> users = new ArrayList<>();
        for (User user : userRepository.findByLastName(lastName, role, status)) {
            UserDto userDto = conversionService.convert(user, UserDto.class);
            users.add(userDto);
        }
        return users;
    }

    @Override
    public UserDto getUserById(Integer id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id = " + id));
        return conversionService.convert(user, UserDto.class);
    }

    @Transactional
    @Override
    public Map<String, Boolean> changeStatus(Integer courierId) throws ResourceNotFoundException {
        User courier = userRepository.findById(courierId)
                .orElseThrow(() -> new ResourceNotFoundException("Courier not found for this id = " + courierId));

        Status status1 = statusRepository.findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 1));

        Status status2 = statusRepository.findById(2)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 2));

        if (courier.getStatus().getId() == 1) {
            userRepository.changeStatus(status2, courierId);
        } else userRepository.changeStatus(status1, courierId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Status was changed", Boolean.TRUE);
        return response;
    }

    @Transactional
    @Override
    public Map<String, Boolean> deleteCourier(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        Status status = statusRepository.findById(3)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 3));
        Map<String, Boolean> response = new HashMap<>();

        userRepository.changeStatus(status, id);
        response.put("status deleted", Boolean.TRUE);
        return response;
    }

    @Override
    public Map<String, Boolean> changePhone(String phone, Integer id) {

        userRepository.changePhone(phone, id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Phone was changed", Boolean.TRUE);
        return response;
    }

    @Override
    public Integer getCurrentHour() {
        return userRepository.currentHour().get(0);
    }

    @Override
    public void updateUser(UserDto userDto) {
        String hashPassword = passwordEncoder.encode(userDto.getPassword());
        User user = conversionService.convert(userDto, User.class);
        userRepository.updateUser(user.getFirstName(), user.getLastName(), user.getPhone(), user.getId(), user.getLogin(), hashPassword);
    }

    @Override
    public void createCustomer(UserDto userDto) throws ResourceNotFoundException {
        String hashPassword = passwordEncoder.encode(userDto.getPassword());
        Role role = roleRepository.findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found for this id = " + 1));
        Status status = statusRepository.findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 1));
        User user = conversionService.convert(userDto, User.class);
        user.setPassword(hashPassword);
        user.setRole(role);
        user.setStatus(status);
        userRepository.save(user);
    }

    @Override
    public void createCourier(UserDto userDto) throws ResourceNotFoundException {
        String hashPassword = passwordEncoder.encode(userDto.getPassword());
        Role role = roleRepository.findById(2)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found for this id = " + 2));
        Status status = statusRepository.findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 1));

        User user = conversionService.convert(userDto, User.class);
        user.setPassword(hashPassword);
        user.setRole(role);
        user.setStatus(status);
        userRepository.save(user);
    }

    @Override
    public Map<String, Boolean> changePassword(String password, Integer id) {
        String hashPassword = passwordEncoder.encode(password);
        userRepository.changePassword(hashPassword, id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Password was changed", Boolean.TRUE);
        return response;
    }

}
