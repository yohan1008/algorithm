package paiza.B110;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 1枚目と2枚目の画像を2次元の配列で保持し、重なる部分の座標を求め、座標のピクセル値を取得する。
 * そして、重なる部分の画像のピクセル値の平均を計算し、Listに保存する。
 * 最終的にListに保存されてある、ピクセル値を順に出力させる。
 *
 * @author kangyohan
 */

public class B110 {
    public static void main(String[] args) {
        //縦の解像度
        int row;
        //横の解像度
        int column;
        //1 枚目の画像
        int[][] picture1;
        //2 枚目の画像
        int[][] picture2;
        //出力される画像
        List<List<Integer>> resultPicture = new ArrayList<>();

        //------------------------------
        // 入力
        //------------------------------
        try (Scanner sc = new Scanner(System.in)) {
            row = sc.nextInt();
            column = sc.nextInt();
            picture1 = new int[row][column];
            picture2 = new int[row][column];

            for (int i = 0; i < picture1.length; i++) {
                for (int j = 0; j < picture1[i].length; j++) {
                    picture1[i][j] = sc.nextInt();
                }
            }

            for (int i = 0; i < picture2.length; i++) {
                for (int j = 0; j < picture2[i].length; j++) {
                    picture2[i][j] = sc.nextInt();
                }
            }
        }

        //------------------------------
        // 画像を処理する
        //------------------------------
        makePicture(picture1, picture2, row, column, resultPicture);

        //------------------------------
        // 出力
        //------------------------------
        for (int i = 0; i < resultPicture.size(); i++) {
            List<Integer> pictureRow = resultPicture.get(i);
            for (int j = 0; j < pictureRow.size(); j++) {
                System.out.print(j != pictureRow.size() - 1 ? pictureRow.get(j) + " " : pictureRow.get(j));
            }
            System.out.println();
        }
    }

    /**
     * 以下のプロセスを重なる部分のマスの縦 x 横分ループを回す。（重なる部分のマスは本来の画像の x 2 - 1で求めることが出来る。）
     * まず、最初に重なる部分を求める。重なる部分の座標は画像2は[0,0]からそして画像1は画像2の座標からx+1, y+1となる。
     *
     * ＜画像2の重なる部分＞               ＜画像1の重なる部分＞
     * [0,0][0,1][0,2][0,3][0,4]       [1,1][1,2][1,3][1,4][1,5]
     * [1,0][1,1][1,2][1,3][1,4]       [2,1][2,2][2,3][2,4][2,5]
     * [2,0][2,1][2,2][2,3][2,4]       [3,1][3,2][3,3][3,4][3,5]
     *
     * そして、重なる部分のピクセル値を取得する。
     * 上記から求めた、重なる部分の座標を2で割ることで、その座標のピクセル値を取得出来る[画像の座標]を求めることが出来る。
     *
     * (x/2 , y/2)
     *    [0,0]      = [0,0][0,1][1,0][1,1]
     *    [0,1]      = [0,2][0,3][1,2][1,3]
     *    [0,2]      = [0,4][0,5][1,4][1,5]
     *    [1,0]      = [2,0][2,1][3,0][3,1]
     *    [1,1]      = [2,2][2,3][3,2][3,3]
     *    [1,2]      = [2,4][2,5][3,4][3,5]
     *    [2,0]      = [4,0][4,1][5,0][5,1]
     *    [2,1]      = [4,2][4,3][5,2][5,3]
     *    [2,2]      = [4,4][4,5][5,4][5,5]
     *
     * 最終的に求めた画像1と画像2のピクセル値の平均値を計算して、Listにaddする。
     *
     * @param picture1 画像1
     * @param picture2 画像2
     * @param row 画像の縦マス
     * @param column 画像の横マス
     * @param resultPicture 出力される画像
     */
    private static void makePicture(int[][] picture1, int[][] picture2, int row, int column, List<List<Integer>> resultPicture) {
        int resultPictureRow = row * 2 - 1;
        int resultPictureColumn = column * 2 - 1;

        int pixel2X = 0;

        for (int i = 0; i < resultPictureRow; i++) {
            int pixel2Y = 0;
            List<Integer> tmpResultPicture = new ArrayList<>();
            for (int j = 0; j < resultPictureColumn; j++) {
                int picture1X = (pixel2X + 1) / 2;
                int picture1Y = (pixel2Y + 1) / 2;

                int picture2X = pixel2X / 2;
                int picture2Y = pixel2Y / 2;

                tmpResultPicture.add((picture1[picture1X][picture1Y] + picture2[picture2X][picture2Y]) / 2);
                pixel2Y++;
            }
            resultPicture.add(tmpResultPicture);
            pixel2X++;
        }
    }
}
