package javaapplication2;

import java.util.Random;

public class Joueur {
    private static Random r = new Random();

    private String nomJoueur;
    private int niveauJoueur;
    private Grille grilleDefense;
    private int[] grilleAttaque;

    public Joueur(String nom, int niveau) {
        nomJoueur = nom;
        niveauJoueur = niveau;
        grilleDefense = new Grille();
        System.out.printf("%nJoueur = %s, niveau = %d%n", nom, niveau);
        grilleDefense.afficher();
        System.out.println("--------------------------------------------");
        grilleAttaque = new int[100];
    }

    public void afficheGrilleDefense() {
        System.out.println("--------------------------------------------");
        System.out.println("Grille des bateaux");
        grilleDefense.afficher();
        System.out.println("x = Touché\n- = Manqué");
    }

    public void afficheGrilleAttaque() {
        char[] charAttaque = {' ', 'X', '-'};
        System.out.println("--------------------------------------------");
        System.out.println("Grille d'attaque");
        System.out.print("\n    0 1 2 3 4 5 6 7 8 9");
        for (int x = 0; x < 100; x++) {
            if (x % 10 == 0)
                System.out.printf("%n%1c : ", x / 10 + 'A');
            System.out.printf("%1c ", charAttaque[grilleAttaque[x]]);
        }
        System.out.println("\n\nx = touché, - = à l'eau");
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    // Version 1 & 2
    public int tir() {
        int x;
        if (niveauJoueur == 0) {
            x = r.nextInt(100);
        } else {
            do {
                x = r.nextInt(98) + 1;
            } while (x == 9 || x == 90 || grilleAttaque[x] != 0);
        }
        return x;
    }

    public boolean recoitTir(int positionTir) {
        return grilleDefense.receptionTir(positionTir);
    }

    // Version 1
    public void tir(Joueur adversaire) {
        int posTir = tir();
        grilleAttaque[posTir] = adversaire.recoitTir(posTir) ? 1 : 2;
    }

    public boolean asPerdu() {
        return grilleDefense.tousCoules();
    }

    // Version 2
    public void encoderTir(int positionTir, boolean estTouche) {
        grilleAttaque[positionTir] = estTouche ? 1 : 2;
    }
}
