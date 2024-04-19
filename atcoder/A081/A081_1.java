package atcoder.A081;

import java.util.Scanner;

/**
 * 주어진 삼의 자리 숫자(num) 중에 1이 갯수를 구하는 문제.
 * num을 10으로 나눠 1의 자리가 0인지 1인지 판별하여 1인 경우 cnt를 1로 증가 시킨다.
 * 그 후 num을 10으로 나눠 몫을 num에 대입하여 1의 자릿수를 하나씩 없앤다.
 * 위의 과정을 3번 반복하여 최종적으로 cnt를 출력한다.
 *
 * @author kangyohan
 */

public class A081_1 {
    public static void main(String[] args) {
        int cnt = 0;

        try (Scanner sc = new Scanner(System.in)) {
            int num = sc.nextInt();
            for (int i = 0; i < 3; i++) {
                int s = num % 10;
                if (s == 1) {
                    cnt++;
                }
                num = num / 10;
            }
        }

        System.out.println(cnt);
    }
}
