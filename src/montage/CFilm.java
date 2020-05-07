package montage;

import java.util.List;
import java.util.ArrayList;

import film.Film;
import film.Films;

public class CFilm implements Film {

	private int largeur, hauteur, curseur;
	private List<char[][]> images;
	
	public CFilm(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.curseur = 0;
		images = new ArrayList<char[][]>();
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

	public void ajouterFilm(Film f) {
		char[][] �cran = Films.getEcran(f);
		while (f.suivante(�cran)) {
			ajouterImage(�cran);
			Films.effacer(�cran);
		}
		f.rembobiner();
	}

	// initialiser nv images vides
	public void ajouterImage(int largeur, int hauteur) {
		images.add(new char[largeur][hauteur]);
	}
	
	public void ajouterImage(char[][] �cran) {
		char[][] image = new char[largeur][hauteur];
		for (int i = 0; i < �cran.length; i++)
			for (int j = 0; j < �cran[0].length; j++)
				image[i][j] = �cran[i][j];
		images.add(image);
	}

	public void extraire(int first, int last) {
		CFilm tmp = new CFilm(largeur, hauteur);
		for (int i = 0; i < images.size(); i++) {
			if (i >= first && i <= last)
				tmp.ajouterImage(images.get(i));
		}
		images = tmp.images;
	}

	public void encadrer(char c) {
		for (char[][] image : images) {
			for (int i = 0; i < largeur; i++)
				image[0][i] = image[hauteur - 1][i] = c;
			for (int i = 0; i < hauteur; i++)
				image[i][0] = image[i][largeur - 1] = c;
		}
	}

	// TODO ceci doit d�gager
	public char[][] getImage(int i) {
		return images.get(i);
	}

}
