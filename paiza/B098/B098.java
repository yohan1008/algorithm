package paiza.B098;

import java.util.*;

/**
 * 各コメントに伴う、時間帯ごとの「Good」リアクションの回数の増加分を配列で保存する。
 * そして、ループを周りながら、配列の中で対象期間内の「Good」リアクション回数の増加分を加算していき、バズっていると判定される回数以上であるか確認する。
 * もし、バズっていると判定される回数以上である場合は、判定されたときの時間をバズ判定結果の配列に格納する。
 * 最終的にバズ判定結果の配列を利用し、結果を出力する。
 *
 * また、全てのループでfor文を利用してしまうとネストが深くなり、複雑に見えてしまうため、コードの可読性を上げるために再帰ループを使用して、バズを検出する。
 *
 * @author kang yohan
 */

public class B098 {
    public static void main(String[] args) {
        //発言の件数
        int commentCnt;
        //監視する時間
        int monitorTime;
        //バズ判定における対象期間
        int targetPeriod;
        //バズと判定する「Good」アクションの回数
        int actionCnt;
        //時間ごとの「Good」アクションの増分
        int[][] goodActions;
        //バズの判定結果
        int[] buzzResults;

        //------------------------------------------------------------
        // 入力
        //------------------------------------------------------------
        try (Scanner sc = new Scanner(System.in)) {
            commentCnt = sc.nextInt();
            monitorTime = sc.nextInt();
            targetPeriod = sc.nextInt();
            actionCnt = sc.nextInt();
            goodActions = new int[commentCnt][monitorTime];
            buzzResults = new int[commentCnt];
            for (int i = 0; i < monitorTime; i++) {
                for (int j = 0; j < commentCnt; j++) {
                    goodActions[j][i] = sc.nextInt();
                }
            }
        }

        //------------------------------------------------------------
        // バズ検出
        //------------------------------------------------------------
        for (int i = 0; i < goodActions.length; i++) {
            buzzResults[i] = detectionBuzz(goodActions[i], 0, targetPeriod - 1, 0, 0, actionCnt);
        }

        //------------------------------------------------------------
        // 出力
        //------------------------------------------------------------
        for (int buzzResult : buzzResults) {
            if (buzzResult != 0) {
                System.out.println("yes " + buzzResult);
            } else {
                System.out.println("no " + buzzResult);
            }
        }
    }

    /**
     * 対象期間の開始時間から終了時間までの「Good」アクションの回数を加算して行く。
     * バズが検出されたら、検出された時間をreturnし、終了させ、バズが検出されなかった場合は0をreturnする。
     *
     * @param goodAction 各コメントでの「Good」アクションの増加分
     * @param startIndex 対象期間の開始時間
     * @param endIndex 対象期間の終了時間
     * @param checkIndex 現在、監視している時間帯
     * @param total 対象期間内での「Good」アクションの総計
     * @param actionCnt バズ判定の「Good」アクションの回数
     * @return バズったタイミングの時間（0の場合はバズ未発生）
     */
    private static int detectionBuzz(int[] goodAction, int startIndex, int endIndex, int checkIndex, int total, int actionCnt) {
        if (endIndex >= goodAction.length) {
            //最終的にバズが検出されなかったため'0'をreturnする。
            return 0;
        }

        total += goodAction[checkIndex];
        if (total >= actionCnt) {
            //バズったタイミング
            return ++checkIndex;
        }

        if (checkIndex >= endIndex) {
            //現対象期間でバズが検出されなかったため、次の対象期間を監視する。
            //対象期間の開始時間と、終了時間を更新し、「Good」アクション総計をリセットする。
            startIndex = ++startIndex;
            return detectionBuzz(goodAction, startIndex, ++endIndex, startIndex, 0, actionCnt);
        } else {
            //現対象期間内の次の時間帯を監視する。
            //監視する時間帯を更新する。
            return detectionBuzz(goodAction, startIndex, endIndex, ++checkIndex, total, actionCnt);
        }
    }
}