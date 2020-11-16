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
public class View extends GridPane{

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
		
		// ajout du fond d'�cran � la vue
		this.add(checkersBoard, 0, 1);
		
		//partie Label
		
		//Création de deux nouvelles grilles pour stocker les labels
		GridPane gridColonnes = new GridPane();
		GridPane gridLignes = new GridPane();

		int compteurColonne = 0;
		
		for(char i = 'A'; i <= 'J'; i++)
		{
			
			//char nomColonne = i;
			String convString = Character.toString(i); //conv de char à string
			//System.out.printf(convString);
			Label nomColonne = new Label(convString); // creation et affectation label
			//Label nomColonne = new Label("stpmarche");
			nomColonne.setPrefWidth(100);
			nomColonne.setAlignment(Pos.CENTER);
			gridColonnes.add(nomColonne, compteurColonne, 0);
			compteurColonne++;
		}
		
		int compteurLigne  = 0;
		for(int i = 10; i >= 1; i--)
		{
			
			String convString = Integer.toString(i); //conv de i à string
			Label nomLignes = new Label(convString); // creation et affectation label
			nomLignes.setPrefHeight(100);
			nomLignes.setAlignment(Pos.CENTER);
			gridLignes.add(nomLignes, 0, compteurLigne);
			compteurLigne++;
		}
		
		checkersBoard.setTop(gridColonnes); //ajoute la grille créé en haut
		checkersBoard.setLeft(gridLignes); //ajoute la grille créé en haut
	}

}


