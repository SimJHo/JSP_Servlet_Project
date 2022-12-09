package dto;

public class SalesDto {

    private int custNo;
    private String custName;
    private String grade;
    private int sales;

    public SalesDto() {}

    public SalesDto(int custNo, String custName, String grade, int sales) {
        this.custNo = custNo;
        this.custName = custName;
        this.grade = grade;
        this.sales = sales;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }
}
