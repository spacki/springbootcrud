package com.ge.hc.emrad.xer.domain;

/**
 * Created by karstenspakowski on 31/03/17.
 */
public class CpacsUser {

    int id;

    private String userName;

    private String password;

    private int active;

    private String firstName;

    private String middleName;

    private String lastName;

    private String decryptPassword;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDecryptPassword() {
        return decryptPassword;
    }

    public void setDecryptPassword(String decryptPassword) {
        this.decryptPassword = decryptPassword;
    }

    @Override
    public String toString() {
        return "CpacsUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", decryptPassword='" + decryptPassword + '\'' +
                '}';
    }

}
