package exemple;

import java.io.FileNotFoundException;

import film.Film;
import film.Films;
import montage.Montage;

/**
 * Un exemple basique d'impl�mentation de l'interface Film.
 */
public class LaDiagonaleDuFou implements Film {
	private int num = 0;
	private static final int NB_IMAGES = 20;

	@Override
	public int hauteur() {
		return 10;
	}

	@Override
	public int largeur() {
		return hauteur(); // ce sera un carr�
	}

	@Override
	public boolean suivante(char[][] �cran) {
		if (num == NB_IMAGES)
			return false;
		�cran[num % hauteur()][num % hauteur()] = 'a'; // un 'a' se balade sur
														// la diagonale
		++num;
		return true;
	}

	@Override
	public void rembobiner() {
		num = 0;
	}

	/**
	 * La projection (puis la sauvegarde) d'un tel film.
	 */
	public static void main(String[] args) {
		Film f1 = new LaDiagonaleDuFou();
		Film f2 = Montage.encadrer(f1);
		Film film = Montage.coller(f1, f2);
		Films.projeter(film);
		film.rembobiner();
		try {
			Films.sauvegarder(film, "fou.txt");
		} catch (FileNotFoundException e) {
			System.err.println("Le fichier 'fou.txt' n'a pas pu �tre cr��.");
		}
	}
}
