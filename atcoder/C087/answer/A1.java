package atcoder.C087.answer;

import java.util.Scanner;

/**
 * 500원, 100원을 0부터 각각 주어진 갯수까지 순차적으로 구하여 장수 만큼의 금액을 총 금액에서 뺀다.
 * 그리고 나머지 금액에 나누기 50을 하여 해당 몫이 0 ~ 50원의 장수사이인 경우 주어진 총 금액을 만들 수 있다는 말이 되므로
 * cnt 갯수를 증가 시켜 최종적으로 cnt를 출력한다.
 */

public class A1 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int t = sc.nextInt();
            int cnt = 0;
            for (int i = 0; i <= a; i++) {
                for (int j = 0; j <= b; j++) {
                    int r = (t - ((i * 500) + (j * 100))) / 50;
                    if (r >= 0 && r <= c) {
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
        }
    }
}
