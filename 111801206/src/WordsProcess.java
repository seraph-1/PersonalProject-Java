

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

    WordsProcess(String input,String output){
        numberChar = 0;
        numberLine = 0;
        numberWord = 0;
        map = new HashMap<>();
        inputName = input;
        outputName = output;
    }

    public void deal() throws IOException{
        String str = readFile();
        this.countChar(str);
        this.countWord(str);
        this.countLine(str);
        this.sortWord();
        this.outputFile();
    }

    private String readFile() throws IOException{
        int ch;
        BufferedReader br = new BufferedReader(new FileReader(inputName));
        StringBuilder builder = new StringBuilder();
        while((ch = br.read()) != -1){
            if (ch < 128)builder.append((char)ch);
        }
        br.close();
        return builder.toString();
    }

    private void countChar(String string){
            numberChar = string.length();
    }

    private void countLine(String string){
        String regex = "\\s*";
        String[] content = string.split("\n");
        int total = content.length;
        for (String line : content) {
            if (line.matches(regex))total --;
        }
        numberLine = total;
    }

    private void countWord(String string){
            String temp;
            String[] content = string.split("[^(a-zA-Z0-9)]");
            for (String element:content) {
                temp = element.toLowerCase();
                if(temp.matches("[a-zA-Z]{4}[a-zA-Z0-9]*")){
                    map.merge(temp, 1, Integer::sum);
                    numberWord ++;
                }
            }
    }

    private void sortWord(){
        int total;
        ArrayList<Map.Entry<String,Integer>> list =
                new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() == o2.getValue()){
                    return (o1.getKey()).compareTo(o2.getKey());
                }
                else return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        LinkedHashMap<String,Integer> newMap = new LinkedHashMap<>();
        for(total = 0;total < list.size();total ++){
            if(total >= 10){
                break;
            }
            Integer value = list.get(total).getValue();
            String key = list.get(total).getKey();
            newMap.put(key,value);
        }
        map = newMap;
    }

    public void outputFile() throws IOException{
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputName));
            writer.write("characters: " + numberChar + '\n');
            writer.write("words: " + numberWord+'\n');
            writer.write("lines: " + numberLine+'\n');
            for(Object obj : map.keySet()){
                Integer value = map.get(obj);
                writer.write(obj + ": " + value + '\n');
            }
            writer.close();
    }
}
