package pagesandblocks;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PageObject {

    public static final NumberFormat NF = new DecimalFormat("#,###", DecimalFormatSymbols.getInstance(Locale.ENGLISH));

    // Перобразует timestemp в формат "MMM d, yyyy, h:mm:ss a"
    public String timestampToFormat(long timestamp) {
        return new SimpleDateFormat("MMM d, yyyy, h:mm:ss a", Locale.ENGLISH).format(timestamp*1000);
    }


/*
* Методы берут значения ключа из json массива.
* */
    // Возвращает значение ключа key1 из json массива: [{key1}]
    public String getJsonKey1Value(String jsonString, String key1){
        JsonElement json = new JsonParser().parse(jsonString).getAsJsonArray().get(0);
        String key1Val = "";
        if (json.getAsJsonObject().has(key1)) {
            key1Val = json.getAsJsonObject().get(key1).toString().replace("\"", "");
        }
        return key1Val;
    }
    // Возвращает значение ключа key2 из json массива: [{key1{key2}}]
    public String getJsonKey2Value(String jsonString, String key1, String key2){
        JsonElement json = new JsonParser().parse(jsonString).getAsJsonArray().get(0);
        String key2Val = "";
        if (json.getAsJsonObject().has(key1) &&
                json.getAsJsonObject().get(key1).getAsJsonObject().has(key2) &&
                !json.getAsJsonObject().get(key1).getAsJsonObject().get(key2).isJsonNull()) {
            key2Val = json.getAsJsonObject().get(key1).getAsJsonObject().get(key2).toString().replace("\"", "");
        }
        return key2Val;
    }
    // Возвращает значение ключа arrKey1 из json массива: [{key1{arrayName[{arrKey1}]}}]
    public String getJsonKey1ValueFromArrayFromKey1(String jsonString, String key1, String arrayName, String arrKey1){
        JsonElement json = new JsonParser().parse(jsonString).getAsJsonArray().get(0);
        String arrKey1Val = "";
        if (json.getAsJsonObject().has(key1) &&
                json.getAsJsonObject().get(key1).getAsJsonObject().has(arrayName) &&
                !json.getAsJsonObject().get(key1).getAsJsonObject().get(arrayName).isJsonNull() &&
                json.getAsJsonObject().get(key1).getAsJsonObject().get(arrayName).getAsJsonArray().get(0).getAsJsonObject().has(arrKey1)) {
            arrKey1Val = json.getAsJsonObject().get(key1).getAsJsonObject().get(arrayName).getAsJsonArray().get(0).getAsJsonObject().get(arrKey1).toString().replace("\"", "");
        }
        return arrKey1Val;
    }
    // Возвращает значение ключа arrKey2 из json массива: [{key1{arrayName[{arrKey1{arrKey2}}]}}]
    public String getJsonKey2ValueFromArrayFromKey1(String jsonString, String key1, String arrayName, String arrKey1, String arrKey2){
        JsonElement json = new JsonParser().parse(jsonString).getAsJsonArray().get(0);
        String arrKey2Val = "";
        if (json.getAsJsonObject().has(key1) &&
                json.getAsJsonObject().get(key1).getAsJsonObject().has(arrayName) &&
                json.getAsJsonObject().get(key1).getAsJsonObject().get(arrayName).getAsJsonArray().get(0).getAsJsonObject().has(arrKey1) &&
                json.getAsJsonObject().get(key1).getAsJsonObject().get(arrayName).getAsJsonArray().get(0).getAsJsonObject().get(arrKey1).getAsJsonObject().has(arrKey2)) {
            arrKey2Val = json.getAsJsonObject().get(key1).getAsJsonObject().get(arrayName).getAsJsonArray().get(0).getAsJsonObject().get(arrKey1).getAsJsonObject().get(arrKey2).toString().replace("\"", "");
        }
        return arrKey2Val;
    }


}
