package sql2o;

import models.News;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oNewsDaoTest {
    private Connection conn;
    private Sql2oNewsDao newsDao;

    @BeforeEach
    void setUp() {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        newsDao = new Sql2oNewsDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    void tearDown() {
        conn.close();
    }

    @Test
    void add() {
        News news = new News("Trending Headline","Wendy Williams");
        newsDao.add(news);
        assertEquals(1,news.getId());
    }

    @Test
    void getAllNews() {
        News news = new News("Trending Headline","Wendy Williams");
        News news2 = new News("Trending Headline","Wendy Williams");
        News news3 = new News("Trending Headline","Wendy Williams");
        newsDao.add(news);
        newsDao.add(news2);//we don't add news3 inorder to correctly test the method
        assertNotEquals(3, newsDao.getAllNews().size());
        News[] allNews = {news,news2};
        assertEquals(Arrays.asList(allNews), newsDao.getAllNews());
    }

    @Test
    void deleteNewsById() {
        News news = new News("Trending Headline","Wendy Williams");
        News news2 = new News("Trending Headline","Wendy Williams");
        newsDao.add(news);
        newsDao.add(news2);
        newsDao.deleteNewsById(news2.getId());
        assertEquals(1, newsDao.getAllNews().size());
    }

    @Test
    void clearAll() {
        News news = new News("Trending Headline","Wendy Williams");
        News news2 = new News("Trending Headline","Wendy Williams");
        newsDao.add(news);
        newsDao.add(news2);
        newsDao.clearAll();
        assertEquals(0,newsDao.getAllNews().size());
    }
}