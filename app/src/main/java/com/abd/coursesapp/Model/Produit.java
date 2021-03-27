package com.abd.coursesapp.Model;

public class Produit extends Modele{

    private int id;
    private String libelle;
    private String photo;
    private int quantiter;
    private Double montant;
    private boolean accomplis;
    private  int listId;


    public Produit() {
    }

    public Produit(String libelle, String photo, int quantiter, Double montant, boolean accomplis, int listId) {
        this.libelle = libelle;
        this.photo = photo;
        this.quantiter = quantiter;
        this.montant = montant;
        this.accomplis = accomplis;
        this.listId = listId;
    }

    public Produit(int id, String libelle, String photo, int quantiter, Double montant, boolean accomplis, int listId) {
        this.id = id;
        this.libelle = libelle;
        this.photo = photo;
        this.quantiter = quantiter;
        this.montant = montant;
        this.accomplis = accomplis;
        this.listId = listId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getQuantiter() {
        return quantiter;
    }

    public void setQuantiter(int quantiter) {
        this.quantiter = quantiter;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public boolean isAccomplis() {
        return accomplis;
    }

    public void setAccomplis(boolean accomplis) {
        this.accomplis = accomplis;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }
}
