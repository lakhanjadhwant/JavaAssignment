public class SumAvgCmdLine {
    public static void main(String[] args) {
        float sum = 0;

        for (String s : args) {
            sum += Float.parseFloat(s);
        }
        System.out.println("Count of given number: " + args.length);
        System.out.println("Sum of the given number: " + sum);
        System.out.println("Average of the given number: " + sum / args.length);
    }
}
