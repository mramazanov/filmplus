package ru.jabka.filmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.jabka.filmplus.model.user.UserRequest;
import ru.jabka.filmplus.model.user.UserResponse;
import ru.jabka.filmplus.service.UserService;

@RestController
@RequestMapping("api/v1/user")
@Tag(name = "Пользователи")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Создать пользователя")
    public UserResponse createUser(@RequestBody final UserRequest user) {
        return userService.userCreate(user);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение пользователя по id")
    public UserResponse getUser(@PathVariable final int id) {
        return userService.getUserById(id);
    }

    @PatchMapping
    @Operation(summary = "Обновление пользователя")
    public UserResponse updateUser(@RequestParam final long userId, @RequestBody final UserRequest user) {
        return userService.update(userId,user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление пользователя")
    public void deleteUser(@PathVariable final int id) throws Exception {
        userService.delete(id);
    }
}