package ru.jabka.filmplus.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ru.jabka.filmplus.model.review.ReviewResponse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class ReviewMapper implements RowMapper<ReviewResponse> {

    @Override
    public ReviewResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ReviewResponse.builder()
                .userId(rs.getLong("user_id"))
                .userId(rs.getLong("movie_id"))
                .review(rs.getString("review_text"))
                .reviewDateTime(rs.getObject("created_at", Timestamp.class).toLocalDateTime())
                .build();
    }
}