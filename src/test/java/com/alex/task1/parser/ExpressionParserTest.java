package com.alex.task1.parser;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import com.alex.task1.exceptions.IncorrectExpressionException;
import com.alex.task1.parser.ExpressionParser;

/**
 * Тестирование парсера выражения
 * @author Alex
 *
 */
public class ExpressionParserTest {
	
	private String goodExpression; //выражение которое должно быть корректно считано
	private String emptyExpression; //пустое выражение
	private String wrongStartExpression; //ошибка в начале выражения
	private String wrongEndExpression; //ошибка в конце выражения 
	private String wrongOperationExpression; //ошибка при вводе операции 
	private String wrongDigitExpression; //ошибка при написании числа
	private String nullExpression; //null строка
	private String incorrectNumberExpression; //ошибка при написании числа(пробел между знаками)
	private LinkedList<String> resultList; //ожидаемый список 
	private ExpressionParser exprPars; //Парсер входной строки

	@Before
	public void setUp() throws Exception {
		exprPars = new ExpressionParser();
		setUpList();
		goodExpression = "5+10*25 - 70 / 35";
		emptyExpression="";
		wrongStartExpression=".5+10*25 - 70 / 35";
		wrongEndExpression = "5+10*25 - 70 / 35/";
		wrongOperationExpression = "5+10*-25 - 70 / 35";
		wrongDigitExpression  = "5+10*-25 - .70 / 35";
		incorrectNumberExpression = "5+1 0*2 5 - 70 / 35";
		nullExpression = null;
	}
	
	private void setUpList(){
		resultList = new LinkedList<>();
		resultList.add("5");
		resultList.add("+");
		resultList.add("10");
		resultList.add("*");
		resultList.add("25");
		resultList.add("-");
		resultList.add("70");
		resultList.add("/");
		resultList.add("35");
	}
	
	@Test
	public void goodExpressionTest() throws Exception {
		LinkedList<String> parserResult = exprPars.parse(goodExpression);
		assertNotNull(parserResult);
		for (int i=0; i<parserResult.size();i++){
			assertEquals(resultList.get(i),parserResult.get(i));
		}
		
	}
	
	@Test(expected = IncorrectExpressionException.class)
	public void wrongStartExpressionTest() throws Exception {
		LinkedList<String> parserResult = exprPars.parse(wrongStartExpression);
	}
	
	@Test(expected = IncorrectExpressionException.class)
	public void wrongEndExpressionTest() throws Exception {
		LinkedList<String> parserResult = exprPars.parse(wrongEndExpression);
	}
	
	@Test(expected = IncorrectExpressionException.class)
	public void wrongOperationExpressionTest() throws Exception {
		LinkedList<String> parserResult = exprPars.parse(wrongOperationExpression);
	}
	
	@Test(expected = IncorrectExpressionException.class)
	public void wrongDigitExpressionTest() throws Exception {
		LinkedList<String> parserResult = exprPars.parse(wrongDigitExpression);
	}
	
	@Test(expected = StringIndexOutOfBoundsException.class)
	public void emptyExpressionTest() throws Exception {
		LinkedList<String> parserResult = exprPars.parse(emptyExpression);
		
	}
	
	@Test(expected = NullPointerException.class)
	public void nullExpressionTest() throws Exception {
		LinkedList<String> parserResult = exprPars.parse(nullExpression);
		
	}
	
	@Test(expected = IncorrectExpressionException.class)
	public void incorrectNumberExpressionTest() throws Exception {
		LinkedList<String> parserResult = exprPars.parse(incorrectNumberExpression);
		
	}
}
