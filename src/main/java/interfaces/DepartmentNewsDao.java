package interfaces;

import models.DepartmentNews;

import java.util.List;

public interface DepartmentNewsDao {
    //create
    void add(DepartmentNews departmentNews);

    //read
    List<DepartmentNews> getAllDepartmentNews();
//    List<DepartmentNews> getAllNewsByDepartment(int department_id);

    //update

    //delete
    void deleteDepartmentNewsById(int id);
    void clearAll();
}
