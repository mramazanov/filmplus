package ru.jabka.filmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.jabka.filmplus.model.user.UserRequest;
import ru.jabka.filmplus.model.user.UserResponse;
import ru.jabka.filmplus.service.FilmService;
import ru.jabka.filmplus.service.UserService;

@RestController
@RequestMapping("api/v1/user")
@Tag(name = "Пользователи")
public class UserController {

    private final UserService userService;
    private final FilmService filmService;

    public UserController(UserService userService, FilmService filmService) {
        this.userService = userService;
        this.filmService = filmService;
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