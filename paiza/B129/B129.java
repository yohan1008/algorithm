package paiza.B129;

import java.util.*;

public class B129 {
    public static void main(String[] args) {
        //作業回数
        int workNum = 0;
        //種類数
        int kindsNum = 0;
        //畑の行数
        int lineNum = 0;
        //畑の列数
        int columnNum = 0;
        //作業範囲
        List<List<Integer>> workScope = new ArrayList<>();
        //収穫数
        int[] resultHarvestNum;
        //作業後の畑の状態
        int[][] resultWork;

        //----------------------
        //入力
        //----------------------
        try (Scanner sc = new Scanner(System.in)) {
            workNum = sc.nextInt();
            kindsNum = sc.nextInt();
            lineNum = sc.nextInt();
            columnNum = sc.nextInt();

            resultHarvestNum = new int[kindsNum];
            resultWork = new int[lineNum][columnNum];

            for (int i = 0; i < workNum; i++) {
                List<Integer> work = new ArrayList<>();
                for (int j = 0; j < 5; j++) {
                    work.add(sc.nextInt());
                }
                workScope.add(work);
            }
        }

        //----------------------
        //作物を栽培
        //----------------------
        harvestWork(workScope, resultWork, resultHarvestNum);

        //----------------------
        //出力
        //----------------------
        for (int i = 0; i < resultHarvestNum.length; i++) {
            System.out.println(resultHarvestNum[i]);
        }

        for (int i = 0; i < resultWork.length; i++) {
            for (int j = 0; j < resultWork[i].length; j++) {
                System.out.print(resultWork[i][j] == 0 ? "." : resultWork[i][j]);
            }
            System.out.println();
        }
    }

    private static void harvestWork(List<List<Integer>> workScope, int[][] resultWork, int[] resultHarvestNum) {

        for (List<Integer> work : workScope) {
            //開始行数
            int lineStart = work.get(0);
            //終了行数
            int lineEnd = work.get(1);
            //開始列数
            int columnStart = work.get(2);
            //終了列数
            int columnEnd = work.get(3);
            //植える作物
            int cropKind = work.get(4);

            for (int i = lineStart - 1; i < lineEnd; i++) {
                //作業する畑の行数を取得
                int[] line = resultWork[i];
                for (int j = columnStart - 1; j < columnEnd; j++) {
                    //作業する畑の列数を取得
                    int harvest = line[j];
                    if (harvest != 0) {
                        //収穫があればカウントする
                        resultHarvestNum[harvest - 1] = ++resultHarvestNum[harvest - 1];
                    }
                    //作物を植える
                    line[j] = cropKind;
                }
            }
        }
    }
}
