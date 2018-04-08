package com.docker.yyf.util;

import java.util.Random;

/**
 * @Author: yyf
 * Description:
 * Date: Created in 2018/1/8 18:39
 * @Modified By
 * \
 */
public class RandomAlphaNumericGenerator {
    private static final char[] symbols;
    private static final Random random = new Random();
    static {
        StringBuilder tmp = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ++ch) {
            tmp.append(ch);
        }
        for (char ch = 'a'; ch <= 'z'; ++ch) {
            tmp.append(ch);
        }
        for (char ch = 'A'; ch <= 'Z'; ++ch) {
            tmp.append(ch);
        }
        // 添加一些特殊字符
        tmp.append("_");
        symbols = tmp.toString().toCharArray();
    }



    public static String nextString(int min,int max) {
        Random rdm = new Random();
        int result=rdm.nextInt(max-min+1)+min;
        char[] buf= new char[result];
        for (int idx = 0; idx < buf.length; ++idx) {
            buf[idx] = symbols[random.nextInt(symbols.length)];
        }
        return new String(buf);
    }

    public static void main(String[] args) {

        Random random=new Random();
        random.nextInt();
    }


}
