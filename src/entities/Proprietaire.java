/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Image;
import java.util.Objects;

/**
 *
 * @author HPOMEN-I7-1TR
 */
public class Proprietaire {

    private int id, num_tlf_prop;
    public String nom_prop, prenom_prop, email_prop;
    public String img_prop;

    public Proprietaire() {
    }

    public Proprietaire(int num_tlf_prop, String nom_prop, String prenom_prop, String email_prop) {
        this.num_tlf_prop = num_tlf_prop;
        this.nom_prop = nom_prop;
        this.prenom_prop = prenom_prop;
        this.email_prop = email_prop;
    }

    public Proprietaire(int id, int num_tlf_prop, String nom_prop, String prenom_prop, String email_prop) {
        this.id = id;
        this.num_tlf_prop = num_tlf_prop;
        this.nom_prop = nom_prop;
        this.prenom_prop = prenom_prop;
        this.email_prop = email_prop;
    }

    public Proprietaire(int num_tlf_prop, String nom_prop, String prenom_prop, String email_pop, String img_prop) {
        this.num_tlf_prop = num_tlf_prop;
        this.nom_prop = nom_prop;
        this.prenom_prop = prenom_prop;
        this.email_prop = email_prop;
        this.img_prop = img_prop;
    }

    public Proprietaire(int aInt, String nom_prop, String prenom_prop) {
        this.id = id;
        this.nom_prop = nom_prop;
        this.prenom_prop = prenom_prop;
    }

    public Proprietaire(int id, int num_tlf_prop, String nom_prop, String prenom_prop, String email_prop, String img_prop) {
        this.id = id;
        this.num_tlf_prop = num_tlf_prop;
        this.nom_prop = nom_prop;
        this.prenom_prop = prenom_prop;
        this.email_prop = email_prop;
        this.img_prop = img_prop;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_tlf_prop() {
        return num_tlf_prop;
    }

    public void setNum_tlf_prop(int num_tlf_prop) {
        this.num_tlf_prop = num_tlf_prop;
    }

    public String getNom_prop() {
        return nom_prop;
    }

    public void setNom_prop(String nom_prop) {
        this.nom_prop = nom_prop;
    }

    public String getPrenom_prop() {
        return prenom_prop;
    }

    public void setPrenom_prop(String prenom_prop) {
        this.prenom_prop = prenom_prop;
    }

    public String getEmail_prop() {
        return email_prop;
    }

    public void setEmail_pop(String email_pop) {
        this.email_prop = email_pop;
    }

    public String getImg_prop() {
        return img_prop;
    }

    public void setImg_prop(String img_prop) {
        this.img_prop = img_prop;
    }

    @Override
    public String toString() {
        return nom_prop + " " + prenom_prop;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.id;
        hash = 31 * hash + this.num_tlf_prop;
        hash = 31 * hash + Objects.hashCode(this.email_prop);
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
        final Proprietaire other = (Proprietaire) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.num_tlf_prop != other.num_tlf_prop) {
            return false;
        }
        if (!Objects.equals(this.email_prop, other.email_prop)) {
            return false;
        }
        return true;
    }

}
