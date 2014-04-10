package ua.com.sourceit.chess.board;

import ua.com.sourceit.chess.figures.IFigure;

/**
 * Interface responsible for handling chessboard
 *
 * Created by yuriy on 03.04.14.
 */
public interface IBoard {

    public static final int BOARD_SIZE = 8;

    public static ICellColor WHITE_CELL = new ICellColor() {};

    public static ICellColor BLACK_CELL = new ICellColor() {};

    /**
     * Moves the figure from one cell to another
     *
     * @param figure - figure to move
     * @param from - cell from which we get the figure
     * @param to - cell where to put figure
     */
    public void move(IFigure figure, ICell from, ICell to);

    /**
     * Chessboard cell interface
     */
    public interface ICell{

        /**
         * Get color of the current cell
         *
         * @return <code>IBoard.WHITE_CELL</code> or <code>IBoard.BLACK_CELL</code>
         */
        public ICellColor getColor();

        /**
         * Place figure on the cell
         *
         * @param figure - figure to place
         */
        public void putFigure(IFigure figure);

        /**
         * Take figure off the cell
         *
         * @param figure - figure to move
         */
        public void removeFigure(IFigure figure);


        /**
         * Getting cell address
         *
         * @return - cell address on board
         */
        public String getCellName();

        /**
         * Get cell to North from current
         *
         * @return - cell to north from current
         */
        public ICell getNorthCell();
    }

    /**
     * Stub interface for board cell colors
     */
    public interface ICellColor{}
}
