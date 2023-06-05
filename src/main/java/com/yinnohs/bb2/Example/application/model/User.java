package com.yinnohs.bb2.Example.application.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yinnohs.bb2.Example.domain.models.interfaces.IUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDate;
import java.util.Collection;

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
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @JsonIgnore
    @Column(name = "is_deleted", nullable = true)
    private  Boolean isDeleted;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "item_id")
    private Collection<Item> items;
}
