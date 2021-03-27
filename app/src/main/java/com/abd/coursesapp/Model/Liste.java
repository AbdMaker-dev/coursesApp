package com.abd.coursesapp.Model;

import java.util.Date;

public class Liste extends Modele{
    private int id;
    private String libelle;
    private Date createdAt;
    private Double prix;
    private Date editedAt;
    private int userId;

    public Liste() {
    }

    public Liste(String libelle, Date createdAt, Double prix, Date editedAt, int userId) {
        this.createdAt = createdAt;
        this.prix = prix;
        this.editedAt = editedAt;
        this.userId = userId;
        this.libelle = libelle;
    }

    public Liste(int id, String libelle, Date createdAt, Double prix, Date editedAt, int userId) {
        this.id = id;
        this.createdAt = createdAt;
        this.prix = prix;
        this.editedAt = editedAt;
        this.userId = userId;
        this.libelle = libelle;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Date getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(Date editedAt) {
        this.editedAt = editedAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


}
