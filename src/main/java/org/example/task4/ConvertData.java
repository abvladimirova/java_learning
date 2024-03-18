package org.example.task4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

@Component
public class ConvertData implements Supplier<List<Data>> {

    public ConvertData(@Autowired LinesData linesData) {
        this.rawData = linesData.rawData;
    }

    List<String> rawData;
    @Bean(name="convertDataList") @DependsOn("getLines")
    public List<Data> get()
    {
        List<Data> d;
        d = rawData.stream().map(s -> new StringToData().convert(s)).toList();
        d.stream().forEach(System.out::println);
        return d;
    }


}
