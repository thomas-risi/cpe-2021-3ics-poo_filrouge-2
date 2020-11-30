package atelier1.checkersGameGui;



import atelier1.checkersGameModel.BoardGame;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * @author francoise.perrin
 * 
 * Cette classe est la fen�tre du jeu de dames
 * Elle d�l�gue a un objet la gestion de l'affichage du damier
 * 
 */
public class View extends GridPane {

	public View (BoardGame<Integer> controller) {
		super();

		// le damier compos� de carr�s noirs et blancs
		// sur lesquels sont positionn�s des pi�ces noires ou blanches
		Pane board = new Board(controller);

		// gestion de la taille du damier
		double height = GuiConfig.HEIGHT;			// TODO - � remplacer (atelier 4) : bad practice
		board.setPrefSize( height, height);			// TODO - � remplacer (atelier 4) : bad practice

		// cr�ation d'un fond d'�cran qui contiendra le damier + les axes (atelier 2)
		BorderPane checkersBoard = new BorderPane();	

		// ajout du damier au centre du fond d'�cran
		checkersBoard.setCenter(board);

		// ajout des axes sur les cot�s du damier
		checkersBoard.setTop(createHorizontalAxis());
		checkersBoard.setBottom(createHorizontalAxis());
		checkersBoard.setLeft(createVerticalAxis());
		checkersBoard.setRight(createVerticalAxis());

		// ajout du fond d'�cran � la vue
		this.add(checkersBoard, 0, 1);
	}

	private GridPane createHorizontalAxis() {
		GridPane pane = new GridPane();

		for (char c = 'a'; c<='j'; c++){
			Label label1 = new Label(String.valueOf(c));
			label1.setAlignment(Pos.CENTER);
			label1.setPrefHeight(10);
			label1.setPrefWidth(60);
			pane.add(label1, c-'a', 0);
		}
		return pane;
	}

	private GridPane createVerticalAxis() {
		GridPane pane = new GridPane();
		for (int c = 10; c>=1; c--){
			Label label1 = new Label(String.valueOf(c));
			label1.setPrefHeight(60);
			label1.setPrefWidth(20);
			pane.add(label1, 0, 10-c+1);
		}
		return pane;
	}
}


