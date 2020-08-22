package org.pika.my_housing.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLoginDto
{

    public String login;

    public String password;

    public UserLoginDto() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
