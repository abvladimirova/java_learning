package org.example.task4;


import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

public class StringToData implements Converter<String, Data> {
    Data data;

    @Override
    public Data convert(String source) {
        //this.data = new Data("user1", "jd ovfo oifvo", LocalDateTime.now(), "web");
        String[] s = source.split(";");
        System.out.println(s.toString());
        this.data = new Data(s[0], s[1], Date.valueOf(s[2]), s[3]);
        return data;
    }
}
