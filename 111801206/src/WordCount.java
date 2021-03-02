
public class WordCount {
        public static void main(String[] args){
        if(args.length != 2){
            System.out.println("there are not 2 parameters");
            return;
        }
        String input = args[0];
        String output = args[1];
            WordsProcess wp = new WordsProcess(input,output);
            wp.deal();
        }
}
