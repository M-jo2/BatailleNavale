package javaapplication2;

public class Grille {
    private Bateau[] listeBateaux;
    char[] grilleBateaux = new char[100];

    public Grille() {
        listeBateaux = new Bateau[5];
        listeBateaux[0] = new Bateau(5, "Porte-Avion", 'P');
        listeBateaux[1] = new Bateau(4, "Black Pearl", 'B');
        listeBateaux[2] = new Bateau(3, "Titanic", 'T');
        listeBateaux[3] = new Bateau(3, "Coule-moi", 'C');
        listeBateaux[4] = new Bateau(2, "Nautilus", 'N');
        PlacerBateaux();
        for (Bateau boat : listeBateaux) boat.setModeJeu();
    }

    private void PlacerBateaux() {
        int[] position;
        for (Bateau bateau : listeBateaux) {
            do {
                position = bateau.positionnerBateau();
            } while (! estBienPlace(position));
            
            for (int x : position) {
                grilleBateaux[x] = bateau.getAcronymeBateau();
            }
        }
    }

    private boolean estBienPlace(int[] positions) {
        for (int x : positions) {
            if (grilleBateaux[x] != 0 ||
                    (x % 10 < 9 && grilleBateaux[x + 1]  != 0) ||
                    (x % 10 > 0 && grilleBateaux[x - 1]  != 0) ||
                    (x < 90     && grilleBateaux[x + 10] != 0) ||
                    (x > 9      && grilleBateaux[x - 10] != 0)) {
                return false;
            }
        }
        return true;
    }

    public void afficher() {
        System.out.print("\n    0 1 2 3 4 5 6 7 8 9");
        for (int x = 0; x < 100; x++) {
            if (x % 10 == 0)
                System.out.printf("%n%1c : ", x / 10 + 'A');
            System.out.printf("%1c ", (grilleBateaux[x] > 0) ? grilleBateaux[x] : ' ');
        }
        System.out.println("\n");
        for (Bateau bateau : listeBateaux) {
            System.out.println(bateau.getAcronymeBateau() + " = " + bateau.getNomBateau());
        }
    }

    public boolean receptionTir(int positionTir) {
        for (Bateau tempBateau : listeBateaux) {
            if (tempBateau.estTouche(positionTir)) {
                grilleBateaux[positionTir] = 'x';
                return true;
            }
        }
        grilleBateaux[positionTir] = '-';
        return false;
    }

    public boolean tousCoules() {
        for (Bateau tempBateau : listeBateaux) {
            if (!tempBateau.estCoule()) {
                return false;
            }
        }
        return true;
    }
}
