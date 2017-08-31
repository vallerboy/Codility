/**
 * Created by Lenovo on 31.08.2017.
 */
public class Task1 {
    public static void main(String[] args) {
        String numbers = "00:01:07,400-234-100\n" +
                "00:05:01,223-125-123\n" +
                "00:05:00,400-234-100\n";
        System.out.println(solution(numbers));
    }

    public static int solution(String s){
        int maxSeconds = 0;
        String maxNumber = "";
        int cash = 0;

        for(String line : s.split("\n")){
            String[] data = line.split(",");
            int localSeconds = 0;
            for(String line1 : s.split("\n")){
                if(data[1].equals( line1.split(",")[1])) {
                    localSeconds += calculateTime(line1.split(",")[0]);
                }
            }
            int localCash = calculateCash(calculateTime(data[0]));
            cash += localCash;
            if(maxSeconds < localSeconds){
                maxSeconds = localSeconds;
                maxNumber = data[1];
            }else if(maxSeconds == localSeconds){
                if(sumOfDigits(maxNumber) < sumOfDigits(data[1])){
                    maxSeconds = localSeconds;
                    maxNumber = data[1];
                }
            }


        }
        int deleteCash = 0;
        for(String line : s.split("\n")){
            String[] data = line.split(",");
            if(data[1].equals(maxNumber)){
                deleteCash += calculateCash(calculateTime(data[0]));
            }
        }
        return cash - deleteCash;
    }

    public static int calculateCash(int data) {
        int callDurationInSeconds = data;
        int cash = 0;

        if(callDurationInSeconds > 300){
            cash += callDurationInSeconds / 60 * 150;
            if(callDurationInSeconds % 60 != 0) {
                cash += 150;
            }
        }else{
            cash += callDurationInSeconds * 3;
        }
        return cash;
    }

    public static int calculateTime(String data) {
        int callDurationInSeconds = 0;
        callDurationInSeconds += 3600 * Integer.parseInt(data.split(":")[0]);
        callDurationInSeconds += 60 * Integer.parseInt(data.split(":")[1]);
        callDurationInSeconds += Integer.parseInt(data.split(":")[2]);
        return callDurationInSeconds;
    }

    public static int sumOfDigits(String data){
        int sum = 0;
        for(int i = 0; i < data.length(); i++) {
            if(Character.isDigit(data.charAt(i))){
                sum += Character.getNumericValue(data.charAt(i));
            }
        }
        return sum;
    }
}
