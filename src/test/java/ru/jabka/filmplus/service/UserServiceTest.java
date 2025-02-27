package ru.jabka.filmplus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.user.UserRequest;
import ru.jabka.filmplus.model.user.UserResponse;
import ru.jabka.filmplus.repository.UserRepository;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void createUser_valid() {
        UserRequest userRequest = buildUserRequest("UserName", "userEmail", "userLogin",  LocalDate.of(2000, 1, 1));
        UserResponse userResponse = buildUserResponse(userRequest);
        Mockito.when(userRepository.insert(userRequest)).thenReturn(userResponse);
        UserResponse user = userService.userCreate(userRequest);
        Assertions.assertEquals(userResponse, user);
        Mockito.verify(userRepository).insert(userRequest);
    }

    @Test
    public void shouldReturnError_whenUserNameIsEmpty() {
        UserRequest userRequest = buildUserRequest("", "userEmail", "userLogin",  LocalDate.of(2000, 1, 1));
        final BadRequestException exception = Assertions.assertThrows(
                BadRequestException.class, () -> userService.userCreate(userRequest)
        );
        Assertions.assertEquals("Укажите имя пользователя", exception.getMessage());
    }

    @Test
    public void shouldReturnError_whenGetUserById() {
        Mockito.when(userRepository.getById(100L)).thenThrow(new BadRequestException("Не удалось найти пользователя с id = 100"));
        final BadRequestException error = Assertions.assertThrows(BadRequestException.class,() -> userService.getUserById(100L));
        Assertions.assertEquals("Не удалось найти пользователя с id = 100", error.getMessage());
    }

    @Test
    public void updateUser_valid() {
        UserRequest userRequest = buildUserRequest("UserName", "userEmail", "userLogin",  LocalDate.of(2000, 1, 1));
        UserResponse userResponse = buildUserResponse(userRequest);
        Mockito.when(userRepository.getById(1L)).thenReturn(userResponse);
        Mockito.when(userRepository.update(1L, userRequest)).thenReturn(userResponse);
        UserResponse user = userService.update(1L, userRequest);
        Assertions.assertEquals(userResponse, user);
        Mockito.verify(userRepository).update(1L, userRequest);
    }

    private UserResponse buildUserResponse(UserRequest userRequest) {
        return UserResponse.builder()
                .id(1L)
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .login(userRequest.getLogin())
                .birthDay(userRequest.getBirthDay())
                .build();
    }

    private UserRequest buildUserRequest(String name, String email, String login, LocalDate birthday) {
        return UserRequest.builder()
                .name(name)
                .email(email)
                .login(login)
                .birthDay(birthday)
                .build();
    }
}