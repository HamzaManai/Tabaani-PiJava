/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entities.Hebergement;
import entities.Proprietaire;
import entities.TypeHebergement;
import java.sql.Date;
import java.util.Scanner;
import services.ServiceHebergement;
import services.ServiceProprietaire;
import services.ServiceTypeHebergement;
import utils.DataSource;

/**
 *
 * @author HPOMEN-I7-1TR
 */
public class MainClass {

    private static Scanner scanner;
    private static String nom_type, nom_prop, prenom_prop, email_prop;
    private static int id, num_tlf_prop;
    private static TypeHebergement t;
    private static Proprietaire p;
    
    public static void main(String[] args) {
/*
        //DataSource Mc = new DataSource() ;
        ServiceTypeHebergement sth = new ServiceTypeHebergement();
        ServiceProprietaire sp = new ServiceProprietaire();
        ServiceHebergement sh = new ServiceHebergement();
        
        TypeHebergement test = new TypeHebergement ("test");
        Proprietaire ptest = new Proprietaire (12345672,"test","test","test");
        sth.ajouter(test);
        sp.ajouter(ptest);
        System.out.println(sh.getAll());
        System.out.println(sh.getAll());

        
        Hebergement htest = new Hebergement (5,10,"test","test",Date.valueOf("1998-11-29"),ptest,test);
        
        System.out.println(sh.getAll());
         sh.ajouter(htest);
        System.out.println(sh.getAll());
        
      //  System.out.println(sp.getAll());

        // TypeHebergement tp= new TypeHebergement ();
        scanner = new Scanner(System.in);

        boolean quitMenu = true;
        while (quitMenu) {
            System.out.println("\n");
            System.out.println("********************************Menu Principale***********************************");
            System.out.println("1-Gestion de Type Hebergements \n"
                    +"2-Gestion de Proprietaires \n"
                    +"3-Quitter \n");
            System.out.println("********************************************************************************");
            System.out.println("\n");
            System.out.println("donner votre choix");
            int choixMenu = scanner.nextInt();

            switch (choixMenu) {
                case 1:
                    boolean quit = true;
                    while (quit) {
                        System.out.println("\n");
                        System.out.println(
                                "********************************Menu Type Hebergement***********************************");
                        System.out.println("1-Ajouter un type d'hebergement \n"
                                + "2-Supprimer un type d'hebergement\n"
                                + "3-Modifier un type d'hebergement\n"
                                + "4-Lister type d'hebergement \n"
                                + "5-Quitter Menu Type Hebergement \n"
                        );
                        System.out.println(
                                "********************************************************************************");
                        System.out.println("\n");
                        System.out.println("donner votre choix");
                        int choix = scanner.nextInt();
                        switch (choix) {
                            case 1:

                                System.out.println("\n");
                                System.out.println("\nDonner le nom de type hebergement");
                                nom_type = scanner.next();
                                t = new TypeHebergement(nom_type);
                                sth.ajouter(t);
                                break;

                            case 2:
                                System.out.println("\n");
                                System.out.println("\n Donner l'id du type Heber");
                                id = scanner.nextInt();
                                sth.supprimer(id);
                                break;
                            case 3:
                                TypeHebergement t = new TypeHebergement();
                                System.out.println("\n Donner l'id du type hebergement");
                                id = scanner.nextInt();
                                //System.out.println(t.toString());
                                System.out.println("\n update du type");
                                System.out.println("\nDonner le nom de type hebergement");
                                nom_type = scanner.next();
                                t = new TypeHebergement(id, nom_type);
                                sth.modifier(t);
                                System.out.println("\n Apr�s modification");
                                System.out.println(t.toString());
                                break;

                            case 4:
                                System.out.println(sth.getAll());
                                break;
                            case 5:
                                quit = false;
                                break;
                        }
                    }
                    break;
                case 2:
                    boolean quit2 = true;
                    while (quit2) {
                        System.out.println("\n");
                        System.out.println(
                                "********************************Menu Propreitaire***********************************");
                        System.out.println("1-Ajouter un Proprietaire \n"
                                + "2-Supprimer un Proprietaire\n"
                                + "3-Modifier un Proprietaire\n"
                                + "4-Lister Proprietaires \n"
                                + "5-Quitter Menu Proprietaire \n"
                        );
                        System.out.println(
                                "********************************************************************************");
                        System.out.println("\n");
                        System.out.println("donner votre choix");
                        int choix = scanner.nextInt();
                        switch (choix) {
                            case 1:

                                System.out.println("\n");
                                System.out.println("\nDonner le nom de Proprietaire");
                                nom_prop = scanner.next();
                                System.out.println("\nDonner le prenom de Proprietaire");
                                prenom_prop = scanner.next();
                                System.out.println("\nDonner l'email");
                                email_prop = scanner.next();
                                System.out.println("\nDonner le num tlf de Proprietaire");
                                num_tlf_prop = scanner.nextInt();
                                p = new Proprietaire(num_tlf_prop,nom_prop,prenom_prop,email_prop);
                                sp.ajouter(p);
                                break;

                            case 2:
                                System.out.println("\n");
                                System.out.println("\n Donner l'id du proprietaire");
                                id = scanner.nextInt();
                                sp.supprimer(id);
                                break;
                            case 3:
                                Proprietaire p = new Proprietaire();
                                System.out.println("\n Donner l'id du Propeitaire");
                                id = scanner.nextInt();
                                //System.out.println(t.toString());
                                System.out.println("\n update du prop");
                                System.out.println("\nDonner le nom de Proprietaire");
                                nom_prop = scanner.next();
                                System.out.println("\nDonner le prenom de Proprietaire");
                                prenom_prop = scanner.next();
                                System.out.println("\nDonner l'email");
                                email_prop = scanner.next();
                                System.out.println("\nDonner le num tlf de Proprietaire");
                                num_tlf_prop = scanner.nextInt();
                                p = new Proprietaire(id,num_tlf_prop,nom_prop,prenom_prop,email_prop);
                                sp.modifier(p);
                                System.out.println("\n Apr�s modification");
                                System.out.println(p.toString());
                                break;

                            case 4:
                                System.out.println(sp.getAll());
                                break;
                            case 5:
                                quit2 = false;
                                break;
                        }
                    }
                    break;
                case 3:
                    quitMenu = false;
                    break;
            }
        }

*/

    }
}
