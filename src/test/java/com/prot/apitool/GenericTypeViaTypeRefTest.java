package com.prot.apitool;


import com.fasterxml.jackson.core.type.TypeReference;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class GenericTypeViaTypeRefTest {
    public static void main(String[] args) throws Exception {
        TypeReference<List<A>> typeRef = new TypeReference<List<A>>(){};
        Type type = typeRef.getType();
        System.out.println(type);

        Field nf = A.class.getDeclaredField("names");
        Class<?> nameType = nf.getType();
        Type nameGenericType = nf.getGenericType();
        System.out.printf("name filed type: [%s], generic types: [%s]%n%n", nameType, nameGenericType);

        Field idf = A.class.getDeclaredField("id");
        Class<?> idType = idf.getType();
        Type idGenericType = idf.getGenericType();
        System.out.printf("id filed type: [%s], generic types: [%s]%n%n", idType, idGenericType);
    }

    public static class A {
        private Map<String, Integer> names;
        private Long id;
    }
}
