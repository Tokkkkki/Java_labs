import java.util.Scanner;
import java.util.Arrays;

public class Main
{
    public static void main(String[] args)
    {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
    }

    public static void task1()
    {
        String usedChars = "";
        String inputString = "aabcccabbabcc";

        int start = 0;
        int maxStart = 0;
        int maxLength = 0;
        int currentLength = 0;

        for (int i = 0; i < inputString.length(); i++)
        {
            String currentChar = "" + inputString.charAt(i);

            if (usedChars.contains(currentChar))
            {
                start = i;
                usedChars = "";
            }

            usedChars = usedChars + currentChar;

            currentLength = i - start + 1;

            if (currentLength > maxLength)
            {
                maxLength = currentLength;
                maxStart = start;
            }
        }

        System.out.println(inputString.substring(maxStart, maxStart + maxLength));
    }

    public static void task2()
    {
        int[] arr1 = {3, 5, 7, 9, 11};
        int[] arr2 = {2, 4, 6, 8, 10};

        int len1 = arr1.length;
        int len2 = arr2.length;
        int[] Array = new int[len1 + len2];
        int count = 0;

        for (int i = 0; i < len1; i++)
        {
            Array[i] = arr1[i];
            count++;
        }

        for (int i = 0; i < len2; i++)
        {
            Array[count++] = arr2[i];
        }

        for (int i = 0; i < Array.length; i++)
        {
            for (int j = 0; j < Array.length; j++)
            {
                if (Array[i] < Array[j])
                {
                    int temp = Array[i];
                    Array[i] = Array[j];
                    Array[j] = temp;
                }
            }
        }

        for (int elem : Array)
        {
            System.out.println(elem);
        }
    }

    public static void task3()
    {
        int[] Array = {6, 1, -4, 2, 0, 7};

        int maxSum = -100000000;
        int sum = 0;

        for (int i = 0 ; i < Array.length; i++)
        {
            for (int j = Array.length; j >= 0; j--)
            {
                sum = 0;
                for (int k = i; k < j; k++)
                    sum += Array[k];
                if (maxSum < sum)
                    maxSum = sum;
            }
        }

        System.out.println(maxSum);
    }

    public static void task4()
    {
        int n = 3;
        int[][] arr =
                {{1, 2, 3},
                 {4, 5, 6},
                 {7, 8, 9}};

        int[][] rotateR = new int [n][n];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                rotateR[i][j] = arr[j][i];
            }
        }

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n/2; j++)
            {
                int temp = rotateR[i][j];
                rotateR[i][j] = rotateR[i][n - 1 - j];
                rotateR[i][n - 1 - j] = temp;
            }
        }

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                System.out.print(rotateR[i][j] + " ");
            }

            System.out.println();
        }
    }

    public static void task5()
    {
        int[] arr = {2, 5, 9, 1, 6};
        int target = 7;

        boolean flag = false;

        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr.length; j++)
            {
                if (arr[i] + arr[j] == target)
                {
                    System.out.println(arr[i]);
                    System.out.println(arr[j]);
                    flag = true;
                    break;
                }

                else
                {
                    flag = false;
                }
            }
        }

        if (!flag)
        {
            System.out.println("null");
        }
    }

    public static void task6()
    {
        int n = 3;
        int sum = 0;

        int[][] arr =
                {{1, 2, 3},
                 {4, 5, 6},
                 {7, 8, 9}};

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j ++)
            {
                sum += arr[i][j];
            }

        System.out.println(sum);
    }

    public static void task7()
    {
        int[][] arr =
                {{1, 2, 3},
                 {4, 5, 6},
                 {7, 8, 9}};

        int[] ans = new int [arr.length];

        for (int i = 0; i < arr.length; i++)
        {
            ans[i] = arr[i][0];
            for (int j = 0; j < arr.length; j++)
            {
                if (ans[i] < arr[i][j])
                {
                    ans[i] = arr[i][j];
                }
            }
        }

        System.out.println(Arrays.toString(ans));
    }

    public static void task8()
    {
        int n = 3;
        int[][] rotateL = new int [n][n];
        int[][] arr =
                {{1, 2, 3},
                 {4, 5, 6},
                 {7, 8, 9}};

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                rotateL[i][j] = arr[j][i];
            }
        }

        for (int i = 0; i < n/2; i++)
        {
            for (int j = 0; j < n; j++)
            {
                int temp = rotateL[i][j];
                rotateL[i][j] = rotateL[n - 1 - i][j];
                rotateL[n - 1 - i][j] = temp;
            }
        }

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                System.out.print(rotateL[i][j] + " ");
            }

            System.out.println();
        }
    }
}