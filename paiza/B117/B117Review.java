package paiza.B117;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 今回の問題は「while文」ですっきりかけると判断したため「while文」で実装してみました！！
 * 
 * 教習所内の車は、1から連番で教習所から出る順番が決まっていて、以下の二つは存在しない前提となっている。
 * ・順番の重複（3, 2, 1, 2）はない。 
 * ・順番の飛び（1, 2, 3, 5）はない。
 * 
 * そのため、教習所の車リストを順番に回して、1から順番に教習所から外に出ていたた車を除外していく。
 * 教習所を出た車の番号と、最大の車の番号が一致するところまで処理を進めたら
 * 最後の車が教習所を出たことになるので、そこまでに教習所を周回した回数が答えとなる。
 * 
 * @author 児島弘
 *
 */
public class B117Review {

	public static void main(String[] args) {
		// 教習所の中で待機している車の台数
		int carNum;

		// 教習所の中で待機している車の順番
		// 待機している車の番号は必ず連番となり、番号の空き、飛びが発生しない
		List<Integer> carsList = new ArrayList<>();

		//------------------------------
		// 入力
		//------------------------------
		try (Scanner sc = new Scanner(System.in)) {
			//教習車の数を取得
			carNum = sc.nextInt();
			//教習車の並び順を取得
			for (int i = 0; i < carNum; i++) {
				carsList.add(sc.nextInt());
			}
		}

		//------------------------------
		// 集計
		//------------------------------
		int roundNum = calcRound(carNum, carsList);

		//------------------------------
		// 出力
		//------------------------------
		System.out.println(roundNum);
	}

	/**
	 * 一番最後の車が教習所の外に出るまでに、何回教習所内のコースを周回したか算出して返す。
	 * 
	 * @param carNum 教習所の中で待機している車の台数
	 * @param carsList 教習所の中で待機している車の順番
	 * @return 最後の車が教習所の外に出るまでに教習所のコースを周回した回数
	 */
	private static int calcRound(int carNum, List<Integer> carsList) {
		// 教習所から外に出た車のインデックス。一番最初に教習所から外に出る車は「1」なので1で初期化
		int outCarIdx = 1;

		// 教習所を何周したか
		int roundNum = 0;

		// 全ての車が教習所の外に出るまで何週必要か分からないのでwhileループ
		while (carNum >= outCarIdx) {
			// 今回のループでまだ教習所に残っている車を記録して次の周回の入力に利用する
			List<Integer> remainingCarsList = new ArrayList<>();

			// 教習所の中に残っている車を順番に処理して小さい順に外に出る。
			for (int carIdx : carsList) {
				if (carIdx == outCarIdx) {
					// 一番小さい番号の車が教習所から外にでる。次に外に出る車の番号に更新する。
					outCarIdx++;
				} else {
					// 自分より小さい番号の車がまだあり、教習所の中に残る車を記録する。
					remainingCarsList.add(carIdx);
				}
			}

			// まだ車が残ってるので一番最後の車は教習所のコースを周回する必要がある。
			roundNum++;
			carsList = remainingCarsList;
		}

		// 最後の車が教習所を出るまでに周回した回数
		// 素直にwhileを回すと、教習所から出たときもカウントされているので、その分を引く
		return roundNum - 1;
	}
}
