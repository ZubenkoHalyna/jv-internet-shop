package mate.academy.internetshop.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private Long id;
    private String name;
    private String login;
    private byte[] salt;
    private byte[] passwordHash;
    private Set<Role> roles;

    public User(String name, String login, byte[] salt,
                byte[] passwordHash, Set<Role> roles) {
        this.name = name;
        this.login = login;
        this.salt = salt;
        this.passwordHash = passwordHash;
        this.roles = new HashSet<>(roles);
    }

    public User(Long id, String name, String login, byte[] salt,
                byte[] passwordHash, Set<Role> roles) {
        this(name, login, salt, passwordHash, roles);
        this.id = id;
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

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
