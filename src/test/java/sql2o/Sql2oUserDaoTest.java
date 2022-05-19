package sql2o;

import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oUserDaoTest {
    private Sql2oUserDao userDao;
    private Connection conn;

    @BeforeEach
    void setUp() {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    void tearDown() {
        conn.close();
    }

    @Test
    void add() {
        User user = setupUser();
        userDao.add(user);
        assertEquals(1, user.getId());
    }

    @Test
    void getAllUsers() {
        User user = setupUser();
        User user2 = setupUser();
        userDao.add(user);
        userDao.add(user2);
        assertEquals(2,userDao.getAllUsers().size());
    }

    @Test
    void deleteUserById() {
        User user = setupUser();
        User user1 = setupUser();
        userDao.add(user);
        userDao.add(user1);
        userDao.deleteUserById(user.getId());
        assertEquals(1,userDao.getAllUsers().size());
        User[] users ={user1};
        assertEquals(Arrays.asList(users),userDao.getAllUsers());//inorder to be sure the remaining one is correct
    }

    @Test
    void deleteAll() {
        User user = setupUser();
        User user1 = setupUser();
        User user2 = setupUser();
        userDao.add(user);
        userDao.add(user1);
        userDao.add(user2);
        userDao.deleteAll();
        assertEquals(0,userDao.getAllUsers().size());
    }
    //helper
    public User setupUser (){
        return new User("Tech Guy", "Head of Department", "Software Engineer", 5);
    }

}