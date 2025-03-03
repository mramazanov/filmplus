package ru.jabka.filmplus.repository;

import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ru.jabka.filmplus.exception.BadRequestException;
import ru.jabka.filmplus.model.friend.Friend;
import ru.jabka.filmplus.repository.mapper.FriendMapper;

@Repository
@RequiredArgsConstructor
public class FriendRepository {

    private static final String INSERT = """
            INSERT INTO filmplus.friend(user_id, friend_id)
            VALUES (:user_id, :friend_id)
            RETURNING *;
            """;

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final FriendMapper friendMaper;

    public Friend insert(final Friend friend) {
        try {
            return jdbcTemplate.queryForObject(INSERT, friendToSql(friend), friendMaper);
        } catch (DuplicateKeyException e){
            throw new BadRequestException(String.format("Пользователи с userId = %d и friendId = %d уже дружат", friend.getUserId(), friend.getFriendId()));
        } catch (DataIntegrityViolationException e){
            throw new BadRequestException(String.format("Не удалось добавить дружбу, проверьте userId = %d и friendId = %d", friend.getUserId(), friend.getFriendId()));
        }
    }

    private MapSqlParameterSource friendToSql(final Friend friend){
        final MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("user_id", friend.getUserId());
        params.addValue("friend_id", friend.getFriendId());

        return params;
    }
}