package Pieces;

public class Rook extends Piece {
    public Rook(String name, char color)
    {
        super(name, color);
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1, Piece[][] wold)
    {
        if (row == row1 || col == col1)
        {
            if (Math.abs(row - row1) != 0) // Проверка на препятствия
            {
                int direction = row < row1 ? 1 : -1;
                for (int i = row + direction; Math.abs(row1 - i) > 0; i = i + direction)
                {
                    if (wold[i][col] != null) return false;
                }
            }
            if (Math.abs(col - col1) != 0)
            {
                int direction = col < col1 ? 1 : -1;
                for (int j = col + direction; Math.abs(col1 - j) > 0; j = j + direction)
                {
                    if (wold[row][j] != null) return false;
                }
            }

            return true;
        }

        return false;
    }
}
