package atcoder.B088.answer;

import java.util.*;

public class A2 {
    public static void main(String[] args) {
        int cardCnt;
        Integer[] arr;
        int aliceScore = 0;
        int bobScore = 0;

        try (Scanner sc = new Scanner(System.in)) {
            cardCnt = sc.nextInt();
            arr = new Integer[cardCnt];
            for (int i = 0; i < cardCnt; i++) {
                arr[i] = sc.nextInt();
            }
        }
        Arrays.sort(arr, Comparator.reverseOrder());

        for (int i = 0; i < arr.length; i += 2) {
            aliceScore += arr[i];
        }

        for (int j = 1; j < arr.length; j += 2) {
            bobScore += arr[j];
        }

        System.out.println(aliceScore - bobScore);
    }
}
