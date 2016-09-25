package com.alex.task1.exceptions;

/**
 * Исключение ввода некорректного выражения
 * @author Alex
 *
 */
public class IncorrectExpressionException extends Exception{
	
	public IncorrectExpressionException(String message){
		super(message);
	}

}
