package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParseMapVsMapChild {
    private static final String FRIENDS = "{\"az\": {\"name\": \"az\", \"age\": 18}, \"lz\": {\"name\": \"lz\", \"age\": 12}}";
    private static final String FRIENDS_LIST = "{\"az\": [{\"name\": \"az\", \"age\": 18}], \"lz\": [{\"name\": \"lz\", \"age\": 12}]}";
    private ObjectMapper om;
    private Person az;
    private Person lz;

    @BeforeEach
    void setup() {
        om = new ObjectMapper();
        az = Person.newPerson("az", 18);
        lz = Person.newPerson("lz", 12);
    }

    @Test
    void testParseToMapWithRef() throws Exception {
        Map<String, EitherModelOrReferenceObject<Person>> friends = om.readValue(FRIENDS, new TypeReference<Map<String, EitherModelOrReferenceObject<Person>>>(){});
        assertEquals(2, friends.size());
        assertEquals(EitherModelOrReferenceObject.modelObject(az), friends.get("az"));
        assertEquals(EitherModelOrReferenceObject.modelObject(lz), friends.get("lz"));
    }

    @Test
    void testParseToMapList() throws Exception {
        Map<String, List<Person>> friends = om.readValue(FRIENDS_LIST, new TypeReference<Map<String, List<Person>>>(){});
        assertEquals(2, friends.size());
        assertEquals(az, friends.get("az").get(0));
        assertEquals(lz, friends.get("lz").get(0));
    }

    @Test
    void testParseToMap() throws Exception {
        Map<String, Person> friends = om.readValue(FRIENDS, new TypeReference<Map<String, Person>>(){});
        assertEquals(2, friends.size());
        assertEquals(az, friends.get("az"));
        assertEquals(lz, friends.get("lz"));
    }

    @Test
    void testParseToMapChild() throws Exception {
        PersonDict friends = om.readValue(FRIENDS, PersonDict.class);
        assertEquals(2, friends.size());
        assertEquals(az, friends.get("az"));
        assertEquals(lz, friends.get("lz"));
    }

    public static class PersonOrRefDict extends LinkedHashMap<String, EitherModelOrReferenceObject<Person>> {
    }
    public static class PersonDict extends LinkedHashMap<String, Person> {
    }
    public static class Person implements SpecModel {
        public static Person newPerson(String name, int age) {
            Person ret = new Person();
            ret.name = name;
            ret.age = age;
            return ret;
        }
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }
}
