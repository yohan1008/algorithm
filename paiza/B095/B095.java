package paiza.B095;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 参加者分ループを回しながら、i番目の正しい音程と歌った音程を取りだして比較する。
 * 比較した結果を絶対値で算出し、マイナスさせる点数を求め、点数からマイナスする。
 * 最終的に計算完了した点数と最高点数を比較し、最高点数より高い場合はその点数を最高点数として記録する。
 *
 * @author kang yohan
 */

public class B095 {
    public static void main(String[] args) {
        //参加者
        int peopleNum;
        //曲の長さ
        int songLength;
        //正しい音程
        List<Integer> correctPitch = new ArrayList<>();
        //参加者の小節の音程
        List<List<Integer>> participantPitch = new ArrayList<>();

        //------------------------------
        // 入力
        //------------------------------
        try (Scanner sc = new Scanner(System.in)) {
            peopleNum = sc.nextInt();
            songLength = sc.nextInt();

            for (int i = 0; i < songLength; i++) {
                correctPitch.add(sc.nextInt());
            }

            for (int i = 0; i < peopleNum; i++) {
                List<Integer> pitch = new ArrayList<>();
                for (int j = 0; j < songLength; j++) {
                    pitch.add(sc.nextInt());
                }
                participantPitch.add(pitch);
            }
        }

        //------------------------------
        //処理及び出力
        //------------------------------
        System.out.println(calculatorMaxScore(correctPitch, participantPitch));

    }

    private static int calculatorMaxScore(List<Integer> correctPitch, List<List<Integer>> participantPitch) {
        int maxScore = 0;

        for (List<Integer> pitch : participantPitch) {
            int score = 100;
            int diff = 0;
            for (int i = 0; i < pitch.size(); i++) {
                diff = Math.abs(correctPitch.get(i) - pitch.get(i));
                if (diff <= 5) {
                    continue;
                } else if (diff <= 10) {
                    score = score - 1;
                } else if (diff <= 20) {
                    score = score - 2;
                } else if (diff <= 30) {
                    score = score - 3;
                } else {
                    score = score - 5;
                }

                if (score <= maxScore) {
                    break;
                }
            }

            if (score > maxScore) {
                maxScore = score;
            }
        }

        return maxScore;
    }
}
