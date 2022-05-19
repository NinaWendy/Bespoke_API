package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewsTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPost() {
        News news = setupNews();
        String post = news.getPost();
        assertEquals("How to kill a mocking bird", post);
    }

    @Test
    void setPost() {
        News news = setupNews();
        news.setPost("Angular 101");
        assertEquals("Angular 101", news.getPost());
    }

    @Test
    void getWrittenby() {
        News news = setupNews();
        String writer= news.getWrittenby();
        assertEquals("Nina Ryan", writer);
    }

    @Test
    void setWrittenby() {
        News news = setupNews();
        news.setWrittenby("John Doe");
        assertEquals("John Doe", news.getWrittenby());
    }

    @Test
    void getId() {
        News news = setupNews();
         int id= news.getId();
        assertEquals(0,id);
    }

    @Test
    void setId() {
        News news = setupNews();
        news.setId(1);
        assertEquals(1,news.getId());
    }
    @Test
    void testEquals() {
        News news = setupNews();
        News news2 = setupNews();
        assertEquals(news, news2);
    }
    //Helper
    public News setupNews (){
        return new News("How to kill a mocking bird", "Nina Ryan");
    }

}