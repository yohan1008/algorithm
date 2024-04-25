package other.O002.answer;

public class A1 {
    public static void main(String[] args) {
        int cnt = 0;
        for (int i = 1; i <= 16777216; i++) {
            String str = "";
            if (i % 3 == 0 && i % 5 == 0) {
                str = "FizzBuzz";
            } else if (i % 3 == 0) {
                str = "Fizz";
            } else if (i % 5 == 0) {
                str = "Buzz";
            } else {
                str = Integer.toString(i);
            }

            for (char c : str.toCharArray()) {
                if (c == '1') {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}
