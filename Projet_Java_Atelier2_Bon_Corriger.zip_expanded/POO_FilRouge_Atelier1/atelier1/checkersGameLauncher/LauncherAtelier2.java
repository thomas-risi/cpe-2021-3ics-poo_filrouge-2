package atelier1.checkersGameLauncher;


import atelier1.checkersGameController.Controller;
import atelier1.checkersGameGui.GuiConfig;
import atelier1.checkersGameGui.View;
import atelier1.checkersGameModel.BoardGame;
import atelier1.checkersGameModel.Coord;
import atelier1.checkersGameModel.Model;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LauncherAtelier2 extends Application {

	private BoardGame<Coord> model;
	private BoardGame<Integer> controller;
	private Parent view;
	
	public static void main (String[] args) {

		LauncherAtelier2.launch();
	}

	@Override
	public void init () throws Exception {
		super.init();
		
		///////////////////////////////////////////////////////////////////////////////////////
		// Objet qui g�re les aspects m�tier du jeu de dame :
		///////////////////////////////////////////////////////////////////////////////////////
		
		this.model = new Model();

		
		///////////////////////////////////////////////////////////////////////////////////////
		// Objet qui contr�le les actions de la vue et les transmet au model
		// il renvoie les r�ponses du model � la vue
		///////////////////////////////////////////////////////////////////////////////////////
		
		this.controller = new Controller(model);
		
		
		///////////////////////////////////////////////////////////////////////////////////////
		// Fen�tre dans laquelle se dessine le damier
		// et qui propose un menu pour changer la couleur des cases
		// et la forme des pions
		///////////////////////////////////////////////////////////////////////////////////////
				
		this.view = new View(controller);
	}


	@Override
	public void start (Stage primaryStage) {

		primaryStage.setScene(new Scene(this.view, GuiConfig.HEIGHT, GuiConfig.HEIGHT));
		primaryStage.setTitle("Super Jeu de Dame Version Deluxe Premium Edition Plus Plus ");
		primaryStage.show();
	}

	
}

