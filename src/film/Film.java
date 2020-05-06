package film;

/**
 * Interface devant �tre respect�e par tout film.
 * 
 * Attention, pour qu'un film puisse �tre projet� ou sauvergard� (voir la classe
 * film.Outils), il doit �tre compos� d'un nombre fini d'images. En cons�quence,
 * la m�thode suivante() doit n�cessairement retourner false au bout d'un nombre
 * fini d'appels.
 */
public interface Film {
	/**
	 * Indique la hauteur des images de ce film (en nombre de caract�res).
	 * 
	 * @return Hauteur minimale de l'�cran pour pouvoir afficher les images de
	 *         ce film.
	 */
	int hauteur();

	/**
	 * Indique la largeur des images de ce film (en nombre de caract�res).
	 * 
	 * @return largeur minimale de l'�cran pour pouvoir afficher les images de
	 *         ce film.
	 */
	int largeur();

	/**
	 * Obtenir l'image suivante (s'il y en a une).
	 * 
	 * @param �cran
	 *            L'�cran o� afficher l'image
	 * @return vrai Si l'image suivante a �t� affich�e sur l'�cran et faux si le
	 *         film est termin�
	 */
	boolean suivante(char[][] �cran);

	/**
	 * Rembobine le film en permettant de rejouer le film dans sa totalit� (via
	 * des appels successifs � la m�thode suivante()).
	 */
	void rembobiner();
}
