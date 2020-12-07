package atelier1.checkersGameModel;



import java.util.LinkedList;
import java.util.List;

import atelier1.checkersGameNutsAndBolts.PieceSquareColor;


public class PawnModel extends AbstractPieceModel implements Promotable {
	
	protected int direction;
	
	public PawnModel(Coord coord, PieceSquareColor pieceColor) {
		super(coord, pieceColor);
		this.direction = PieceSquareColor.BLACK.equals(this.getPieceColor()) ? -1 : 1;
	}
	
	@Override
	public boolean isMoveOk(Coord targetCoord, boolean isPieceToTake) {
		boolean ret = false;

		int colDistance = targetCoord.getColonne() - this.getCoord().getColonne();
		int ligDistance = targetCoord.getLigne() - this.getCoord().getLigne();
		int deltaLig = (int) Math.signum(ligDistance);
		
		// Cas d'un d�placement en diagonale
		if (Math.abs(colDistance) == Math.abs(ligDistance)){
			
			// sans prise
			if (!isPieceToTake) {
				if (deltaLig == this.direction && Math.abs(colDistance) == 1) {
					ret = true;
				}
			}
			// avec prise
			else {
				if (Math.abs(colDistance) == 2) {
					ret = true;
				}
			}
		}
		return ret;
	}

	@Override
	public boolean isPromotable() {
		boolean isPromotable= false;
		//PieceSquareColor couleur = this.pieceColor;
		int direction = this.direction;
		int ligne = this.getCoord().getLigne();
		
		
		if(direction == 1 && ligne == 10) {
			isPromotable = true;			//La pièce est blanche et elle est sur la ligne 10
		}
		
		if(direction == -1 && ligne == 1) { // La pièce est noire et elle est sur la ligne 1
			isPromotable = true;
		}
		
		return isPromotable;
	}
	
	@Override
	public int hashCode() {
	    
		int result = 1;
	    //result = result + this.pieceColor.hashCode();
	    //result = result + this.coord.hashCode();
	    System.out.println(result);
	    return result;
	}
	
//	@Override
//	public boolean equals(Object o) {
//		
//        if (o == this) { 
//            return true; 
//        }
//		
//		return false;
//	    
//
//	}
	
	
}

