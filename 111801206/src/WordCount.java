import java.io.IOException;

public class WordCount {

        public static void main(String[] args){
            if(args.length != 2){
                System.out.println("there are not 2 parameters");
                return;
            }
            String input = args[0];
            String output = args[1];
            try {
                WordsProcess wp = new WordsProcess(input, output);
                wp.deal();
            }
            catch (IOException e){
                System.out.println("File Error!");
            }
        }
}
