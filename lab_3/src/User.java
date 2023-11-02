import java.util.ArrayList;

public abstract class User
{
    private boolean Permission;
    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    public User(boolean Permission)
    {
        this.Permission = Permission;
    }

    public boolean getPermission()
    {
        return this.Permission;
    }

    public void Actions()
    {
        System.out.println("Выберите действие:");
        System.out.println("0 - посмотреть купленные билеты");
        System.out.println("1 - посмотреть ближайший сеанс");
        System.out.println("2 - купить билет");
    };

    public void buyTicket(int cinemaId, int hallId, String movieName, int row, int column)
    {
        Ticket ticket = new Ticket(cinemaId, hallId, movieName, row, column);
        tickets.add(ticket);
    }

    public void Tickets()
    {
        if (tickets.isEmpty())
        {
            System.out.println("У вас нет купленных билетов");
            return;
        }

        System.out.println("Ваши купленные билеты:");
        for (Ticket ticket : tickets)
        {
            System.out.printf("Название: %s, кинотеатр %d, зал %d \n", ticket.getMovieName(), ticket.getCinema(), ticket.getHall());
        }
    }
}