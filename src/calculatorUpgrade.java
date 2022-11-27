/*
К калькулятору из предыдущего дз добавить логирование.
 */

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.XMLFormatter;

public class calculatorUpgrade {
    public static void main(String[] args) throws Exception {
        Logger logger = Logger.getLogger(calculatorUpgrade.class.getName());
        FileHandler fh = new FileHandler("logCalc.xml");
        logger.addHandler(fh);
        XMLFormatter xml = new XMLFormatter();
        fh.setFormatter(xml);
        logger.info("калькулятор запущен.");
        System.out.println("Перед вами калькулятор.");
        System.out.println("Для работы со мной выберите операцию.");
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        Double num1 ;
        Double num2 ;
        while (true) {
            System.out.println("\n1.Сложение \n2.Вычитание \n3.Умножение \n4.Деление\n5.Выход\n");
            System.out.println("Ваш выбор: ");
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                logger.info(String.format("пользователь ввел %s", input));
                if (input < 1 && input > 5) {
                    System.out.println("Такого пункта нет!");
                    logger.warning("Пользователь ввел неизвестный пункт.");
                    continue;
                }
                if (input == 5){
                    logger.info("программа завершена.");
                    fh.close();
                    scanner.close();
                    break;
                }
            } else {
                System.out.println("Вы ввели не число");
                logger.warning("Пользователь ввел неизвестное число.");
            }
            while (true) {
                System.out.println("Введите 1 число: ");
                if (scanner.hasNextDouble()) {
                    num1 = scanner.nextDouble();
                    logger.info(String.format("пользователь ввел первое число %s", num1));
                } else {
                    System.out.println("Вы ввели не число.");
                    logger.warning("Пользователь ввел неизвестное число.");
                    continue;
                }
                System.out.println("Введите второе число: ");
                if (scanner.hasNextDouble()) {
                    num2 = scanner.nextDouble();
                    logger.info(String.format("пользователь ввел второе число %s", num2));
                    if (input ==4) {
                        if (num2 == 0) {
                            logger.warning(String.format("пользователь пытается делить на '%s'", num2));
                            System.out.println("На 0 делить нельзя!");
                            continue;
                        }
                    }
                } else {
                    System.out.println("Вы ввели не число.");
                    logger.warning("Пользователь ввел неизвестное число.");
                    continue;
                }
                Double result = calc(input, num1, num2);
                logger.info(String.format("пользователь получил результат '%s'", result));
                System.out.printf("ответ = %s \n", result);
                break;
            }
        }
    }

    private static Double calc(int inp, Double num1, Double num2){
        Double result = 0.0;
        if (inp == 1) {
            result = num1 + num2;
        }
        else if (inp == 2) {
            result = num1 - num2;
        } else if (inp == 3) {
            result = num1 * num2;
        }else if (inp == 4) {
            result = num1 / num2;
        }
        return result;
    }
}

