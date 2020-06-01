package com.dev.internet.shop.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private Long id;
    private String name;
    private String login;
    private String salt;
    private String passwordHash;
    private Set<Role> roles;

    public User(String name, String login, String password, Set<Role> roles) {
        this.name = name;
        this.login = login;
        this.passwordHash = password;
        this.roles = new HashSet<>(roles);
    }

    public User(Long id, String name, String login, String salt,
                String password, Set<Role> roles) {
        this(name, login, password, roles);
        this.id = id;
        this.salt = salt;
    }

    public boolean hasRole(Role.RoleName roleName) {
        for (Role r : roles) {
            if (roleName == r.getRoleName()) {
                return true;
            }
        }
        return false;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
