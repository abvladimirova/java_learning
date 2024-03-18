package org.task4;


import java.sql.Date;

import org.springframework.core.convert.converter.Converter;

public class StringToData implements Converter<String, Data> {
    Data data;

    @Override
    public Data convert(String source) {
        //this.data = new Data("user1", "jd ovfo oifvo", LocalDateTime.now(), "web");
        String[] s = source.split(";");
        this.data = new Data(s[0], s[1], Date.valueOf(s[2]), s[3]);
        return data;
    }
}
