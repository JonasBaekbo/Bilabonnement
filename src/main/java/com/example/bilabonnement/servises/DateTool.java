package com.example.bilabonnement.servises;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class DateTool {

    public Timestamp getTimeStamp() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        return timestamp;
    }

    public java.sql.Date getUtilDateAsSQL(java.util.Date utilDate) {
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

    public Date getSQLDateAsUtil(Date sqlDate) {
        java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
        return utilDate;
    }

}
