package com.alex.task1.parser;

import java.util.LinkedList;

import com.alex.task1.exceptions.IncorrectExpressionException;

/**
 * Класс отвечающий за парсинг входной строки выражения
 * @author Alex
 *
 */
public class ExpressionParser implements IExpressionParser {
	
	
	/**
	 * Преобразование строки выражения типа String в список LinkedList типа String
	 * @param fullExpression - исходная строка с выражением
	 * @return
	 * @throws Exception
	 */
	@Override
	public LinkedList<String> parse(String fullExpression) throws IncorrectExpressionException
	{
		//Проверяем на null
		if (fullExpression == null) {
			throw new NullPointerException("Выражение не определено");
		}
		LinkedList<String> parsedList = new LinkedList<>();
		//Проверяем на корректность записи всех чисел(не допускать подобных 10 000)
		if (!isNumberCorrect(fullExpression)){
			throw new IncorrectExpressionException("Проверьте корректность введенного выражения."+"\n"+
					"Пробелы в числах не допускаются");
		}
		//удаляем пробелы из исходного выражения.
		StringBuilder alterExpression  = new StringBuilder(fullExpression.replaceAll(" ", ""));
		//Проверяем на корректность введенного выражения
		if (!isStringCorrect(alterExpression)){
			throw new IncorrectExpressionException("Проверьте корректность введенного выражения."+"\n"+
					"Допустимо использовать операции: +, -, *, / "+ "\n"+
					"Для отделения целой части числа от дробной используйте '.'");

		} else {
			int lastIndex=0;

			for (int i =0;i <alterExpression.length(); i++){
				if (isOperation(alterExpression.charAt(i))){
					parsedList.add(alterExpression.substring(lastIndex, i));
					parsedList.add(alterExpression.substring(i, i+1));
					lastIndex = i+1;
				}
				if (i==alterExpression.length()-1)
				{
					parsedList.add(alterExpression.substring(lastIndex, i+1));
				}
				
			}
		
		}
		
		return parsedList;
		
	}
	
	/**
	 * Проверка принадлежности символа к символам операций
	 * @param c - символ
	 * @return true - если символ является операцией, false иначе
	 */
	private boolean isOperation(char c){
		 switch (c) {
         case '-':
         case '+':
         case '*':
         case '/':
             return true;
		 }
		 return false;
	}
	
	/**
	 * Проверка принадлежности символа к алфавиту символов
	 * на данный момент алфавит( +, -, *, /, .)
	 * @param c - проверяемый символ
	 * @return
	 */
	private boolean isPartOfAlphabet(char c){
		switch (c) {
        case '-':
        case '+':
        case '*':
        case '/':
        case '.':
            return true;
		 }
		 return false;
	}
		
	
	/**
	 * Проверка корректности введеной строки
	 * @param expressionString
	 * @return
	 */
	private boolean isStringCorrect(StringBuilder expressionString){
		//проверяем первый и последние символы в строке выражения(должны быть цифрами)
		if (!Character.isDigit(expressionString.charAt(0)) ||
				!Character.isDigit(expressionString.charAt(expressionString.length()-1))) {
			return false;
		}
		//Проверяем остальные символы (с 1го до предпоследнего)
		for (int i=1;i<expressionString.length()-1;i++){
			char currentChar = expressionString.charAt(i);
			//Проверка на использование только символов обозначенных в алфавите символов
			if (!(Character.isDigit(currentChar) | isPartOfAlphabet(currentChar))){
				return false;
			}
			//Проверка на невозможность написания подряд символов операции(пример: 2++3)
			// или неправильного написания числа до и после операции(пример до: 2.+3 ; после: 2+.3)
			if (isOperation(currentChar)){
				if (!(Character.isDigit(expressionString.charAt(i-1)) & 
						Character.isDigit(expressionString.charAt(i+1)))) return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Проверка на корректность введенных чисел в выражении
	 * Не допускать числа вида 10 000(с пробелами между цифрами)
	 * @param expressionString - исходное выражение
	 * @return
	 */
	private boolean isNumberCorrect(String expressionString){
		boolean numberArea=false;
		for (int i=0;i<expressionString.length()-1;i++){
			if (Character.isDigit(expressionString.charAt(i))){
				numberArea = true;
			}else{
				numberArea = false;
			}
			
			if (numberArea) {
				int j=i;
				StringBuilder numberString=new StringBuilder();
				while (numberArea & j<expressionString.length()-1){
					numberString.append(expressionString.charAt(j));
					j++;
					if (isPartOfAlphabet(expressionString.charAt(j))){
						numberArea = false;
					}
				}
				boolean emptyArea=true;
				while (emptyArea){
					int lastIndex = numberString.length()-1;
					//Если последний символ в области числа не цифра
					if (!Character.isDigit(numberString.charAt(lastIndex))){
						numberString.deleteCharAt(lastIndex);
					} else {
						emptyArea = false;
					}
				}
				if (numberString.toString().contains(" ")){
					return false;
				}
			}
		}
		return true;
	}


}
