package io.redbee.product.conf.ms.talks.talks.shared.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LocalDateTimeUtils {
    public static LocalDateTime formatDate(Timestamp timestamp){
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }
}
