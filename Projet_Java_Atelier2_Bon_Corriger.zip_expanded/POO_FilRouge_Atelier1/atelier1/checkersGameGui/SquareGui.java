package atelier1.checkersGameGui;


import atelier1.checkersGameNutsAndBolts.PieceSquareColor;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * @author francoiseperrin
 * 
 * Classe d'affichage des carr�s du damier
 * leur couleur est initialis� par les couleurs par d�faut du jeu
 *
 */
class SquareGui extends BorderPane  {

	private PieceSquareColor squareColor;    		// le carr� est Noir ou Blanc

	public SquareGui (PieceSquareColor squareColor) {
		super();
		this.squareColor = squareColor;

		// la couleur est d�finie par les valeurs par d�faut de configuration
		Color color = PieceSquareColor.BLACK.equals(this.squareColor) ?
				GuiConfig.CASEBLACK : GuiConfig.CASEWHITE;
		this.setBackgroundColor(color);

	}

	private void setBackgroundColor (Color color) {
		this.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}

}
