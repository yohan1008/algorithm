package paiza.C031;

import java.time.LocalTime;
import java.util.*;

/**
 * C031問題は標準時刻と標準時刻からの各都市の進み、この二つを利用して全世界の各都市に合った日記投稿時刻を求める問題となる。
 * 最初に入力から一つの都市名、都市からの投稿時刻、標準時刻からの進みを必ず提供して貰えるため、この三つを利用して標準時刻を求める
 * その後、求めた標準時刻に各都市の標準刻からの進みを足し、各都市の投稿時刻を計算する。
 * <p>
 * 各都市と標準時刻を紐づけて置く必要があるのと、入力された順で投稿時刻を出力させる必要があるため、
 * LikedHashMapに都市名と標準時刻からの進みを保持することにする。
 * また、時間は24時間制(00:00~23:59)で表示する必要があるため24以上の場合は-24をし、-1以下の場合は+24の計算を行う。
 * 分に関しては処理が不要なため最初に求めた分をそのまま保持し、出力させる。
 *
 * @author kang yohan
 */

public class C031 {

    //----------------------------------------------------------------
    //・指摘事項
    //副作用を起こさずにローカル変数だけで使用する場合はクラスフィル―ドに変数定義は出来ればしないこと。
    //無駄にクラスフィールドで定義をしてしまうと予期しない部分で値が更新されたりして動作に影響を与えてしまう。
    //----------------------------------------------------------------

    public static void main(String[] args) {

        //------------------------------------------------------------
        // 入力
        //------------------------------------------------------------
        //各都市の標準時刻からの進み
        Map<String, Integer> cityTimeDiffes = new LinkedHashMap<>();
        //投稿を行ったユーザの所在地の都市名
        String cityName;
        //投稿を行ったユーザの現地での投稿時刻
        String cityTime;

        try (Scanner sc = new Scanner(System.in)) {
            //各都市の総数を取得
            int cityNum = sc.nextInt();
            //各都市の標準時刻からの進みを取得
            for (int i = 0; i < cityNum; i++) {
                cityTimeDiffes.put(sc.next(), sc.nextInt());
            }
            //投稿を行ったユーザの所在地の都市名を取得
            cityName = sc.next();
            //投稿を行ったユーザの現地での投稿時刻を取得
            cityTime = sc.next();
        }

        //------------------------------------------------------------
        // 処理及び出力
        //------------------------------------------------------------
        //各都市の投稿時間を求めて出力する
        calcAndPrintPostTime(cityTimeDiffes, cityName, cityTime);
    }

    /**
     * 各都市の投稿時刻を計算して出力する
     *
     * @author kang yohan
     */
    //-------------------------------------------------------------
    //・指摘事項
    //日付の計算はLocalTimeクラスを使うことで簡単に実現が可能になる
    //またインスタンスを生成せずに実行した方がメモリ消費なども少なくなる
    //paizaなどのコーディングテストなどでは有利になる
    //-------------------------------------------------------------
    static void calcAndPrintPostTime(Map<String, Integer> cityTimeDiffes, String cityName, String cityTime) {
        //標準時間を計算するため文字列で入力された時間をLocalTimeオブジェクトに変換する
        LocalTime inputTime = LocalTime.of(Integer.parseInt(cityTime.substring(0, 2)), Integer.parseInt(cityTime.substring(3, cityTime.length())));

        //ユーザーの投稿時刻にその都市の標準時間からの進みをマイナスして標準時刻の時間を計算
        LocalTime standardTime = inputTime.plusHours(-cityTimeDiffes.get(cityName));

        for (String key : cityTimeDiffes.keySet()) {
            //標準時刻に標準時刻からの進みを足しての各都市の投稿時刻を計算する
            LocalTime localTime = standardTime.plusHours(cityTimeDiffes.get(key));

            //LocalTimeオブジェクトの時刻を文字列で出力する
            System.out.println(localTime.toString());
        }
    }
}