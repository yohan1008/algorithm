package paiza.B109;

import java.util.*;

/**
 * B109の問題はもっとも映画が見やすい席からマンハッタン距離を計算して映画が見やすい席を求める問題となる。
 * 既に利用されているポジションのチェックが文字列でそのまま比較できることと、同じ座標は複数出てこないことから、文字列を格納するSetオブジェクトを利用してチェック対象の座席が利用済みであるか調査する。
 *
 * もっとも映画が見やすいと指定された席は一つでその席の予約がされていない場合はもっとも映画が見やすい席となるので、その席の位置をただ出力する。
 * もしもっとも映画が見やすい席が利用済みの場合はマンハッタン距離を計算してまだ利用されていない席からもっとも映画がみやすい席をもとめて出力する。
 * @author kang yohan
 */

public class B109Review {

    public static void main(String[] args) {

        //既に利用されている座席
        int used;
        //座席の縦列数
        int height;
        //座席の横列数
        int width;
        //最も良い縦列
        int bestH;
        //最も良い横列
        int bestW;
        //予約済みの席
        Set<String> usedPos = new HashSet<>();

        try (Scanner sc = new Scanner(System.in)) {
            //既に利用されている座席を取得
            used = sc.nextInt();
            //座席の縦列数を取得
            height = sc.nextInt();
            //座席の横列数を取得
            width = sc.nextInt();
            //最も良い縦列を取得
            bestH = sc.nextInt();
            //最も良い横列を取得
            bestW = sc.nextInt();
            //予約済みの席を取得
            for (int i = 0; i < used; i++) {
                usedPos.add(sc.nextInt() + " " + sc.nextInt());
            }
        }

        // ベストポジションに最も近い座標を取得
        List<String> minPos = calcMinPos(height, width, bestH, bestW, usedPos);

        // 出力
        for (String pos : minPos) {
            System.out.println(pos);
        }

    }

    /**
     * 映画館の座席を網羅的にチェックしてベストポジションに最もマンハッタン距離が近い座標の一覧を取得する
     *
     * @param height  座席の縦列数
     * @param width   座席の横列数
     * @param bestH   最も良い縦列
     * @param bestW   最も良い横列
     * @param usedPos 既に利用されている座席の座標
     * @return ベストポジションに最も近いマンハッタン距離の座標一覧
     */
    private static List<String> calcMinPos(int height, int width, int bestH, int bestW, Set<String> usedPos) {
        // 最小マンハッタン距離を初期化（この映画館で最も大きなマンハッタン距離で初期化する）
        int minDist = height + width;
        // 最小マンハッタン距離の座標を保存するリスト
        List<String> minPos = new ArrayList<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // 今回チェック対象の座標文字列を作成
                String pos = y + " " + x;

                // 既に利用されている座席の場合は処理不要
                if (usedPos.contains(pos)) {
                    continue;
                }

                // マンハッタン距離算出
                int manDist = Math.abs((bestH - y)) + Math.abs((bestW - x));
                // より小さいマンハッタン距離があったら距離と座標を初期化
                if (minDist > manDist) {
                    minDist = manDist;
                    minPos.clear();
                }
                // 現状最も小さいマンハッタン距離である座標を保存
                if (minDist == manDist) {
                    minPos.add(pos);
                }
            }
        }

        return minPos;
    }

}
