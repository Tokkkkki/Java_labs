import Pieces.Bishop;
import Pieces.Piece;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Queen;
import Pieces.Rook;

import java.util.ArrayList;

public class Board {
    private Piece[][] fields = new Piece[8][8];
    private ArrayList<String> takeWhite = new ArrayList(16);
    private ArrayList<String> takeBlack = new ArrayList(16);

    public char getUserColor()
    {
        return colorUser;
    }

    public void setUserColor(char colorUser)
    {
        this.colorUser = colorUser;
    }

    private char colorUser;

    public void init()
    {
        this.fields[0] = new Piece[]
        {
                new Rook("R", 'w'), new Knight("N", 'w'),
                new Bishop("B", 'w'), new Queen("Q", 'w'),
                new King("K", 'w'), new Bishop("B", 'w'),
                new Knight("N", 'w'),new Rook("R", 'w')
        };

        this.fields[1] = new Piece[]
        {
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
        };

        this.fields[7] = new Piece[]
        {
                new Rook("R", 'b'), new Knight("N", 'b'),
                new Bishop("B", 'b'), new Queen("Q", 'b'),
                new King("K", 'b'), new Bishop("B", 'b'),
                new Knight("N", 'b'),new Rook("R", 'b')
        };

        this.fields[6] = new Piece[]
        {
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
        };
    }

    public String getCell(int row, int col)
    {
        Piece piece = this.fields[row][col];

        if (piece == null)
        {
            return "    ";
        }

        return " " + piece.getColor() + piece.getName() + " ";
    }

    public ArrayList<String> getTakeWhite()
    {
        return takeWhite;
    }

    public ArrayList<String> getTakeBlack()
    {
        return takeBlack;
    }

    public void move_piece(int row1, int col1, int row2, int col2 ) throws Error
    {
        Piece piece;

        try
        {
            piece =  this.fields[row1][col1];
        }

        catch (Exception e)
        {
            throw new Error("На выбранном поле фигура отсутствует");
        }

        if (piece.getColor() != getUserColor())
        {
            throw new Error("На этом поле находится фигура другого цвета");
        }

        if (piece.getClass().getSimpleName().equals("Pawn"))
        {
            Piece nextWold;

            try
            {
                nextWold = this.fields[row2][col2];
            }

            catch (IndexOutOfBoundsException e)
            {
                throw new Error("Вы выбрали клетку за пределами игрового поля");
            }

            if (nextWold != null && piece.canAttack(row1, col1, row2, col2, nextWold))
            {
                this.attack_piece(row1, col1, row2, col2, piece);
                return;
            }

            else if (nextWold != null)
            {
                throw new Error("Поле уже занято фигурой вашего цвета");
            }

            else if (piece.canMove(row1, col1, row2, col2, fields))
            {
                System.out.println("move");
                this.fields[row2][col2] = piece;
                this.fields[row1][col1] = null;
                return;
            }

            throw new Error("Эта фигура не может встать на данную клетку");
        }

        // Если для фигуры нет препятствий на пути и траектория соответствует ходу фигуры
        if (piece.canMove(row1, col1, row2, col2, this.fields))
        {
            Piece nextField = this.fields[row2][col2];

            // Проверка на шах для короля
            if (piece.getClass().getSimpleName().equals("King") && this.is_check(row2, col2, this.getUserColor()))
            {
                throw new Error("Король не может подставляться под удар");
            }

            if (nextField != null)
            {
                if (piece.canAttack(row1, col1, row2, col2, nextField))
                {
                    this.attack_piece(row1, col1, row2, col2, piece);

                    // Проверка на шах
                    char oppositeKingColor = this.getUserColor() == 'w' ? 'b' : 'w';
                    System.out.println(oppositeKingColor);
                    int[] oppositeKingPos = this.find_king_position(oppositeKingColor);

                    if (this.is_check(oppositeKingPos[0], oppositeKingPos[1], oppositeKingColor))
                    {
                        System.out.println("Шах вражескому королю!");

                        if (this.is_mate(row2, col2, oppositeKingPos[0], oppositeKingPos[1], oppositeKingColor))
                        {
                            System.out.println("Вражескому королю поставлен мат! Игра завершена.");
                        }
                    }

                    return;
                }

                throw new Error("Поле уже занято фигурой вашего цвета");
            }

            System.out.println("move");
            this.fields[row2][col2] = piece;
            this.fields[row1][col1] = null;

            // Проверка на шах
            char oppositeKingColor = this.getUserColor() == 'w' ? 'b' : 'w';
            System.out.println(oppositeKingColor);
            int[] oppositeKingPos = this.find_king_position(oppositeKingColor);

            if (this.is_check(oppositeKingPos[0], oppositeKingPos[1], oppositeKingColor))
            {
                System.out.println("Шах вражескому королю!");

                if (this.is_mate(row2, col2, oppositeKingPos[0], oppositeKingPos[1], oppositeKingColor))
                {
                    System.out.println("Вражескому королю поставлен мат! Игра завершена.");
                }
            }

            return;
        }

        throw new Error("Эта фигура не может пройти на данную клетку");
    }

