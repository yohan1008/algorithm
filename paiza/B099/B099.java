package paiza.B099;

import java.util.*;

/**
 * 通れるか通れないかを保存するbooleanのマップを定義する。
 * 各ルートの降水量を取り出し、通ることのできない降水量を超えているか確認する。
 * ここで通ることのできない降水量を超えている場合はマップにtrueで記録を更新する。
 * 最終的にマップをループさせ、falseで記録されてある、ルートの番号を出力する。
 * 全ルートがtrueの場合は、waitを出力する。
 *
 * @author kangyohan
 */

public class B099 {
    public static void main(String[] args) {
        //ルート
        List<List<Integer>> maps = new ArrayList<>();
        //通勤可能なルート
        boolean[] mapsFlag;
        //マップのサイズ
        int mapSize;
        //降水量
        int precipitation;

        //-----------------------------------------
        //入力
        //-----------------------------------------
        try (Scanner sc = new Scanner(System.in)) {
            mapSize = sc.nextInt();
            precipitation = sc.nextInt();
            mapsFlag = new boolean[mapSize];
            for (int i = 0; i < mapSize; i++) {
                ArrayList<Integer> map = new ArrayList<>();
                for (int j = 0; j < mapSize; j++) {
                    map.add(sc.nextInt());
                }
                maps.add(map);
            }
        }

        //-----------------------------------------
        //処理
        //-----------------------------------------
        calcCommutingPassage(precipitation, maps, mapsFlag);

        //-----------------------------------------
        //出力
        //-----------------------------------------
        boolean noExistPassage = true;
        for (int i = 0; i < mapsFlag.length; i++) {
            if (!mapsFlag[i]) {
                String output = noExistPassage ? Integer.toString(i + 1) : " " + (i + 1);
                System.out.print(output);
                noExistPassage = false;
            }
        }

        if (noExistPassage) {
            System.out.print("wait");
        }

    }

    private static void calcCommutingPassage(int precipitation, List<List<Integer>> maps, boolean[] mapFlag) {
        for (List<Integer> map : maps) {
            for (int i = 0; i < map.size(); i++) {
                if (mapFlag[i]) {
                    continue;
                }
                if (map.get(i) >= precipitation) {
                    mapFlag[i] = true;
                }
            }
        }
    }
}