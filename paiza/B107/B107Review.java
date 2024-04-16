package paiza.B107;

import java.util.*;

/**
 * B107は与えられたカードを決められたカードの枚数ごとにグループ化を行い、グループ単位でシャッフルをした後の結果を出力する問題となる。
 * MapのKeyにグループNoを、そしてValueにArrayListとしてカードを保持することでグループ化を行い、シャッフルは単純にクループの順が反転するだけなので、Mapからすべてのグループを取得し、ArrayListのreverseを行う。
 * すなわち、「グループ化 → シャッフル → グループ化 → シャッフル 」を繰り返すことになる。
 * また、グループNoを採番するさいは「カードの番号/グループのサイズ」の結果とする。例は以下となる。
 * 0番目から2番目:0 (0~2/3)
 * 3番目から5番目:1 (3~5/3)
 * 6番目から8番目:2 (6~8/3)
 * 9番目:3 (9/3)
 *
 * @author kang yohan
 */

public class B107Review {
    public static void main(String[] args) {

        //カードの枚数
        int shuffleNum;
        //グループの枚数
        int groupSize;
        //カード
        List<Integer> cards = new ArrayList<>();
        //シャッフルの回数
        int shuffleCnt;

        //-----------------------------------------
        //入力
        //-----------------------------------------
        try (Scanner sc = new Scanner(System.in)) {
            //カードの枚数を取得
            shuffleNum = sc.nextInt();
            //グループの枚数を取得
            groupSize = sc.nextInt();
            //シャッフルの回数を取得
            shuffleCnt = sc.nextInt();
            for (int i = 0; i < shuffleNum; i++) {
                cards.add(i + 1);
            }
        }

        // シャッフル処理
        cardShuffle(shuffleCnt, groupSize, cards);

        // 出力
        for (Integer number : cards) {
            System.out.println(number);
        }
    }

    /**
     * 引数cardsで受け取ったカードの束をシャッフルする。
     *
     * @param shuffleNum カードをシャッフルする回数
     * @param groupSize  カードをグループ毎に分ける際の1グループの枚数
     * @param cards      シャッフルするカードの束。本メソッドではこのオブジェクトのデータをシャッフルして更新する。
     */
    private static void cardShuffle(int shuffleNum, int groupSize, List<Integer> cards) {
        // カードシャッフル回数分繰り返し
        for (int shuffleCnt = 0; shuffleCnt < shuffleNum; shuffleCnt++) {
            // カードのグループ化
            Map<Integer, List<Integer>> cardMap = new LinkedHashMap<>();
            for (int cardCnt = 0; cardCnt < cards.size(); cardCnt++) {
                int groupIndex = cardCnt / groupSize;
                cardMap.computeIfAbsent(groupIndex, k -> new ArrayList<>()).add(cards.get(cardCnt));
            }

            // カードの逆順ソート
            List<List<Integer>> cardGroup = new ArrayList<>(cardMap.values());
            Collections.reverse(cardGroup);

            // もともとのカード順を更新
            cards.clear();
            for (List<Integer> temp : cardGroup) {
                cards.addAll(temp);
            }
        }
    }
}
