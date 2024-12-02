package com.yorange.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        //this.testStr("abc");
        //this.testStr("1,2,3");
        String [] aa = new String[]{"yi", "er", "san"};
        this.testStr(aa);
    }

    private void testStr(String... str) {
        if (str.length == 1) {

            System.out.println("01===" + str);
            System.out.println("02===" + str[0]);
        } else {
            System.out.println("03===" + str);
            CollectionUtils.arrayToList(str).forEach(item -> System.out.println("05===" + item));
        }
    }

}
