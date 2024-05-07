package atcoder.B088.answer;

import java.util.*;

public class A1 {
    public static void main(String[] args) {
        int cardCnt;
        List<Integer> set = new ArrayList<>();
        int aliceScore = 0;
        int bobScore = 0;

        try (Scanner sc = new Scanner(System.in)) {
            cardCnt = sc.nextInt();
            for (int i = 0; i < cardCnt; i++) {
                set.add(sc.nextInt());
            }
        }

        for (int i = 0; i < cardCnt; i++) {
            if (i % 2 == 0) {
                aliceScore += Collections.max(set);
                set.remove(Collections.max(set));
            } else if (i % 2 == 1) {
                bobScore += Collections.max(set);
                set.remove(Collections.max(set));
            }
        }

        System.out.println(aliceScore - bobScore);
    }
}
