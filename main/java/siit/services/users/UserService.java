package siit.services.users;

import siit.db.UserDao;
import siit.model.User;

import javax.naming.AuthenticationException;

public class UserService {

    UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public User getUserByNameAndPassword(String name, String password) throws AuthenticationException {
        return userDao.getUserByNameAndPassword(name, password);
    }

}
