package com.dale.kotlin;

import com.dale.kotlinlib.array.TestArray;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * kotlin数据结构混编
 */
public class TestJava {


    private void test() {
        TestArray testArray = new TestArray();
        List<String> list1 = testArray.getList1();
        List<String> list2 = testArray.getList2();
        Map<String, Integer> map = testArray.getMap1();
        Map<String, Integer> map2 = testArray.getMap2();
        Set<String> set = testArray.getSet1();
    }
}
