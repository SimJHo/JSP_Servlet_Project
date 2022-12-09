package dto;

import java.sql.Date;

public class MemberDto {

    private int custNo;
    private String custName;
    private String phone;
    private String address;
    private Date joinDate;
    private String grade;
    private String city;

    public MemberDto() {}

    public MemberDto(int custNo, String custName, String phone, String address, Date joinDate, String grade, String city) {
        this.custNo = custNo;
        this.custName = custName;
        this.phone = phone;
        this.address = address;
        this.joinDate = joinDate;
        this.grade = grade;
        this.city = city;
    }

    public int getCustNo() {
        return custNo;
    }

    public void setCustNo(int custNo) {
        this.custNo = custNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
