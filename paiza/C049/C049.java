package paiza.C049;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C049 {
    public static void main(String[] args) {
        //ログの行数
        int logs;
        //エレベーター止まった階数
        List<Integer> stopFloor = new ArrayList<>();
        //----------------------------
        //入力
        //----------------------------
        try (Scanner sc = new Scanner(System.in)) {
            logs = sc.nextInt();
            for (int i = 0; i < logs; i++) {
                stopFloor.add(sc.nextInt());
            }
        }

        //----------------------------
        //処理及び出力
        //----------------------------
        System.out.println(calcMoveFloor(stopFloor));
    }

    private static int calcMoveFloor(List<Integer> stopFloor) {
        int total = 0;
        int curStopFloor = 1;
        for (int nextFloor : stopFloor) {
            total = total + Math.abs(curStopFloor - nextFloor);
            curStopFloor = nextFloor;
        }
        return total;
    }
}
