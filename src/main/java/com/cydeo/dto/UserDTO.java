package com.cydeo.dto;

import com.cydeo.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    //Data Transfer Object. We create Entity/Model and according to them DTOs. At the user interface portion we use DTOs. When we need to operate at Database we transfer the data with DTOs and convert it with Mappers to Entities and use them at our database operations.
    @NotBlank
    @Size(max = 15, min = 2)
    private String firstName;

    @NotBlank
    @Size(max = 15, min = 2)
    private String lastName;

    @NotBlank
    @Email
    private String userName;

    @NotBlank
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,}")
    private String passWord;

    @NotNull
    private String confirmPassWord;

    private boolean enabled;

    @NotBlank
    @Pattern(regexp = "^\\d{10}$")
    private String phone;

    @NotNull
    private RoleDTO role;

    @NotNull
    private Gender gender;

    public String getPassWord() {
        return passWord;
    }

    //We create our own getter and setters. And by using that methods we control the passWord.
    public void setPassWord(String passWord) {
        this.passWord = passWord;
        checkConfirmPassword();
    }

    public String getConfirmPassWord() {
        return confirmPassWord;
    }

    public void setConfirmPassWord(String confirmPassWord) {
        this.confirmPassWord = confirmPassWord;
        checkConfirmPassword();
    }

    private void checkConfirmPassword() {
        if (this.passWord == null || this.confirmPassWord == null) {
            return;
        } else if (!this.passWord.equals(this.confirmPassWord)) {
            this.confirmPassWord = null;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

}
