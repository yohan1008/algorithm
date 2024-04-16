package paiza.C098;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C098 {
    public static void main(String[] args) {
        //人数
        int peopleNum;
        //最初に持っているボールの個数
        List<Integer> balls = new ArrayList<>();
        //パス回しの情報の数
        int passInfoNum;
        //パス回しの情報
        List<List<Integer>> passInfos = new ArrayList<>();
        //------------------------------------------
        //入力
        //------------------------------------------
        try (Scanner sc = new Scanner(System.in)) {
            peopleNum = sc.nextInt();
            for (int i = 0; i < peopleNum; i++) {
                balls.add(sc.nextInt());
            }
            passInfoNum = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < passInfoNum; i++) {
                String info = sc.nextLine();
                List<Integer> passInfo = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    passInfo.add(Integer.parseInt(info.split(" ")[j]));
                }
                passInfos.add(passInfo);
            }
        }

        //------------------------------------------
        //処理
        //------------------------------------------
        calcBallInfo(balls, passInfos);

        //------------------------------------------
        //出力
        //------------------------------------------
        for (Integer ball : balls) {
            System.out.println(ball);
        }
    }

    private static void calcBallInfo(List<Integer> balls, List<List<Integer>> passInfos) {
        for (List<Integer> passInfo : passInfos) {
            int passPerson = passInfo.get(0) - 1;
            int receivePerson = passInfo.get(1) - 1;
            int ballCnt = passInfo.get(2);

            int passCnt = ballCnt > balls.get(passPerson) ? balls.get(passPerson) : ballCnt;
            balls.set(passPerson, balls.get(passPerson) - passCnt);
            balls.set(receivePerson, balls.get(receivePerson) + passCnt);
        }
    }
}
