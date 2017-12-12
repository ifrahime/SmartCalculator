
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.fail;
import static junit.framework.TestCase.assertEquals;

/**
 * Calculator Service test
 */

public class CalculatorServiceTest {

    CalculatorService calculatorService;

    @Before
    public void beforeTest(){
        calculatorService = new CalculatorService();
    }



    /**
     * <given>calculation = nine over eight plus four times two divided-by three</given>
     * <when>calling the calculator service</when>
     * <then>the calculation is trigged and the actual result is equals to the expected one</then>
     */
    @Test
    public void testTriggerTheGivenCalculationTest_COMPLICATED_CALCULATION(){
        /**
         * GIVEN
         */
        CalculatorService calculatorService = new CalculatorService();
        String calculationAsStr = "nine over eight plus four times two divided-by three";
        /**
         * WHEN
         */
        double actualResult = calculatorService.triggerTheGivenCalculation(calculationAsStr);
        /**
         * THEN
         */
        assertEquals(3.79,actualResult, 0.01);
    }

    /**
     * <given>calculation = three plus one</given>
     * <when>calling the calculator service</when>
     * <then>the calculation is trigged and the actual result is equals to the expected one</then>
     */
    @Test
    public void testTriggerTheGivenCalculationTest_PLUS_OPERATOR(){
        /**
         * GIVEN
         */
        CalculatorService calculatorService = new CalculatorService();
        String calculationAsStr = "three plus one";
        /**
         * WHEN
         */
        double actualResult = calculatorService.triggerTheGivenCalculation(calculationAsStr);
        /**
         * THEN
         */
        assertEquals(4.0,actualResult);
    }

    /**
     * <given>calculation = one add two times three</given>
     * <when>calling the calculator service</when>
     * <then>the calculation is trigged and the actual result is equals to the expected one</then>
     */
    @Test
    public void triggerTheGivenCalculationTest_ADD_TIMES_OPERATOR(){
        /**
         * GIVEN
         */
        CalculatorService calculatorService = new CalculatorService();
        String calculationAsStr = "one add two times three";
        /**
         * WHEN
         */
        double actualResult = calculatorService.triggerTheGivenCalculation(calculationAsStr);
        /**
         * THEN
         */
        assertEquals(7.0,actualResult);
    }



    /**
     * <given>four minus eight plus six times nine</given>
     * <when>calling the calculator service</when>
     * <then>the calculation is trigged and the actual result is equals to the expected one</then>
     */
    @Test
    public void triggerTheGivenCalculationTest_MINUS_PLUS_TIMES_OPERATOR(){
        /**
         * GIVEN
         */
        CalculatorService calculatorService = new CalculatorService();
        String calculationAsStr = "four minus eight plus six times nine";
        /**
         * WHEN
         */
        double actualResult = calculatorService.triggerTheGivenCalculation(calculationAsStr);
        /**
         * THEN
         */
        assertEquals(50.0,actualResult);
    }

    /**
     * <given>seven over nine plus two</given>
     * <when>calling the calculator service</when>
     * <then>the calculation is trigged and the actual result is equals to the expected one</then>
     */
    @Test
    public void triggerTheGivenCalculationTest_OVER_PLUS_OPERATOR(){
        /**
         * GIVEN
         */
        CalculatorService calculatorService = new CalculatorService();
        String calculationAsStr = "seven over nine plus two";
        /**
         * WHEN
         */
        double actualResult = calculatorService.triggerTheGivenCalculation(calculationAsStr);
        /**
         * THEN
         */
        assertEquals(2.78,actualResult, 0.01);
    }


    /**
     * <given>trois plus two</given>
     * <when>calling the calculator service</when>
     * <then>An exception UnexpectedNumberStringFormat is thrown</then>
     */
    @Test
    public void triggerTheGivenCalculationTest_UNKNOWN_NUMBER(){
        /**
         * GIVEN
         */
        CalculatorService calculatorService = new CalculatorService();
        String calculationAsStr = "trois plus two";
        /**
         * WHEN
         */
        try{
            calculatorService.triggerTheGivenCalculation(calculationAsStr);
            fail("The exception UnexpectedNumberStringFormat has been thrown.");
        }catch (UnexpectedNumberStringFormat e){
            assertEquals("Unexpected number format (the enterred value should be between zero and nine inclusive).",e.getMessage());
        }


    }

    /**
     * <given>three fois two</given>
     * <when>calling the calculator service</when>
     * <then>An exception UnavailableCalculationException is thrown</then>
     */
    @Test
    public void triggerTheGivenCalculationTest_UNKNOWN_OPERATOR(){
        /**
         * GIVEN
         */
        CalculatorService calculatorService = new CalculatorService();
        String calculationAsStr = "three fois two";
        /**
         * WHEN
         */
        try{
            calculatorService.triggerTheGivenCalculation(calculationAsStr);
            fail("The exception UnavailableCalculationException has been thrown.");
        }catch (UnavailableCalculationException e){
            assertEquals("Unavailable calculation! Please check your calculation.",e.getMessage());
        }


    }

}