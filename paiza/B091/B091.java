package paiza.B091;

import java.util.*;

/**
 * マップの標高を二次元配列に格納し、各マスの標高をループを回しながら、周りの標高と比較する。
 * 周りの標高を求める際、XまたはYの座標が 0 以下である場合は標高を 0 と計算する。
 * 確認対象の標高が周りの標高より高い場合は、山頂と判定した結果を保存するListに格納する。
 * 最終的に、山頂と判定した結果を保存するListを降順でソートし、出力する。
 *
 * @author kangyohan
 */

public class B091 {
    public static void main(String[] args) {
        //地図の大きさ
        int mapSize;
        //標高
        int[][] elevations;
        //山頂
        List<Integer> mountains = new ArrayList<>();

        //------------------------------------------------------------
        // 入力
        //------------------------------------------------------------
        try (Scanner sc = new Scanner(System.in)) {
            mapSize = sc.nextInt();
            elevations = new int[mapSize][mapSize];
            for (int i = 0; i < mapSize; i++) {
                for (int j = 0; j < mapSize; j++) {
                    elevations[i][j] = sc.nextInt();
                }
            }
        }

        //------------------------------------------------------------
        // 山頂であるか確認する
        //------------------------------------------------------------
        isMnt(elevations, mountains);

        //------------------------------------------------------------
        // 出力
        //------------------------------------------------------------
        for (Integer mountain : mountains) {
            System.out.println(mountain);
        }
    }

    private static void isMnt(int[][] elevations, List<Integer> mountains) {
        for (int i = 0; i < elevations.length; i++) {
            for (int j = 0; j < elevations.length; j++) {
                int elevation = elevations[i][j];
                List<Integer> aroundElevations = new ArrayList<>();

                //周りの座標を求める
                //XまたはYの座標が 0 以下である場合は標高を 0 と計算する。
                int upper = i - 1 < 0 ? 0 : elevations[i - 1][j];
                int left = j - 1 < 0 ? 0 : elevations[i][j - 1];
                int lower = i + 1 >= elevations.length ? 0 : elevations[i + 1][j];
                int right = j + 1 >= elevations.length ? 0 : elevations[i][j + 1];

                aroundElevations.add(upper);
                aroundElevations.add(left);
                aroundElevations.add(lower);
                aroundElevations.add(right);

                if (aroundElevations.stream().allMatch(aroundElevation -> aroundElevation < elevation)) {
                    //確認対象の標高が周りの標高より高い場合
                    mountains.add(elevation);
                }
            }
        }

        Collections.sort(mountains, Collections.reverseOrder());
    }
}
