package paiza.B096;

import java.util.*;

/**
 * 最初に爆弾を設置する座標をListに保管する。
 * そしてその爆弾を設置した座標を元に、爆風が広がる座標を求め、Setに保存する。
 * 最終的にSetに保存されてある、データの数を出力する。
 *
 * @author kang yohan
 */
public class B096 {
    public static void main(String[] args) {
        //行数
        int rowNum;
        //列数
        int columnNum;
        //爆弾を設置している座標
        List<List<Integer>> bombs = new ArrayList<>();
        //爆風の広がる座標
        Set<String> bombSections = new HashSet<>();

        //------------------------------------------------------------
        // 入力
        //------------------------------------------------------------
        try (Scanner sc = new Scanner(System.in)) {
            rowNum = sc.nextInt();
            columnNum = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < rowNum; i++) {
                String row = sc.nextLine();
                char[] charTmp = row.toCharArray();
                for (int j = 0; j < charTmp.length; j++) {
                    if (charTmp[j] == '#') {
                        List<Integer> bomb = new LinkedList<>();
                        bomb.add(i);
                        bomb.add(j);
                        bombs.add(bomb);
                    }
                }
            }
        }

        //------------------------------------------------------------
        // 爆弾が広がるマスを求める
        //------------------------------------------------------------
        makeBombSection(rowNum, columnNum, bombs, bombSections);

        //------------------------------------------------------------
        // 出力
        //------------------------------------------------------------
        System.out.println(bombSections.size());
    }

    /**
     * 設置された爆弾の座標から、爆風の位置を求める。
     * 爆風の位置は[爆弾が設置れた座標のx, 0から表の列数分]、[0から表の行数分,爆弾が設置れた座標のy]となる。
     * <p>
     * 　行5列8の表で爆弾が設置されている座標が[1,2]だとすると爆風が広がる座標は以下となる。
     * [0,8][1,8][2,8][3,8][4,8]
     * [1,0][1,1][1,2][1,3][1,4][1,5][1,6][1,7]
     * <p>
     * そして、爆風の広がる座標をSetに保存する。
     * ※重複は許可されないため、Setを利用
     *
     * @param rowNum
     * @param columnNum
     * @param bombs
     * @param bombSections
     */
    private static void makeBombSection(int rowNum, int columnNum, List<List<Integer>> bombs, Set<String> bombSections) {
        int repeatNum = rowNum > columnNum ? rowNum : columnNum;

        for (List<Integer> bomb : bombs) {
            int rowBombSec = bomb.get(0);
            int colBombSec = bomb.get(1);

            for (int i = 0; i < repeatNum; i++) {
                String bombSectionRow = "";
                if (i < columnNum) {
                    bombSectionRow = rowBombSec + " " + i;
                }

                String bombSectionCol = "";
                if (i < rowNum) {
                    bombSectionCol = i + " " + colBombSec;
                }

                if (!bombSectionRow.isEmpty()) {
                    bombSections.add(bombSectionRow);
                }

                if (!bombSectionCol.isEmpty()) {
                    bombSections.add(bombSectionCol);
                }
            }
        }
    }
}
