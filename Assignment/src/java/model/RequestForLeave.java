package model;

import java.sql.Date;
import java.sql.Timestamp;

public class RequestForLeave extends BaseModel {
    private Employee created_by;
    private Timestamp created_time;
    private Date from;
    private Date to;
    private String reason;
    private int status; // 1=InProgress, 2=Approved, 3=Rejected
    private Employee processed_by;

    public Employee getCreated_by() {
        return created_by;
    }

    public void setCreated_by(Employee created_by) {
        this.created_by = created_by;
    }

    public Timestamp getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Timestamp created_time) {
        this.created_time = created_time;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Employee getProcessed_by() {
        return processed_by;
    }

    public void setProcessed_by(Employee processed_by) {
        this.processed_by = processed_by;
    }

    // 🔹 Optional: helper để hiển thị trạng thái bằng chữ
    public String getStatusText() {
        return switch (status) {
            case 1 -> "In Progress";
            case 2 -> "Approved";
            case 3 -> "Rejected";
            default -> "Unknown";
        };
    }

    @Override
    public String toString() {
        return "RequestForLeave{" +
                "id=" + getId() +
                ", created_by=" + (created_by != null ? created_by.getName() : "null") +
                ", from=" + from +
                ", to=" + to +
                ", reason='" + reason + '\'' +
                ", status=" + getStatusText() +
                ", processed_by=" + (processed_by != null ? processed_by.getName() : "null") +
                '}';
    }

    public void setCreatedBy(Employee employee) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setStatus(String inProgress) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
