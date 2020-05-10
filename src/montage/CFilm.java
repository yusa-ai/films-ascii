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
