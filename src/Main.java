import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws KataException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи строку для вычисления");
        String input = scanner.nextLine();  //Получаем строку
        calc(input);}


    public static String calc (String input) throws KataException {
        String[] arabArr = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}; //массив соответствий арабский
        String[] rimArr = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"}; //массив соответствий римский
        String[] operatorsArr = new String[]{"+", "-", "*", "/"}; //массив соответствий операторов
        int result = 0; //начальный результат вычислений
        int resultRim = 0; //вспомогательная переменная для конвертации в римские
        int num1 = 0; //начальное число1
        int num2 = 0; //начальное число2
        String [] calcValueArr = input.split(" "); //Делим на 3 слогаемых
            if (calcValueArr.length != 3) {
                throw new KataException("Строка не соответствует типу a + b, a - b, a * b или a / b ");}
            //проверки на ожидаемые числа и оператор//
            boolean isArab = Arrays.stream(arabArr).anyMatch(x -> x.equals(calcValueArr[0])) && Arrays.stream(arabArr).anyMatch(x -> x.equals(calcValueArr[2])); //проверка на араба
            boolean isOperator = Arrays.stream(operatorsArr).anyMatch(x -> x.equals(calcValueArr[1])); //проверка на оператор
            boolean isRim = Arrays.stream(rimArr).anyMatch(x -> x.equals(calcValueArr[0].toUpperCase())) && Arrays.stream(rimArr).anyMatch(x -> x.equals(calcValueArr[2].toUpperCase())); //проверка на рим
            //присваивание арабского числа
            if (isArab) {
                num1 = Integer.parseInt(calcValueArr[0]); //Конвертируем стоку в int
                num2 = Integer.parseInt(calcValueArr[2]); //Конвертируем стоку в int
            }

            //присваиваивание римского числа
            if (isRim) {
                RimNum rNum1 = RimNum.valueOf(calcValueArr[0].toUpperCase()); //приводим значение1 к верхнему регистру
                RimNum rNum2 = RimNum.valueOf(calcValueArr[2].toUpperCase()); //приводим значение2 к верхнему регистру
                num1 = rNum1.getRimNum(); //получаем число1 из Энама
                num2 = rNum2.getRimNum(); //получаем число2 из Энама
            }

            //проверка соответсвия систем, целочисленности и диапазона
            if (!isArab && !isRim) {
                throw new KataException("Числа введены в разных системах исчисления или не соответствуют целочисленному диапазону 1-10");
            }

            //проверка на оператор и длину (кол-во) слогаемых
            if ((isArab || isRim) && isOperator) {

                //конвертация символа в оператор вычисления
                switch (calcValueArr[1]) {
                    case "+" :
                        result = num1 + num2;
                        break;
                    case "-" :
                        result = num1 - num2;
                        break;
                    case "*" :
                        result = num1 * num2;
                        break;
                    case "/" :
                        result = num1 / num2;
                        break;
                }

                if(isArab) {System.out.println(result);} //вывод арабского результата
                if(isRim) {
                    resultRim = result;
                    int[] values = {100, 90, 50, 40, 10, 9, 5, 4, 1};
                    String[] romanLetters = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
                    StringBuilder roman = new StringBuilder();
                    for (int i = 0; i < values.length; i++) {
                        while (resultRim >= values[i]) {
                            resultRim = resultRim - values[i];
                            roman.append(romanLetters[i]);
                        }
                    }
                    if (result > 0) {
                        System.out.println(roman);
                    } else {
                        throw new KataException("В римской системе нет отрицательных чисел");
                    }
                }
            } else {throw new KataException("В строке недопустимый оператор");
            } return "";}
    }

