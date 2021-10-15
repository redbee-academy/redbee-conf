package io.redbee.product.conf.ms.talks.talks.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class TalkDao<Talk> {

    private final NamedParameterJdbcTemplate template;

    public TalkDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(TalkDao.class);

    private static final String getQuery = "SELECT " +
            "id, " +
            "redbee_employee" +
            "reference" +
            "talk_name" +
            "talk_topic" +
            "talk_description" +
            "creation_date, " +
            "FROM talks";

    private static final String insertQuery = "" + "INSERT INTO talks (redbee_employee, reference, talk_name, talk_topic, talk_description, creation_date)" +
            "VALUES (:redbee_employee, :reference, :talk_name, :talk_topic, :talk_description, :creation_date)";

//    public void save (Talk talk){
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        template.update(insertQuery, keyHolder);
//    }


}
