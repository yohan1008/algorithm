package paiza.B102;

import java.util.*;

/**
 * 黒い画素と白い画素をSetに保持をする。座標は順序が関係なく、重複がないため、Setを使用する。
 * そして、オペレーションによって以下の処理を行う。
 *
 * ・オペレーションが膨張 (Dilation)の場合
 * 黒い画素の周辺の座標を求める。
 * 黒い座標が保持してあるSetにAddする。
 * 白い座標が保持してあるSetからremoveする。
 *
 * ・オペレーションが収縮 (Erosion) の場合
 * 白い画素の周辺の座標を求める。
 * 白い座標が保持してあるSetにAddする。
 * 黒い座標が保持してあるSetからremoveする。
 *
 * 最終的に黒い座標を保持するSetに存在する座標は＃で出力し、存在しない場合は.で出力させる。
 *
 * @author kang yohan
 */
public class B102 {
    public static void main(String[] args) {
        //縦
        int rows;
        //横
        int columns;
        //回数
        int turn;
        //オペレーション
        String operations;
        //黒い画素
        Set<String> blackPixel = new HashSet<>();
        //白い画素
        Set<String> whitePixel = new HashSet<>();
        //------------------------------------
        //入力
        //------------------------------------
        try (Scanner sc = new Scanner(System.in)) {
            rows = sc.nextInt();
            columns = sc.nextInt();
            turn = sc.nextInt();
            for (int i = 0; i < rows; i++) {
                String str = sc.next();
                char[] charArr = str.toCharArray();
                for (int j = 0; j < columns; j++) {
                    if (charArr[j] == '#') {
                        blackPixel.add(i + " " + j);
                    } else {
                        whitePixel.add(i + " " + j);
                    }
                }
            }
            operations = sc.next();
        }

        //------------------------------------
        //処理
        //------------------------------------
        dilationErosion(rows, columns, turn, operations, blackPixel, whitePixel);

        //------------------------------------
        //出力
        //------------------------------------
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                String pixel = blackPixel.contains(i + " " + j) ? "#" : ".";
                System.out.print(pixel);
            }
            System.out.println();
        }
    }

    private static void dilationErosion(int rows, int columns, int turn, String operations, Set<String> blackPixel, Set<String> whitePixel) {
        for (int i = 0; i < turn; i++) {
            Set<String> targetPixel;
            Set<String> nonTargetPixel;
            Set<String> manipulationPixel = new HashSet<>();
            //オペレーション
            char operation = operations.toCharArray()[i];

            if (operation == 'D') {
                targetPixel = blackPixel;
                nonTargetPixel = whitePixel;
            } else {
                targetPixel = whitePixel;
                nonTargetPixel = blackPixel;
            }

            for (String pixel : targetPixel) {
                int x = Integer.parseInt(pixel.split(" ")[0]);
                int y = Integer.parseInt(pixel.split(" ")[1]);

                //周辺の座標を求める。xまたはyの座標が0以下の場合は0に、そしてマスの大きさを超える場合はマスの大きさにセットして上げる。
                String upper = x < 0 ? 0 + " " + y : (x - 1) + " " + y;
                String left = y < 0 ? x + " " + y : x + " " + (y - 1);
                String under = x >= rows - 1 ? (rows - 1) + " " + y : (x + 1) + " " + y;
                String right = y >= columns - 1 ? x + " " + (columns - 1) : x + " " + (y + 1);

                manipulationPixel.addAll(Arrays.asList(upper, left, right, under));
            }

            targetPixel.addAll(manipulationPixel);
            nonTargetPixel.removeAll(manipulationPixel);
        }
    }
}
