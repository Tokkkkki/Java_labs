package Pieces;

public class King extends Piece {
    public King(String name, char color)
    {
        super(name, color);
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1, Piece[][] wold)
    {
        if ((Math.abs(row - row1) == 1 && col == col1) || (row == row1 && Math.abs(col - col1) == 1) || ((Math.abs(row - row1) == 1 && Math.abs(col - col1) == 1)))
        {
            return true;
        }

        return false;
    }
}
