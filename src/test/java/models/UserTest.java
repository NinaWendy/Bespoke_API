package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void newObjectInstantiatesCorrectly() {
        User user = setupUser();
        assertNotNull(user);
    }

    @Test
    void getName_returnsCorrectName() {
        User user = setupUser();
        String name = user.getName();
        assertEquals("Tech Guy", name);
    }

    @Test
    void setName_setsCorrectName() {
        User user = setupUser();
        user.setName("IT Guy");
        assertEquals("IT Guy", user.getName());
    }

    @Test
    void getPosition() {
        User user = setupUser();
        String position = user.getPosition();
        assertEquals("Head of Department", position);
    }

    @Test
    void setPosition() {
        User user = setupUser();
        user.setPosition("Executive");
        assertNotEquals("IT Guy", user.getPosition());
    }

    @Test
    void getRole() {
        User user = setupUser();
        String role = user.getRole();
        assertEquals("Software Engineer", role);
    }

    @Test
    void setRole() {
        User user = setupUser();
        user.setRole("Software Dev");
        assertEquals("Software Dev", user.getRole());
    }

    @Test
    void getDepartment_id() {
        User user = setupUser();
        int departmentId = user.getDepartment_id();
        assertEquals(5, departmentId);
    }

    @Test
    void setDepartment_id() {
        User user = setupUser();
        user.setDepartment_id(1);
        assertNotEquals(5, user.getDepartment_id());
    }

    @Test
    void testEquals() {
        User user = setupUser();
        User user2 = setupUser();
        assertEquals(user, user2);
    }
    //Helper method
    public User setupUser (){
        return new User("Tech Guy", "Head of Department", "Software Engineer", 5);
    }
}