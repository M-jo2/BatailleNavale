package javaapplication2;

public class Jeu {
    public static void main(String[] args) {
        
        Joueur []joueurs = new Joueur[2];
        int joueurTireur = 1, joueurReceveur = 0, joueurTemp;
        int version = 1; // Version du type de code à utiliser

        joueurs[0] = new Joueur("Joueur 1", 1);
        joueurs[1] = new Joueur("Joueur 2", 1);

        do {
            // Version 1
            if (version == 1) {
                joueurTireur = ++joueurTireur % 2;
                joueurReceveur = ++joueurReceveur % 2;
                joueurs[joueurTireur].tir(joueurs[joueurReceveur]);
                
            } else if(version == 2){
             // Version 2
                joueurTemp = joueurTireur;
                joueurTireur = joueurReceveur;
                joueurReceveur = joueurTemp;
                int tir = joueurs[joueurTireur].tir();
                boolean reponse = joueurs[joueurReceveur].recoitTir(tir);
                joueurs[joueurTireur].encoderTir(tir, reponse);
            }
        } while (!(joueurs[0].asPerdu() || joueurs[1].asPerdu()));
        
        System.out.println("Le gagnant est " + joueurs[joueurTireur].getNomJoueur());
        joueurs[joueurTireur].afficheGrilleAttaque();
        joueurs[joueurTireur].afficheGrilleDefense();
    }
}
