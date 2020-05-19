package montage;

import static org.junit.Assert.*;

import org.junit.Test;

import exemple.LaDiagonaleDuFou;
import film.Film;
import film.Films;

public class MontageTest {

	@Test
	public void testRépéter() {
		Film f = new LaDiagonaleDuFou();
		Film f2 = Montage.répéter(f, 0);
		char[][] écran = Films.getEcran(f2);
		assertFalse(f2.suivante(écran));
		
	}

	@Test
	public void testExtraire() {
		Film f = new LaDiagonaleDuFou();
		Film f2 = Montage.extraire(f, 2, 1);
		char[][] écran = Films.getEcran(f2);
		assertFalse(f2.suivante(écran));
	}

	@Test
	public void testEncadrerFilm() {
		Film f = new LaDiagonaleDuFou();
		Film f2 = Montage.encadrer(f);
		assertEquals(f2.largeur(), f.largeur() + 2);
		assertEquals(f2.hauteur(), f.hauteur() + 2);
	}

	@Test
	public void testColler() {
		Film f = new LaDiagonaleDuFou();
		Film f2 = Montage.encadrer(f);
		Film f3 = Montage.coller(f, f2);
		assertEquals(f3.largeur(), f2.largeur());
		assertEquals(f3.hauteur(), f3.largeur());
	}

	@Test
	public void testIncruster() {
		Film f = new LaDiagonaleDuFou();
		Film f2 = Montage.répéter(f, 1);
		Film f3 = Montage.incruster(f, f2, 1, 1);
		
		char[][] écran = Films.getEcran(f);
		int nbImages = 0;
		while (f3.suivante(écran))
			nbImages++; // compte le nombre d'images du film
		f3.rembobiner();
		
		assertEquals(nbImages, 20); // 20: nb images LaDiagonaleDuFou
	}

}
