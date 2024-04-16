package paiza.B143;

import java.util.*;

/**
 * 最初に園児の人数分、Map＜String, List<Integer>>でkey=先頭、value=自分の値で保持する。
 * そして、じゃんけんの勝敗の結果はList<int[]>で0は勝者、1は敗者で保持する。
 *
 * 勝敗結果のリストをループで回しながら、勝者（key）のリスト（value）に敗者のリストを全て追加後、敗者のリストを削除する。
 * そして、勝者の列車の長さを確認して、一番長いまたは同じである場合は記録する。
 *
 * @author kang yohan
 */

public class B143 {
    public static void main(String[] args) {
        //園児の人数
        int peopleNum;
        //じゃんけんの回数
        int rpsCnt;
        //じゃんけんの結果
        List<int[]> rpsResult = new ArrayList<>();
        //列車
        Map<Integer, List<Integer>> trains = new HashMap<>();
        //列車ゲームの勝者
        List<Integer> winners = new ArrayList<>();

        try(Scanner sc = new Scanner(System.in)){
            peopleNum = sc.nextInt();
            rpsCnt = sc.nextInt();
            sc.nextLine();
            for(int i = 1 ; i <= peopleNum ; i++) {
                //園児の人数分、列車を作成する
                trains.put(i, new ArrayList<>(Arrays.asList(i)));
            }
            for(int i = 0 ; i < rpsCnt ; i++) {
                String tmp = sc.nextLine();
                int[] game = new int[2];
                game[0] = Integer.parseInt(tmp.split(" ")[0]);
                game[1] = Integer.parseInt(tmp.split(" ")[1]);
                rpsResult.add(game);
            }
        }

        //------------
        //処理
        //------------
        playGame(rpsResult, trains, winners);

        //------------
        //出力
        //------------
        winners.stream().forEach(System.out::println);
    }

    private static void playGame(List<int[]> rpsResult, Map<Integer, List<Integer>> trains, List<Integer> winners) {
        int longestSize = 0;
        for(int[] rps : rpsResult) {
            int winner = rps[0];
            int loser = rps[1];

            List<Integer> winnerTrain = trains.get(winner);
            List<Integer> loserTrain = trains.get(loser);

            winnerTrain.addAll(loserTrain);
            trains.remove(loser);

            if(winnerTrain.size() > longestSize) {
                //今での列車の中で一番長い場合はリストを初期化して追加
                longestSize = winnerTrain.size();
                winners.clear();
                winners.add(winner);
            } else if(winnerTrain.size() == longestSize) {
                //一番長い列車と同じ長さの場合は初期化はせずにリストに追加してあげる
                winners.add(winner);
            }
        }
    }
}
