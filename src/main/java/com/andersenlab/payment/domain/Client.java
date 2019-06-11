package com.andersenlab.payment.domain;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class Client {

    private long id;

    private String name;

    private StringBuilder password;

    private List<GrantedAuthority> grandedAuthorities;

    public Client() {
    }

    public Client(long id, String name, StringBuilder password, List<GrantedAuthority> grandedAuthorities) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.grandedAuthorities = grandedAuthorities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StringBuilder getPassword() {
        return password;
    }

    public void setPassword(StringBuilder password) {
        this.password = password;
    }

    public List<GrantedAuthority> getGrandedAuthorities() {
        return grandedAuthorities;
    }

    public void setGrandedAuthorities(List<GrantedAuthority> grandedAuthorities) {
        this.grandedAuthorities = grandedAuthorities;
    }

}
