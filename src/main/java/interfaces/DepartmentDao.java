package interfaces;

import models.Department;

import java.util.List;

public interface DepartmentDao {
    //CRUD
    //Create
    void  add (Department department);

    //Read
    List<Department> getAllDepartments(); //returns a list of all departments
    Department findDepartmentById(int id);

    //Update
    void update(int id,int size, String department_name, String description);


    //Destroy
    void deleteDepartmentById(int id);//removes only a particular instance
    void deleteAll();//remove all instances of department
}
