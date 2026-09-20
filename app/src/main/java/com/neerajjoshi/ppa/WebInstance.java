package com.neerajjoshi.ppa;

public class WebInstance {

    private String website_name;
    private String user_name;
    private String pass_word;
    private int id;

    public WebInstance(String website_name, String user_name, String pass_word, int id) {
        this.website_name = website_name;
        this.user_name = user_name;
        this.pass_word = pass_word;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWebsite_name(String website_name) {
        this.website_name = website_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }

    public String getWebsite_name() {
        return website_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPass_word() {
        return pass_word;
    }
}
