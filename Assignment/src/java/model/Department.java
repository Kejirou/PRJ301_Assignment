package model;

/**
 * Department model — represents Division table in database
 */
public class Department extends BaseModel {
    private String name;

    public Department() {
    }

    public Department(int id, String name) {
        super.setId(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{id=" + getId() + ", name='" + name + "'}";
    }
}
