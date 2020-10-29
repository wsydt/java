package com.example.wsy.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class StringTest {

    public static void main(String[] args) {
//        String str = "a,b,c,,";
//        String[] splits = str.split(",");
//        System.out.println(splits.length);

//        List<String> list = new ArrayList<>();
//        list.add("object a");
//        list.add("object b");
//        list.add("object c");
//
//        List<Object> a = Arrays.asList(list.toArray());
//        a.add("Object d");
        List<? super A> list = new ArrayList<>();
        A a = new A(10);
        B b = new B(13, 89);
        C c = new C(50, 109);
        list.add(b);
        list.add(a);
        list.add(c);

        A object = (A) list.get(0);
        System.out.println(object);
        A object1 = (A) list.get(1);
        System.out.println(object1);
        A object2 = (A) list.get(2);
        System.out.println(object2);

        Iterator<? super A> iterator = list.iterator();
        for (;iterator.hasNext();) {
            Object o = iterator.next();
            if(o instanceof  A) {
                A a1 = (A) o;
                if(a1.getA() == 10)
                    iterator.remove();
            }
        }

        System.out.println("remove item -----------------------");
        for (Object o : list) {
            System.out.println(o);
        }


    }

}
class A {
    private int a;

    public A(int a) {
        this.a = a;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "A{" +
                "a=" + a +
                '}';
    }
}

class B extends A {
    private int b;

    public B(int a, int b) {
        super(a);
        this.b = b;
    }

    @Override
    public String toString() {
        return "B{" +
                "b=" + b +
                '}';
    }
}

class C extends A{
    private int c;

    public C(int a, int c){
        super(a);
        this.c = c;
    }

    @Override
    public String toString() {
        return "C{" +
                "c=" + c +
                '}';
    }
}

