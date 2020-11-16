package atelier1.checkersGameController;

import atelier1.checkersGameModel.BoardGame;
import atelier1.checkersGameModel.Coord;
import atelier1.checkersGameModel.ModelConfig;

/**
 * @author francoiseperrin
 *
 * Méthodes du controller sont invoquées par écouteurs du damier
 * Elles interrogent le Model pour savoir si les déplacements sont légaux
 * Elles permettent également de transformer les coordonnées des pièces :
 * 	- index de 0 à  99 pour la view
 * 	- Coord (col, ligne) pour le model ['a'..'j'][10..1]
 * 
 * On peut dire que le controller se substitue au model vis à vis de la view
 * car la view et le model ne gèrent pas les coordonnées des pièces de la même façon
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
	 * @return true si la PieceGUI sélectionnée correspond à  une PieceModel qui peut àªtre déplacée 
	 * La coordonnée d'origine du déplacement est alors conservée
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
	 * @return true si la case de destination peut recevoir la pièce sélectionnée 
	 * c'est à  dire si le déplacement est légal du point de vue du model
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
	 * @return index de l'éventuelle pièce à  capturer, -1 sinon
	 * invite le model à  effectuer le déplacement métier
	 */
	public Integer movePiece(Integer initSquareIndex, Integer targetSquareIndex) {
		int tookPieceIndex = -1;
		Coord tookPieceCoord = null ;
		Coord initCoord = this.transformIndexToCoord(initSquareIndex);
		Coord targetCoord = this.transformIndexToCoord(targetSquareIndex);
		tookPieceCoord  = this.model.movePiece(initCoord, targetCoord);

		// les coord de la pièce capturée sont retournée à  la vue pour l'effacer
		if (tookPieceCoord != null) {
			tookPieceIndex = transformCoordToIndex(tookPieceCoord);
		}
		return tookPieceIndex;
	}

	/**
	 * @param squareIndex
	 * @param length
	 * @return les coordonnées métier calculées à  partir de l'index du SquareGUI de la PieceGUI
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
