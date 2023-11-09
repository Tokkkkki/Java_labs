import java.util.Scanner;

public class Main
{
    static Board board = new Board();
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args)
    {
        board.setUserColor('w');
        board.init();
        start_game();
    }

    public static void start_game()
    {
        if (board.getTakeBlack().size() == 15 && board.getTakeWhite().size() == 15)
        {
            System.out.println("Ничья!");
            System.out.println();
            System.out.println("Игра завeршена.");
        }

        board.print_board();
        System.out.println();
        System.out.println("Команды: ");
        System.out.println("----- exit: Выход из игры");
        System.out.println("------y1 x1 y2 x2: Ход фигуры из клетки x1, y1 в клекту x2, y2");

        System.out.println("Взятые Белые:" + board.getTakeWhite().toString());
        System.out.println("Взятые Черные:" + board.getTakeBlack().toString());

        switch (board.getUserColor())
        {
            case 'w' -> System.out.println("Ход Белых:");
            case 'b' -> System.out.println("Ход Черных:");
        }

        String inputLine = scan.nextLine();
        if (inputLine.equals("exit"))
        {
            System.out.println();
            System.out.println("Игра завeршена.");
        }

        int x1, y1, x2, y2;
        String[] coords = inputLine.split("");
        y1 = Integer.parseInt(coords[0]);
        x1 = Integer.parseInt(coords[1]);
        y2 = Integer.parseInt(coords[2]);
        x2 = Integer.parseInt(coords[3]);

        try
        {
            board.move_piece(y1, x1, y2, x2);
            switch (board.getUserColor())
            {
                case 'w' -> board.setUserColor('b');
                case 'b' -> board.setUserColor('w');
            }
        }

        catch (Error e)
        {
            System.out.println(e.getMessage());

            if (e.getClass().getSimpleName().equals("GameOverError"))
            {
                board.print_board();
                System.out.println();
                System.out.println("Игра завeршена.");
            }
        }

        finally
        {
            start_game();
        }
    }
}