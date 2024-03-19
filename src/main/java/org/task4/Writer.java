package org.task4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;
@Component
public class Writer /*implements Consumer<List<Data>>*/ {
    //@Override
    @Bean @DependsOn("convertDataList")

    public boolean accept(@Autowired List<Data> data) {
        // TODO здесь должна быть запись в БД
        System.out.println("DataList=");
        data.forEach(d -> System.out.println(d.toString()));
        return true;
    }


}
