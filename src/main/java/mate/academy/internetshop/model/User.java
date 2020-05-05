package mate.academy.internetshop.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private Long id;
    private String name;
    private String login;
    private String password;
    private Set<Role> roles;

    public User(String name, String login, String password, Set<Role> roles) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.roles = new HashSet<>(roles);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
