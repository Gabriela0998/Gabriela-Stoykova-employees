package entity;

import java.util.Date;

public class EmployeesProjectEntity {
    private Integer employeeID1;
    private Integer employeeID2;
    private Integer projectID;
    private Date dateFrom;
    private Date dateTo;
    private Integer days;

    public EmployeesProjectEntity(){}

    public Integer getEmployeeID1() {
        return employeeID1;
    }

    public void setEmployeeID1(Integer employeeID1) {
        this.employeeID1 = employeeID1;
    }

    public Integer getEmployeeID2() {
        return employeeID2;
    }

    public void setEmployeeID2(Integer employeeID2) {
        this.employeeID2 = employeeID2;
    }

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}
