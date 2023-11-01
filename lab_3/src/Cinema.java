import java.util.ArrayList;

public class Cinema {
    private ArrayList<Cinema_Hall> cinemaHalls = new ArrayList<Cinema_Hall>();

    public void addCinemaHall(Cinema_Hall hall) {
        this.cinemaHalls.add(hall);
    }

    public int getHallsCount() {
        return cinemaHalls.size();
    }

    public void showHalls() {
        System.out.println("Номера доступных залов:");
        for (int i = 0; i < this.getHallsCount(); i++) {
            System.out.printf("%d ", i);
        }
        System.out.println("");
    }

    public void addMovie(int hallId, Movie_Session movie) {
        Cinema_Hall hall;
        hall = this.getHall(hallId);
        hall.addMovie(movie);
    }

    public Cinema_Hall getHall(int hallId) {
        Cinema_Hall hall;

        try {
            hall = this.cinemaHalls.get(hallId);
        }
        catch (IndexOutOfBoundsException e) {
            throw new Error("В этом кинотеатре нет такого кинозала");
        }

        return hall;
    }
}