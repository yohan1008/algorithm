package other.O007.answer;

public class A1 {
    public static void main(String[] args) {
        int truckCapacity = 5000; // 트럭의 최대 적재량
        int numOfTrucks = 0; // 필요한 트럭의 개수를 저장하는 변수
        int currentWeight = 0; // 현재 트럭에 실린 짐의 무게

        // 무거운 짐부터 순서대로 실어 나간다.
        for (int i = 777; i >= 1; i--) {
            // 현재 짐을 실었을 때 트럭의 적재량을 넘어가는 경우
            if (currentWeight + i > truckCapacity) {
                numOfTrucks++; // 트럭을 하나 더 추가하고
                currentWeight = i; // 현재 짐을 새 트럭에 실는다.
            } else { // 적재량을 넘어가지 않는 경우
                currentWeight += i; // 현재 짐을 현재 트럭에 실는다.
            }
        }

        // 마지막에 남아있는 짐을 실을 트럭을 추가한다.
        if (currentWeight > 0) {
            numOfTrucks++;
        }

        System.out.println("The number of trucks needed: " + numOfTrucks); // 필요한 트럭의 개수를 출력한다.
    }
}
