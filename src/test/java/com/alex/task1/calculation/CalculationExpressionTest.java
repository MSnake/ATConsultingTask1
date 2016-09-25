package com.alex.task1.calculation;
import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import com.alex.task1.calculation.CalculationExpression;
import com.alex.task1.exceptions.IncorrectExpressionException;
import com.alex.task1.parser.ExpressionParser;


/**
 * Тестирование вычисления результата выражения
 * @author Alex
 *
 */
public class CalculationExpressionTest {
	
	private String goodExpression; //Выражение для вычисление
	private String divByZeroExpression; //Выражение для вычисление
	private ExpressionParser exprPars; //Парсер входной строки
	double delta;  //точность вычислений

	@Before
	public void setUp() throws Exception {
		exprPars = new ExpressionParser();
		delta = 0.001;
		goodExpression = "5+10*25 - 70 / 35";
		divByZeroExpression = "5+10*25 - 70 / 0";
	}
	
	@Test
	public void goodCalculationTest() throws Exception {
		LinkedList<String> inputList = exprPars.parse(goodExpression);
		CalculationExpression calc = new CalculationExpression(inputList);
		double result = calc.calculateExpression();
		assertEquals(253, result, delta);
		
	}
	
	@Test(expected = ArithmeticException.class)
	public void divByZeroCalculationTest() throws Exception {
		LinkedList<String> inputList = exprPars.parse(divByZeroExpression);
		CalculationExpression calc = new CalculationExpression(inputList);
		double result = calc.calculateExpression();
		
	}
	


}
