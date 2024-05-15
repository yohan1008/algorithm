package atcoder.C085.answer;

import java.util.Scanner;

public class A1 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            int t = sc.nextInt();

            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= n - i; j++) {
                    int rn = n - (i + j);
                    int rt = i * 10000 + j * 5000 + rn * 1000;
                    if (t == rt) {
                        System.out.println(i + " " + j + " " + rn);
                        return;
                    }
                }
            }

            System.out.println("-1 -1 -1");
        }
    }
}
