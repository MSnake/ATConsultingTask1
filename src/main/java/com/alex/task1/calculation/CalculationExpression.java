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
					//Получаем элемент коллекции
					String itrString = itr.next();
					//Проверяем элемент на соответствие приоритету 
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
	 * Преобразование сложного выражения(выражние, которое содержит операции с повышенным приоритетом)
	 * в простое(выражение содаржащее обычные операции + и -)
	 * @return
	 * @throws Exception
	 */
	private void createSimpleExpression() {
		ListIterator<String> itr = operandList.listIterator();
		// Перемещаемся по коллекции в поисках элементов имеющих наивысщий
		// приоритет (*,/)
		while (itr.hasNext()) {
			// Получаем элемент коллекции
			String itrString = itr.next();
			// Проверяем элемент на соответствие операции приоритета
			if (isPriority(itrString.charAt(0))) {
				itr.set("" + getLocalReult(itr));
			}
		}
	}
	
	
	/**
	 * Метод вычисления локального результата для двух операндов
	 * @param itr - итератор
	 * @return локальный результат вычисления типа double
	 * @throws Exception
	 */
	private double getLocalReult(ListIterator<String> itr) {
		double result=0d;
		
		//возвращаемся на элемент перед операцией
		itr.previous();
		//Получаем элемент
		String prevString = itr.previous();
		//Превращаем элемент в число типа double
		double previousNumber = Double.parseDouble(prevString);
		//Удаляем элемент из коллекции(он больше не нужен, у нас есть его альтернатива типа double)
		itr.remove();
		//Возвращаемся к элементу со значением операции, и записываем операцию для дальнейшей работы
		String operation = itr.next();
		//Удаляем элемент с операцией из коллекции(у нас есть его альтернатива)
		itr.remove();
		
		//Получаем следующий элемент коллекции(предположительно второе число)
		String nextString = itr.next();
		//Превращаем элемент в число типа double
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
        		 throw new ArithmeticException("В ходе вычисления выражения обнаружен блок с делением на 0");
        	}
            break;
        case '*':
        	result = previousNumber*nextNumber;
            break;
		}
		return result;
	}
	
	
	
	/**
	 * Проверка на принадлежность символа к классу операции с повышенным приоритетом
	 * @param str - строка содержащая операцию
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

}
