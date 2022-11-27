/*
Реализуйте алгоритм сортировки пузырьком числового массива,
результат после каждой итерации запишите в лог-файл.
 */


import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.XMLFormatter;
import java.util.logging.Level;
public class bublesort {
    public static void main(String[] args) throws Exception {
        Logger logger = Logger.getLogger(bublesort.class.getName());
        FileHandler fileHandler = new FileHandler("logs.xml");
        logger.addHandler(fileHandler);
        XMLFormatter xmlFormatter = new XMLFormatter();
        fileHandler.setFormatter(xmlFormatter);
        logger.log(Level.INFO,"Приложение сортировки пузырьком запущено.");
        int[] array = new int[10];
        Random rnd = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rnd.nextInt(100);
        }
        logger.info(String.format("Программа сгенерировала массив целых чисел %s ", Arrays.toString(array)));
        System.out.println("\nИсходный массив");
        print(array);
        System.out.println("\n");
        array = bublesort(array);
        System.out.println("Массив после сортировки пузырьком");
        print(array);
//        logger.info("Программа успешно завершена."); Если расскомментировать строку, вывод в консоль в IntelliJ IDEA может запутать.
        fileHandler.close();
    }

    public static int[] bublesort(int[] arr) throws Exception{
        Logger logger = Logger.getLogger(bublesort.class.getName());
        FileHandler fileHandler = new FileHandler("logs.xml");
        logger.addHandler(fileHandler);
        XMLFormatter xmlFormatter = new XMLFormatter();
        fileHandler.setFormatter(xmlFormatter);
        int count = 0;
        int temp;
        for (int i = arr.length-1; i >= 1; i--) {
            count++;
            for (int j = 0; j < i; j++) {
                count++;
                if(arr[j] > arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
//                    Для удобного поиска выполнения условия в логе, присваиваю Level = Warning.
                    logger.log(Level.WARNING, String.format("Иттерация i = %d, j = %d, array = %s ", i, j, Arrays.toString(arr)));
                }else {
                    logger.log(Level.INFO, String.format("Иттерация i = %d, j = %d, array = %s ", i, j, Arrays.toString(arr)));
                }
            }
        }
        logger.info(String.format("Программа отсортировала массив целых чисел -> %s за %d иттераций.", Arrays.toString(arr), count));
        return arr;
    }

    public static void print(int[] arr){
        for (int i: arr) {
            System.out.printf("%d ", i);
        }
    }
}
