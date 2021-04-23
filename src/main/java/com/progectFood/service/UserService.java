package com.progectFood.service;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.UserDto;


import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<UserDto> getByLogin(String login);

    List<UserDto> getCouriers() throws ResourceNotFoundException;

    List<UserDto> getCustomers() throws ResourceNotFoundException;

    List<UserDto> getActiveCouriers() throws ResourceNotFoundException;

    List<UserDto> getInactiveCouriers() throws ResourceNotFoundException;

    List<UserDto> getCouriersByLastname(String lastName) throws ResourceNotFoundException;

    List<UserDto> getCustomersByLastname(String lastName) throws ResourceNotFoundException;

    UserDto getUserById(Integer id) throws ResourceNotFoundException;

    Map<String, Boolean> changeStatus(Integer courierId)
            throws ResourceNotFoundException;

    Map<String, Boolean> deleteCourier(Integer id)
            throws ResourceNotFoundException;

    Integer getCurrentHour();
}
