package atelier1.checkersGameController;


import atelier1.checkersGameModel.BoardGame;
import atelier1.checkersGameModel.Coord;
import atelier1.checkersGameModel.ModelConfig;

/**
 * @author francoiseperrin
 *
 * M�thodes du controller sont invoqu�es par �couteurs du damier
 * Elles interrogent le Model pour savoir si les d�placements sont l�gaux
 * Elles permettent �galement de transformer les coordonn�es des pi�ces :
 * 	- index de 0 � 99 pour la view
 * 	- Coord (col, ligne) pour le model ['a'..'j'][10..1]
 * 
 * On peut dire que le controller se substitue au model vis � vis de la view
 * car la view et le model ne g�rent pas les coordonn�es des pi�ces de la m�me fa�on
 */
public class Controller implements BoardGame<Integer> {

	private BoardGame<Coord> model;

	private int  length;

	public Controller(BoardGame<Coord> model) {
		this.model =  model;
		this.length = ModelConfig.LENGTH;
	}


	/**
	 * @param initSquareIndex
	 * @return true si la PieceGUI s�lectionn�e correspond � une PieceModel qui peut �tre d�plac�e 
	 * La coordonn�e d'origine du d�placement est alors conserv�e
	 */
	public boolean isPieceMoveable(Integer initSquareIndex) {
		boolean bool  = false;
		Coord initCoord = this.transformIndexToCoord(initSquareIndex);
		bool = this.model.isPieceMoveable(initCoord);
		return bool;
	}

	/**
	 * @param targetSquareIndex 
	 * @param squareIndex
	 * @return true si la case de destination peut recevoir la pi�ce s�lectionn�e 
	 * c'est � dire si le d�placement est l�gal du point de vue du model
	 */
	public boolean isMovePieceOk(Integer initSquareIndex, Integer targetSquareIndex) {
		boolean bool  = false;
		Coord initCoord = this.transformIndexToCoord(initSquareIndex);
		Coord targetCoord = this.transformIndexToCoord(targetSquareIndex);
		if (this.model.isPieceMoveable(initCoord)) {
			bool  = this.model.isMovePieceOk(initCoord, targetCoord);
		}
		return bool;
	}


	/**
	 * @param targetSquareIndex 
	 * @param squareIndex
	 * @return index de l'�ventuelle pi�ce � capturer, -1 sinon
	 * invite le model � effectuer le d�placement m�tier
	 */
	public Integer movePiece(Integer initSquareIndex, Integer targetSquareIndex) {
		int tookPieceIndex = -1;
		Coord tookPieceCoord = null ;
		Coord initCoord = this.transformIndexToCoord(initSquareIndex);
		Coord targetCoord = this.transformIndexToCoord(targetSquareIndex);
		tookPieceCoord  = this.model.movePiece(initCoord, targetCoord);

		// les coord de la pi�ce captur�e sont retourn�e � la vue pour l'effacer
		if (tookPieceCoord != null) {
			tookPieceIndex = transformCoordToIndex(tookPieceCoord);
		}
		return tookPieceIndex;
	}

	/**
	 * @param squareIndex
	 * @param length
	 * @return les coordonn�es m�tier calcul�es � partir de l'index du SquareGUI de la PieceGUI
	 */
	private Coord transformIndexToCoord (int squareIndex) {
		Coord coord = null;
		char col = (char) ((squareIndex)%length + 'a');
		int ligne = length - (squareIndex)/length;
		coord = new Coord(col, ligne);
		return coord;
	}

	private int transformCoordToIndex (Coord coord) {
		int squareIndex = -1;
		if (coord != null) {
			squareIndex = (length - coord.getLigne()) * length + (coord.getColonne()-'a');
		}
		return squareIndex;
	}

}
