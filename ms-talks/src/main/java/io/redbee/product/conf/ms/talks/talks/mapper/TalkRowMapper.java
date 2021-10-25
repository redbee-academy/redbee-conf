package io.redbee.product.conf.ms.talks.talks.mapper;

import io.redbee.product.conf.ms.talks.talks.model.Talk;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static io.redbee.product.conf.ms.talks.talks.shared.util.LocalDateTimeUtils.formatDate;

public class TalkRowMapper implements RowMapper<Talk> {

    @Override
    public Talk mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Talk(
                rs.getInt("id"),
                rs.getBoolean("redbee_employee"),
                rs.getString("reference"),
                rs.getString("talk_name"),
                rs.getString("talk_topic"),
                rs.getString("talk_description"),
                formatDate(rs.getTimestamp("creation_date")),
                rs.getString("speaker_name"),
                rs.getString("speaker_email")
        );
    }
}
