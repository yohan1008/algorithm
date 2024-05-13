package atcoder.B085.answer;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class A1 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int num = sc.nextInt();
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < num; i++) {
                set.add(sc.nextInt());
            }
            System.out.println(set.size());
        }
    }
}
