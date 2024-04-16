package paiza.B105;

import java.util.*;

/**
 * 塗りつぶすエリア情報をListで保持する。そして、そのListでループを周りながら、エリアを塗りつぶして行く。
 * エリアを塗りつぶすときはエリアの座標を求め、その座標を持って再度ループを回し、二次元配列で保持されてあるゲームの結果のマスにすでに塗りつぶされてあるか確認する。
 * もし、何も塗りつぶされていない場合は、そのターンのPLAYERの色でマスを塗りつぶし、すでに塗りつぶされてある色がある時は塗りつぶされてある色ではないPLAYERの色で
 * マスを塗る。
 *
 * そして最終的にゲームの結果で、各PLAYERの色の個数を出力する。
 *
 * @author kangyohan
 */

public class B105 {
    public static void main(String[] args) {
        //ターン数
        int turnNum;
        //ボード
        int[][] gameResult;
        //塗りつぶすエリア
        List<List<Integer>> strategies = new ArrayList<>();

        //--------------------------------
        //入力
        //--------------------------------
        try (Scanner sc = new Scanner(System.in);) {
            turnNum = sc.nextInt();
            gameResult = new int[sc.nextInt()][sc.nextInt()];
            for (int i = 0; i < turnNum * 3; i++) {
                List<Integer> strategy = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    strategy.add(sc.nextInt());
                }
                strategies.add(strategy);
            }
        }

        //--------------------------------
        //ゲーム開始
        //--------------------------------
        startGame(gameResult, strategies);

        //--------------------------------
        //出力
        //--------------------------------
        int player1 = 0;
        int player2 = 0;
        int player3 = 0;
        for (int i = 0; i < gameResult.length; i++) {
            for (int j = 0; j < gameResult[i].length; j++) {
                switch (gameResult[i][j]) {
                    case 1:
                        player1++;
                        break;
                    case 2:
                        player2++;
                        break;
                    case 3:
                        player3++;
                        break;
                }
            }
        }
        System.out.println(player1 + " " + player2 + " " + player3);
    }

    private static void startGame(int[][] gameResult, List<List<Integer>> strategies) {
        for (int i = 0; i < strategies.size(); i++) {
            int player = (i % 3) + 1;
            List<Integer> paintInfo = strategies.get(i);

            int startY = paintInfo.get(0);
            int startX = paintInfo.get(1);
            int endY = startY + paintInfo.get(2) > gameResult[0].length ? gameResult[0].length : startY + paintInfo.get(2);
            int endX = startX + paintInfo.get(2) > gameResult.length ? gameResult.length : startX + paintInfo.get(2);

            for (int j = startX; j < endX; j++) {
                for (int k = startY; k < endY; k++) {
                    if (gameResult[j][k] != 0 && gameResult[j][k] != player) {
                        switch (gameResult[j][k]) {
                            case 1:
                                gameResult[j][k] = player == 2 ? 3 : 2;
                                break;
                            case 2:
                                gameResult[j][k] = player == 3 ? 1 : 3;
                                break;
                            case 3:
                                gameResult[j][k] = player == 1 ? 2 : 1;
                                break;
                        }
                    } else {
                        gameResult[j][k] = player;
                    }
                }
            }
        }
    }
}
