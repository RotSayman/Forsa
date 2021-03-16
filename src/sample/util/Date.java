package sample.util;

import java.time.LocalDate;

public class Date {
    private java.sql.Date selectDate;
    private static Date date;

    private Date(){}

    public java.sql.Date getDate(){
        if(selectDate == null){
            selectDate = java.sql.Date.valueOf(LocalDate.now());
        }
        return selectDate;
    }

    public void setDate(LocalDate localDate){
        selectDate = java.sql.Date.valueOf(localDate);
    }


    public static Date newInstance(){
        if(date == null){
            date = new Date();
        }
        return date;
    }
}
