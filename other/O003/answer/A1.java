package other.O003.answer;

public class A1 {
    public static void main(String[] args) {
        int n = 40;
        int[] sequence = new int[n];
        sequence[0] = 1;
        sequence[1] = 2;
        for (int i = 2; i < n; i++) {
            sequence[i] = sequence[i - 1] + sequence[i - 2] - 1;
        }
        System.out.println(sequence[n - 1]);
    }
}
