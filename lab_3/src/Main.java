import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static User user;
    static ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
    static Scanner scanner = new Scanner(System.in, "Cp866");
    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
    public static void main (String[] args)
    {
        System.out.println("Введите: 1 - войти как администратор; 2 - войти как пользователь");
        int userType = scanner.nextInt();
        scanner.nextLine();

        try
        {
            DefaultCinemas();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        switch (userType)
        {
            case 1 -> user = new Admin();
            case 2 -> user = new Client();
        }

        UserActions();
    }

    public static void UserActions()
    {
        user.Actions();
        int action = scanner.nextInt();
        scanner.nextLine();

        try
        {
            switch (action)
            {
                case 0 ->
                {
                    user.Tickets();
                    UserActions();
                }
                case 1 -> NextMovie();
                case 2 -> buyTicket();
                case 3 -> addCinema();
                case 4 -> addCinemaHall();
                case 5 -> addMovie();
                default -> UserActions();
            }
        }
        catch (Error e)
        {
            System.out.println(e.getMessage());
            UserActions();
        }
    }

    public static void NextMovie()
    {
        Movie_Session currentMovie = new Movie_Session(new Date(0), 0, "");
        int cinemaId = -1;
        int hallId = -1;

        for (int i = 0; i < cinemas.size(); i++)
        {
            Cinema cinema = cinemas.get(i);
            for (int j = 0; j < cinema.getHallsCount(); j++)
            {
                Cinema_Hall hall = cinema.getHall(j);
                Movie_Session movie = hall.getNextMovie();

                if (movie != null)
                {
                    if (movie.movieDate.before(currentMovie.movieDate) || currentMovie.movieDate.getTime() == 0)
                    {
                        currentMovie = movie;
                        cinemaId = i;
                        hallId = j;
                    }
                }
            }
        }

        if (cinemaId == -1)
        {
            System.out.println("Ближайшего сеанса не найдено");
            UserActions();
            return;
        }

        System.out.println("Ближайший сеанс со свободными местами:");
        System.out.println(currentMovie.movieName);
        System.out.printf("Время начала %s, длительность %d минут\n", formatter.format(currentMovie.movieDate.getTime()), currentMovie.movieDuration);
        System.out.printf("Кинотеатр %d, зал %d \n", cinemaId, hallId);
        UserActions();
    }

    public static void buyTicket()
    {
        Cinema cinema = getCinema();

        cinema.Halls();
        System.out.println("Введите номер кинозала");
        int hallId = scanner.nextInt();
        scanner.nextLine();

        Cinema_Hall hall;
        try
        {
            hall = cinema.getHall(hallId);
        }
        catch (Error e)
        {
            System.out.println(e.getMessage());
            UserActions();
            return;
        }

        hall.Movies(formatter);

        System.out.println("Введите название фильма:");
        String movieName = scanner.nextLine();

        Movie_Session movie = hall.getMovie(movieName);
        movie.Seats();

        System.out.println("Введите номер строки и столбца кресла:");
        int row = scanner.nextInt();
        scanner.nextLine();
        int column = scanner.nextInt();
        scanner.nextLine();

        try
        {
            movie.BookSeat(row, column);
            user.buyTicket(cinemas.indexOf(cinema), hallId, movieName, row, column);
            user.Tickets();
            UserActions();
        }
        catch (Error e)
        {
            System.out.println(e.getMessage());
            UserActions();
            return;
        }
    }

    public static void addCinema()
    {
        if (!user.getPermission()) throw new Error("Вам не хватает прав для этого действия");

        Cinema newCinema = new Cinema();
        cinemas.add(newCinema);

        System.out.printf("Номер нового кинотеатра - %d \n\n", cinemas.size() - 1);
        UserActions();
    }

    public static void addCinemaHall()
    {
        if (!user.getPermission()) throw new Error("Вам не хватает прав для этого действия");

        System.out.println("Введите количество рядов:");
        int row = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введите количество сидений в 1 ряду:");
        int column = scanner.nextInt();
        scanner.nextLine();

        Cinema_Hall newCinemaHall = new Cinema_Hall(row, column);

        Cinema cinema;
        try
        {
            cinema = getCinema();
        }
        catch (Error e)
        {
            System.out.println(e.getMessage());
            UserActions();
            return;
        }

        cinema.addCinemaHall(newCinemaHall);
        System.out.printf("Номер нового зала - %d \n\n", cinema.getHallsCount() - 1);
        UserActions();
    }

    public static void addMovie()
    {
        if (!user.getPermission()) throw new Error("Вам не хватает прав для этого действия");

        Cinema cinema;
        try
        {
            cinema = getCinema();
        }
        catch (Error e)
        {
            System.out.println(e.getMessage());
            UserActions();
            return;
        }

        cinema.Halls();
        System.out.println("Введите номер кинозала");
        int hallId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите название фильма");
        String title = scanner.nextLine();

        System.out.println("Введите дату показа (пример: 2023-10-31 14:30)");
        String stringDate = scanner.nextLine();

        System.out.println("Введите продолжительность фильма в минутах");
        int length = scanner.nextInt();
        scanner.nextLine();

        Date date;
        try
        {
            date = formatter.parse(stringDate);
        }
        catch (ParseException e)
        {
            System.out.println("Произошла ошибка при записи даты");
            UserActions();
            return;
        }

        Movie_Session movie = new Movie_Session(date, length, title);

        try
        {
            cinema.addMovie(hallId, movie);
        }
        catch (Error e)
        {
            System.out.println(e.getMessage());
            UserActions();
            return;
        }

        UserActions();
    }

    public static void Cinemas()
    {
        if (cinemas.isEmpty())
        {
            System.out.println("На данный момент нет доступных кинотеатров");
            UserActions();
            return;
        }

        System.out.println("Номера доступных кинотеатров:");
        for (int i = 0; i < cinemas.size(); i++)
        {
            System.out.printf("%d ", i);
        }
        System.out.println();
    }

    public static Cinema getCinema()
    {
        Cinemas();
        System.out.println("Введите номер кинотеатра");
        int cinemaId = scanner.nextInt();
        scanner.nextLine();

        Cinema cinema;
        try
        {
            cinema = cinemas.get(cinemaId);
        }
        catch (IndexOutOfBoundsException e)
        {
            throw new Error("Такого кинотеатра не существует");
        }

        return cinema;
    }

    public static void DefaultCinemas() throws ParseException
    {
        for (int i = 0; i <= 1; i++)
        {
            Cinema cinema = new Cinema();
            for (int j = 0; j <= 1; j++)
            {
                Cinema_Hall hall = new Cinema_Hall(5, 5);
                cinema.addCinemaHall(hall);
            }
            cinemas.add(cinema);
        }

        Movie_Session firstMovie = new Movie_Session(formatter.parse("2023-10-30 11:30"), 90, "Морской бой");
        Movie_Session secondMovie = new Movie_Session(formatter.parse("2023-10-25 9:30"), 120, "Человек-паук");
        Movie_Session thirdMovie = new Movie_Session(formatter.parse("2023-10-31 12:00"), 150, "Начало");

        cinemas.get(0).getHall(0).addMovie(firstMovie);
        cinemas.get(0).getHall(0).addMovie(thirdMovie);
        cinemas.get(1).getHall(0).addMovie(firstMovie);
        cinemas.get(1).getHall(0).addMovie(secondMovie);
        cinemas.get(1).getHall(1).addMovie(secondMovie);
    }
}