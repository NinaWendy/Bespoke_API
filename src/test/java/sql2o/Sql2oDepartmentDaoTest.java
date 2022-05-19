package sql2o;

import models.Department;
import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oDepartmentDaoTest {
    private Sql2oDepartmentDao departmentDao;
    private Sql2oUserDao userDao;
    private Connection conn;

    @BeforeEach
    void setUp() {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";// h2 stores data in memory
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    void tearDown() {
        conn.close();
    }

    @Test
    void add_correctlySetsIdInDatabase() {
        Department department = setUpDepartment();
        int originalDepartmentId = department.getId();
        departmentDao.add(department);
        assertNotEquals(originalDepartmentId, department.getId()); //Because database ID is different form the model ID which starts from 0
        assertEquals(1,department.getId());
    }

    @Test
    void getAllDepartments_returnsListOfAllDepartments() {
        Department department = setUpDepartment();
        Department department2 = setUpDepartment();
        departmentDao.add(department);
        departmentDao.add(department2);
        Department[] departments = {department, department2};
        assertEquals(Arrays.asList(departments), departmentDao.getAllDepartments());
    }

    @Test
    void findDepartmentById() {
        Department department = setUpDepartment();
        departmentDao.add(department);
        Department foundDepartment = departmentDao.findDepartmentById(department.getId());
        assertEquals(department, foundDepartment);
    }

    @Test
    void update() {
        Department department = setUpDepartment();
        departmentDao.add(department);
        departmentDao.update(department.getId(),50,"Finance","Deals wit money");
        Department updatedDepartment = departmentDao.findDepartmentById(department.getId());
        assertNotEquals(department.getDepartment_name(), updatedDepartment.getDepartment_name());
    }

    @Test
    void deleteDepartmentById() {
        Department department = setUpDepartment();
        Department department2 = setUpDepartment();
        departmentDao.add(department);
        departmentDao.add(department2);
        departmentDao.deleteDepartmentById(department2.getId());
        assertEquals(1, departmentDao.getAllDepartments().size());
        Department[] departments = {department};
        assertEquals(Arrays.asList(departments), departmentDao.getAllDepartments());
    }

    @Test
    void deleteAll() {
        Department department = setUpDepartment();
        Department department2 = setUpDepartment();
        Department department3 = setUpDepartment();
        departmentDao.add(department);
        departmentDao.add(department2);
        departmentDao.add(department3);
        departmentDao.deleteAll();
        assertEquals(0, departmentDao.getAllDepartments().size());
    }
    @Test
    public void noDepartmentReturnsEmptyList(){
        assertEquals(0, departmentDao.getAllDepartments().size());
    }


    @Test
    void getAllUsersOfADepartment() {
        Department department = setUpDepartment();
        departmentDao.add(department);
        int deptId= department.getId();
        User user = new User("John Doe","Manager","Head of Board",deptId);
        User user2 = new User("John Doe","Manager","Head of Board",deptId);
        User user3 = new User("John Doe","Manager","Head of Board",deptId);
        userDao.add(user);
        userDao.add(user2);
        //we are not adding task 3, so we can test things precisely.
        departmentDao.getAllUsersOfADepartment(deptId);
        assertEquals(2, departmentDao.getAllUsersOfADepartment(deptId).size());
    }

    //Helper
    public Department setUpDepartment (){
        return new Department("Tech", "Deals with software installation", 10);
    }
}