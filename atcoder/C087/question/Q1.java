package atcoder.C087.question;

import java.util.Scanner;

/**
 * 문제를 푼 횟수 : 10
 * <p>
 * 500원 a장 100원 b장 50원 c장이 주어진다.
 * 그리고 주어진 돈으로 t원을 만들 수 있는 경우의 수를 구하시오.
 * 단, 여기서 t원은 항상 50의 배수이다.
 * <p>
 * 예시1)
 * 2
 * 2
 * 2
 * 100
 * →
 * 2
 * <p>
 * 예시2)
 * 5
 * 1
 * 0
 * 150
 * →
 * 0
 * <p>
 * 예시3)
 * 30
 * 40
 * 50
 * 6000
 * →
 * 213
 */

public class Q1 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int t = sc.nextInt();
            int cnt = 0;
            for (int i = 0; i <= a; i++) {
                for (int j = 0; j <= b; j++) {
                    int r = (t - ((500 * i) + (100 * j))) / 50;
                    if (r >= 0 && r <= c) {
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
        }
    }
}
