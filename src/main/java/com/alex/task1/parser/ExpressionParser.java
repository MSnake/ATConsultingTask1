package com.alex.task1.parser;

import java.util.LinkedList;

import com.alex.task1.exceptions.IncorrectExpressionException;

/**
 * ����� ���������� �� ������� ������� ������ ���������
 * @author Alex
 *
 */
public class ExpressionParser implements IExpressionParser {
	
	
	/**
	 * �������������� ������ ��������� ���� String � ������ LinkedList ���� String
	 * @param fullExpression - �������� ������ � ����������
	 * @return
	 * @throws Exception
	 */
	@Override
	public LinkedList<String> parse(String fullExpression) throws IncorrectExpressionException
	{
		//��������� �� null
		if (fullExpression == null) {
			throw new NullPointerException("��������� �� ����������");
		}
		LinkedList<String> parsedList = new LinkedList<>();
		//��������� �� ������������ ������ ���� �����(�� ��������� �������� 10 000)
		if (!isNumberCorrect(fullExpression)){
			throw new IncorrectExpressionException("��������� ������������ ���������� ���������."+"\n"+
					"������� � ������ �� �����������");
		}
		//������� ������� �� ��������� ���������.
		StringBuilder alterExpression  = new StringBuilder(fullExpression.replaceAll(" ", ""));
		//��������� �� ������������ ���������� ���������
		if (!isStringCorrect(alterExpression)){
			throw new IncorrectExpressionException("��������� ������������ ���������� ���������."+"\n"+
					"��������� ������������ ��������: +, -, *, / "+ "\n"+
					"��� ��������� ����� ����� ����� �� ������� ����������� '.'");

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
	 * �������� �������������� ������� � �������� ��������
	 * @param c - ������
	 * @return true - ���� ������ �������� ���������, false �����
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
	 * �������� �������������� ������� � �������� ��������
	 * �� ������ ������ �������( +, -, *, /, .)
	 * @param c - ����������� ������
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
	 * �������� ������������ �������� ������
	 * @param expressionString
	 * @return
	 */
	private boolean isStringCorrect(StringBuilder expressionString){
		//��������� ������ � ��������� ������� � ������ ���������(������ ���� �������)
		if (!Character.isDigit(expressionString.charAt(0)) ||
				!Character.isDigit(expressionString.charAt(expressionString.length()-1))) {
			return false;
		}
		//��������� ��������� ������� (� 1�� �� ��������������)
		for (int i=1;i<expressionString.length()-1;i++){
			char currentChar = expressionString.charAt(i);
			//�������� �� ������������� ������ �������� ������������ � �������� ��������
			if (!(Character.isDigit(currentChar) | isPartOfAlphabet(currentChar))){
				return false;
			}
			//�������� �� ������������� ��������� ������ �������� ��������(������: 2++3)
			// ��� ������������� ��������� ����� �� � ����� ��������(������ ��: 2.+3 ; �����: 2+.3)
			if (isOperation(currentChar)){
				if (!(Character.isDigit(expressionString.charAt(i-1)) & 
						Character.isDigit(expressionString.charAt(i+1)))) return false;
			}
		}
		
		return true;
	}
	
	/**
	 * �������� �� ������������ ��������� ����� � ���������
	 * �� ��������� ����� ���� 10 000(� ��������� ����� �������)
	 * @param expressionString - �������� ���������
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
					//���� ��������� ������ � ������� ����� �� �����
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
