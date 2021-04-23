package com.progectFood.controller;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.UserDto;
import com.progectFood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{login}")
    public Optional<UserDto> getByLogin(@PathVariable(value = "login") String login) {
        return userService.getByLogin(login);
    }

    @GetMapping("/couriers")
    public List<UserDto> getCouriers() throws ResourceNotFoundException {
        return userService.getCouriers();
    }

    @GetMapping("/customers")
    public List<UserDto> getCustomers() throws ResourceNotFoundException {
        return userService.getCustomers();
    }

    @GetMapping("/couriers/status/active")
    public List<UserDto> getActiveCouriers() throws ResourceNotFoundException {
        return userService.getActiveCouriers();
    }

    @GetMapping("/couriers/status/inactive")
    public List<UserDto> getInactiveCouriers() throws ResourceNotFoundException {
        return userService.getInactiveCouriers();
    }

    @GetMapping("/couriers/{lastname}")
    public List<UserDto> getCouriersByLastname(@PathVariable(value = "lastname") String lastName) throws ResourceNotFoundException {
        return userService.getCouriersByLastname(lastName);
    }

    @GetMapping("/customers/{lastname}")
    public List<UserDto> getCustomersByLastname(@PathVariable(value = "lastname") String lastName) throws ResourceNotFoundException {
        return userService.getCustomersByLastname(lastName);
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return userService.getUserById(id);
    }

    @GetMapping("/time/hour")
    public Integer getCurrentHour() {
        return userService.getCurrentHour();
    }

    @Transactional
    @PutMapping("/couriers/status/{id}")
    public Map<String, Boolean> changeStatus(@PathVariable(value = "id") Integer courierId)
            throws ResourceNotFoundException {
        return userService.changeStatus(courierId);
    }

    @Transactional
    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteCourier(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {

        return userService.deleteCourier(id);
    }
}
