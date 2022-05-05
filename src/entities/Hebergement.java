/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author HPOMEN-I7-1TR
 */
public class Hebergement {

    private int id, nbr_place_hbrg, prix_hbrg;
    private String nom_hbrg, adresse_hbrg;
    private LocalDate date_hbrg;
    public TypeHebergement type;
    public Proprietaire propritaire;
    public String img_hbrg;

    public Hebergement() {
    }

    public Hebergement(int id, int nbr_place_hbrg, int prix_hbrg, String nom_hbrg, String adresse_hbrg, LocalDate date_hbrg, String img_hbrg) {
        this.id = id;
        this.nbr_place_hbrg = nbr_place_hbrg;
        this.prix_hbrg = prix_hbrg;
        this.nom_hbrg = nom_hbrg;
        this.adresse_hbrg = adresse_hbrg;
        this.date_hbrg = date_hbrg;
        this.img_hbrg = img_hbrg;
    }

    public Hebergement(int nbr_place_hbrg, int prix_hbrg, String nom_hbrg, String adresse_hbrg, LocalDate date_hbrg, String img_hbrg) {
        this.nbr_place_hbrg = nbr_place_hbrg;
        this.prix_hbrg = prix_hbrg;
        this.nom_hbrg = nom_hbrg;
        this.adresse_hbrg = adresse_hbrg;
        this.date_hbrg = date_hbrg;
        this.img_hbrg = img_hbrg;
    }

    public Hebergement(int nbr_place_hbrg, int prix_hbrg, String nom_hbrg, String adresse_hbrg, LocalDate date_hbrg, Proprietaire propritaire, TypeHebergement type) {
        this.nbr_place_hbrg = nbr_place_hbrg;
        this.prix_hbrg = prix_hbrg;
        this.nom_hbrg = nom_hbrg;
        this.adresse_hbrg = adresse_hbrg;
        this.date_hbrg = date_hbrg;
        this.propritaire = propritaire;
        this.type = type;

    }

    public Hebergement(int nbr_place_hbrg, int prix_hbrg, String nom_hbrg, String adresse_hbrg, LocalDate date_hbrg, Proprietaire propritaire, TypeHebergement type, String img_hbrg) {
        this.nbr_place_hbrg = nbr_place_hbrg;
        this.prix_hbrg = prix_hbrg;
        this.nom_hbrg = nom_hbrg;
        this.adresse_hbrg = adresse_hbrg;
        this.date_hbrg = date_hbrg;
        this.propritaire = propritaire;
        this.type = type;
        this.img_hbrg = img_hbrg;

    }

    public Hebergement(int id, int nbr_place_hbrg, int prix_hbrg, String nom_hbrg, String adresse_hbrg, LocalDate date_hbrg, TypeHebergement type, Proprietaire propritaire, String img_hbrg) {
        this.id = id;
        this.nbr_place_hbrg = nbr_place_hbrg;
        this.prix_hbrg = prix_hbrg;
        this.nom_hbrg = nom_hbrg;
        this.adresse_hbrg = adresse_hbrg;
        this.date_hbrg = date_hbrg;
        this.type = type;
        this.propritaire = propritaire;
        this.img_hbrg = img_hbrg;
    }

    public Hebergement(int nbr_place_hbrg, int prix_hbrg, String nom_hbrg, String adresse_hbrg, LocalDate date_hbrg, TypeHebergement type) {
        this.nbr_place_hbrg = nbr_place_hbrg;
        this.prix_hbrg = prix_hbrg;
        this.nom_hbrg = nom_hbrg;
        this.adresse_hbrg = adresse_hbrg;
        this.date_hbrg = date_hbrg;
        this.type = type;

    }

    public Hebergement(int nbr_place_hbrg, int prix_hbrg, String nom_hbrg, String adresse_hbrg, LocalDate date_hbrg, TypeHebergement type, String img_hbrg) {
        this.nbr_place_hbrg = nbr_place_hbrg;
        this.prix_hbrg = prix_hbrg;
        this.nom_hbrg = nom_hbrg;
        this.adresse_hbrg = adresse_hbrg;
        this.date_hbrg = date_hbrg;
        this.type = type;
        this.img_hbrg = img_hbrg;

    }

    public Hebergement(int id, int nbr_place_hbrg, int prix_hbrg, String nom_hbrg, String adresse_hbrg, LocalDate date_hbrg, TypeHebergement type, String img_hbrg) {
        this.id = id;
        this.nbr_place_hbrg = nbr_place_hbrg;
        this.prix_hbrg = prix_hbrg;
        this.nom_hbrg = nom_hbrg;
        this.adresse_hbrg = adresse_hbrg;
        this.date_hbrg = date_hbrg;
        this.type = type;
        this.img_hbrg = img_hbrg;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbr_place_hbrg() {
        return nbr_place_hbrg;
    }

    public void setNbr_place_hbrg(int nbr_place_hbrg) {
        this.nbr_place_hbrg = nbr_place_hbrg;
    }

    public int getPrix_hbrg() {
        return prix_hbrg;
    }

    public void setPrix_hbrg(int prix_hbrg) {
        this.prix_hbrg = prix_hbrg;
    }

    public String getNom_hbrg() {
        return nom_hbrg;
    }

    public void setNom_hbrg(String nom_hbrg) {
        this.nom_hbrg = nom_hbrg;
    }

    public String getAdresse_hbrg() {
        return adresse_hbrg;
    }

    public void setAdresse_hbrg(String adresse_hbrg) {
        this.adresse_hbrg = adresse_hbrg;
    }

    public LocalDate getDate_hbrg() {
        return date_hbrg;
    }

    public void setDate_hbrg(LocalDate date_hbrg) {
        this.date_hbrg = date_hbrg;
    }

    public TypeHebergement getType() {
        return type;
    }

    public void setType(TypeHebergement type) {
        this.type = type;
    }

    public Proprietaire getPropritaire() {
        return propritaire;
    }

    public void setPropritaire(Proprietaire propritaire) {
        this.propritaire = propritaire;
    }

    public String getImg_hbrg() {
        return img_hbrg;
    }

    public void setImg_hbrg(String img_hbrg) {
        this.img_hbrg = img_hbrg;
    }

    @Override
    public String toString() {
        return "Hebergement{" + "id=" + id + ", nbr_place_hbrg=" + nbr_place_hbrg + ", prix_hbrg=" + prix_hbrg + ", nom_hbrg=" + nom_hbrg + ", adresse_hbrg=" + adresse_hbrg + ", date_hbrg=" + date_hbrg + ", type=" + type + ", propritaire=" + propritaire + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hebergement other = (Hebergement) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
