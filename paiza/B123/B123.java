package paiza.B123;

import java.util.*;

/**
 * 手札、命令、山札をれぞれListに保存する。順番が重要なので、保存する型はLinkedListにする。
 * 単純に手札と山札を取得し、命令による結果を計算し、それをまた手札の変数に代入する。そしてこれを命令の数分、繰り返す。
 * 最終的に手札の変数に存在する番号の中で一番高得点の数字を持つインデックス（プレイヤー）を出力する。
 *
 * @author kang yohan
 */

public class B123 {
    public static void main(String[] args) {
        //プレイヤー数
        int playerNum;
        //山札の枚数
        int yamahudaNum;
        //手札
        List<List<Integer>> tehudaes = new LinkedList<>();
        //命令
        List<String> orders = new LinkedList<>();
        //山札
        List<List<Integer>> yamahudaes = new LinkedList<>();
        //勝利したプレイヤー
        List<Integer> winneres = new ArrayList<>();

        //-----------------------------------------
        //入力
        //-----------------------------------------
        try (Scanner sc = new Scanner(System.in)) {
            playerNum = sc.nextInt();
            yamahudaNum = sc.nextInt();
            for (int i = 0; i < playerNum; i++) {
                List<Integer> tehuda = new LinkedList<>();
                tehuda.add(sc.nextInt());
                tehuda.add(sc.nextInt());
                tehuda.add(sc.nextInt());
                tehudaes.add(tehuda);
            }

            for (int i = 0; i < yamahudaNum; i++) {
                orders.add(sc.next());
                List<Integer> yamahuda = new LinkedList<>();
                yamahuda.add(sc.nextInt());
                yamahuda.add(sc.nextInt());
                yamahuda.add(sc.nextInt());
                yamahudaes.add(yamahuda);
            }
        }

        //-----------------------------------------
        //処理
        //-----------------------------------------
        List<List<Integer>> resultTehudaes = playGame(tehudaes, yamahudaes, orders);

        //-----------------------------------------
        //出力
        //-----------------------------------------
        int maxScore = 0;
        for (int i = 0; i < resultTehudaes.size(); i++) {
            List<Integer> tehuda = resultTehudaes.get(i);
            String gameScoreStr = Integer.toString(tehuda.get(0)) + tehuda.get(1) + tehuda.get(2);
            int gameScore = Integer.parseInt(gameScoreStr);
            if (gameScore == maxScore) {
                winneres.add(i + 1);
            } else if (gameScore > maxScore) {
                maxScore = gameScore;
                winneres.clear();
                winneres.add(i + 1);
            }
        }

        for (Integer winner : winneres) {
            System.out.println(winner);
        }
    }

    private static List<List<Integer>> playGame(List<List<Integer>> tehudaes, List<List<Integer>> yamahudaes, List<String> orderes) {
        for (int i = 0; i < orderes.size(); i++) {
            //命令と山札を取得
            String order = orderes.get(i);
            List<Integer> yamahuda = yamahudaes.get(i);
            List<List<Integer>> nextTehudaes = new LinkedList<>();
            for (int j = 0; j < tehudaes.size(); j++) {
                //手札を取得
                List<Integer> tehuda = tehudaes.get(j);
                List<Integer> nextTehuda = new LinkedList<>();
                for (int k = 0; k < tehuda.size(); k++) {
                    //手札、山札を利用して命令をもとに計算する
                    int num;
                    switch (order) {
                        case "a":
                            num = tehuda.get(k) == 1 || yamahuda.get(k) == 1 ? 1 : 0;
                            nextTehuda.add(num);
                            break;
                        case "b":
                            num = tehuda.get(k) == 0 || yamahuda.get(k) == 0 ? 0 : 1;
                            nextTehuda.add(num);
                            break;
                        case "c":
                            num = tehuda.get(k) == yamahuda.get(k) ? 0 : 1;
                            nextTehuda.add(num);
                    }
                }
                nextTehudaes.add(nextTehuda);
            }
            //次の計算のために手札の変数に代入する
            tehudaes = nextTehudaes;
        }
        return tehudaes;
    }
}
