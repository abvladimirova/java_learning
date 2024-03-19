package org.task4.writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.task4.entity.LoginsDairy;
import org.task4.entity.User;
import org.task4.fileshandler.Data;
import org.task4.repository.LoginsRepo;
import org.task4.repository.UsersRepo;

import java.util.List;

@Component
public class DBWriter implements MyConsumer<List<Data>> {
    @Autowired UsersRepo usersRepo;
    @Autowired LoginsRepo loginsRepo;
    @Override
    @Bean @DependsOn("convertDataList")
    public boolean accept(@Autowired List<Data> data) {
        data.forEach(d -> {
            var users = usersRepo.findByUserName(d.getLoginUser());
            User user;
            if (users.size()>0) {
                user = new User(users.get(0).getId(),d.getLoginUser(),d.getFIO());
            }
            else {
                user = new User(0,d.getLoginUser(),d.getFIO());
            }
            usersRepo.save(user);
            LoginsDairy loginsRec = new LoginsDairy(0,d.getLoginDate(),user,d.getApp());
            loginsRepo.save(loginsRec);
        });
        return true;
    }
}
