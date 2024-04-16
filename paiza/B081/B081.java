package paiza.B081;

import java.util.*;

/**
 * 花壇の周辺を囲むロープの個数を求める問題となる。
 * まず、ロープの総合個数を求めて（花壇個数 X 4）を求める。
 * そして、各花壇の周辺に存在する花壇個数分、ロープの総合個数から引いてあげる。
 *
 * ..#.
 * ..#.
 * ###.
 * ..#.
 *
 * 上記の場合は、計算方法は以下となる。
 * [0 2] →　[-1 2][0 1][1 2][0 3]が周辺の座標で[1 2]に花壇があるため -1
 * [1 2] →　[0 2][1 1][2 2][1 3]が周辺の座標で[0 2][2 2]に花壇があるため -2
 * [2 0] →　上記と同じ方法で求めると-1
 * [2 1] →　上記と同じ方法で求めると-2
 * [2 2] →　上記と同じ方法で求めると-3
 * [3 2] →　上記と同じ方法で求めると-1
 *
 * 花壇の総合個数が 24（6X4）となるため、24-10で必要なロープの個数は14となる。
 * この時に、花壇の座標を保持する変数の型はコードをシンプル書くためにStreamを利用したいため、List<String>に保持する。
 */

public class B081 {
    public static void main(String[] args) {
        //縦
        int rows;
        //横
        int columns;
        //花壇の数
        int flowerCnt = 0;
        //花壇の座標
        List<String> flowerLocations = new ArrayList<>();

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
                        //花壇の座標をaddする
                        flowerLocations.add(i + " " + j);
                        //花壇の個数
                        flowerCnt++;
                    }
                }
            }
        }

        //------------------------------------------------------------
        // 処理及び出力
        //------------------------------------------------------------
        System.out.println(calcRope(flowerCnt, flowerLocations));
    }

    private static int calcRope(int flowerCnt, List<String> flowerLocations) {
        //ロープの総合個数
        int totalFlowerCnt = flowerCnt * 4;
        //不要な部分のロープの個数を求めて、総合個数から引いてあげる
        for (String flowerLocation : flowerLocations) {
            //対象となる花壇
            int x = Integer.parseInt(flowerLocation.split(" ")[0]);
            int y = Integer.parseInt(flowerLocation.split(" ")[1]);

            //花壇の周辺の座標
            String upper = (x - 1) + " " + y;
            String left = x + " " + (y - 1);
            String under = (x + 1) + " " + y;
            String right = x + " " + (y + 1);

            //花壇の周辺に花壇がいくつあるか求める
            List<String> list = Arrays.asList(upper, left, under, right);
            Long removeRopeCnt = list.stream().filter(s -> flowerLocations.contains(s)).count();

            //ロープの総合個数から引いてあげる
            totalFlowerCnt -= removeRopeCnt;
        }
        return totalFlowerCnt;
    }
}
