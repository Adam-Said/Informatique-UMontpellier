package com.yourcompany;

/**
 * Hello world!
 *
 */
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.collections4.MultiValuedMap;
 
public class App 
{
    public static void main(String[] args) {
        MultiValuedMap<String, String> valueMap = new ArrayListValuedHashMap<String, String>();
        valueMap.put("h1", "value1");
        valueMap.put("h1", "value2");

        System.out.println(valueMap.get("h1"));
    }
}
