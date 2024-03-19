package org.task4.entity;
import lombok.*;
import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@Entity(name = "logins")
public class LoginsDairy {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "access_date")
    private Date accessDate;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User userId;
    @Column(name = "application")
    private String application;
}
