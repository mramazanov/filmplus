package ru.jabka.filmplus.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ru.jabka.filmplus.model.friend.Friend;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FriendMapper implements RowMapper<Friend> {
    @Override
    public Friend mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Friend.builder()
                .idUser(rs.getLong("user_id"))
                .idFriend(rs.getLong("friend_id"))
                .build();
    }
}