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
	 * R�p�te un film un nombre de fois donn�
	 * @param f, le film � r�p�ter
	 * @param nb, le nombre de r�p�titions
	 * @return un nouveau film, r�p�tition du film initial
	 */
	public static Film r�p�ter(Film f, int nb) {
		FilmMont� fm = new FilmMont�(f.largeur(), f.hauteur());
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
	 * @param first, le rang de la premi�re image � extraire
	 * @param last, le rang de la derni�re image � extraire
	 * @return un nouveau film  contenant les images de rang first � last
	 */
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

	/**
	 * Encadre un film avec le caract�re choisi
	 * @param f, le film � encadrer
	 * @param c, le caract�re pour l'encadrement
	 * @return un nouveau film, encadr�
	 */
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

	/**
	 * Colle un film � la suite d'un autre
	 * @param f1, le film source
	 * @param f2, le film venant s'ajouter au film source
	 * @return un nouveau film, collage des deux pr�c�dents
	 */
	public static Film coller(Film f1, Film f2) {
		FilmMont� fm = new FilmMont�(Math.max(f1.largeur(), f2.largeur()),
				Math.max(f1.hauteur(), f2.hauteur()));
		fm.ajouterFilm(f1);
		fm.ajouterFilm(f2);
		return fm;
	}

	/**
	 * Incruste les images d'un film dans un autre, au point d'incrustation
	 * sp�cifi�
	 * @param f1, le film source
	 * @param f2, le film � incruster
	 * @param l, coordonn�e x du point d'incrustation
	 * @param h, coordonn�e y du point d'incrustation
	 * @return un nouveau film ainsi g�n�r�
	 */
	public static Film incruster(Film f1, Film f2, int l, int h) {
		FilmMont� fm = new FilmMont�(f1.largeur(), f1.hauteur());
		fm.ajouterFilm(f1);
		fm.incruster(f2, l, h);
		return fm;
	}
}
