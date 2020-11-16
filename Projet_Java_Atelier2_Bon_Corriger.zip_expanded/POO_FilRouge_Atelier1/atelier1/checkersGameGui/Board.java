package atelier1.checkersGameGui;


import atelier1.checkersGameModel.BoardGame;
import atelier1.checkersGameNutsAndBolts.PieceSquareColor;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


/**
 * @author francoiseperrin
 * <p>
 * Cette classe représente le damier de la vue
 * <p>
 * Elle tire les valeurs d'affichage d'une fabrique de constante (GuiConfig)
 * 		public final static int size = 10;
 * 		public final static double height = 600.0;
 * 
 */
class Board extends GridPane {

	private final BoardGame<Integer> controller;

	private int selectedPieceIndex;						// index de la pièce à déplacer 

	private int nbCol, nbLig;                			// le nb de ligne et de colonne du damier
	private double height;								// taille du damier en pixel

	private EventHandler<MouseEvent> squareListener;    // l'écouteur d'évènements souris sur les carrés du damier
	private EventHandler<MouseEvent> pieceListener;    	// l'écouteur d'évènements souris sur les pièces

	public Board (BoardGame<Integer> controller) {
		super();

		this.nbCol = nbLig = GuiConfig.SIZE;
		this.height = (double) GuiConfig.HEIGHT;

		this.controller = controller;

		this.squareListener = new SquareListener();
		this.pieceListener = new PieceListener();

		// initialisation du damier
		this.addSquaresOnCheckersBoard();
		this.addPiecesOnCheckersBoard();
	}


	private void addSquaresOnCheckersBoard () {

		Pane square = null;
		PieceSquareColor squareColor = null;

		for (int ligne = 0; ligne < this.nbLig; ligne++) {

			for (int col = 0; col < this.nbCol; col++) {
				// sélection de la couleur de la case
				if ((col % 2 == 0 && ligne % 2 == 0) || (col % 2 != 0 && ligne % 2 != 0)) {
					squareColor = PieceSquareColor.WHITE;
				} else {
					squareColor = PieceSquareColor.BLACK;
				}

				// création d'un Pane
				square = GuiFactory.createSquare(squareColor);

				// ajout d'un écouteur sur le carré
				square.setOnMouseClicked(squareListener);

				// gestion de la taille des Pane
				square.setPrefHeight(this.height/this.nbLig);	// TODO - à  remplacer : bad practice
				square.setPrefWidth(this.height/this.nbCol);	// TODO - à  remplacer : bad practice

				// ajout du carre sur le damier
				this.add(square, col, ligne);

			}
		}
	}


	private void addPiecesOnCheckersBoard () {

		int blackIndex;
		int whiteIndex;

		// ajout pions noirs et blancs sur les cases noires des 4 lignes du haut et du bas
		// Rq : les index des carrés sur le damier varient de 0 à  nbLig*nbLig-1 (=99)
		for (int j = 0; j < this.nbLig * 4; j += 2) {

			// recherche index du carré noir qui contient la pièce noire ou blanche
			if ((j / this.nbLig) % 2 == 0) {
				blackIndex = j + 1;
				whiteIndex = this.nbLig * this.nbLig - j - 2;
			} else {
				blackIndex = j;
				whiteIndex = this.nbLig * this.nbLig - j - 1;
			}

			// ajout effectif du pion noir puis du pion blanc sur les carrés identifiés
			addPieceOnSquare((Pane) this.getChildren().get(blackIndex), PieceSquareColor.BLACK);
			addPieceOnSquare((Pane) this.getChildren().get(whiteIndex), PieceSquareColor.WHITE);
		}

	}


	/**
	 * @param targetSquare
	 * @param pieceColor   Création d'une pièce et ajout dans le bon carré noir
	 */
	private void addPieceOnSquare (Pane targetSquare, PieceSquareColor pieceColor) {

		Canvas pieceGUI;

		// création de la pièce
		pieceGUI = GuiFactory.createPiece(pieceColor);

		// ajout d'un écouteur de souris
		// si la pièce est sélectionnée, elle sera supprimé de son emplacement actuel
		// et repositionnée sur une autre case
		pieceGUI.setOnMouseClicked(this.pieceListener);

		// Ajout de la pièce sur le carré noir
		targetSquare.getChildren().add(pieceGUI);

	}

