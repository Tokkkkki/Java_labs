public class Admin extends User
{
    public Admin()
    {
        super(true);
    }

    @Override
    public void Actions()
    {
        System.out.println("Выберите действие:");
        System.out.println("1 - посмотреть ближайший сеанс со свободными местами");
        System.out.println("2 - купить билет");
        System.out.println("3 - добавить кинотеатр");
        System.out.println("4 - добавить зал в кинотеатр");
        System.out.println("5 - создать сеанс фильма");
    };
}