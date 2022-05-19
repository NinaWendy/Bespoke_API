package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {
    //pojo test
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void newObjectInstantiatesCorrectly() {
        Department department = setUpDepartment();
        assertNotNull(department);
    }

    @Test
    void getDepartment_name_returnsDepartmentNameCorrectly_String() {
        Department department =setUpDepartment();
        String deptName= department.getDepartment_name();
        assertEquals("Tech",deptName);
    }

    @Test
    void setDepartment_name_setsDepartmentNameForThatParticularInstance() {
        Department department = setUpDepartment();
        department.setDepartment_name("Finance");
        assertEquals("Finance", department.getDepartment_name());
    }

    @Test
    void getDescription_returnsDepartmentDescriptionCorrectly_String() {
        Department department= setUpDepartment();
        String desc = department.getDescription();
        assertEquals("Deals with software installation", desc);
    }

    @Test
    void setDescription_setsDepartmentDescriptionForThatParticularInstance() {
        Department department = setUpDepartment();
        department.setDescription("Software maintenance");
        assertNotEquals("Deals with software installation", department.getDescription());//to confirm we have changed the description
    }

    @Test
    void getSize_returnsDepartmentSizeCorrectly_int() {
        Department department = setUpDepartment();
        int size = department.getSize();
        assertEquals(10,size);
    }

    @Test
    void setSize_setsDepartmentNameForThatParticularInstance(){
        Department department = setUpDepartment();
        department.setSize(5);
        assertNotEquals(10,department.getSize());
    }

    @Test
    void testEquals_comparesContentsOfInstance() {
        Department department = setUpDepartment();
        Department department2 = setUpDepartment();
        assertEquals(department, department2);
    }
    //Helper Method to allow easy refactoring
    public Department setUpDepartment (){
        return new Department("Tech", "Deals with software installation", 10);
    }

}