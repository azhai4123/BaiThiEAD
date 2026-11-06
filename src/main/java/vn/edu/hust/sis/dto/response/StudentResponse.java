package vn.edu.hust.sis.dto.response;

public class StudentResponse {

    private Integer studentId;
    private String studentCode;
    private String fullName;
    private String address;

    public StudentResponse() {
    }

    public StudentResponse(Integer studentId, String studentCode, String fullName, String address) {
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.fullName = fullName;
        this.address = address;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

