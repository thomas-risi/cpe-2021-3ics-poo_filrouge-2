package atelier1.checkersGameModel;


import java.util.List;

import atelier1.checkersGameNutsAndBolts.PieceSquareColor;

/**
 * @author francoiseperrin
 *
 *le mode de d�placement et de prise de la reine est diff�rent de celui du pion
 */ 
public class QueenModel implements PieceModel {
	private Coord coord;
	private PieceSquareColor pieceColor;
	
	public QueenModel(Coord coord, PieceSquareColor pieceColor) {
		super();
		
		// ToDo 
		
	}

	@Override
	public Coord getCoord() {
		Coord ret = null;

		// ToDo 
		
		return ret;
	}

	@Override
	public void move(Coord coord) {

		// ToDo 
		
	}

	@Override
	public PieceSquareColor getPieceColor() {
		PieceSquareColor ret = null;

		// ToDo 
		
		return ret;
	}

	@Override
	public boolean isMoveOk(Coord targetCoord, boolean isPieceToTake) {
		boolean ret = false;

		// ToDo 
		
		return ret;
	}

	@Override
	public List<Coord> getCoordsOnItinerary(Coord targetCoord) {
		
		List<Coord> coordsOnItinerary = null;
		
		// ToDo
		
		return coordsOnItinerary;
	}
	

}

