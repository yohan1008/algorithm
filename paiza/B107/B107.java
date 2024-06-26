package paiza.B107;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * B107は与えられたカードを決められたカードの枚数ごとにグループ化を行い、グループ単位でシャッフルをした後の結果を出力する問題となる。
 * ArrayListにグループを配列として保持することでグループ化を行い、シャッフルは単純にクループの順が反転するだけなので、ArrayListのreverseを行う。
 * 最初にグループ化をした後、その状態で持続的にシャッフルを行うことは意味がないため、シャッフル後に配列に変換後、再度グループ化を行う必用がある。
 * そのためには、各グループの枚数の情報を保持して置く必用があり、ArrayListに保持をすることにする。
 * 結論的に「グループ化 → シャッフル → 配列に変換 → グループ化 → シャッフル → 配列に変換」を繰り返すことになる。
 *
 * @author kang yohan
 */
public class B107 {

    //カードの枚数
    static int cardNum;
    //グループの枚数
    static int groupCardNum;
    //カード
    static int[] cardArr;
    //シャッフルの回数
    static int shuffleCnt;

    public static void main(String[] args) {
        //-----------------------------------------
        //入力
        //-----------------------------------------
        try (Scanner sc = new Scanner(System.in)) {
            //カードの枚数を取得
            cardNum = sc.nextInt();
            //グループの枚数を取得
            groupCardNum = sc.nextInt();
            //シャッフルの回数を取得
            shuffleCnt = sc.nextInt();
            //カードを取得
            cardArr = new int[cardNum];
            for (int i = 0; i < cardArr.length; i++) {
                cardArr[i] = i + 1;
            }
        }

        //グループ数と余ったカードの枚数を計算
        //カード / 各グループの枚数　= 必ず存在するグループ数
        int mainGroupNum = cardNum / groupCardNum;
        //カード % 各グループの枚数　= グループ化をした後、残りのカード枚数
        int remainGroupCardNum = cardNum % groupCardNum;

        //グループの枚数
        ArrayList<Integer> groupNumList = new ArrayList<>();

        //各グループの枚数を格納
        for (int i = 0; i < mainGroupNum; i++) {
            groupNumList.add(groupCardNum);
        }

        if (remainGroupCardNum != 0) {
            groupNumList.add(remainGroupCardNum);
        }

        //-----------------------------------------
        //処理
        //-----------------------------------------
        for (int i = 0; i < shuffleCnt; i++) {
            //グループ化をする
            ArrayList<int[]> groupedCardList = makeGroup(groupNumList);
            //シャッフルをする
            shuffle(groupedCardList);
        }

        //-----------------------------------------
        //出力
        //-----------------------------------------
        for (int card : cardArr) {
            //最終的にシャッフルが終わったカードを出力する
            System.out.println(card);
        }

    }

    /**
     * グループ化を行う。
     *
     * @param groupNumList
     * @return
     */
    static ArrayList<int[]> makeGroup(ArrayList<Integer> groupNumList) {
        //グループ化されたカード
        ArrayList<int[]> groupedCardList = new ArrayList<>();
        //カードのインデックス
        int cardIdx = 0;
        //各グループの枚数通りに配列を作り格納する
        for (int num : groupNumList) {
            int[] tmpCardArr = new int[num];
            for (int i = 0; i < num; i++) {
                tmpCardArr[i] = cardArr[cardIdx];
                cardIdx++;
            }
            groupedCardList.add(tmpCardArr);
        }

        return groupedCardList;
    }

    /**
     * シャッフルを行う。
     *
     * @param groupedCardList
     */
    static void shuffle(ArrayList<int[]> groupedCardList) {
        //グループ前後の反転を行う
        Collections.reverse(groupedCardList);
        int cardIdx = 0;
        //グループ化されたカードのリストを再度配列に戻す
        for (int[] tmpCardArr : groupedCardList) {
            for (int cardNo : tmpCardArr) {
                cardArr[cardIdx] = cardNo;
                cardIdx++;
            }
        }
    }
}
