package com.example.hawk.fastjson;

import com.alibaba.fastjson.JSON;

/**
 * @author hawk
 * @package com.example.hawk.fastjson
 * @desc
 * @date 2022/6/12
 */
public class Test {
    public static void main(String[] args) {
        String str = "{\n" +
                "\t\"name\":\"xxx\",\n" +
                "\t\"age\":\"18\",\n" +
                "\t\"studentId\":\"10086\"\n" +
                "}";

        Student s = JSON.parseObject(str, Student.class);

        System.out.println(JSON.toJSONString(s));
    }
}
