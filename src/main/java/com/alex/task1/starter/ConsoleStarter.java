package com.alex.task1.starter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.LinkedList;

import com.alex.task1.calculation.CalculationExpression;
import com.alex.task1.exceptions.IncorrectExpressionException;
import com.alex.task1.parser.ExpressionParser;


public class ConsoleStarter {
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));

        String inputString="";
        LinkedList<String> expressionStringList = new LinkedList<>();
        while (true) {
        	try {
        		System.out.println("Введите выражение:");
        		inputString = f.readLine();
        		ExpressionParser expressionParser = new ExpressionParser();
        		expressionStringList = expressionParser.parse(inputString);
        		CalculationExpression claculate = new CalculationExpression(expressionStringList);
        		double result = claculate.calculateExpression();
        		System.out.println("Результат:"+result);
        		System.out.println(inputString+" = "+result);
         	} catch (IncorrectExpressionException e) {
        		System.out.println(e.getMessage());
        	}catch (StringIndexOutOfBoundsException e) {
        		System.out.println("Выражение должно содержать как минимум одну операцию (пример:2+2)");
			}catch (ArithmeticException e) {
        		System.out.println(e.getMessage());
			}catch (Exception e) {
        		System.out.println(e.getMessage());
			}
        }
	}	

}