	/**
	 * @param selectedSquareIndex
	 * @param targetSquareIndex
	 * @param tookPieceIndex
	 * Cette méthode est appelée par l'écouteur SquareListener
	 * lorsqu'une case est cliquée afin d'y déposer une pièce précédemment sélectionnée
	 * la promouvoir éventuellement en dame et supprimer l'éventuelle pièce capturée
	 */
	private void movePieceOnGui(int selectedSquareIndex, int targetSquareIndex, int tookPieceIndex) {

		// la PieceGui de la vue est effectivement déplacée
		Board.this.movePiece(selectedSquareIndex, targetSquareIndex);
		
		// seul le déplacement est géré dans cette version
	}

	/**
	 * @param selectedSquareIndex
	 * @param targetSquareIndex 
	 * Cette méthode est appelée indirectement par l'écouteur SquareListener
	 * lorsqu'un carré est cliqué afin d'y déposer une pièce précédemment sélectionnée
	 * à travers movePieceOnGui()
	 */
	private void movePiece (int selectedSquareIndex, int targetSquareIndex) {

		Node selectedPiece = null;
		Pane selectedPieceSquare = (Pane) Board.this.getChildren().get(selectedSquareIndex);
		Pane targetSquare = (Pane) Board.this.getChildren().get(targetSquareIndex);

		if (!selectedPieceSquare.getChildren().isEmpty())
			selectedPiece = selectedPieceSquare.getChildren().get(0);
		if (selectedPiece != null) {
			targetSquare.getChildren().add(selectedPiece);
			selectedPieceSquare.getChildren().removeAll();
		}
	}

	/**
	 * 
	 * @param removeSquare
	 * 
	 * Cette méthode est appelée indirectement par l'écouteur SquareListener
	 * suppression effective d'une pièce
	 */
	private void removePiece(int tookPieceIndex) {

		// clear le carré d'origine de la pièce supprimée
		Pane tookPieceSquare = (Pane) Board.this.getChildren().get(tookPieceIndex);
		tookPieceSquare.getChildren().clear();
	}

	/**
	 * @return selectedPieceIndex
	 * Cette méthode est appelée par l'écouteur SquareListener
	 * lorsqu'un clic est effectué sur une case après qu'une 
	 * pièce ait été sélectionnée
	 */
	private int getSelectedPieceIndex() {
		return this.selectedPieceIndex;
	}
	/**
	 * @param selectedPieceIndex 
	 * Cette méthode est appelée par l'écouteur PieceListener
	 * lorsqu'un clic est effectué sur une pièce avant de la déplacer
	 */
	private void setSelectedPieceIndex (int selectedPieceIndex) {
		this.selectedPieceIndex =  selectedPieceIndex;
	}

	/**
	 * @return controller
	 * Cette méthode est appelé par les écouteurs de cases et de pièces
	 * pour connaitre le controller à qui "parler"
	 */
	private BoardGame<Integer> getController() {
		return this.controller;
	}

	
	/**
	 * @author francoise.perrin
	 *
	 * Objet qui écoute les événements Souris sur les cases du damier
	 * et agit en conséquence 
	 */
	private class SquareListener implements EventHandler<MouseEvent> {

		@Override
		public void handle (MouseEvent mouseEvent) {

			int selectedSquareIndex = Board.this.getSelectedPieceIndex();

			// Recherche SquareGUI sélectionné
			Pane square = (Pane) mouseEvent.getSource();
			int targetSquareIndex = Board.this.getChildren().indexOf(square);

			// Pour l'instant, on ne supprime pas les pièces pouvant se trouver sur le trajet 
			int tookPieceIndex = 0;

			// la PieceGui de la vue est effectivement déplacée et éventuellement promue
			// l'éventuelle pièce intermédiaire est supprimée dans la vue
			Board.this.movePieceOnGui(selectedSquareIndex, targetSquareIndex, tookPieceIndex);

			// On évite que le parent ne récupère l'event
			mouseEvent.consume();
		}

	}


	
	/**
	 * @author francoise.perrin
	 *
	 * Objet qui écoute les événements Souris sur les cases du damier
	 * et agit en conséquence 
	 */
	private class PieceListener implements EventHandler<MouseEvent> {

		@Override
		public void handle (MouseEvent mouseEvent) {

			// Recherche PieceGui sélectionnée
			Canvas selectedPiece = (Canvas) mouseEvent.getSource();

			// Recherche coordonnée du carré 
			Pane parentSquare = (Pane)  selectedPiece.getParent();
			int squareIndex = Board.this.getChildren().indexOf(parentSquare);

			// l'index de la PieceGui de la vue à déplacer est fixée
			Board.this.setSelectedPieceIndex(squareIndex);

			mouseEvent.consume();
		}
	}


}



