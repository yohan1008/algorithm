package atcoder.B081.answer;

import java.util.Scanner;

/**
 * 짝수인지 확인을 하기 위해서는 10진법을 2진법으로 변환 후 최소위가 0인지를 확인하면 된다.
 * 또한 해당 숫자가 2로 몇번 나눠지는지를 알아내기 위해서는 아래와 같이 뒤에서 0이 몇개 존재하는지 갯수를 구하면 된다.
 *
 * 11001000 → 0의 자릿수는 200(최초 숫자)이 2로 나눠떨어지는지 확인할 수 있음
 * 11001000 → 1의 자릿수는 100(200을 2로 나눈 숫자)이 2로 나눠떨어지는지 확인할 수 있음
 * 11001000 → 2의 자릿수는 50(100을 2로 나눈 숫자)이 2로 나눠떨어지는지 확인할 수 있음
 * .....
 *
 * 예를 들어 12라는 숫자는 12 → 6 → 3의 과정으로 2번 2로 나눠떨어진다.
 * 2진법에서 최소위에서 부터의 0의 갯수를 확인해보게 되면 12는 1100이므로 0이 최소위에서 부터 2개 존재하는 것을 확인할 수 있다.
 *
 * 또한 주어지는 숫자가 여러개인 경우는 주어진 모든 숫자를 or연산을 진행하여 나온 숫자를 이진법으로 변환 후 최소위에서 부터의 0의
 * 갯수를 구하게 되면 모든 숫자를 한번에 2로 몇번 나눠떨어지는지 확인할 수 있다.
 *
 * or연산을 사용하는 이유는 1과0인 경우는 1이 되기 때문에 주어진 숫자 중에 홀수가 하나라도 있게 되면 이진법으로 변환시 최소위가 1이
 * 되므로 홀수가 존재여부를 쉽게 확인할 수 있기 때문이다.
 * ※ or계산의 예 → 4(0100) | 5(0101) = 5(0101)
 *
 * 따라서 준어진 숫자를 모두 or연산을 진행하여
 * 도출해 낸 결과값을 이진법으로 변환 후
 * 최종적으로 최소위에서 부터의 0의 갯수를 출력하면 해당 문제를 쉽게 풀 수 있다.
 */

public class A1 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            int b = 0;
            for (int i = 0; i < n; i++) {
                b |= sc.nextInt();
            }
            System.out.println(Integer.numberOfTrailingZeros(b));
        }
    }
}