    public void print_board()
    {
        System.out.println(" +----+----+----+----+----+----+----+----+");

        for (int row = 7; row > -1; row--)
        {
            System.out.print(row);

            for (int col = 0; col< 8; col++)
            {
                System.out.print("|"+getCell(row, col));
            }

            System.out.println("|");
            System.out.println(" +----+----+----+----+----+----+----+----+");
        }

        for (int col = 0; col < 8; col++)
        {
            System.out.print("    "+col);
        }
    }

    public void attack_piece(int row1, int col1, int row2, int col2, Piece piece)
    {
        System.out.println("attack");

        switch (this.fields[row2][col2].getColor())
        {
            case 'w' -> this.takeWhite.add(this.fields[row2][col2].getColor() + this.fields[row2][col2].getName());
            case 'b' -> this.takeBlack.add(this.fields[row2][col2].getColor() + this.fields[row2][col2].getName());
        }

        this.fields[row2][col2] = piece;
        this.fields[row1][col1] = null;
    }

    // Проверка шаха
    public boolean is_check(int kingRow, int kingCol, char kingColor)
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                Piece piece = this.fields[i][j];

                if (piece == null || piece.getColor() == kingColor)
                {
                    continue;
                }

                if (piece.getClass().getSimpleName().equals("Pawn") && (Math.abs(i - kingRow) == 1 && Math.abs(j - kingCol) == 1))
                {
                    return true;
                }

                if (piece.canMove(i, j, kingRow, kingCol, fields))
                {
                    return true;
                }
            }
        }

        return false;
    }

    // Проверка мата
    public boolean is_mate(int row, int col, int kingRow, int kingCol, char kingColor)
    {
        // 1. Проверка вшзможности побега короля
        for (int i = -1; i < 2; i++)
        {
            for (int j = -1; j < 2; j++)
            {
                if (kingRow + i >= 8 || kingRow + i < 0 || kingCol + j >= 8 || kingCol + j < 0) continue;
                if (i == 0 && j == 0) continue; // Уже проверяли ранее данную клетку

                if (this.fields[kingRow + i][kingCol + j] == null && !(this.is_check(kingRow + i, kingCol + j, kingColor)))
                {
                    return false;
                }
            }
        }

        // 2. Проверка возможности защиты короля
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                Piece piece = this.fields[i][j];

                if (piece == null || piece.getColor() != kingColor)
                {
                    continue;
                }

                // Если при самостоятельном съедании вражеской фигуры Король все равно попадает под шах
                if (piece.getClass().getSimpleName().equals("King") && this.is_check(row, col, kingColor))
                {
                    continue;
                }

                if (piece.getClass().getSimpleName().equals("Pawn") && piece.canAttack(i, j, row, col, this.fields[row][col]))
                {
                    return false;
                }

                if (piece.canMove(i, j, row, col, this.fields) && piece.canAttack(i, j, row, col, this.fields[row][col]))
                {
                    return false;
                }
            }
        }

        return true;
    }

    public int[] find_king_position(char color)
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                Piece piece = this.fields[i][j];

                if (piece != null && piece.getClass().getSimpleName().equals("King") && piece.getColor() == color)
                {
                    int[] kingPosition = {i, j};
                    return kingPosition;
                }
            }
        }

        return null;
    }
}
