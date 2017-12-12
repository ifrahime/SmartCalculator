import java.util.*;


/**
 * Calculator service
 *
 * Created by mountassirbrahim on 07/12/17.
 */
public class CalculatorService {


    private static final String MULTIPLIED_BY = "multiplied-by";
    private static final String DIVIDED_BY = "divided-by";
    private static final String SPACE_CHARACTER = " ";


    public CalculatorService() {
    }

    public double triggerTheGivenCalculation(String fullCalculationAsStr){
        String operatorAsStr = Operator.add.name();
        /**
         * Used to split the calculation by addition and substract and then by operator (times, divided-by)
         */

        String[] splitCalculationByOperator = fullCalculationAsStr.split(operatorAsStr);
        if(splitCalculationByOperator.length==1){
            if(fullCalculationAsStr.contains(Operator.plus.name())){
                splitCalculationByOperator = fullCalculationAsStr.split(Operator.plus.name());
                operatorAsStr = Operator.plus.name();
            }else if(fullCalculationAsStr.contains(Operator.substract.name())){
                splitCalculationByOperator = fullCalculationAsStr.split(Operator.substract.name());
                operatorAsStr = Operator.substract.name();
            }else if(fullCalculationAsStr.contains(Operator.less.name())){
                splitCalculationByOperator = fullCalculationAsStr.split(Operator.less.name());
                operatorAsStr = Operator.less.name();
            }else if(fullCalculationAsStr.contains(Operator.minus.name())){
                splitCalculationByOperator = fullCalculationAsStr.split(Operator.minus.name());
                operatorAsStr = Operator.minus.name();
            }else if(fullCalculationAsStr.contains(Operator.over.name())){
                splitCalculationByOperator = fullCalculationAsStr.split(Operator.over.name());
                operatorAsStr = Operator.over.name();
            }else if(fullCalculationAsStr.contains(DIVIDED_BY)){
                splitCalculationByOperator = fullCalculationAsStr.split(DIVIDED_BY);
                operatorAsStr =DIVIDED_BY ;
            }
            else if(fullCalculationAsStr.contains(Operator.times.name())){
                splitCalculationByOperator = fullCalculationAsStr.split(Operator.times.name());
                operatorAsStr =Operator.times.name() ;
            }
            else if(fullCalculationAsStr.contains(MULTIPLIED_BY)){
                splitCalculationByOperator = fullCalculationAsStr.split(MULTIPLIED_BY);
                operatorAsStr =MULTIPLIED_BY ;
            }

        }


        LinkedList<String> splitCalculations = new LinkedList<>(Arrays.asList(splitCalculationByOperator));
        double rightResult= isPriorOperator(operatorAsStr) ? 1 : 0;

        /**
         * Trigger sub calculations (start from the right to the left side)
         * Example: 9 - 3*7
         * I will calculate 3*7 and then 9-21 = 12
         */
        while(!splitCalculations.isEmpty()){
            String calculation = splitCalculations.getLast();
            String[] rightOperand = calculation.trim().split(SPACE_CHARACTER);
            if (rightOperand.length != 1 && rightOperand.length<=3){
                rightResult = triggerSingleCalculation(triggerSingleCalculation(triggerTheGivenCalculation(rightOperand[0]), triggerTheGivenCalculation(rightOperand[2]), rightOperand[1]),rightResult, operatorAsStr);
            }else if(rightOperand.length>3){
                rightResult = triggerSingleCalculation(triggerTheGivenCalculation(calculation),rightResult,operatorAsStr);
            }else if(rightOperand.length==1){
                rightResult = triggerSingleCalculation(getNumberFromString(rightOperand[0]),rightResult,operatorAsStr) ;
            }
            splitCalculations.removeLast();
        }

        return rightResult;
    }




    private boolean isPriorOperator(String operatorAsStr){
        return  operatorAsStr.equals(MULTIPLIED_BY)|| operatorAsStr.equals(Operator.times.name()) || operatorAsStr.equals(DIVIDED_BY) || operatorAsStr.equals(Operator.over.name());
    }





    private double triggerSingleCalculation(final double leftNumber, final double rightNumber, final String operator){
        double result = 0;
        boolean isOperatorFound = true;
        boolean isAvailableOperation = true;

        try {
            switch (Operator.valueOf(operator)) {
                case add:
                    result = leftNumber + rightNumber;
                    break;
                case plus:
                    result = leftNumber + rightNumber;
                    break;
                case substract:
                    result = leftNumber - rightNumber;
                    break;
                case minus:
                    result = leftNumber - rightNumber;
                    break;
                case less:
                    result = leftNumber - rightNumber;
                    break;
                case times:
                    result = leftNumber * rightNumber;
                    break;
                case over:
                    if (rightNumber == 0) {
                        isAvailableOperation = false;
                    } else {
                        result = leftNumber / rightNumber;
                    }
                    break;
                default:
                    isOperatorFound = false;
                    break;
            }
        }catch(Exception e){
            isOperatorFound = false;
            isAvailableOperation=false;
        }



            if(!isOperatorFound){
                if(operator.equals(MULTIPLIED_BY)){
                    result = (leftNumber * rightNumber);
                    isAvailableOperation=true;
                }else if(operator.equals(DIVIDED_BY)){
                    if(rightNumber==0){
                        isAvailableOperation = false;
                    }else{
                        isAvailableOperation=true;
                        result = leftNumber / rightNumber;
                    }

                }
            }

            if(!isAvailableOperation){
                throw new UnavailableCalculationException("Unavailable calculation! Please check your calculation.");
            }


        return result;
    }




     private int getNumberFromString(String numberAsString){
         String numberAsStrToLowerCase =numberAsString.toLowerCase();
         int number;

         try {
             switch (Number.valueOf(numberAsStrToLowerCase)) {
                 case zero:
                     number = 0;
                     break;
                 case one:
                     number = 1;
                     break;
                 case two:
                     number = 2;
                     break;
                 case three:
                     number = 3;
                     break;
                 case four:
                     number = 4;
                     break;
                 case five:
                     number = 5;
                     break;
                 case six:
                     number = 6;
                     break;
                 case seven:
                     number = 7;
                     break;
                 case eight:
                     number = 8;
                     break;
                 case nine:
                     number = 9;
                     break;

                 default:
                     number = 0;
                     break;
             }
             }catch (IllegalArgumentException e){
                 String errorMessage = "Unexpected number format (the enterred value should be between zero and nine inclusive).";
                 throw new UnexpectedNumberStringFormat(errorMessage);
         }



         return number;
     }




}
