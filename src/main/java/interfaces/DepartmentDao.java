package interfaces;

import models.Department;
import models.User;

import java.util.List;

public interface DepartmentDao {
    //CRUD
    //Create
    void  add (Department department);

    //Read
    List<Department> getAllDepartments(); //returns a list of all departments
    List<User> getAllUsersOfADepartment(int department_id);

    Department findDepartmentById(int id);

    //Update
    void update(int id,int size, String department_name, String description);


    //Destroy
    void deleteDepartmentById(int id);//removes only a particular instance
    void deleteAll();//remove all instances of department
}
