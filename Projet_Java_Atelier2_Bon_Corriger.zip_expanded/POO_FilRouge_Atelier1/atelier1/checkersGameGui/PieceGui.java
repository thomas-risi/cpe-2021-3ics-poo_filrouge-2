package atelier1.checkersGameGui;

import atelier1.checkersGameNutsAndBolts.PieceSquareColor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

class PieceGui extends Canvas {

	private PieceShape shape;
	private GraphicsContext graphicsContext;

	public PieceGui (PieceSquareColor pieceColor) {
		super();

		this.graphicsContext = this.getGraphicsContext2D();

		// Gestion de la taille des Canvas // TODO - à remplacer : bad practice
		double height = GuiConfig.HEIGHT;
		int nbCol = GuiConfig.SIZE;
		this.setHeight(height/nbCol);		
		this.setWidth(height/nbCol);

		// la forme est d�finie par les valeurs par d�faut de configuration
		this.shape = GuiConfig.SHAPE;

		// la couleur est d�finie en dur
		Color color = Color.BLACK;
		if (pieceColor == PieceSquareColor.WHITE) {
			color = Color.WHITE;
		}
		this.graphicsContext.setFill(color);

		this.draw();
	}

	private void draw () /**/ {

		// calcul taille et position pi�ce en fonction du carr�
		double rowWidth = this.getWidth();
		double rowHeight = this.getHeight();
		int offset = (int) ((rowWidth + rowHeight) / 6)   ;
		double width = rowWidth - offset;
		double height = rowHeight - offset;
		double upperLeftWidth = offset / 2 ;
		double upperLeftHeight = offset / 2 ;

		// On efface l'ancienne forme
		this.graphicsContext.clearRect(0, 0, rowWidth, rowHeight);

		// On trace la nouvelle forme
		switch (this.shape) {

		case CIRCLE:
			graphicsContext.fillOval(upperLeftWidth, upperLeftHeight, width, height);
			break;

		case SQUARE:
			graphicsContext.fillRect(upperLeftWidth, upperLeftHeight, width, height);
			break;

		case ARC:
			graphicsContext.fillArc(upperLeftWidth, upperLeftHeight, width, height, 30, 300, ArcType.ROUND);
			break;

		}

	}

}
