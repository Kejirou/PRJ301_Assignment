package model;

public class Employee extends BaseModel {
    private String name;
    private Department dept; 
    private Employee supervisor;

    public Employee() {
    }

    public Employee(int id, String name) {
        super.setId(id);
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Department getDept() { return dept; }
    public void setDept(Department dept) { this.dept = dept; }

    public Employee getSupervisor() { return supervisor; }
    public void setSupervisor(Employee supervisor) { this.supervisor = supervisor; }

    @Override
    public String toString() {
        return "Employee{id=" + getId() + ", name='" + name + "'}";
    }

    public Object getEmployee() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
