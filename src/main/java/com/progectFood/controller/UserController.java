package com.progectFood.controller;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.UserDto;
import com.progectFood.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @SneakyThrows
    @GetMapping("/couriers")
    public List<UserDto> getCouriers() {
        return userService.getCouriers();
    }

    @SneakyThrows
    @GetMapping("/customers")
    public List<UserDto> getCustomers() {
        return userService.getCustomers();
    }

    @SneakyThrows
    @GetMapping("/couriers/status/active")
    public List<UserDto> getActiveCouriers() {
        return userService.getActiveCouriers();
    }

    @SneakyThrows
    @GetMapping("/couriers/status/inactive")
    public List<UserDto> getInactiveCouriers() {
        return userService.getInactiveCouriers();
    }

    @SneakyThrows
    @GetMapping("/couriers/{lastname}")
    public List<UserDto> getCouriersByLastname(@PathVariable(value = "lastname") String lastName) {
        return userService.getCouriersByLastname(lastName);
    }

    @SneakyThrows
    @GetMapping("/customers/{lastname}")
    public List<UserDto> getCustomersByLastname(@PathVariable(value = "lastname") String lastName) {
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

    @Transactional
    @PutMapping("/users/phone/{phone}/{id}")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public Map<String, Boolean> changePhone(@PathVariable(value = "phone") String phone,
                                            @PathVariable(value = "id") Integer id) {
        return userService.changePhone(phone, id);
    }

    @SneakyThrows
    @PostMapping("/customers/create")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public UserDto createCustomer(@RequestBody UserDto userDto) {
        return userService.createCustomer(userDto);
    }

    @SneakyThrows
    @PostMapping("/couriers/create")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public UserDto createCourier(@RequestBody UserDto userDto) {
        return userService.createCourier(userDto);
    }

    @Transactional
    @PutMapping("/users/update")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @GetMapping("/getUser")
    public String getAuthUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails.getUsername();
    }

    @Transactional
    @PutMapping("/users/password/{password}/{id}")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public Map<String, Boolean> changePassword(@PathVariable(value = "password") String password,
                                               @PathVariable(value = "id") Integer id) {
        return userService.changePassword(password, id);
    }
}
