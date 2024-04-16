package paiza.B138;

import java.util.*;

/**
 * '.'で表示される座標は（後続の処理で周りの座標を求める必要があるため）リストで保存する。
 * そして'#'で表示される座標は（重複がないため）セットで保存する。
 *
 * '.'を保存したリストでループを回しながら、周りの座標を全て求め、'#'で表示される座標を持っているセットに全ての座標が含まれているか確認する。
 * もし、全ての座標が存在していれば、ドーナツの絵と判断し、合計数をカウントする。
 *
 * 最終的にドーナツの絵の合計数を出力する。
 *
 * @author kang yohan
 */

public class B138 {
    public static void main(String[] args) {
        //縦方向のマス数
        int rows;
        //横方向のマス数
        int columns;
        //'.'の座標
        List<List<Integer>> dots = new ArrayList<>();
        //'#'の座標
        Set<String> sharps = new HashSet<>();

        //------------------------------------------------------------
        // 入力
        //------------------------------------------------------------
        try (Scanner sc = new Scanner(System.in)) {
            rows = sc.nextInt();
            columns = sc.nextInt();

            for (int i = 0; i < rows; i++) {
                String str = sc.next();
                char[] charArr = str.toCharArray();
                for (int j = 0; j < columns; j++) {
                    if (charArr[j] == '#') {
                        sharps.add(i + " " + j);
                    } else if (i != 0) {
                        //縦が0の場合はドーナツの絵が表現できないため除外する
                        List<Integer> dot = new ArrayList<>();
                        dot.add(i);
                        dot.add(j);
                        dots.add(dot);
                    }
                }
            }
        }

        //------------------------------------------------------------
        // ドーナツのカウント
        //------------------------------------------------------------
        System.out.println(calcDonuts(dots, sharps));

    }

    private static int calcDonuts(List<List<Integer>> dots, Set<String> sharps) {
        int totalDonuts = 0;
        for (List<Integer> dot : dots) {
            int dotRow = dot.get(0);
            int dotColumn = dot.get(1);

            //'.'周りの座標を求める
            String leftUpper = (dotRow - 1) + " " + (dotColumn - 1);
            String middleUpper = (dotRow - 1) + " " + dotColumn;
            String rightUpper = (dotRow - 1) + " " + (dotColumn + 1);
            String leftMiddle = dotRow + " " + (dotColumn - 1);
            String rightMiddle = dotRow + " " + (dotColumn + 1);
            String leftLower = (dotRow + 1) + " " + (dotColumn - 1);
            String middleLower = (dotRow + 1) + " " + dotColumn;
            String rightLower = (dotRow + 1) + " " + (dotColumn + 1);

            if (sharps.containsAll(Set.of(leftUpper, middleUpper, rightUpper, leftMiddle, rightMiddle, leftLower, middleLower, rightLower))) {
                totalDonuts++;
            }
        }

        return totalDonuts;
    }
}
