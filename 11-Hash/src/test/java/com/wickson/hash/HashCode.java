package com.wickson.hash;


import com.wickson.hash.entity.Person;
import com.wickson.hash.map.HashMap;
import org.junit.Test;


public class HashCode {

    @Test
    public void hashCodeByFloat() {
        float key = 7.2f;
        int code = Float.floatToIntBits(key);
        System.out.println("code = " + code); // 1088841318
        System.out.println("code = " + Integer.toBinaryString(code)); // 1000000111001100110011001100110
    }

    @Test
    public void hashCodeByLong() {
        long key = 9000000000000000000L;
        int code = (int) (key ^ (key >>> 32));
        System.out.println("code = " + code);
    }

    @Test
    public void hashCodeByDouble() {
        double key = 123456.12;
        long code = Double.doubleToLongBits(key);
        System.out.println("code = " + code);
        System.out.println("code = " + Long.toBinaryString(code));
        int i = (int) (code ^ (code >>> 32));
        System.out.println("i = " + Integer.toBinaryString(i));
    }

    @Test
    public void hashCodeByString() {
        String key = "Jack";
        int hashCode = 0;
        for (int i = 0; i < key.length(); i++) {
            hashCode = hashCode * 31 + key.charAt(i);
        }
        System.out.println("hashCode = " + hashCode);
    }

    @Test
    public void hashCodeByObject() {
//        Map<Object, Object> hashMap = new HashMap<>();
//        for (int i = 0; i < 1000000; i++) {
//            Person person = new Person(18, 175.1f, "jack");
//            hashMap.put(person, person);
//        }
//        Person person1 = new Person(18, 175.1f, "jack");
//        hashMap.put(person1, person1);
//        hashMap.put("object", new Object());
//        hashMap.forEach((key, value) -> System.out.println("key = " + key + ", value = " +value));
//        System.out.println("hashMap.size() = " + hashMap.size());
    }

    @Test
    public void hashCodeByHashMap() {
        Person person = new Person(18, 175.1f, "jack");
        Person person1 = new Person(18, 175.1f, "jack");
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(person, 1);
        hashMap.put(person1, 2);
        hashMap.put("object", 3);
        hashMap.put(null, 4);
        System.out.println("hashMap.get() = " + hashMap.get(person1));
        System.out.println("hashMap.get() = " + hashMap.get("object"));
        System.out.println("hashMap.get() = " + hashMap.get(null));
    }

}
//0100 0000 1111 1110 0010 0100 0000 0001 1110 1011 1000 0101 0001 1110 1011 1000
//0000 0000 0000 0000 0000 0000 0000 0000 0100 0000 1111 1110 0010 0100 0000 0001
//0100 0000 1111 1110 0010 0100 0000 0001 1110 1011 0111 1011 0011 1010 1011 1001