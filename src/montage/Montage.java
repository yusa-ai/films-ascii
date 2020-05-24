package montage;

import film.*;

/**
 * Montage de films
 * @author Ryan Malonzo
 *
 */
public class Montage {

	private static final int CADRE = 2;

	private Montage() {
	}

	/**
	 * Répète un film un nombre de fois donné
	 * @param f, le film à répéter
	 * @param nb, le nombre de répétitions
	 * @return un nouveau film, répétition du film initial
	 */
	public static Film répéter(Film f, int nb) {
		FilmMonté fm = new FilmMonté(f.largeur(), f.hauteur());
		if (nb <= 0)
			return fm; // film vide
		for (int i = 0; i <= nb; i++) {
			fm.ajouterFilm(f);
		}
		return fm;
	}

	/**
	 * Extrait les images d'un film
	 * @param f, le film dont on souhaite extraire les images
	 * @param first, le rang de la première image à extraire
	 * @param last, le rang de la dernière image à extraire
	 * @return un nouveau film  contenant les images de rang first à last
	 */
	public static Film extraire(Film f, int first, int last) {
		FilmMonté fm = new FilmMonté(f.largeur(), f.hauteur());
		if (last < first)
			return fm;
		fm.ajouterFilm(f);
		fm.extraire(first, last);
		return fm;
	}

	public static Film encadrer(Film f) {
		return encadrer(f, '*');
	}

	/**
	 * Encadre un film avec le caractère choisi
	 * @param f, le film à encadrer
	 * @param c, le caractère pour l'encadrement
	 * @return un nouveau film, encadré
	 */
	public static Film encadrer(Film f, char c) {
		char[][] écran = Films.getEcran(f);
		int nbImages = 0;
		// Création d'un nouveau film avec autant d'images (d'abord vides) que
		// f, aux dimensions étendues de 2 unités pour le cadre
		while (f.suivante(écran))
			nbImages++; // compte le nombre d'images du film
		f.rembobiner();
		FilmMonté fm = new FilmMonté(f.largeur() + CADRE, f.hauteur() + CADRE);
		for (int i = 0; i < nbImages; i++)
			fm.ajouterImage(fm.largeur(), fm.hauteur()); // nv images vides

		fm.encadrer(c);

		fm.incruster(f, 1, 1);
		return fm;
	}

	/**
	 * Colle un film à la suite d'un autre
	 * @param f1, le film source
	 * @param f2, le film venant s'ajouter au film source
	 * @return un nouveau film, collage des deux précédents
	 */
	public static Film coller(Film f1, Film f2) {
		FilmMonté fm = new FilmMonté(Math.max(f1.largeur(), f2.largeur()),
				Math.max(f1.hauteur(), f2.hauteur()));
		fm.ajouterFilm(f1);
		fm.ajouterFilm(f2);
		return fm;
	}

	/**
	 * Incruste les images d'un film dans un autre, au point d'incrustation
	 * spécifié
	 * @param f1, le film source
	 * @param f2, le film à incruster
	 * @param l, coordonnée x du point d'incrustation
	 * @param h, coordonnée y du point d'incrustation
	 * @return un nouveau film ainsi généré
	 */
	public static Film incruster(Film f1, Film f2, int l, int h) {
		FilmMonté fm = new FilmMonté(f1.largeur(), f1.hauteur());
		fm.ajouterFilm(f1);
		fm.incruster(f2, l, h);
		return fm;
	}
}
