package io.redbee.product.conf.ms.conferences.mapper;

import io.redbee.product.conf.ms.conferences.models.Conference;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static io.redbee.product.conf.ms.conferences.shared.util.LocalDateTimeUtils.formatDate;

public class ConferenceRowMapper implements RowMapper<Conference> {

    @Override
    public Conference mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Conference(
                rs.getInt("id"),
                rs.getString("name"),
                formatDate(rs.getTimestamp("start_date")),
                formatDate(rs.getTimestamp("end_date")),
                rs.getString("description"),
                rs.getString("visibility")
        );
    }

}
