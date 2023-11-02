import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Cinema_Hall
{
    private ArrayList<Movie_Session> movieSessions = new ArrayList<Movie_Session>();
    int seatsRowCount;
    int seatsColumnsCount;

    public Cinema_Hall(int seatsRowsCount, int seatsColumnsCount)
    {
        if (seatsRowsCount <= 0 || seatsColumnsCount <= 0)
        {
            throw new Error("Зал не может быть создан с такими параметрами");
        }

        this.seatsRowCount = seatsRowsCount;
        this.seatsColumnsCount = seatsColumnsCount;
    }

    public void addMovie(Movie_Session newMovie)
    {
        for (Movie_Session movie : this.movieSessions)
        {
            if (movie.movieDate.after(newMovie.movieDate))
            {
                if (movie.movieDate.getTime() < newMovie.movieDate.getTime() + (long) newMovie.movieDuration * 60000L)
                {
                    throw new Error("В это время в зале проводится другой сеанс");
                }
            }

            else if (movie.movieDate.before(newMovie.movieDate))
            {
                if (movie.movieDate.getTime() + (long) movie.movieDuration * 60000L > newMovie.movieDate.getTime())
                {
                    throw new Error("В это время в зале проводится другой сеанс");
                }
            }

            else
            {
                throw new Error("В это время в зале проводится другой сеанс");
            }
        }

        newMovie.SeatsConfig(seatsRowCount, seatsColumnsCount);
        movieSessions.add(newMovie);
        movieSessions.sort((a, b) -> a.movieDate.compareTo(b.movieDate));
    }

    public Movie_Session getMovie(String movieName)
    {
        for (Movie_Session movie : movieSessions)
        {
            if (movie.movieName.equals(movieName))
            {
                return movie;
            }
        }

        throw new Error("В этом зале такого кино нет");
    }

    public Movie_Session getNextMovie()
    {
        for (Movie_Session movieSession : movieSessions)
        {
            if (movieSession.FreeSeats())
            {
                return movieSession;
            }
        }

        return null;
    }

    public void Movies(SimpleDateFormat formatter)
    {
        System.out.println("Доступные фильмы:");
        for (Movie_Session movie : movieSessions)
        {
            if (movie.FreeSeats())
            {
                System.out.printf("Название '%s', длительность %d минут, начало %s \n", movie.movieName, movie.movieDuration, formatter.format(movie.movieDate.getTime()));
            }
        }
    }
}