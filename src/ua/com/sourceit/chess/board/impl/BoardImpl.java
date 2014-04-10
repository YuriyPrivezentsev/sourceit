package ua.com.sourceit.chess.board.impl;

import ua.com.sourceit.chess.board.IBoard;
import ua.com.sourceit.chess.figures.IFigure;

/**
 * Generic chess board
 *
 * Created by yuriy on 03.04.14.
 */
public class BoardImpl implements IBoard{
    private final ICell[][] bordContent;
    private static final Character[] literals = {'a','b','c','d','e','f','g','h'};

    public BoardImpl() {
        bordContent = new Cell[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                bordContent[i][j] = new Cell((i + j) % 2 == 0 ? BLACK_CELL : WHITE_CELL, i, j);
            }
        }
    }

    @Override
    public void move(IFigure figure, ICell from, ICell to) {
        from.removeFigure(figure);
        to.putFigure(figure);
    }

    private class Cell implements ICell{

        /**
         * Creates the chessboard cell
         *
         * @param color - cell color
         * @param x - x position on board
         * @param y - y position on board
         * @param figureInField - figure which is placed on cell
         */
        private final ICellColor color;

        private final int x;

        private final int y;

        private IFigure figureInField;

        private Cell(ICellColor color, int x, int y) {
            this(color, x, y, null);
        }

        /**
         * Creates the chessboard cell
         *
         * @param color - cell color
         * @param x - x position on board
         * @param y - y position on board
         * @param figureInField - figure which is placed on cell
         */
        private Cell(ICellColor color, int x, int y, IFigure figureInField) {
            this.color = color;
            this.x = x;
            this.y = y;
            this.figureInField = figureInField;
        }

        @Override
        public ICellColor getColor() {
            return color;
        }

        @Override
        public void putFigure(IFigure figure) {

        }

        @Override
        public void removeFigure(IFigure figure) {

        }

        @Override
        public String getCellName() {
            return literals[x].toString().toUpperCase() + (y + 1);
        }

        @Override
        public ICell getNorthCell() {
            return bordContent[x+1][y];
        }
    }
}
