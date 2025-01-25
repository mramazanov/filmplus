package ru.jabka.filmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.jabka.filmplus.model.User;
import ru.jabka.filmplus.service.UserService;

@RestController
@RequestMapping("api/v1/user")
@Tag(name = "Пользователи")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    @Operation(summary = "Создать пользователя")
    public User createUser(@RequestBody final User user) {
        return userService.userCreate(user);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение пользователя по id")
    public User getUser(@RequestParam final int id) {
        return userService.getUserById(id);
    }

    @PatchMapping
    @Operation(summary = "Обновление пользователя")
    public User updateUser(@RequestBody final User user) {
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление пользователя")
    public void deleteUser(@RequestParam final int id) throws Exception {
        throw new Exception("Привет");
       // userService.delete(id);
    }
}