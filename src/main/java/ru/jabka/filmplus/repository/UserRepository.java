package ru.jabka.filmplus.repository;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.user.UserRequest;
import ru.jabka.filmplus.model.user.UserResponse;
import ru.jabka.filmplus.repository.mapper.UserMapper;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private static final String INSERT = """
            INSERT INTO filmplus.user (name, email, login, birthday)
            VALUES (:name, :email, :login, :birthday)
            RETURNING *;
            """;

    private static final String UPDATE = """
            UPDATE filmplus.user SET name = :name, email = :email, login = :login, BIRTHDAY = :birthday
            WHERE id = :id
            RETURNING *;
            """;

    private static final String GET_BY_ID = """
            SELECT *
            FROM filmplus.user
            WHERE id = :id
            """;

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;

    public UserResponse insert(final UserRequest userRequest) {
        return jdbcTemplate.queryForObject(INSERT, userToSql(null, userRequest), userMapper);
    }

    public UserResponse update(final Long userId, final UserRequest userRequest) {
        return jdbcTemplate.queryForObject(UPDATE, userToSql(userId, userRequest), userMapper);
    }

    public UserResponse getById(final Long userId) {
        try {
            return jdbcTemplate.queryForObject(GET_BY_ID, userToSql(userId, null), userMapper);
        } catch (Exception e) {
            throw new BadRequestException(String.format("Не удалось найти пользователя с id = %d", userId));
        }
    }

    private MapSqlParameterSource userToSql(final Long userId, final UserRequest userRequest) {
        final MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("id", userId);
        if (userRequest != null) {
            params.addValue("name", userRequest.getName());
            params.addValue("email", userRequest.getEmail());
            params.addValue("login", userRequest.getLogin());
            params.addValue("birthday", userRequest.getBirthDay());
        }
        return params;
    }
}