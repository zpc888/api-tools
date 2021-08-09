package com.prot.apitool.mock.dto;

import java.util.*;

/**
 * key is string, value can be string or list of string
 */
public class FlexMap {
    private Map<String, Object> map = Collections.emptyMap();

    public int size() {
        return map.size();
    }

    public String value(String key) {
        if (!map.containsKey(key)) {
            return null;
        }
        Object currValue = map.get(key);
        if (currValue instanceof String) {
            return (String) currValue;
        } else {
            @SuppressWarnings(value="unchecked")
            List<String> valueList = (List<String>) map.get(key);
            return valueList.get(0);
        }
    }

    @SuppressWarnings(value="unchecked")
    public List<String> values(String key) {
        if (!map.containsKey(key)) {
            return Collections.emptyList();
        }
        Object currValue = map.get(key);
        if (currValue instanceof String) {
            return Collections.singletonList((String)currValue);
        } else {
            return (List<String>) map.get(key);
        }
    }

    @SuppressWarnings(value="unchecked")
    public void add(String key, String value) {
        if (map == Collections.<String, Object>emptyMap()) {
            map = new HashMap<>();
        }
        if (map.containsKey(key)) {
            Object prev = map.get(key);
            if (prev instanceof List) {
                ((List<String>)prev).add(value);
            } else {        // string
                List<String> merged = new ArrayList<>();
                map.put(key, merged);
                merged.add((String)prev);
                merged.add(value);
            }
        } else {
            map.put(key, value);
        }
    }

    public boolean contains(String key, String... values) {
        if (!map.containsKey(key)) {
            return false;
        }
        if (values != null && values.length > 0) {
            Object objValue = map.get(key);
            if (objValue instanceof String) {
                for (String expected: values) {
                    if (!expected.equals(objValue)) {
                        return false;
                    }
                }
            } else if (objValue instanceof List) {
               @SuppressWarnings(value="unchecked")
               List<String> list = (List<String>) objValue;
               for (String expected: values) {
                   if (!list.contains(expected)) {
                       return false;
                   }
               }
            } else {
                // impossible
                throw new IllegalStateException("Impossible: value can either be string or list, but it is " + objValue.getClass().getName());
            }
        }
        return true;
    }
}
