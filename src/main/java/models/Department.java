package models;

import java.util.Objects;

public class Department {
    //Sample POJO
    //1.member variables
    private String department_name;
    private  String description;
    private int size;
    private int id;

    //2.constructor
    public Department(String department_name, String description, int size) {
        this.department_name = department_name;
        this.description = description;
        this.size = size;
    }
    //3.Getters and Setters

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //4.Override Equals method

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (size != that.size) return false;
        if (id != that.id) return false;
        if (!Objects.equals(department_name, that.department_name))
            return false;
        return Objects.equals(description, that.description);
    }
    // good practice to include hashCode when overriding equals
    @Override
    public int hashCode() {
        int result = department_name != null ? department_name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + size;
        result = 31 * result + id;
        return result;
    }
}
