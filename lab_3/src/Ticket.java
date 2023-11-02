public class Ticket
{
    private int cinemaId;
    private int hallId;
    private int[] seat = new int[2];
    private String movieName;

    public Ticket(int cinemaId, int hallId, String movieName, int row, int column)
    {
        this.cinemaId = cinemaId;
        this.hallId = hallId;
        this.movieName = movieName;
        this.seat[0] = row;
        this.seat[1] = column;
    }

    public int getCinema()
    {
        return this.cinemaId;
    }

    public int getHall()
    {
        return this.hallId;
    }

    public String getMovieName()
    {
        return this.movieName;
    }
}