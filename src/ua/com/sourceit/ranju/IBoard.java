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

    /**
     * Desk cell class.
     */
    public static final class IPoint {
        private final int x;
        private final int y;

        /**
         * Default constructor
         * @param x - x coordinate
         * @param y - y coordinate
         */
        public IPoint(int x, int y) {
            if(x < 0 || x > BOARD_SIZE ||
                    y < 0 || y > BOARD_SIZE){
                throw new IllegalArgumentException(String.format("Illegal point coordinates (%i, %i)",x,y));
            }
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
