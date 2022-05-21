package models;

public class DepartmentNews extends News {
    private int department_id;
    private int id;

    public DepartmentNews(String post, String writtenby, int department_id)
    {
        super(post, writtenby);
        this.department_id= department_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DepartmentNews that = (DepartmentNews) o;

        if (department_id != that.department_id) return false;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + department_id;
        result = 31 * result + id;
        return result;
    }
}
