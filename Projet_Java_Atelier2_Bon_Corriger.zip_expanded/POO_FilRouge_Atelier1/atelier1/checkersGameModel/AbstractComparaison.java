package atelier1.checkersGameModel;

import java.util.Comparator;

public abstract class AbstractComparaison implements Comparator<PieceModel> {

	@Override
	public int compare (PieceModel piece1, PieceModel piece2) {
		
		int comparaison = 0;
		
		if(true) {
			
			comparaison = piece2.getCoord().getLigne() - piece1.getCoord().getLigne(); // Renvoi 0 si égale, -1 si inférieur ou +1 si suppérieur
			
		}
		else {
			comparaison = piece1.getCoord().getColonne() - piece2.getCoord().getColonne(); // Renvoi 0 si égale, -1 si inférieur ou +1 si suppérieur
		}
		
		return comparaison;
	}
	
	
}
