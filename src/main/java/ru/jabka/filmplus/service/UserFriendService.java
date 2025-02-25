package ru.jabka.filmplus.service;

import org.springframework.stereotype.Service;

import ru.jabka.filmplus.model.user.UserResponse;
import ru.jabka.filmplus.model.userfriend.Userfriend;

@Service
public class UserFriendService {
    private UserService userService;
    public UserFriendService(UserService userService) {
        this.userService = userService;
    }
    public UserResponse addFriend(final Userfriend userfriend) {
        return userService.addFriend(userfriend);
    }
}