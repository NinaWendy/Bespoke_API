package sql2o;

import models.DepartmentNews;
//import models.Department;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oDepartmentNewsDaoTest {
    private Connection conn;
    private Sql2oDepartmentNewsDao departmentNewsDao;

    @BeforeEach
    void setUp() {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentNewsDao= new Sql2oDepartmentNewsDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    void tearDown() {
        conn.close();
    }

    @Test
    void add() {
        DepartmentNews departmentNews = setUpDepartmentNews();
        departmentNewsDao.add(departmentNews);
        assertEquals(1, departmentNews.getId());
    }

    @Test
    void getAllDepartmentNews() {
        DepartmentNews departmentNews = setUpDepartmentNews();
        DepartmentNews departmentNews2 = setUpDepartmentNews();
        departmentNewsDao.add(departmentNews);
        departmentNewsDao.add(departmentNews2);
        assertEquals(2,departmentNewsDao.getAllDepartmentNews().size());
    }

    @Test
    void deleteById() {
        DepartmentNews departmentNews = setUpDepartmentNews();
        DepartmentNews departmentNews2 = setUpDepartmentNews();
        departmentNewsDao.add(departmentNews);
        departmentNewsDao.add(departmentNews2);
        assertEquals(2, departmentNewsDao.getAllDepartmentNews().size());
        departmentNewsDao.deleteDepartmentNewsById(departmentNews.getId());
        assertEquals(1, departmentNewsDao.getAllDepartmentNews().size());
    }

    @Test
    void clearAll() {
        DepartmentNews departmentNews = setUpDepartmentNews();
        DepartmentNews departmentNews2 = setUpDepartmentNews();
        departmentNewsDao.add(departmentNews);
        departmentNewsDao.add(departmentNews2);
        departmentNewsDao.clearAll();
        assertEquals(0,departmentNewsDao.getAllDepartmentNews().size());
    }
    public DepartmentNews setUpDepartmentNews (){
        return new DepartmentNews("How to install ubuntu", "Nina Ryan",3);
    }
}