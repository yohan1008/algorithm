package paiza.B128;

import java.util.*;

/**
 * 最初に与えられるbarcodeNum整数の値を元に'#'で表示すべきbarcodeの座標を計算し、マップで保存する。
 * 最終的にbarcodeの座標の数分ループさせ、マップで保存されてある座標は'#'で表示し、保存されていない座標は'.'で表示する。
 *
 * @author kangyohan
 */

public class B128 {
    public static void main(String[] args) {
        //二次元バーコードの中、シャープで表現すべき個数(String)
        String barcodeNum;
        //二次元バーコードの中、シャープで表現すべき個数(List)
        List<Integer> barcodeNumList = new ArrayList<>();
        //二次元バーコードの中、シャープで表現すべき座標
        Set<String>  hashCoordinate = new LinkedHashSet<String>();

        //------------------------------------------------------------
        // 入力
        //------------------------------------------------------------
        try (Scanner sc = new Scanner(System.in)) {
            barcodeNum = sc.nextLine();
            for(int i = 0 ; i < barcodeNum.length() ; i++) {
                barcodeNumList.add(Character.getNumericValue(barcodeNum.charAt(i)));
            }
        }

        //------------------------------------------------------------
        // 処理
        //------------------------------------------------------------
        makeBarcode(hashCoordinate, barcodeNumList);

        //------------------------------------------------------------
        // 出力
        //------------------------------------------------------------
        for (int i = 0; i < barcodeNumList.size(); i++) {
            for (int j = 0; j < 9; j++) {
                if(hashCoordinate.contains(i + " " + j)) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    /**
     * 以下の規則で'#'で表示させる座標を求める。
     *
     * １、ブロックごとの初期値
     * 最初のブロックのスタートは[0,0]から始まる。
     * xはブロック三つごとに3が増加し、yは0,3,6を繰り返す。
     *
     * ２，ブロック内
     * スタートする座標の初期値は上記で決まる。
     * そしてxは座標三つごとに１増加し、yは座標三つ分だけ座標ごとに1増加し、その後、初期値に戻る。
     *
     * 9999999の場合、'#'で表示する座標は以下となる。
     * 0st 9 = [0,0][0,1][0,2][1,0][1,1][1,2][2,0][2,1][2,2]
     * 1nd 9 = [0,3][0,4][0,5][1,3][1,4][1,5][2,3][2,4][2,5]
     * 2rd 9 = [0,6][0,7][0,8][1,6][1,7][1,8][2,6][2,7][2,8]
     * 3th 9 = [3,0][3,1][3,2][4,0][4,1][4,2][5,0][5,1][5,2]
     * 4th 9 = [3,3][3,4][3,5][4,3][4,4][4,5][5,3][5,4][5,5]
     * 5th 9 = [3,6][3,7][3,8][4,6][4,7][4,8][5,6][5,7][5,8]
     * 6th 9 = [6,0][6,1][6,2][7,0][7,1][7,2][8,0][8,1][8,2]
     *
     *
     * @param hashCoordinate
     * @param barcodeNumList
     */
    private static void makeBarcode(Set<String> hashCoordinate, List<Integer> barcodeNumList) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < barcodeNumList.size(); i++) {
            int n = barcodeNumList.get(i);
            if(i != 0 && i % 3 == 0) {
                x = x + 3;
                y = 0;
            } else if(i % 3 == 1) {
                y = 3;
            } else if(i % 3 == 2) {
                y = 6;
            }
            int tmpX = x;
            int tmpY = y;
            for (int j = 0; j < n; j++) {
                if(j != 0 && j % 3 == 0) {
                    tmpX++;
                    tmpY = y;
                }
                hashCoordinate.add(tmpX + " " + tmpY);
                tmpY++;
            }
        }
    }
}