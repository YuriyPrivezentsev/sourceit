package ua.com.sourceit.ranju;

/**
 * Interface for computer tic-tac-toe player
 */
public interface IPlayer {
    /**
     * Set gaming board
     * @param board
     */
    public void setBoard(IBoard board);

    /**
     * Set whether player plays X or O
     * @param figure
     */
    public void setPlayerFigure(IBoard.Figure figure);

    /**
     * Initiate player's move
     */
    public void takeTurn();
}
