package com.leetcode.reeflection;

import java.lang.reflect.Proxy;
import java.nio.charset.Charset;
import java.util.Random;

public class ProxyExperiments {

    public static void main(String[] args) {
        TestInterface testInstance = (TestInterface) Proxy.newProxyInstance(
                TestInterface.class.getClassLoader(),
                new Class[]{TestInterface.class},
                (proxy, method, methodArgs) -> {
                    if (Void.TYPE.equals(method.getReturnType())) {
                        System.out.println("call void method: " + method.getName() + " params: " + methodArgs);
                    } else if (String.class.equals(method.getReturnType())) {
                        byte[] array = new byte[7]; // length is bounded by 7
                        new Random().nextBytes(array);
                        return new String(array, Charset.forName("UTF-8"));
                    }
                    return new Object();
                });

        try {
            Class clazz = Class.forName("com.leetcode.reeflection.TestInterface");
            System.out.println(clazz.getMethods()[0]);
        } catch (ClassNotFoundException e) {
            System.out.println(":( " + e);
            e.printStackTrace();

        }

        System.out.println(testInstance.getName());
        testInstance.doSmth(4);
    }


}
