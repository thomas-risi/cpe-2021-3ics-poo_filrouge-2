package atelier1.checkersGameGui;



import atelier1.checkersGameNutsAndBolts.PieceSquareColor;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class GuiFactory {
	
	public static Pane createSquare(PieceSquareColor squareColor) {
		//Pane pane = new BorderPane();
		Pane pane = new SquareGui(squareColor);
		
//		// la couleur est d�finie par les valeurs par d�faut de configuration
//		Color color = PieceSquareColor.BLACK.equals(squareColor) ?
//				GuiConfig.CASEBLACK : GuiConfig.CASEWHITE;
//		pane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
		return pane;
	}

	public static Canvas createPiece(PieceSquareColor pieceColor) {
		Canvas piece = new PieceGui(pieceColor);
//		GraphicsContext graphicsContext = piece.getGraphicsContext2D();
//		
//		// Gestion de la taille des Canvas 		// TODO - � remplacer atelier 4 : bad practice
//		piece.setHeight(GuiConfig.HEIGHT/GuiConfig.SIZE);		
//		piece.setWidth(GuiConfig.HEIGHT/GuiConfig.SIZE);		
//		
//		// la couleur est d�finie en dur
//		Color color = Color.BLACK;
//		if (pieceColor == PieceSquareColor.WHITE) {
//			color = Color.WHITE;
//		}
//		graphicsContext.setFill(color);
//		
//		// calcul taille et position pi�ce en fonction du carr�
//		double rowWidth = piece.getWidth();
//		double rowHeight = piece.getHeight();
//		int offset = (int) ((rowWidth + rowHeight) / 6)   ;
//		double width = rowWidth - offset;
//		double height = rowHeight - offset;
//		double upperLeftWidth = offset / 2;
//		double upperLeftHeight = offset / 2;
//		graphicsContext.fillArc(upperLeftWidth, upperLeftHeight, width, height, 30, 300, ArcType.ROUND);

		return piece;
	}
}
