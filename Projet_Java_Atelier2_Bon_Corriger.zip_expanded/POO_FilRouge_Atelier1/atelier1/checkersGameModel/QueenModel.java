package atelier1.checkersGameModel;


import java.util.List;

import atelier1.checkersGameNutsAndBolts.PieceSquareColor;

/**
 * @author francoiseperrin
 *
 *le mode de d�placement et de prise de la reine est diff�rent de celui du pion
 */ 
public class QueenModel extends AbstractPieceModel {
	
	public QueenModel(Coord coord, PieceSquareColor pieceColor) {
		super(coord, pieceColor);
		
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
					ret = true;
			}
			// avec prise
			else {
					ret = true;
			}
		}
		return ret;
	}
	
}

