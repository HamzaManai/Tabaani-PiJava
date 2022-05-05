/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author HPOMEN-I7-1TR
 */
public class TypeHebergement {
    private int id;
    private String nom_type_hbrg;
    
    public TypeHebergement() {
    }

    public TypeHebergement(String nom_type_hbrg) {
        this.nom_type_hbrg = nom_type_hbrg;
    }

    public TypeHebergement(int id, String nom_type_hbrg) {
        this.id = id;
        this.nom_type_hbrg = nom_type_hbrg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_type_hbrg() {
        return nom_type_hbrg;
    }

    public void setNom_type_hbrg(String nom_type_hbrg) {
        this.nom_type_hbrg = nom_type_hbrg;
    }

    @Override
    public String toString() {
        return nom_type_hbrg ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.nom_type_hbrg);
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
        final TypeHebergement other = (TypeHebergement) obj;
        if (!Objects.equals(this.nom_type_hbrg, other.nom_type_hbrg)) {
            return false;
        }
        return true;
    }

    
}
