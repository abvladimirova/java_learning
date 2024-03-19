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
            var users = usersRepo.findByUserName(d.loginUser());
            User user;
            if (!users.isEmpty()) {
                user = new User(users.get(0).getId(),d.loginUser(),d.FIO());
            }
            else {
                user = new User(0,d.loginUser(),d.FIO());
            }
            usersRepo.save(user);
            LoginsDairy loginsRec = new LoginsDairy(0,d.loginDate(),user,d.app());
            loginsRepo.save(loginsRec);
        });
        return true;
    }
}
