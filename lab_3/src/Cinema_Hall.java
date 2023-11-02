import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Cinema_Hall
{
    private final ArrayList<Movie_Session> movieSessions = new ArrayList<Movie_Session>();
    int seatsRowCount;
    int seatsColumnsCount;

    public Cinema_Hall(int seatsRowsCount, int seatsColumnsCount)
    {
        if (seatsRowsCount <= 0 || seatsColumnsCount <= 0)
        {
            throw new Error("Зал не может быть создан с такими параметрами");
        }

        this.seatsColumnsCount = seatsColumnsCount;
        this.seatsRowCount = seatsRowsCount;
    }

    public void addMovie(Movie_Session newMovie)
    {
        for (Movie_Session movie : this.movieSessions)
        {
            if (movie.movieStart.after(newMovie.movieStart))
            {
                if (movie.movieStart.getTime() < newMovie.movieStart.getTime() + (long) newMovie.movieLength * 60000L)
                {
                    throw new Error("В это время в зале проводится другой сеанс");
                }
            }

            else if (movie.movieStart.before(newMovie.movieStart))
            {
                if (movie.movieStart.getTime() + (long) movie.movieLength * 60000L > newMovie.movieStart.getTime())
                {
                    throw new Error("В это время в зале проводится другой сеанс");
                }
            }

            else
            {
                throw new Error("В это время в зале проводится другой сеанс");
            }
        }

        newMovie.setSeatsConfig(seatsRowCount, seatsColumnsCount);
        movieSessions.add(newMovie);
        movieSessions.sort((a, b) -> a.movieStart.compareTo(b.movieStart));
    }

    public Movie_Session getMovie(String movieTitle)
    {
        for (Movie_Session movie : movieSessions)
        {
            if (movie.movieTitle.equals(movieTitle))
            {
                return movie;
            }
        }

        throw new Error("В этом зале нет такого кино");
    }

    public Movie_Session getNextMovie()
    {
        for (Movie_Session movieSession : movieSessions)
        {
            if (movieSession.hasFreeSeats())
            {
                return movieSession;
            }
        }

        return null;
    }

    public void showMovies(SimpleDateFormat formatter)
    {
        System.out.println("Доступные фильмы:");
        for (Movie_Session movie : movieSessions)
        {
            if (movie.hasFreeSeats())
            {
                System.out.printf("Название '%s', длительность %d минут, начало %s \n", movie.movieTitle, movie.movieLength, formatter.format(movie.movieStart.getTime()));
            }
        }
    }
}