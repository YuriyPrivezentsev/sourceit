package ua.com.sourceit.ranju;

/**
 * The playing desk interface for playing tic-tac-toes
 * on the large square field field.
 *
 */
public interface IBoard {

    /**
     * Enum which represents possible field values
     */
    public enum Figure{
        X,
        O,
        EMPTY;
    }
    /**
     * Board size for square field
     */
    public static final int BOARD_SIZE = 20;

    public Figure getCellValue(int x, int y);

}
