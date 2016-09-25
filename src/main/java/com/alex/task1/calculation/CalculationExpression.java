package com.alex.task1.calculation;

import java.util.LinkedList;
import java.util.ListIterator;

public class CalculationExpression {
	
	LinkedList<String> operandList;
	
	public CalculationExpression(LinkedList<String> expressionList){
		this.operandList = expressionList;
		
	}
	
	public double calculateExpression() {
		double result =0d;
		createSimpleExpression();
		if (operandList.size()>1){
			while (operandList.size()>1){
				ListIterator<String> itr = operandList.listIterator();
				while (itr.hasNext()){
					//�������� ������� ���������
					String itrString = itr.next();
					//��������� ������� �� ������������ ���������� 
					if (isOperation(itrString.charAt(0))){
						result = getLocalReult(itr);
						itr.set(""+result);
					}
					
				}
				
			}
		} else {
			result = Double.parseDouble(operandList.get(0));
		}
			
		return result;
	}
	
	/**
	 * �������������� �������� ���������(��������, ������� �������� �������� � ���������� �����������)
	 * � �������(��������� ���������� ������� �������� + � -)
	 * @return
	 * @throws Exception
	 */
	private void createSimpleExpression() {
		ListIterator<String> itr = operandList.listIterator();
		// ������������ �� ��������� � ������� ��������� ������� ���������
		// ��������� (*,/)
		while (itr.hasNext()) {
			// �������� ������� ���������
			String itrString = itr.next();
			// ��������� ������� �� ������������ �������� ����������
			if (isPriority(itrString.charAt(0))) {
				itr.set("" + getLocalReult(itr));
			}
		}
	}
	
	
	/**
	 * ����� ���������� ���������� ���������� ��� ���� ���������
	 * @param itr - ��������
	 * @return ��������� ��������� ���������� ���� double
	 * @throws Exception
	 */
	private double getLocalReult(ListIterator<String> itr) {
		double result=0d;
		
		//������������ �� ������� ����� ���������
		itr.previous();
		//�������� �������
		String prevString = itr.previous();
		//���������� ������� � ����� ���� double
		double previousNumber = Double.parseDouble(prevString);
		//������� ������� �� ���������(�� ������ �� �����, � ��� ���� ��� ������������ ���� double)
		itr.remove();
		//������������ � �������� �� ��������� ��������, � ���������� �������� ��� ���������� ������
		String operation = itr.next();
		//������� ������� � ��������� �� ���������(� ��� ���� ��� ������������)
		itr.remove();
		
		//�������� ��������� ������� ���������(���������������� ������ �����)
		String nextString = itr.next();
		//���������� ������� � ����� ���� double
		double nextNumber = Double.parseDouble(nextString);
		
		switch (operation.charAt(0)) {
        case '+':
        	result = previousNumber + nextNumber;
            break;
        case '-':
        	result = previousNumber - nextNumber;
            break;
        case '/':
        	if (nextNumber != 0){
        		result = previousNumber/nextNumber;
        	}
        	else
        	{
        		 throw new ArithmeticException("� ���� ���������� ��������� ��������� ���� � �������� �� 0");
        	}
            break;
        case '*':
        	result = previousNumber*nextNumber;
            break;
		}
		return result;
	}
	
	
	
	/**
	 * �������� �� �������������� ������� � ������ �������� � ���������� �����������
	 * @param str - ������ ���������� ��������
	 * @return
	 */
	private boolean isPriority(char c){
		switch (c) {
		case '/':
		case '*':
			return true;
		}
		return false;
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

}
