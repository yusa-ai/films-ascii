package montage;

import film.*;

public class Montage {

	private static final int CADRE = 2;

	private Montage() {
	}

	public static Film r�p�ter(Film f, int nb) {
		FilmMont� fm = new FilmMont�(f.largeur(), f.hauteur());
		if (nb <= 0)
			return fm; // film vide
		for (int i = 0; i <= nb; i++) {
			fm.ajouterFilm(f);
		}
		return fm;
	}

	public static Film extraire(Film f, int first, int last) {
		FilmMont� fm = new FilmMont�(f.largeur(), f.hauteur());
		if (last < first)
			return fm;
		fm.ajouterFilm(f);
		fm.extraire(first, last);
		return fm;
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
		FilmMont� fm = new FilmMont�(f.largeur() + CADRE, f.hauteur() + CADRE);
		for (int i = 0; i < nbImages; i++)
			fm.ajouterImage(fm.largeur(), fm.hauteur()); // nv images vides

		fm.encadrer(c);

		fm.incruster(f, 1, 1);
		return fm;
	}

	public static Film coller(Film f1, Film f2) {
		FilmMont� fm = new FilmMont�(Math.max(f1.largeur(), f2.largeur()),
				Math.max(f1.hauteur(), f2.hauteur()));
		fm.ajouterFilm(f1);
		fm.ajouterFilm(f2);
		return fm;
	}

	public static Film incruster(Film f1, Film f2, int l, int h) {
		FilmMont� fm = new FilmMont�(f1.largeur(), f1.hauteur());
		fm.ajouterFilm(f1);
		fm.incruster(f2, l, h);
		return fm;
	}
}
