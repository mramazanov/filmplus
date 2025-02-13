package ru.jabka.filmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.jabka.filmplus.model.user.UserResponse;
import ru.jabka.filmplus.model.userfriend.Userfriend;
import ru.jabka.filmplus.service.UserFriendService;

@RestController
@RequestMapping("api/v1/friend")
@Tag(name = "Друзья")
public class UserFriendController {

    private final UserFriendService userFriendService;

    public UserFriendController(final UserFriendService userFriendService) {
        this.userFriendService = userFriendService;
    }

    @PostMapping
    @Operation(summary = "Добавление друга")
    public UserResponse addFriend(@RequestBody final Userfriend userfriend) {
        return userFriendService.addFriend(userfriend);
    }
}