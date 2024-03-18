package org.task4;

import java.sql.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Data {
    private String loginUser;
    private String FIO;
    private Date loginDate;
    private String app;

    public Data(String loginUser, String FIO, Date loginDate, String app) {
        this.loginUser = loginUser;
        this.FIO = capitalize(FIO);
        this.loginDate = loginDate;
        this.app = checkApp(app);
    }

    private static String checkApp(String word)
    {
        if (word.equals("web") || word.equals("mobile")) {
            return word;
        }
        return "other:"+word;
    }


    private static String capitalizeWord(String word)
    {
        if (word == null || word.length() == 0) {
            return word;
        }
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    private static String capitalize(String str)
    {
        if (str == null || str.length() == 0) {
            return str;
        }

        return Stream.of(str.trim().split("\\s+"))
                .map(Data::capitalizeWord)
                .collect(Collectors.joining(" "));

    }

    @Override
    public String toString() {
        return "Data{" +
                "loginUser='" + loginUser + '\'' +
                ", FIO='" + FIO + '\'' +
                ", loginDate=" + loginDate +
                ", app='" + app + '\'' +
                '}';
    }
}
