package paiza.B115;

import java.util.*;

/**
 * 複数の単語の入力値を全て辞書順に並び変え、同じ単語の個数を調べる。
 * そして、Map＜String, Integer>で key=単語、value=個数 で保持する。
 *
 * その後、自分含め、入力された単語を一つずつ辞書順に並べた後、くっつけていく。
 * そして、MapのKeyに単語が存在する場合はその個数をトータルに加算する。
 * この時、カウントをした文字列をListに保持し、次に同じコンビネーションの文字列が来た時はノーカウントをするようにする。
 * この処理を入力された単語の文字列分だけ繰り返し、最終的にトータルを出力する。
 */

public class B115 {
    public static void main(String[] args) {
        //単語数
        int wordCnt;
        //入力された単語
        String[] words;

        //------------
        //入力
        //------------
        try (Scanner sc = new Scanner(System.in)) {
            wordCnt = sc.nextInt();
            sc.nextLine();
            words = new String[wordCnt];
            for (int i = 0; i < wordCnt; i++) {
                words[i] = sc.nextLine();
            }
        }

        //------------
        //処理及び出力
        //------------
        System.out.println(countCombinations(words));
    }

    public static int countCombinations(String[] words) {
        //トータル
        int count = 0;
        //リスト内に存在する単語を辞書順に並べた時の各個数
        Map<String, Integer> map = new HashMap<>();
        //カウントした単語の組合せを保持
        List<List<String>> counted = new ArrayList<>();

        for (String word : words) {
            char[] wordArr = word.toCharArray();
            Arrays.sort(wordArr);
            map.put(new String(wordArr), map.getOrDefault(new String(wordArr), 0) + 1);
        }

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                char[] combinedArr = (words[i] + words[j]).toCharArray();
                Arrays.sort(combinedArr);
                String combined = new String(combinedArr);
                if (map.containsKey(combined) && !counted.contains(Arrays.asList(words[i], words[j]))) {
                    count += map.get(combined);
                    //順序は区別しないため、前後変えて、リストに入れて上げる
                    counted.add(Arrays.asList(words[i], words[j]));
                    counted.add(Arrays.asList(words[j], words[i]));
                }
            }
        }

        return count;
    }
}
