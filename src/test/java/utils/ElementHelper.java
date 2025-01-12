package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import java.io.FileReader;

public class ElementHelper {
    private static JSONArray elementsArray;

    static {
        try {
            JSONParser parser = new JSONParser();
            elementsArray = (JSONArray) parser.parse(new FileReader("src/test/resources/elements.json"));
        } catch (Exception e) {
            throw new RuntimeException("Elements JSON dosyası yüklenemedi", e);
        }
    }

    public static By getElementBy(String key) {
        try {
            JSONObject element = findElementByKey(key);
            if (element == null) {
                throw new RuntimeException("Element bulunamadı: " + key);
            }

            String type = (String) element.get("type");
            String value = (String) element.get("value");

            switch (type.toLowerCase()) {
                case "id":
                    return By.id(value);
                case "css":
                    return By.cssSelector(value);
                case "xpath":
                    return By.xpath(value);
                case "name":
                    return By.name(value);
                case "class":
                    return By.className(value);
                case "tagname":
                    return By.tagName(value);
                case "linktext":
                    return By.linkText(value);
                case "partiallinktext":
                    return By.partialLinkText(value);
                default:
                    throw new IllegalArgumentException("Desteklenmeyen lokator tipi: " + type);
            }
        } catch (Exception e) {
            throw new RuntimeException("Element işlenirken hata oluştu: " + key, e);
        }
    }

    private static JSONObject findElementByKey(String key) {
        for (Object obj : elementsArray) {
            JSONObject element = (JSONObject) obj;
            if (key.equals(element.get("key"))) {
                return element;
            }
        }
        return null;
    }
}