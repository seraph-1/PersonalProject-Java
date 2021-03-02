package src;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class WordsProcess {
    private int numberChar;
    private int numberLine;
    private int numberWord;
    private HashMap<String,Integer> map;
    private String inputName;
    private String outputName;
    private FileReader fileReader;

    WordsProcess(String input,String output){
        numberChar = 0;
        numberLine = 0;
        numberWord = 0;
        map = new HashMap<>();
        inputName = input;
        outputName = output;
    }

    //统计字符数
    private void countChar(){
        try {
            int ch;
            BufferedReader br = new BufferedReader(new FileReader(inputName));
            while((ch = br.read()) != -1){
                if(ch < 128)numberChar++;
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //统计行数
    private void countLine(){
        try{
            String regex = "\\s*";
            String line = null;
            BufferedReader br = new BufferedReader(new FileReader(inputName));
            while((line = br.readLine()) != null){
                if(!line.matches(regex))numberLine++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
