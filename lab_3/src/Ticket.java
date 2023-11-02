public class Ticket
{
    private final int cinemaId;
    private final int hallId;
    private final int[] seat = new int[2];
    private final String movieTitle;

    public Ticket(int cinemaId, int hallId, String movieTitle, int row, int column)
    {
        this.cinemaId = cinemaId;
        this.hallId = hallId;
        this.movieTitle = movieTitle;
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

    public String getMovieTitle()
    {
        return this.movieTitle;
    }
}