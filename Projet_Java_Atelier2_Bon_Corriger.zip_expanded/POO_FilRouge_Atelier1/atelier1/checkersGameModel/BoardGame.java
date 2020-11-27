package atelier1.checkersGameModel;

/**
 * @author francoise.perrin
 *
 * Cette interface d�finit le comportement attendu des jeux de plateau
 * @param <T>
 * 
 * 
 */
public interface BoardGame<T>  {

	/**
	 * @param coord
	 * @return true si la PieceModel qui se trouve aux coordonn�es indiqu�es 
	 * est d�pla�able :
	 * a minima de la couleur du joueur courant et � terme, celle qui rapporte le plus de coup
	 */
	public boolean isPieceMoveable(T  coord) ;

	/**
	 * @param initCoord
	 * @param targetCoord
	 * @return true si le d�placement est l�gal
	 * 
	 */
	public boolean isMovePieceOk(T initCoord, T targetCoord) ;


	/**
	 * @param initCoord
	 * @param targetCoord
	 * @return �ventuellement les coordonn�es de la pi�ce captur�e 
	 */
	public T movePiece(T initCoord, T targetCoord);
	
	//public void removePiece(T Coord);


}