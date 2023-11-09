package Pieces;

public class Piece {
    public char getColor()
    {
        return color;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setColor(char color)
    {
        this.color = color;
    }

    private String name;

    public String getName()
    {
        return name;
    }

    private char color;

    public Piece(String name, char color)
    {
        this.name = name;
        this.color = color;
    }

    public boolean canMove(int row, int col, int row1, int col1, Piece[][] wold)
    {
        return (row >=0 && row < 8 )&&(col >=0 && col < 8)&&
                (row1 >=0 && row1 < 8 )&&(col1 >=0 && col1 < 8) &&
                (row!=row1) && (col!=col1);
    }

    public boolean canAttack(int row, int col, int row1, int col1, Piece nextWold)
    {
        if (nextWold.getColor() != this.getColor())
        {
            return true;
        }

        return false;
    }
}
