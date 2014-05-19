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

    /**
     * Get the figure on the specified cell
     * @param x - x cell coordinate
     * @param y - y cell coordinate
     * @return - figure residing in the cell
     * @deprecated use <code>getCellValue(Point point)</code> instead
     */
    @Deprecated
    public Figure getCellValue(int x, int y);

    /**
     * Get the figure on the specified cell
     * @param point - coordinate of the cell
     * @return - figure residing in the cell
     */
    public Figure getCellValue(Point point);

    /**
     * Desk cell class.
     */
    public static final class Point {
        private final int x;
        private final int y;

        /**
         * Default constructor
         * @param x - x coordinate
         * @param y - y coordinate
         */
        public Point(int x, int y) {
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
