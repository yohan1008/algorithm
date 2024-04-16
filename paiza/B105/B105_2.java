package paiza.B105;

import java.util.*;

/**
 * 塗りつぶすエリア情報をListで保持する。そして、そのListでループを周りながら、エリアを塗りつぶして行く。
 * エリアを塗りつぶすときはエリアの座標を求め、その座標を持って再度ループを回し、各PLAYERが塗りつぶして座標の情報を保持しているSetにすでに塗りつぶされてあるか確認する。
 * もし、何も塗りつぶされていない場合は、そのターンのPLAYERの色でマスを塗りつぶし、すでに塗りつぶされてある色がある時は塗りつぶされてある色ではないPLAYERの色で
 * マスを塗る。
 *
 * そして最終的にゲームの結果で、各PLAYERの色の個数を出力する。
 *
 * @author kangyohan
 */

public class B105_2 {
    public static void main(String[] args) {
        //ターン数
        int turnNum;
        //ボードの縦
        int column;
        //ボードの横
        int row;
        //塗りつぶすエリア
        List<List<Integer>> strategies = new ArrayList<>();
        //ゲームの結果
        Map<Integer, Set<String>> gameResult = new HashMap<>();

        //--------------------------------
        //入力
        //--------------------------------
        try (Scanner sc = new Scanner(System.in);) {
            turnNum = sc.nextInt();
            column = sc.nextInt();
            row = sc.nextInt();
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
        startGame(column, row, strategies, gameResult);

        //--------------------------------
        //出力
        //--------------------------------
        int player1 = gameResult.get(1).size();
        int player2 = gameResult.get(2).size();
        int player3 = gameResult.get(3).size();
        System.out.println(player1 + " " + player2 + " " + player3);
    }

    private static void startGame(int column, int row, List<List<Integer>> strategies, Map<Integer, Set<String>> gameResult) {
        gameResult.put(1, new HashSet<>());
        gameResult.put(2, new HashSet<>());
        gameResult.put(3, new HashSet<>());

        for (int i = 0; i < strategies.size(); i++) {
            int player = (i % 3) + 1;
            List<Integer> paintInfo = strategies.get(i);

            int startY = paintInfo.get(0);
            int startX = paintInfo.get(1);
            int endY = startY + paintInfo.get(2) > row ? row : startY + paintInfo.get(2);
            int endX = startX + paintInfo.get(2) > column ? column : startX + paintInfo.get(2);

            for (int j = startX; j < endX; j++) {
                for (int k = startY; k < endY; k++) {
                    String paintArea = j + " " + k;
                    int removePlayer = player;
                    int addPlayer = player;
                    switch (player) {
                        case 1:
                            if (gameResult.get(2).contains(paintArea)) {
                                removePlayer = 2;
                                addPlayer = 3;
                            } else if (gameResult.get(3).contains(paintArea)) {
                                removePlayer = 3;
                                addPlayer = 2;
                            }
                            break;
                        case 2:
                            if (gameResult.get(1).contains(paintArea)) {
                                removePlayer = 1;
                                addPlayer = 3;
                            } else if (gameResult.get(3).contains(paintArea)) {
                                removePlayer = 3;
                                addPlayer = 1;
                            }
                            break;
                        case 3:
                            if (gameResult.get(1).contains(paintArea)) {
                                removePlayer = 1;
                                addPlayer = 2;
                            } else if (gameResult.get(2).contains(paintArea)) {
                                removePlayer = 2;
                                addPlayer = 1;
                            }
                            break;
                    }
                    gameResult.get(removePlayer).remove(paintArea);
                    gameResult.get(addPlayer).add(paintArea);
                }
            }
        }
    }
}

