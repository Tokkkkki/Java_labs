import java.util.Date;

public class Movie_Session
{
    public String movieName;
    private boolean[][] seats;
    private int freeSeats;
    public Date movieDate = new Date();
    public int movieDuration;

    public Movie_Session(Date movieDate, int movieDuration, String movieName)
    {
        this.movieDuration = movieDuration;
        if (movieName.isEmpty()) throw new Error("Название фильма не может быть незаполненным");
        if (movieDuration < 60) throw new Error("Продолжительность фильма не может быть меньше 60 минут");

        this.movieDate = movieDate;
        this.movieName = movieName;
    }

    public void SeatsConfig(int rowsCount, int columnsCount)
    {
        this.seats = new boolean[rowsCount][columnsCount];
        freeSeats = rowsCount * columnsCount;
    }

    public void Seats()
    {
        System.out.println("План зала (x - кресло забронировано, 0 - кресло свободно)");

        System.out.print(" ");
        for (int i = 0; i < seats[0].length; i++)
        {
            System.out.printf(" %d", i);
        }

        System.out.println();
        for (int i = 0; i < seats.length; i++)
        {
            System.out.printf("%d ", i);
            for (int j = 0; j < seats[i].length; j++)
            {
                System.out.printf("%s ", this.checkSeatBook(i, j) ? "x" : "0");
            }
            System.out.println();
        }
    }

    private boolean checkSeatBook(int row, int column) {
        return this.seats[row][column];
    }

    public void BookSeat(int row, int column)
    {
        if (this.checkSeatBook(row, column))
        {
            throw new Error("Это место уже забронировано");
        }

        this.seats[row][column] = true;
        freeSeats -= 1;
    }

    public boolean FreeSeats()
    {
        return freeSeats != 0;
    }
}