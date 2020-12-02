package atelier1.checkersGameModel;

import java.util.LinkedList;
import java.util.List;

import atelier1.checkersGameNutsAndBolts.PieceSquareColor;

public abstract class AbstractPieceModel implements PieceModel {

	protected Coord coord;
	protected PieceSquareColor pieceColor;
	
	protected int direction;
	
	public AbstractPieceModel (Coord coord, PieceSquareColor pieceColor) {
		super();
		this.coord = coord;
		this.pieceColor = pieceColor;
	
	}
	
	
	public List<Coord> getCoordsOnItinerary(Coord targetCoord) {
		
		List<Coord> coordsOnItinery = new LinkedList<Coord>(); 
		int initCol = this.getCoord().getColonne();
		int initLig = this.getCoord().getLigne();
		int colDistance = targetCoord.getColonne() - this.getCoord().getColonne();
		int ligDistance = targetCoord.getLigne() - this.getCoord().getLigne();
		int deltaLig = (int) Math.signum(ligDistance);
		int deltaCol = (int) Math.signum(colDistance);
		
		// V�rif d�placement en diagonale
		if (Math.abs(colDistance) == Math.abs(ligDistance)){
			
			// recherche coordonn�es des cases travers�es
			for (int i = 1; i < Math.abs(colDistance); i++) {
				Coord coord = new Coord((char) (initCol + i*deltaCol), initLig + i*deltaLig);
				coordsOnItinery.add(coord);
			}
		}
		
		return coordsOnItinery;
	}

	@Override
	public Coord getCoord() {
		return coord;
	}

	@Override
	public void move(Coord coord) {
		this.coord = coord; 
	}

	@Override
	public PieceSquareColor getPieceColor() {
		return pieceColor;
	}
	
	@Override
	public String toString() {
		return " ["+pieceColor.toString().charAt(0) + coord + "]";
	}
	
}
