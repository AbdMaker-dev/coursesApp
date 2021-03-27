package com.abd.coursesapp.Model;

public class User extends Modele{

    private int id;
    private String nom;
    private String prenom;
    private String tel;
    private String login;
    private String password;

    public User(String nom, String prenom, String tel, String login, String password)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.login = login;
        this.password = password;
    }

    public User(int id, String nom, String prenom, String tel, String login, String password)
    {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.login = login;
        this.password = password;
    }

    public User(){}


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

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNom()
    {
        return this.nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return this.prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public String getTel()
    {
        return this.tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

}

