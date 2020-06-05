package com.ghd.dao;

import com.sun.corba.se.spi.ior.ObjectKey;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class TestJava extends TestCoreJava {

    public static void main(String[] args) throws Exception {

        /*Class<?> aClass = Class.forName("com.ghd.dao.TestCoreJava");
        TestCoreJava testCoreJava = (TestCoreJava) aClass.newInstance();*/

       /* String s = testCoreJava.returnName();
        System.out.println(s);*/

        /*Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }*/

        /*Method privateMethod = aClass.getDeclaredMethod("returnParams",String.class,String.class);
        privateMethod.setAccessible(true);
        System.out.println(privateMethod.invoke(testCoreJava,"asd","qwe").toString());*/

        HashSet<Object> objects = new HashSet<>();

        TestCoreJava testCoreJava1 = new TestCoreJava();
        TestCoreJava testCoreJava2 = new TestCoreJava();
        testCoreJava1.setAge(1);
        testCoreJava2.setAge(2);
        objects.add(testCoreJava1);
        objects.add(testCoreJava2);

        for (Object object : objects) {
            System.out.println(object);
        }

    }
}
