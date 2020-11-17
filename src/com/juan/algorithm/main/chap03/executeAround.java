package com.juan.algorithm.main.chap03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Gatsjy
 * @since 2020-11-17
 * realize dreams myself
 * Blog : https://blog.naver.com/gkswndks123
 * Github : https://github.com/gatsjy
 */
class ExecuteAround {

    private static final String FILE = ExecuteAround.class.getResource("C:\\Users\\Gatsjy\\Desktop\\ModernJavaInAction\\src\\resources\\chap03\\data.txt").getFile();

    public static void main(String... args) throws IOException {
        String result = processFileLimited();
        System.out.println(result);
    }

    public static String processFileLimited() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(FILE))){
            return br.readLine();
        }
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(FILE))){
            return p.process(br);
        }
    }

    public interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }
}
