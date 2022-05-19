package models;

import java.util.Objects;

public class News {
    private String post;
    private String writtenby;
    private int id;

    public News(String post, String writtenby) {
        this.post = post;
        this.writtenby = writtenby;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getWrittenby() {
        return writtenby;
    }

    public void setWrittenby(String writtenby) {
        this.writtenby = writtenby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private int department_id;

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (id != news.id) return false;
        if (!Objects.equals(post, news.post)) return false;
        return Objects.equals(writtenby, news.writtenby);
    }

    @Override
    public int hashCode() {
        int result = post != null ? post.hashCode() : 0;
        result = 31 * result + (writtenby != null ? writtenby.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
