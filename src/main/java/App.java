import com.google.gson.Gson;
import exceptions.ApiException;
import models.Department;
import models.DepartmentNews;
import models.News;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import sql2o.Sql2oDepartmentDao;
import sql2o.Sql2oDepartmentNewsDao;
import sql2o.Sql2oNewsDao;
import sql2o.Sql2oUserDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.after;

public class App {
    public static void main(String[] args) {
        Sql2oNewsDao newsDao;
        Sql2oDepartmentDao departmentDao;
        Sql2oDepartmentNewsDao departmentNewsDao;
        Sql2oUserDao userDao;
        Connection conn;
        Gson g = new Gson();

        String connectionString = "jdbc:h2:~/news.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        newsDao = new Sql2oNewsDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        departmentNewsDao = new Sql2oDepartmentNewsDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();


        //add news affecting whole company
        post("/news/new", "application/json", (request, response) -> {  //accepts a request in format JSON from an app
            News news = g.fromJson(request.body(), News.class);
            newsDao.add(news); //implements Dao
            response.status(200);
            return g.toJson(news);
        });

        //get all news for the company
        get("/news","application/json", ((request, response) -> {
            List<News> list = newsDao.getAllNews();
            response.status(200);
            return g.toJson(list);
        }));

        //add new department
        post("/departments/new", "application/json", (request, response) -> {  //accepts a request in format JSON from an app
            Department department = g.fromJson(request.body(), Department.class);
            departmentDao.add(department); //implements Dao
            response.status(200);
            return g.toJson(department);
        });

        //view all departments
        get("/departments","application/json", ((request, response) -> {
            List<Department> list = departmentDao.getAllDepartments();
            response.status(200);
            return g.toJson(list);
        }));

        //add a department news
        post("/department-news/new", "application/json", (request, response) -> {  //accepts a request in format JSON from an app
            DepartmentNews departmentNews = g.fromJson(request.body(), DepartmentNews.class);
            departmentNewsDao.add(departmentNews); //implements Dao
            response.status(200);
            return g.toJson(departmentNews);
        });
        get("/department-news","application/json", ((request, response) -> {
            List<DepartmentNews> list = departmentNewsDao.getAllDepartmentNews();
            response.status(200);
            return g.toJson(list);
        }));
        //add a user
        post("/users/new", "application/json", (request, response) -> {  //accepts a request in format JSON from an app
            User user = g.fromJson(request.body(), User.class);
            userDao.add(user); //implements Dao
            response.status(200);
            return g.toJson(user);
        });

        //view all users
        get("/users","application/json", ((request, response) -> {
            List<User> list = userDao.getAllUsers();
            response.status(200);
            return g.toJson(list);
        }));

        //view a particular user and their details
        get("/users/:id", "application/json", (request, response) -> { //accept a request in format JSON from an app
            response.type("application/json");
            int userId = Integer.parseInt(request.params("id"));
            return g.toJson(userDao.findUserById(userId));
        });

        //view a particular department and its details
        get("/departments/:id", "application/json", (request, response) -> { //accept a request in format JSON from an app
            response.type("application/json");
            int departmentId = Integer.parseInt(request.params("id"));
            return g.toJson(departmentDao.findDepartmentById(departmentId));
        });

        //add a user to a particular department
        post("/departments/:department_id/users/new", "application/json", (request, response) -> {
            int department_id = Integer.parseInt(request.params("department_id"));
            User user = g.fromJson(request.body(), User.class);
            user.setDepartment_id(department_id); //we need to set this separately because it comes from our route, not our JSON input.
            userDao.add(user);
            response.status(200);
            return g.toJson(user);
        });

        //view all users from a particular department
        get("/departments/:department_id/users", "application/json", (request, response) -> {
            int department_id = Integer.parseInt(request.params("department_id"));
            Department departmentToFind = departmentDao.findDepartmentById(department_id);
            List<User> allUsersInDepartment;

            if (departmentToFind == null){
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", request.params("department_id")));
            }
            allUsersInDepartment =departmentDao.getAllUsersOfADepartment(department_id);
            return g.toJson(allUsersInDepartment);
        });

        //add news for a particular department
        post("/departments/:department_id/news/new", "application/json", (request, response) -> {
            int id = Integer.parseInt(request.params("department_id"));
            News departmentNews = g.fromJson(request.body(),News.class);
            departmentNews.setDepartment_id(id); //we need to set this separately because it comes from our route, not our JSON input.
            newsDao.add(departmentNews);
            response.status(200);
            return g.toJson(departmentNews);
        });

        //view news for a specific department
        get("/departments/:department_id/news", "application/json", (request, response) -> {
            int id = Integer.parseInt(request.params("department_id"));
            Department departmentToFind = departmentDao.findDepartmentById(id);
            List<News> allNewsInDepartment;

            if (departmentToFind == null){
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", request.params("department_id")));
            }
            allNewsInDepartment =newsDao.getAllNewsByDepartment(id);
            return g.toJson(allNewsInDepartment);
        });



//FILTERS
        exception(ApiException.class, (exception, req, res) -> {
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", exception.getStatusCode());
            jsonMap.put("errorMessage", exception.getMessage());
            res.type("application/json");
            res.status(exception.getStatusCode());
            res.body(g.toJson(jsonMap));
        });
        after((request, response) -> response.type("application/json"));

    }
}
