package com.alex.task1.parser;

import java.util.LinkedList;

import com.alex.task1.exceptions.IncorrectExpressionException;

public interface IExpressionParser {
	
	LinkedList<String> parse(String str) throws IncorrectExpressionException;

}
