package montage;

import film.*;

public class Montage {

	private static final int CADRE = 2;

	private Montage() {
	}

	public static Film répéter(Film f, int nb) {
		CFilm cf = new CFilm(f.largeur(), f.hauteur());
		if (nb <= 0)
			return cf; // film vide
		for (int i = 0; i <= nb; i++) {
			cf.ajouterFilm(f);
		}
		return cf;
	}

	public static Film extraire(Film f, int first, int last) {
		CFilm cf = new CFilm(f.largeur(), f.hauteur());
		if (last < first)
			return cf;
		cf.ajouterFilm(f);
		cf.extraire(first, last);
		return cf;
	}

	public static Film encadrer(Film f) {
		char[][] écran = Films.getEcran(f);
		int nbImages = 0;

		// Création d'un nouveau film avec autant d'images (d'abord vides) que
		// f, avec dimensions étendues de 2 unités pour le cadre
		while (f.suivante(écran))
			nbImages++; // compte le nombre d'images du film
		f.rembobiner();
		CFilm cf = new CFilm(f.largeur() + CADRE, f.hauteur() + CADRE);
		for (int i = 0; i < nbImages; i++)
			cf.ajouterImage(cf.largeur(), cf.hauteur());

		cf.encadrer('*');

		// TODO méthode de collage à refactoriser pour l'attendu n°5 ?
		// Réécriture des images originales du film, à l'intérieur de leur cadre
		Film tmp = extraire(f, 0, nbImages - 1); // copie intégrale
		nbImages = 0;
		while (tmp.suivante(écran)) {
			for (int j = 0; j < tmp.largeur(); j++)
				for (int k = 0; k < tmp.hauteur(); k++)
					cf.getImage(nbImages)[j + 1][k + 1] = écran[j][k];
			nbImages++;
		}
		return cf;
	}

	public static Film coller(Film f1, Film f2) {
		CFilm cf = new CFilm(Math.max(f1.largeur(), f2.largeur()),
				Math.max(f1.hauteur(), f2.hauteur()));
		cf.ajouterFilm(f1);
		cf.ajouterFilm(f2);
		return cf;
	}
}
