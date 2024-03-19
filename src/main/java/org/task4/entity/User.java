package org.task4.entity;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@Entity(name = "users")
public class User {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "username")
    private String userName;
    @Column(name = "fio")
    private String fio;
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private final List<LoginsDairy> logins = new ArrayList<>();
}
