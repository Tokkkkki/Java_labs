
import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        task1();
        task2();
        task3();
        task4();
        task5();
    }

    public static void task1()
    {
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        //int input = 10;
        int k = 0;

        while (i != 1)
        {
            if (i % 2 == 0)
                i = i / 2;
            else if (i % 2 == 1)
                i = 3 * i + 1;
            else
                break;

            k++;
        }
        System.out.println(k);
    }

    public static void task2()
    {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] num = new int[n];

        for(int i = 0; i < n; i++)
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

    public static void task3()
    {
        Scanner scan = new Scanner(System.in);

        int x = 0;
        int y = 0;

        int xJewel = scan.nextInt();
        int yJewel = scan.nextInt();

        String worldSide = "";
        int step;
        int instructions = 0;

        while (true)
        {
            worldSide = scan.next();
            if (worldSide.equals("стоп"))
                break;
            step = scan.nextInt();

            if (xJewel == x && yJewel ==y)
                continue;

            switch (worldSide)
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

            instructions ++;
        }

        System.out.println(instructions);
    }

    public static void task4()
    {
        Scanner scan = new Scanner(System.in);
        int roads = scan.nextInt();

        int height = 0;
        int numRo = 0;

        int tunnelsCount;
        int maxHeight;
        int tunnelH;

        for (int i = 0; i < roads; i++)
        {
            tunnelsCount = scan.nextInt();
            maxHeight = 1000000000;

            for (int j = 0; j < tunnelsCount; j++)
            {
                tunnelH = scan.nextInt();
                maxHeight = Math.min(maxHeight, tunnelH);
            }

            if (maxHeight > height)
            {
                height = maxHeight;
                numRo = i + 1;
            }
        }

        System.out.println(numRo + " " + height);
    }

    public static void task5()
    {
        Scanner scan = new Scanner(System.in);

        int num = scan.nextInt();
        // boolean flag = true;

        int a = 0;
        //int b = 0;
        //int c = 0;

        int sum = 0;
        int prod = 1;

        while (num > 0)
        {
            a = num % 10;
            num /= 10;

            sum += a;
            prod *= a;

        }
        if (sum % 2 == 0 && prod % 2 ==0)
            System.out.println("дважды чётное");
        else
            System.out.println("не дважды чётное");
    }

}










//task 1
/*
import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        //int input = 10;
        int k = 0;

        while (i != 1)
        {
            if (i % 2 == 0)
                i = i / 2;
            else if (i % 2 == 1)
                i = 3 * i + 1;
            else
                break;

            k++;
        }
    System.out.println(k);
    }
} //ввод 10 вывод 6
*/

//task 2
/*
import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] num = new int[n];

        for(int i = 0; i < n; i++)
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
}//ввод 4 1 2 3 4 вывод -2
*/

//task 3
/*
import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        int x = 0;
        int y = 0;

        int xJewel = scan.nextInt();
        int yJewel = scan.nextInt();

        String worldSide = "";
        int step;
        int instructions = 0;

        while (true)
        {
            worldSide = scan.next();
            if (worldSide.equals("стоп"))
                break;
            step = scan.nextInt();

            if (xJewel == x && yJewel ==y)
                continue;

            switch (worldSide)
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

            instructions ++;
        }

        System.out.println(instructions);
    }
} //ввод -2 9 север 9 запад 2 восток 17 стоп    вывод 2
*/

//task 4
/*
import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int roads = scan.nextInt();

        int height = 0;
        int numRo = 0;

        int tunnelsCount;
        int maxHeight;
        int tunnelH;

        for (int i = 0; i < roads; i++)
        {
            tunnelsCount = scan.nextInt();
            maxHeight = 1000000000;

            for (int j = 0; j < tunnelsCount; j++)
            {
                tunnelH = scan.nextInt();
                maxHeight = Math.min(maxHeight, tunnelH);
            }

            if (maxHeight > height)
            {
                height = maxHeight;
                numRo = i + 1;
            }
        }

        System.out.println(numRo + " " + height);
    }
} //ввод 2 3 470 430 465 2 451 450     вывод  2 450
 */

//task 5
/*
import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        int num = scan.nextInt();
       // boolean flag = true;

        int a = 0;
        //int b = 0;
        //int c = 0;

        int sum = 0;
        int prod = 1;

        while (num > 0)
        {
            a = num % 10;
            num /= 10;

            sum += a;
            prod *= a;

        }
        if (sum % 2 == 0 && prod % 2 ==0)
            System.out.println("дважды чётное");
        else
            System.out.println("не дважды чётное");
    }
} //ввод  вывод
 */