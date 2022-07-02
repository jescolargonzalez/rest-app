package com.rest.prueba.restapp.controller;

import com.rest.prueba.restapp.controller.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private List<UserDto> userDtos;

    public UserController() {
        userDtos = new ArrayList<>();
        userDtos.add(new UserDto(1l, "Guillem Serra", 31));
        userDtos.add(new UserDto(2l, "Jesus Escolar", 29));
        userDtos.add(new UserDto(3l, "Devra jajajiua", 11));
    }

    // http://localhost:8080/user GET

    @GetMapping
    public List<UserDto> listAll() {
        return userDtos;
    }

    // http://localhost:8080/users/3

    @GetMapping(value = "/{id}")
    public UserDto findById(@PathVariable("id")  Long id) {
        return userDtos.stream().filter(user -> user.getId() == id).findAny().get();
    }

    @PostMapping
    public void add(@RequestBody UserDto userDto) {
        userDtos.add(userDto);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody UserDto userDto ) {
        UserDto userFromList = userDtos.stream().filter(user -> user.getId() == id).findAny().get();

        userFromList.setEdad(userDto.getEdad());
        userFromList.setNombre(userDto.getNombre());
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        userDtos = userDtos.stream().filter(user -> user.getId() != id)
                .collect(Collectors.toList());
    }

}