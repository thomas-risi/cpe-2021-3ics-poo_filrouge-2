package atelier1.checkersGameModel;


import java.util.List;

import atelier1.checkersGameNutsAndBolts.PieceSquareColor;

/**
 * @author francoise.perrin
 *
 * Cette classe g�re les aspects m�tiers du jeu de dame
 * ind�pendement de toute vue
 * 
 * Elle d�l�gue � son objet ModelImplementor 
 * le stockage des PieceModel dans une collection
 * 
 * Les pi�ces sont capables de se d�placer d'une case en diagonale 
 * si la case de destination est vide
 * 
 * Ne sont pas g�r�s les prises, les rafles, les dames, 
 * ni le fait que lorsqu'une prise est possible
 * une autre pi�ce ne doit pas �tre jou�e
 * 
 */
public class Model implements BoardGame<Coord> {

	private PieceSquareColor currentColor;	// couleur du joueur courant

	private ModelImplementor implementor = null;
	private boolean isPieceToMove;			// pi�ce � d�placer

	public Model() {
		super();
		this.implementor = new ModelImplementor();
		this.currentColor = ModelConfig.BEGIN_COLOR;
		System.out.println(this);
	}

	/**
	 * @param coord
	 * @return true si la PieceModel qui se trouve aux coordonn�es indiqu�es 
	 * est de la couleur du joueur courant 
	 */
	@Override
	public boolean isPieceMoveable(Coord coord) {
		boolean bool  = false;
		PieceSquareColor pieceColor = this.implementor.getPieceColor(coord);

		if (pieceColor != null && pieceColor.equals(currentColor)) {
			bool  = true;
		}
		return bool ;
	}

	/**
	 * @param initCoord
	 * @param targetCoord
	 * @return true si le d�placement est l�gal
	 * (s'effectue en diagonale, POUR L'INSTANT sans prise)
	 * La PieceModel qui se trouve aux coordonn�es pass�es en param�tre 
	 * est capable de r�pondre � cette question (par l'interm�diare du ModelImplementor)
	 * 
	 */
	@Override
	public boolean isMovePieceOk(Coord initCoord, Coord targetCoord) {

		boolean isMoveOk = false;
		this.isPieceToMove = false;
		boolean isPieceToTake = false;
		
		
		// s'il existe une pi�ce � d�placer, 
		// et que case d'arriv�e est inoccup�e et dans les limites du damier
		if(this.implementor.isPiecehere(initCoord) &&
				Coord.coordonnees_valides(targetCoord) &&
				!this.implementor.isPiecehere(targetCoord)) {

			// V�rif si d�placement en diagonale est possible avec le bon pas
			// Pour l'instant, on ne teste que le d�placement sans prise
			List<Coord> piecesPrises = implementor.getCoordsOnItinerary(initCoord, targetCoord);
			if(piecesPrises != null) {
				
				//if(piecesPrises.size() == 1) {
					
				
					PieceSquareColor couleurPieceCourrante = implementor.getPieceColor(piecesPrises.get(0));
					if(couleurPieceCourrante != this.currentColor && couleurPieceCourrante != null) {
						isPieceToTake = true;
					//}

				}
				
			}
			
			isMoveOk = this.implementor.isMovePieceOk(initCoord, targetCoord, isPieceToTake ); //isPieceToTake = Tru/False
			
			this.isPieceToMove = isMoveOk;
		}
		return isMoveOk;
	}


	/**
	 * @param initCoord
	 * @param targetCoord
	 * @return null car POUR L'INSTANT, test sans prise 
	 */
	@Override
	public Coord movePiece(Coord initCoord, Coord targetCoord) {

		Coord tookPieceCoord = null;

		// si le d�placement est l�gal
		if (this.isPieceToMove) {

			// d�placement effectif de la pi�ce
			this.implementor.movePiece(initCoord, targetCoord);
			
			// changement joueur courant 
			this.currentColor = (PieceSquareColor.WHITE).equals(this.currentColor) ?
					PieceSquareColor.BLACK : PieceSquareColor.WHITE;
		}
		System.out.println(this);
		
		return  tookPieceCoord;
	}

	@Override
	public String toString() {
		return implementor.toString();
	}



}