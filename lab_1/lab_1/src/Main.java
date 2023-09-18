import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        problem1();
        problem2();
        problem3();
        problem4();
        problem5();
    }

    public static void problem1()
    {
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        int n = 0;

        while (i != 1)
        {
            if (i % 2 == 0)
                i = i / 2;
            else if (i % 2 == 1)
                i = 3 * i + 1;
            else
                break;

            n++;
        }
        System.out.println(n);
    }

    public static void problem2()
    {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] num = new int[n];

        for (int i = 0; i < n; i++)
            num[i] = scan.nextInt();

        int sum = 0;

        for (int i = 0; i < num.length; i++)
        {
            if (i % 2 == 0)
                sum += num[i];
            else
                sum -= num[i];
        }
        System.out.println(sum);
    }

    public static void problem3()
    {
        Scanner scan = new Scanner(System.in);

        int x = 0;
        int y = 0;

        int xTrove = scan.nextInt();
        int yTrove = scan.nextInt();

        String sideWorld = "";
        int step;
        int indication = 0;

        while (true)
        {
            sideWorld = scan.next();
            if (sideWorld.equals("стоп"))
                break;

            step = scan.nextInt();

            if (xTrove == x && yTrove == y)
                continue;

            switch (sideWorld)
            {
                case "запад":
                    x -= step;
                    break;
                case "восток":
                    x += step;
                    break;
                case "юг":
                    y -= step;
                    break;
                case "север":
                    y += step;
                    break;
            }
            indication ++;
        }
        System.out.println(indication);
    }

    public static void problem4()
    {
        Scanner scan = new Scanner(System.in);
        int roads = scan.nextInt();

        int height = 0;
        int num = 0;

        int tunnelCount;
        int maxHeight;
        int tunnelHeight;

        for (int i = 0; i < roads; i++)
        {
            tunnelCount = scan.nextInt();
            maxHeight = 1000000000;

            for (int j = 0; j < tunnelCount; j++)
            {
                tunnelHeight = scan.nextInt();
                maxHeight = Math.min(maxHeight, tunnelHeight);
            }

            if (maxHeight > height)
            {
                height = maxHeight;
                num = i + 1;
            }
        }
        System.out.println(num + " " + height);
    }

    public static void problem5()
    {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();

        int k = 0;
        int sum = 0;
        int mult = 1;

        while (num > 0)
        {
            k = num % 10;
            num /= 10;

            sum += k;
            mult *= k;
        }
        if (sum % 2 == 0 && mult % 2 ==0)
            System.out.println("дважды чётное число");
        else
            System.out.println("не дважды чётное число");
    }
}