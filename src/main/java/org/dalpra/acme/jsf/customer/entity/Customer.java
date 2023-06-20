package org.dalpra.acme.jsf.customer.entity;

import jakarta.persistence.Column;
import org.dalpra.acme.jsf.utility.Stato;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Customer {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private LocalDate dob;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Stato state;

    public Customer() {
        id = null;
        name = "";
        surname = "";
        email = "";
        dob = LocalDate.now();
        state = Stato.ACTIVE;
        createAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public Stato getState() {
        return state;
    }

    public void setState(Stato state) {
        this.state = state;
    }

}
