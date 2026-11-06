package vn.edu.hust.sis.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentCreateRequest {

    @NotBlank(message = "Student Id / Code cannot be blank")
    @Size(max = 20, message = "Student Code must not exceed 20 characters")
    private String studentCode;

    @NotBlank(message = "Student Name cannot be blank")
    @Size(max = 100, message = "Student Name must not exceed 100 characters")
    private String fullName;

    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;

    public StudentCreateRequest() {
    }

    public StudentCreateRequest(String studentCode, String fullName, String address) {
        this.studentCode = studentCode;
        this.fullName = fullName;
        this.address = address;
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

