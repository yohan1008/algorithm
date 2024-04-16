package paiza.B068;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * チョコレートをListで保持し、ループを周りながら、アリスとボブのチョコレート分割判定をしていく。
 * チョコレート分割判定作業はチョコレートの各行を取り出し、その各行をインデックス0から一個ずつ増やしながら、アリスのチョコレートと、
 * そして残りをボブのチョコレートとしてみなしていく。そして、各アリスとボブのチョコレートの糖分を合算し、同じ糖分で分割できた場合、その基準値をListに保存する。
 * 同じ糖分ではない行が一行でもある場合は、完全に分割が出来ないため、チョコレート分割判定作業を中断する。
 *
 * 全てのチョコレートが分割できた場合は、チョコレート分割の基準値が保存されてあるListをもとに結果を出力する。
 *
 * @author kangyohan
 */

public class B068 {
    public static void main(String[] args) {
        //------------------------------------------------------------
        // 入力
        //------------------------------------------------------------
        //チョコレートの行数
        int row;
        //チョコレートの列数
        int column;
        //チョコレート
        List<List<Integer>> chocolates = new ArrayList<>();
        //分割するチョコレートの基準（基準値以下の場合は、アリスの分）
        List<Integer> separateResults = new ArrayList<>();

        try (Scanner sc = new Scanner(System.in)) {
            row = sc.nextInt();
            column = sc.nextInt();
            for (int i = 0; i < row; i++) {
                List<Integer> chocolate = new ArrayList<>();
                for (int j = 0; j < column; j++) {
                    chocolate.add(sc.nextInt());
                }
                chocolates.add(chocolate);
            }
        }

        //------------------------------------------------------------
        // チョコレートの分割
        //------------------------------------------------------------
        if (separateChocolates(chocolates, separateResults)) {
            System.out.println("Yes");
            for (Integer separateStandard : separateResults) {
                String outputResult = "";
                for (int i = 0; i < column; i++) {
                    outputResult += i <= separateStandard ? "A" : "B";
                }
                System.out.println(outputResult);
            }
        } else {
            System.out.println("No");
        }
    }

    private static boolean separateChocolates(List<List<Integer>> chocolates, List<Integer> separateResults) {
        for (List<Integer> chocolate : chocolates) {
            for (int i = 0; i < chocolate.size(); i++) {
                List<Integer> aliceChocolate = new ArrayList<>(chocolate.subList(0, i));
                List<Integer> bobChocolate = new ArrayList<>(chocolate.subList(i, chocolate.size()));

                int aliceSugar = aliceChocolate.stream().mapToInt(sugar -> sugar).sum();
                int bobSugar = bobChocolate.stream().mapToInt(sugar -> sugar).sum();

                if (aliceSugar == bobSugar) {
                    //糖分が同じ場合、基準値を格納する。
                    separateResults.add(i - 1);
                    break;
                } else if (aliceSugar > bobSugar || i == chocolate.size() - 1) {
                    //同じ糖分ではない行が一行でもある場合、完全に分割が出来ないため、終了する。
                    return false;
                }
            }
        }

        return true;
    }
}
