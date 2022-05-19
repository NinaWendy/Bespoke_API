package interfaces;

import models.News;

import java.util.List;

public interface NewsDao {
    //CRUD

    //create
    void add(News news);

    //read
    List<News> getAllNews();
    List<News> getAllNewsByDepartment(int department_id);

    //delete
    void deleteNewsById(int id);
    void clearAll();
}
