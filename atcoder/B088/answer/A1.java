package atcoder.B088.answer;

import java.util.*;

public class A1 {
    public static void main(String[] args) {
        int cardCnt;
        List<Integer> cards = new ArrayList<>();
        int aliceScore = 0;
        int bobScore = 0;

        try (Scanner sc = new Scanner(System.in)) {
            cardCnt = sc.nextInt();
            for (int i = 0; i < cardCnt; i++) {
                cards.add(sc.nextInt());
            }
        }

        for (int i = 0; i < cardCnt; i++) {
            int maxScore = Collections.max(cards);
            if (i % 2 == 0) {
                aliceScore += maxScore;
            } else if (i % 2 == 1) {
                bobScore += maxScore;
            }
            cards.remove(Integer.valueOf(maxScore));
        }

        System.out.println(aliceScore - bobScore);
    }
}
