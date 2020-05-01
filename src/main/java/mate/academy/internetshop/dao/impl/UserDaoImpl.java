package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.User;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        Storage.addNewUser(user);
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.users.stream()
                .filter(it -> it.getId().equals(id))
                .findAny();
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public User update(User user) {
        User oldUser = get(user.getId()).get();
        if (!oldUser.getName().equals(user.getName())) {
            oldUser.setName(user.getName());
        }
        if (!oldUser.getPassword().equals(user.getPassword())) {
            oldUser.setPassword(user.getPassword());
        }
        if (!oldUser.getLogin().equals(user.getLogin())) {
            oldUser.setLogin(user.getLogin());
        }
        return oldUser;
    }

    @Override
    public boolean delete(Long id) {
        Optional<User> user = get(id);
        user.ifPresent(Storage.users::remove);
        return user.isPresent();
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return Storage.users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findAny();
    }
}
