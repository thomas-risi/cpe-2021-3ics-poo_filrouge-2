package atelier1.checkersGameGui;



import atelier1.checkersGameNutsAndBolts.PieceSquareColor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class PieceGui extends Canvas{
	
//	public PieceGui(PieceSquareColor pieceColor) {
//		// TODO Auto-generated constructor stub
//	}
	public PieceGui(PieceSquareColor pieceColor) {
		
		
		
GraphicsContext graphicsContext = this.getGraphicsContext2D();
		
		// Gestion de la taille des Canvas 		// TODO - � remplacer atelier 4 : bad practice
		this.setHeight(GuiConfig.HEIGHT/GuiConfig.SIZE);		
		this.setWidth(GuiConfig.HEIGHT/GuiConfig.SIZE);		
		
		// la couleur est d�finie en dur
		Color color = Color.BLACK;
		if (pieceColor == PieceSquareColor.WHITE) {
			color = Color.WHITE;
		}
		graphicsContext.setFill(color);
		
		// calcul taille et position pi�ce en fonction du carr�
		double rowWidth = this.getWidth();
		double rowHeight = this.getHeight();
		int offset = (int) ((rowWidth + rowHeight) / 6)   ;
		double width = rowWidth - offset;
		double height = rowHeight - offset;
		double upperLeftWidth = offset / 2;
		double upperLeftHeight = offset / 2;
		graphicsContext.fillArc(upperLeftWidth, upperLeftHeight, width, height, 30, 300, ArcType.ROUND);
		
	}
	
}
