package montage;

import java.util.List;
import java.util.ArrayList;

import film.Film;
import film.Films;

/**
 * Impl�mentation de Film utile au Montage
 * @author Ryan Malonzo
 *
 */
public class FilmMont� implements Film {

	private int largeur;
	private int hauteur;
	private int curseur;
	private List<char[][]> images;

	public FilmMont�(int l, int h) {
		largeur = l;
		hauteur = h;
		curseur = 0;
		images = new ArrayList<>();
	}

	@Override
	public int hauteur() {
		return hauteur;
	}

	@Override
	public int largeur() {
		return largeur;
	}

	@Override
	public boolean suivante(char[][] �cran) {
		if (curseur == images.size())
			return false;
		for (int i = 0; i < largeur; i++)
			for (int j = 0; j < hauteur; j++)
				�cran[i][j] = images.get(curseur)[i][j];
		curseur++;
		return true;
	}

	@Override
	public void rembobiner() {
		curseur = 0;
	}

	/**
	 * Ajoute les images d'un film f aux images du film, dans la liste
	 * @param f, le film dont il faut ajouter les images
	 */
	public void ajouterFilm(Film f) {
		char[][] �cran = Films.getEcran(f);
		while (f.suivante(�cran)) {
			ajouterImage(�cran);
			Films.effacer(�cran);
		}
		f.rembobiner();
	}

	/**
	 * Initialise une nouvelle image vide et l'ajoute dans la liste
	 * @param largeur, largeur de l'image
	 * @param hauteur, hauteur de l'image
	 */
	public void ajouterImage(int largeur, int hauteur) {
		images.add(new char[largeur][hauteur]);
	}

	/**
	 * Ajoute une image � la liste d'images
	 * @param img, l'image � ajouter
	 */
	public void ajouterImage(char[][] img) {
		char[][] image = new char[largeur][hauteur];
		for (int i = 0; i < img.length; i++)
			for (int j = 0; j < img[0].length; j++)
				image[i][j] = img[i][j];
		images.add(image);
	}

	/**
	 * Extrait les images de rang first � last et remplace la liste courante
	 * par ces images
	 * @param first, rang de la premi�re image
	 * @param last, rang de la derni�re image
	 */
	public void extraire(int first, int last) {
		FilmMont� tmp = new FilmMont�(largeur, hauteur);
		for (int i = 0; i < images.size(); i++) {
			if (i >= first && i <= last)
				tmp.ajouterImage(images.get(i));
		}
		images = tmp.images;
	}

	/**
	 * Encadre les images du film par le caract�re sp�cifi�
	 * @param c, le caract�re d'encadrement
	 */
	public void encadrer(char c) {
		for (char[][] image : images) {
			for (int i = 0; i < largeur; i++)
				image[0][i] = image[hauteur - 1][i] = c;
			for (int i = 0; i < hauteur; i++)
				image[i][0] = image[i][largeur - 1] = c;
		}
	}

	/**
	 * Incruste un film au point d'incrustation sp�cifi�
	 * @param f, le film � incruster
	 * @param lar, coordonn�e x du point d'incrustation
	 * @param hau, coordonn�e y du point d'incrustation
	 */
	public void incruster(Film f, int lar, int hau) {
		char[][] �cran = Films.getEcran(f);
		int nbImages = 0;
		while (f.suivante(�cran))
			nbImages++; // compte le nombre d'images du film
		f.rembobiner();
		Film tmp = Montage.extraire(f, 0, nbImages - 1); // copie int�grale
		nbImages = 0;
		while (tmp.suivante(�cran) && nbImages < images.size()) {
			for (int j = 0; j < tmp.largeur(); j++)
				for (int k = 0; k < tmp.hauteur(); k++)
					if (j + lar < largeur && k + hau < hauteur)
						images.get(nbImages)[j + lar][k + hau] = �cran[j][k];
			nbImages++;
		}
	}

}
