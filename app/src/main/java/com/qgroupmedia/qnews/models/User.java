package com.qgroupmedia.qnews.models;

public class User {

    private String _id;
    private String firstName;
    private String lastName;
    private String imagePath;

    public User(String uid, String ufirstName, String ulastName, String uimagePath) {

        _id = uid;
        firstName = ufirstName;
        lastName = ulastName;
        imagePath = uimagePath;
    }


    public String getId() {
        return _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImagePath() {
        return imagePath;
    }


}
