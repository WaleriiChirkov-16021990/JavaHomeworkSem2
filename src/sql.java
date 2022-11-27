/*
Дана строка sql-запроса "select * from students where ".
Сформируйте часть WHERE этого запроса, используя StringBuilder.
Данные для фильтрации приведены ниже в виде json строки.
Если значение null, то параметр не должен попадать в запрос.
Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
 */

import org.json.simple.JSONObject;

public class sql {
    public static void main(String[] args) {
        String request = "select * from students where";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Ivanov");
        jsonObject.put("age", 30);
        jsonObject.put("deadAge", null);
        jsonObject.put("country", "Russia");
        jsonObject.put("city", "Moscow");

        StringBuilder result = new StringBuilder();
        String[] keys;
        keys = jsonObject.keySet().toString().replace("[", "").replace(" ","").replace("]", "").split("\\,");
        result.append(request);
        boolean string;
        for (int i = 0; i < keys.length; i++) {
            string = jsonObject.get(keys[i]) instanceof String;
            if (jsonObject.get(keys[i]) != null && i == 0){
                if (string) {
                    result.append(" " + keys[i] + "=" + "\"" + jsonObject.get(keys[i]) + "\"");
                } else {
                    result.append(" " + keys[i] + "=" + jsonObject.get(keys[i]));
                }
            } else if (jsonObject.get(keys[i]) != null){
                result.append(" AND ");
                if(string){
                    result.append(keys[i] + "=" + "\""+ jsonObject.get(keys[i]) + "\"");
                } else {
                    result.append(keys[i] + "=" + jsonObject.get(keys[i]));
                }
            }
        }
        System.out.println("\n" + result + "\n");
        System.out.println(jsonObject);
    }
}
