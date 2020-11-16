package atelier1.checkersGameGui;

import atelier1.checkersGameNutsAndBolts.PieceSquareColor;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class SquareGui extends Pane{

	
	
	// la couleur est d�finie par les valeurs par d�faut de configuration
	public SquareGui(PieceSquareColor squareColor) {
		
		Color color = PieceSquareColor.BLACK.equals(squareColor) ?
		GuiConfig.CASEBLACK : GuiConfig.CASEWHITE;
		this.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
}
