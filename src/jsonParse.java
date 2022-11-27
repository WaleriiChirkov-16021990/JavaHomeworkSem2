/*
3**Дана json строка (можно сохранить в файл и читать из файла)
[{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},
{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},
{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
Написать метод(ы), который распарсит json и,
 используя StringBuilder, создаст строки вида: Студент [фамилия] получил [оценка] по предмету [предмет].
Пример вывода:
Студент Иванов получил 5 по предмету Математика.
Студент Петрова получил 4 по предмету Информатика.
Студент Краснов получил 5 по предмету Физика.

https://comaqa.gitbook.io/java-automation/rabota-s-failami/rabota-s-json-failami
 */

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class jsonParse {
    private static final String firstField = "фамилия";
    private static final String secondField = "оценка";
    private static final String thirdField = "предмет";
    private static final String filename = "people.json";
    private static final String Tag_people = "People";

    public static void main(String[] args) throws Exception {
        //Создаю Json объект эдентичный объекту из задания.
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = getJSONObj("Иванов", "5", "Математика");
        JSONObject jsonObject2 = getJSONObj("Петрова", "4", "Информатика");
        JSONObject jsonObject3 = getJSONObj("Краснов", "5", "Физика");
        JSONObject jsonObject4 = new JSONObject();
        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject2);
        jsonArray.add(jsonObject3);
        jsonObject4.put("People",jsonArray);
        // Объект создан.
        System.out.println();
        System.out.println(jsonObject4); // вывожу объект в консоль
        System.out.println("\nJSonObject записан в файл people.json");
        writerJsonObj(jsonObject4); // записываю объект в файл

        JSONObject object = (JSONObject) readJSON((filename)); //читаю файл и полученные данные помещаю в новый JSONObject.
        System.out.println("\nПрочитан файл people.json.\n");
        System.out.println(object);
        JSONArray peopleArray = (JSONArray) object.get(Tag_people); //парсю JSONArray по ключу, получая его по ключу. в скобках (JSONArray) -> приведение типа.
        System.out.println("Получаем требуемый текст через форматированнную строку:");
        for (Object i: peopleArray) { // обход циклом полученного массива
            JSONObject peopleJsonObject = (JSONObject) i; // с приведение типов
//            Далее получаю необходимые мне значения по известным мне ключам и вывожу их через String.format.
            String namePeople = (String) peopleJsonObject.get(firstField);
            String markPeople = (String) peopleJsonObject.get(secondField);
            String objectPeople = (String) peopleJsonObject.get(thirdField);
            System.out.printf("Студент %s получил %s по предмету %s.\n", namePeople,markPeople,objectPeople);
        }

        StringBuilder result = new StringBuilder(); // Так как заданние было использовать и стрингбилдер ....
        System.out.println("Теперь через StringBuilder.");
        for (Object i: peopleArray){
            JSONObject people = (JSONObject) i;
            result.append("Студент " + (String) people.get(firstField) + " ");
            result.append("получил " + (String) people.get(secondField)+ " ");
            result.append("по предмету " + (String) people.get(thirdField)+ ".");
            System.out.println(result);
            result = new StringBuilder();
        }
    }

    public static JSONObject getJSONObj ( String secondName, String mark,String object){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(firstField, secondName);
        jsonObject.put(secondField, mark);
        jsonObject.put(thirdField, object);
//        Создаю JSONObject по спланированному шаблону.
        return jsonObject;
    }

    public static void writerJsonObj( JSONObject object) throws Exception{
//        Метод записи JSONObject в файл filename с расширением json.
        Files.write(Paths.get(filename), object.toJSONString().getBytes());
    }

    public static Object readJSON(String filename) throws Exception {
//        Метод для чтения файла json.
        try(FileReader reader = new FileReader(filename)) {
            JSONParser parser = new JSONParser();
            return parser.parse(reader);
        }
    }
}
