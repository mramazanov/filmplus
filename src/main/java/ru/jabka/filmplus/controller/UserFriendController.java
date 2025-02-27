package ru.jabka.filmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.jabka.filmplus.model.friend.Friend;
import ru.jabka.filmplus.service.FriendService;

@RestController
@RequestMapping("api/v1/friend")
@Tag(name = "Друзья")
@RequiredArgsConstructor
public class UserFriendController {

    private final FriendService userFriendService;

    @PostMapping
    @Operation(summary = "Добавление друга")
    public Friend addFriend(@RequestBody final Friend userfriend) {
        return userFriendService.addFriend(userfriend);
    }
}