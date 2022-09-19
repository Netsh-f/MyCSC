package com.buaa.data;

public class User {
    private String id;
    private String firstName;//名
    private String lastName;//姓
    private String name;//姓名
    private String emailAddress;
    private String password;

    public User(String id, String firstName, String lastName, String emailAddress, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;

        this.name = this.lastName + this.firstName;
    }

    public boolean isIdLegal(String id){
        //19375030 19375168 BY2215201 SY2021118
        (\b[B,S]Y\d{7}\b)|\b((1[7-9])|(2[0-2])((0[1-9])|([1-3][0-9])|4[0-3])[1-6]\d{3})\b|(\b\d{5}\b)
        boolean ret = id.matches("")
    }
}
