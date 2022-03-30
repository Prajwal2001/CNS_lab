import java.util.Scanner;

public class CRC {
    public static void main(String args[]) {
        int data_len, divisor_len, divisor[], data[], div[], rem[], crc[], i, tot_len;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of data bits: ");
        data_len = scanner.nextInt();
        data = new int[data_len];

        System.out.println("Enter the data bits: ");
        for (i = 0; i < data_len; i++)
            data[i] = scanner.nextInt();

        System.out.print("Enter the number of divisor bits: ");
        divisor_len = scanner.nextInt();
        divisor = new int[divisor_len];

        System.out.println("Enter the divisor bits: ");
        for (i = 0; i < divisor_len; i++)
            divisor[i] = scanner.nextInt();

        tot_len = data_len + divisor_len - 1;
        div = new int[tot_len];
        rem = new int[tot_len];
        crc = new int[tot_len];

        for (i = 0; i < data_len; i++) {
            div[i] = data[i];
            rem[i] = data[i];
        }

        System.out.print("Data bits after appending 0's are: ");
        for (int j : div)
            System.out.print(j);
        System.out.println();

        rem = divide(divisor, rem);

        for (i = 0; i < tot_len; i++)
            crc[i] = div[i] ^ rem[i];

        System.out.print("CRC code: ");
        for (i = 0; i < tot_len; i++)
            System.out.print(crc[i]);
        System.out.println();

        System.out.println("Enter the " + tot_len + " bit CRC code: ");
        for (i = 0; i < tot_len; i++)
            crc[i] = scanner.nextInt();

        rem = divide(divisor, crc);

        boolean flag = true;

        for (i = 0; i < tot_len; i++)
            if (rem[i] != 0) {
                System.out.println("Error");
                flag = false;
                break;
            }
        if (flag)
            System.out.println("No Error");

    }

    public static int[] divide(int[] divisor, int[] rem) {
        while (true) {
            int i, cur = 0;
            for (i = 0; i < divisor.length; i++)
                rem[cur + i] = rem[cur + i] ^ divisor[i];
            while (rem[cur] == 0 && cur != rem.length - 1)
                cur++;
            if (rem.length - cur > divisor.length)
                break;
        }
        return rem;
    }
}
