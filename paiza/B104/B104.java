package paiza.B104;

import java.util.*;

/**
 * データを入力する際、数字であるか確認をし、数字である場合は0以上100以下であるかのクレンジングを行う。
 * そして、クレンジングでかからなかったデータをMapに保存する。
 * 最終的にMapにデータがない場合は、クレンジング後、まともなデータがなかったため、0を出力し、
 * データがある場合は、全てのデータの平均値を計算して出力する。
 *
 * @author kangyohan
 */

public class B104 {
    public static void main(String[] args) {
        //クレンジングされたデータ
        Map<Integer, List<Integer>> cleansingAnswers = new HashMap<>();

        //------------------------------------------------------------
        // 入力及びデータクレンジング
        //------------------------------------------------------------
        try (Scanner sc = new Scanner(System.in)) {
            String[] line = sc.nextLine().split(" ");

            for (int i = 0; i < Integer.parseInt(line[1]); i++) {
                cleansingAnswers.put(i, new ArrayList<>());
            }

            for (int i = 0; i < Integer.parseInt(line[0]); i++) {
                String[] answers = sc.nextLine().split(" ");
                for (int j = 0; j < answers.length; j++) {
                    if (answers[j].chars().allMatch(Character::isDigit)
                            && Integer.parseInt(answers[j]) >= 0 && Integer.parseInt(answers[j]) <= 100) {
                        cleansingAnswers.get(j).add(Integer.parseInt(answers[j]));
                    }
                }
            }
        }

        //------------------------------------------------------------
        // 出力
        //------------------------------------------------------------
        for (Integer key : cleansingAnswers.keySet()) {
            if (cleansingAnswers.get(key).isEmpty()) {
                System.out.println(0);
            } else {
                int sum = cleansingAnswers.get(key).stream().mapToInt(Integer::intValue).sum();
                int cnt = cleansingAnswers.get(key).size();
                System.out.println(sum / cnt);
            }
        }
    }
}
