package atcoder.B083.answer;

import java.util.Scanner;

public class A1 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int num = sc.nextInt();
            int min = sc.nextInt();
            int max = sc.nextInt();
            int sum = 0;

            for (int i = 1; i <= num; i++) {
                int digSum = 0;
                int n = i;
                while (n > 0) {
                    digSum += n % 10;
                    n = n / 10;
                }

                if (min <= digSum && max >= digSum) {
                    sum += i;
                }
            }

            System.out.println(sum);
        }
    }
}
