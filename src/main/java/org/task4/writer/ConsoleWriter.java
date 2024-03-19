package org.task4.writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.task4.fileshandler.Data;

import java.util.List;

//@Component
public class ConsoleWriter implements MyConsumer<List<Data>> {
    @Override
    //@Bean   @DependsOn("convertDataList")
    public boolean accept(@Autowired List<Data> data) {
        System.out.println("DataList=");
        data.forEach(d -> {
            System.out.println(d.toString());
        });

        return true;
    }


}
