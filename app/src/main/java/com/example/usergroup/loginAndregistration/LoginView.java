package com.example.usergroup.loginAndregistration;

public interface LoginView {
    void openHomePage();
    void showToastLoggedIn();
    void showToastException(Exception e);
}