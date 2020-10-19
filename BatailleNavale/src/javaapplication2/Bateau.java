package javaapplication2;

import java.util.Random;

public class Bateau {
    private static int[] valeurDirection = {-10, -1, 1, 10};
    private static Random r = new Random();

    private int tailleBateau;
    private String nomBateau;
    private int[] positionsBateau;
    private char acronymeBateau;
    private boolean[] positionsTouche;
    private boolean modeJeu;

    public Bateau(int taille, String nom, char acronyme) {
        tailleBateau = taille;
        nomBateau = nom;
        acronymeBateau = acronyme;
        positionsBateau = new int[taille];
        positionsTouche = new boolean[taille];
    }

    public String getNomBateau() {
        return nomBateau;
    }

    public char getAcronymeBateau() {
        return acronymeBateau;
    }

    public boolean estCoule() {
        for (boolean estTouche : positionsTouche) {
            if (!estTouche) {
                return false;
            }
        }
        return true;
    }

    public int[] positionnerBateau() {
        if (! modeJeu) {
            int position, direction;
            do {
                position = r.nextInt(98) + 1;
            } while (position == 9 || position == 90);
            direction = valeurDirection[r.nextInt(4)];
            //    (haut)   -10
            //  (gauche) -1   +1 (droite)
            //     (bas)   +10
            if (position < 9)
                direction = 10;
            else if (position > 90)
                direction = -10;
            else if (position % 10 == 0)
                direction = 1;
            else if (position % 10 == 9)
                direction = -1;
            else if (position % 10 > 10 - tailleBateau && direction == 1)
                direction = -1;
            else if (position % 10 < tailleBateau - 1 && direction == -1)
                direction = 1;
            else if (position < (tailleBateau - 1) * 10 && direction == -10)
                direction = 10;
            else if (position > 100 - (tailleBateau * 10) && direction == 10)
                direction = -10;
            for (int x = position, y = 0; x != position + tailleBateau * direction; positionsBateau[y++] = x, x += direction);
        }
        return positionsBateau;
    }

    public boolean estTouche(int positionTir) {
        int x = 0;
        if (modeJeu) {
            for (int position : positionsBateau) {
                if (position == positionTir) {
                    positionsTouche[x] = true;
                    return true;
                }
                x++;
            }
        }
        return false;
    }

    public void setModeJeu() {
        modeJeu = true;
    }
}
