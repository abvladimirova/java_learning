package org.task4.fileshandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.task4.fileshandler.Data;
import org.task4.fileshandler.StringToData;

import java.util.List;
import java.util.function.Supplier;

@Component
public class LinesToDataConverter implements Supplier<List<Data>> {
    List<String> rawData;

    public LinesToDataConverter(@Autowired List<String> rawData) {
        this.rawData = rawData;
    }

    @Bean(name="convertDataList") @DependsOn("getLines")
    public List<Data> get()
    {
        return rawData.stream().map(s -> new StringToData().convert(s)).toList();
    }
}
