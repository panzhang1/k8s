package calculator;


public class MyCalculator {
    public static void main(String[] args) {
        MyCalculator myCalculator =  new MyCalculator();
        myCalculator.yourCalculator();
    }

    public void yourCalculator() {
        int duration = 20;
        float roa = 0.1f;

        //case1: start = 50k, increase = 5k
        int startAmount = 50000;
        int increaseAmount = 60000;
        int assertOfCase1 = (int) calculate(startAmount,increaseAmount,duration,roa);
        System.out.println("Case1:");
        printTrace(yourInvest(startAmount,increaseAmount,duration),assertOfCase1);

        //case2: start = 0k, increase = 2k
        startAmount = 0;
        increaseAmount = 24000;
        System.out.println("Case2:");
        int assertOfCase2 = (int) calculate(startAmount,increaseAmount,duration,roa);
        printTrace(yourInvest(startAmount,increaseAmount,duration),assertOfCase2);
    }

    private void printTrace(float investedAmount, float finalAmount) {
        System.out.println("投入:" + formatAmount((int)investedAmount) + "，结果："
                + formatAmount((int)finalAmount));
    }

    public String formatAmount(int originalAmount) {
        int formatedAmt = originalAmount/10000;
        return formatedAmt + " 万";
    }

    public int yourInvest(int startAmount, int increaseAmount, int duration) {
        return startAmount + increaseAmount * duration;
    }

    /**
     *
     * @param startAmount
     * @param increaseAmount
     * @param duration
     * @param roa
     * @return
     */
    public float calculate(float startAmount, float increaseAmount, int duration, float roa) {
        float result = startAmount;
        for (int i = 0; i < duration; i ++) {
            result = (result + increaseAmount) * (1 + roa);
        }
        return result;
    }
}
