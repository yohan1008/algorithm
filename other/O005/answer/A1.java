package other.O005.answer;

/**
 * 이 문제는 브루트 포스 방식으로 1과 8로만 이루어진 8자리 이하의 모든 수를 만들어서, 각 수를 181로 나눈 나머지가 8인지 확인하고,그 중에서 최대값을 찾는다.
 * ※ 브루트(Brute) : 무식한 + 포스(force) : 힘 즉, 발생할 수 있는 모든 경우를 무식하게 탐색하여 결과를 도출해낸다는 뜻
 * <p>
 * 여기서 1과 8로만 이루어진 8자리 이하의 모든 수는 재귀함수를 이용하여 지속적으로 1과 8을 붙여나가여 만든다.
 * 그리고 수를 만들어 나가면서 181로 나눈 나먼지가 8인 경우는 해당 값이 최대값인지 확인하여 해당되는 경우는 최대값을 갱신한다.
 * 최종적으로 도출해낸 최대값을 출력한다.
 */

public class A1 {
    private static int max = 0;

    public static void main(String[] args) {
        generateNumbers("");
        System.out.println(max);
    }

    private static void generateNumbers(String n) {
        if (n.length() > 8) {
            return;
        } else if (n.length() != 0) {
            int pn = Integer.parseInt(n);
            if (pn % 181 == 8 && pn > max) {
                max = pn;
            }
        }

        generateNumbers(n + "1");
        generateNumbers(n + "8");
    }
}
