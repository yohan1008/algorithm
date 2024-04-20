package atcoder.A081.answer;

import java.util.Scanner;

/**
 * 주어진 삼의 자리 숫자(num) 중에 1의 갯수를 구하는 문제.
 * num을 문자열로 받아서 문자열중의 0을 replaceAll함수를 사용하여 제거한다.
 * 최종적으로 남아있는 문자열을 길이가 1이므로 최종적으로 해당 문자열의 길이을 출력한다.
 *
 * @author kangyohan
 */

public class A1 {
    public static void main(String[] args) {
        String str = "";
        try (Scanner sc = new Scanner(System.in)) {
            str = sc.next();
            str = str.replaceAll("0", "");
        }
        System.out.println(str.length());
    }
}
