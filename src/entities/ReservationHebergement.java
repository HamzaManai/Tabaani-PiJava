/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author HPOMEN-I7-1TR
 */
public class ReservationHebergement {
    
    int num_resv,id_user;
    Hebergement hebergement;

    public ReservationHebergement(int num_resv, int id_user, Hebergement hebergement) {
        this.num_resv = num_resv;
        this.id_user = id_user;
        this.hebergement = hebergement;
    }

    public ReservationHebergement() {
    }

    public int getNum_resv() {
        return num_resv;
    }

    public void setNum_resv(int num_resv) {
        this.num_resv = num_resv;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Hebergement getHebergement() {
        return hebergement;
    }

    public void setHebergement(Hebergement hebergement) {
        this.hebergement = hebergement;
    }

    @Override
    public String toString() {
        return "ReservationHebergement{" + "num_resv=" + num_resv + ", id_user=" + id_user + ", hebergement=" + hebergement + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.num_resv;
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
        final ReservationHebergement other = (ReservationHebergement) obj;
        if (this.num_resv != other.num_resv) {
            return false;
        }
        return true;
    }
    
    
}
