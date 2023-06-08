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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name="users")
public class User implements IUser, UserDetails {

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Collection<Role> authorities;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @JsonIgnore
    @Column(name = "is_deleted", nullable = true)
    private  Boolean isDeleted;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "item_id")
    private Collection<Item> items;


    public  User(){
        super();
        this.authorities = new HashSet<>();
    }

    public User(long userId, String name, String surname, String email, LocalDate creationDate, Collection<Role> authorities, String password, Boolean isDeleted, Collection<Item> items) {
        super();
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.creationDate = creationDate;
        this.authorities = authorities;
        this.password = password;
        this.isDeleted = isDeleted;
        this.items = items;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Collection<Role> authorities){
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public String getEmail(){
        return this.email;
    }

    public  String getName(){
        return  this.name;
    }

    public long getUserId() {
        return userId;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public Collection<Item> getItems() {
        return items;
    }

    public  void setEmail(String email){
        this.email = email;
    }

    public  void setName(String name){
        this.name = name;
    }


    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public void setUsername(String email){
        this.email = email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.isDeleted;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.isDeleted;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.isDeleted;
    }

    @Override
    public boolean isEnabled() {
        return !this.isDeleted;
    }
}
