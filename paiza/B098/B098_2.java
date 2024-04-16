package paiza.B098;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class B098_2 {
    public static void main(String[] args) {
        // 自分の得意な言語で
        // Let's チャレンジ！！
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        // コメント数
        int commentCnt = Integer.parseInt(line[0]);
        // バズる条件（時間）
        int buzzConditionsTime = Integer.parseInt(line[2]);
        // バズる条件（良いね数）
        int buzzConditionsCnt = Integer.parseInt(line[3]);

        // レコードを配列にする
        List<String[]> allCommentLecord = new ArrayList<>();
        while (sc.hasNext()) {
            allCommentLecord.add(sc.nextLine().split(" "));
        }

        // バズった判定
        int total = 0;
        for (int i = 0; i < commentCnt; i++) {
            judgeBussComment(i, allCommentLecord, buzzConditionsTime, buzzConditionsCnt);
        }
    }

    /**
     * バズったか判定する<br>
     * バズっている場合はyes バズった時間<br>
     * バズっていない場合はno 0<br>
     * と出力
     * @param commentNo コメント番号
     * @param buzzLecord 全コメントレコード
     * @param buzzConditionsTime バズる条件（時間）
     * @param buzzConditionsCnt バズる条件（良いね数）
     */
    private static void judgeBussComment(int commentNo, List<String[]> buzzLecord, int buzzConditionsTime,
                                         int buzzConditionsCnt) {
        for (int i = 0; i <= buzzLecord.size() - buzzConditionsTime; i++) {
            int totalGood = 0;
            for (int j = 0; j < buzzConditionsTime; j++) {
                // 合計を計算
                totalGood += Integer.parseInt(buzzLecord.get(i + j)[commentNo]);
                if (totalGood >= buzzConditionsCnt) {
                    // バズったら処理終了
                    System.out.println("yes " + (i + j + 1));
                    return;
                }
            }
        }
        // バズってない場合の出力
        System.out.println("no 0");
    }
}