package com.shoppingcart.exampletest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

@DisplayName("Test Math operation in the Calculator class")
public class ExampleTest {

    static class Calculator{

        public int integerDivision(int dividend, int divisor){
            return dividend / divisor;
        }

        public int integerSubstraction(int num1, int num2){
            return Math.abs(num1 - num2);
        }
    }
    /*  * AAA : (Test Format)
        * Arrange == Given
        * Act == When
        * Assert == Then */

    /* test <System Under Test> <Condition or State change> <Expected Result> */

    @BeforeEach
    void beforeEachTest(){
        Calculator calculator = new Calculator();
        System.out.println("Inside Before Each Method !");
    }

    @Test
    void test_integerDivision_WhenFiveIsDividedByOne_ShouldReturnTwo() {

        Calculator calculator = new Calculator();
        int result = calculator.integerDivision(15, 5);
        assertEquals(3, result, "Integer Division Does not Produce Expected Result");
    }

    @Test
    void testSubstraction(){
        Calculator calculator = new Calculator();
        int result = calculator.integerSubstraction(10,2);
        assertEquals(8, result, "Integer abs() Substraction");
    }



    @Test
    @Disabled
    void divisionByZeroThrowsException(){
        Calculator calculator = new Calculator();
        /* Given: */
        int num1 = 10;
        int num2 = 0;

        String expectedException = "/ by zero";

        /* When: */
        ArithmeticException actualException = assertThrows(ArithmeticException.class, ()->{
            calculator.integerDivision(num1, num2);
        });

        /* Then: */
        assertEquals(expectedException, actualException.getMessage());
    }
}
