package com.zxw.model;

/**
 * Created with IntelliJ IDEA.
 * User: ZXW
 * Date: 13-6-19
 * Time: 下午1:37
 * To change this template use File | Settings | File Templates.
 */
public class User {

    private Integer id;
    private String username;
    private String password;
    private String key;
    private String secret;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
