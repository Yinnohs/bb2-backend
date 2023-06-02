package com.yinnohs.bb2.Example.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yinnohs.bb2.Example.domain.models.interfaces.IUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User implements IUser {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private  long userId;

    @Column(name = "name")
    private  String name;

    @Column(name= "surname")
    private  String surname;

    @Column(name = "email", nullable = false, unique = true)
    private  String email;

    @CreatedDate
    private LocalDate creationDate;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private  Boolean isDeleted;

}
