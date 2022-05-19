package interfaces;

import models.User;

import java.util.List;

public interface UserDao {
    //CRUD

    //Create
    void  add (User user);
//    void addUserToDepartment( int department_id);

    //Read
    List<User> getAllUsers();
    User findUserById(int id);

    //Delete
    void deleteUserById(int id);
    void deleteAll();
}
