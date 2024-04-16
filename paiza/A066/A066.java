package paiza.A066;

import java.util.*;

/**
 * 最大日数を求め、出勤日をSetに保持する。
 * ここで出勤日は順序が関係ないのと、重複が存在しないため、Setにする。
 *
 * そして、1日から最大日までループを回しながら、出勤日のSetにループを回る日が存在すれば、連続出勤日のカウントを加算し、
 * 存在しなければ、連続出勤日を初期化し、再度カウントする。この時、最大連続出勤日の場合は別の変数にその日数を保持する。
 *
 * 最終的に最大連続出勤日を出力する。
 *
 * @author kang yohan
 */

public class A066 {
    public static void main(String[] args) {
        //引き受けた仕事の数
        int workCnt;
        //仕事の日数
        int maxDays = 0;
        //仕事をする日
        Set<Integer> workDays = new HashSet<>();

        //------------------------------------
        //入力
        //------------------------------------
        try (Scanner sc = new Scanner(System.in)) {
            workCnt = Integer.parseInt(sc.nextLine());
            for (int i = 0; i < workCnt; i++) {
                String workDay = sc.nextLine();
                int start = Integer.parseInt(workDay.split(" ")[0]);
                int end = Integer.parseInt(workDay.split(" ")[1]);
                for (int j = start; j <= end; j++) {
                    workDays.add(j);
                    if (maxDays < j) {
                        maxDays = j;
                    }
                }
            }
        }

        //------------------------------------
        //連勤日数の計算及び出力
        //------------------------------------
        System.out.println(calcConsecutiveWorkDay(maxDays, workDays));
    }

    private static int calcConsecutiveWorkDay(int maxDays, Set<Integer> workDays) {
        int consecutiveWorkDay = 0;
        int maxConsecutiveWorkDay = 0;
        for (int i = 1; i <= maxDays; i++) {
            if (workDays.contains(i)) {
                consecutiveWorkDay++;
            } else {
                consecutiveWorkDay = 0;
            }
            //最大連続出勤日の場合は別の変数に保存
            if (consecutiveWorkDay > maxConsecutiveWorkDay) {
                maxConsecutiveWorkDay = consecutiveWorkDay;
            }
        }

        //最大連続出勤日が0の場合は一度も出勤日が途切れていないため、出勤日（consecutiveWorkDay）をreturnする。
        return maxConsecutiveWorkDay == 0 ? consecutiveWorkDay : maxConsecutiveWorkDay;
    }
}
