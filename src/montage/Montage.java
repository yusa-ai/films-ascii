package montage;

import film.*;

public class Montage {

	private static final int CADRE = 2;

	private Montage() {
	}

	public static Film r�p�ter(Film f, int nb) {
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
		return encadrer(f, '*');
	}
	
	public static Film encadrer(Film f, char c) {
		char[][] �cran = Films.getEcran(f);
		int nbImages = 0;
		// Cr�ation d'un nouveau film avec autant d'images (d'abord vides) que
		// f, aux dimensions �tendues de 2 unit�s pour le cadre
		while (f.suivante(�cran))
			nbImages++; // compte le nombre d'images du film
		f.rembobiner();
		CFilm cf = new CFilm(f.largeur() + CADRE, f.hauteur() + CADRE);
		for (int i = 0; i < nbImages; i++)
			cf.ajouterImage(cf.largeur(), cf.hauteur()); // nv images vides

		cf.encadrer(c);

		cf.incruster(f, 1, 1);
		return cf;
	}

	public static Film coller(Film f1, Film f2) {
		CFilm cf = new CFilm(Math.max(f1.largeur(), f2.largeur()),
				Math.max(f1.hauteur(), f2.hauteur()));
		cf.ajouterFilm(f1);
		cf.ajouterFilm(f2);
		return cf;
	}
	
	public static Film incruster(Film f1, Film f2, int l, int h) {
		CFilm cf = new CFilm(f1.largeur(), f1.hauteur());
		cf.ajouterFilm(f1);
		cf.incruster(f2, l, h);
		return cf;
	}
}
