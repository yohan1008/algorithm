package other.O001.answer;

/**
 * StringBuilder를 사용하여 반복문을 100000번 돌아 문자를 이어붙이다.
 * 여기서 실제로 구하면되는 정답은 88888번 부터 20번째 문자인 88907번째 문자까지 이므로
 * 88908번째 문자이상이 되면 반복문에서 빠져나와
 * 최종적으로 이어붙여진 문자를 substring메소드를 사용하여 88888번째 문자부터 88907번째 문자까지를 출력한다.
 *
 * @author kangyohan
 */

public class A1 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 100000; i++) {
            sb.append(i).append("SHEEP");
            if (sb.length() > 88907) {
                break;
            }
        }
        System.out.println(sb.substring(88887, 88907));
    }
}
