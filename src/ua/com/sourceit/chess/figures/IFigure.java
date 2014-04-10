package ua.com.sourceit.chess.figures;

import ua.com.sourceit.chess.board.IBoard;

/**
 * Chess figure interface
 *
 * Created by yuriy on 03.04.14.
 */
public interface IFigure {
    /**
     * Validates whether this figure can do such route
     *
     * @param from - source cell
     * @param to - target cell
     * @return - true if move is acceptable, false if not
     */
    public boolean isValidRoute(IBoard.ICell from, IBoard.ICell to);
}
