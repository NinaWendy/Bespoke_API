package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentNewsTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPost() {
        DepartmentNews departmentNews = setUpDepartmentNews();
        String post = departmentNews.getPost();
        assertEquals("How to install ubuntu",post);

    }

    @Test
    void setPost() {
        DepartmentNews departmentNews = setUpDepartmentNews();
        departmentNews.setPost("Hacking 101");
        assertEquals("Hacking 101", departmentNews.getPost());
    }

    @Test
    void getWrittenby() {
        DepartmentNews departmentNews = setUpDepartmentNews();
        String writer = departmentNews.getWrittenby();
        assertEquals("Nina Ryan", writer);
    }

    @Test
    void setWrittenby() {
        DepartmentNews departmentNews = setUpDepartmentNews();
        departmentNews.setWrittenby("Gray");
        assertNotEquals("Nina Ryan", departmentNews.getWrittenby());
    }

    @Test
    void getDepartment_id() {
        DepartmentNews departmentNews = setUpDepartmentNews();
        int deptId= departmentNews.getDepartment_id();
        assertEquals(3,deptId);
    }

    @Test
    void setDepartment_id() {
        DepartmentNews departmentNews = setUpDepartmentNews();
        departmentNews.setDepartment_id(2);
        assertEquals(2,departmentNews.getDepartment_id());
    }

    @Test
    void testEquals() {
        DepartmentNews departmentNews = setUpDepartmentNews();
        DepartmentNews departmentNews2 = setUpDepartmentNews();
        assertEquals(departmentNews, departmentNews2);
    }
    //Helper method
    public DepartmentNews setUpDepartmentNews (){
        return new DepartmentNews("How to install ubuntu", "Nina Ryan",3);
    }
}