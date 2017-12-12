import java.util.Scanner;

/**
 * Application launcher
 *
 * Created by mountassirbrahim on 12/12/17.
 */
public class Main {


    public static void main(final String[] args) {
        Scanner inputReader = new Scanner(System.in);
        System.out.println("Please enter a calculation:");
        String calculationAsStr = inputReader.nextLine();

        CalculatorService calculator = new CalculatorService();
        double result = calculator.triggerTheGivenCalculation(calculationAsStr);
        System.out.println("Result: "+result);
    }
}
