package org.task4;


import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.convert.converter.Converter;

public class StringToData implements Converter<String, Data> {
    Data data;

    @Override
    public Data convert(String source) {
        try (
            CSVParser parser = CSVParser.parse(source, CSVFormat.DEFAULT);
        ) {
            var record = parser.stream().toList().get(0);
            this.data = new Data(record.get(0), record.get(1), Date.valueOf(record.get(2)), record.get(3));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        return data;
    }
}
