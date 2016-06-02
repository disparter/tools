import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;

public class JCodenameGen
{
    static int count = 20;
    static int sortOption = 0;
    static int printOption = 0;
    static WordList nouns;
    static WordList adjectives;
    static Set<String> codenames;
    
    public enum SortOptions {
        UNSORTED(0),ASC(1),DESC(2);
        private final int option;
        SortOptions(int option){
            this.option = option;
        }
        public int getOption(){
            return option;
        }
    }
    
    public enum PrintOptions {
        CLEAN(0),CSV(1),GRID(2);
        private final int option;
        PrintOptions(int option){
            this.option = option;
        }
        public int getOption(){
            return option;
        }
    }
    
    private static class WordList {
        
        private Set<String> wordsBundle;

        public WordList( String inputFileName ) throws FileNotFoundException {
            wordsBundle = new HashSet<>();
            this.loadFromFile( inputFileName );
        }
        
        private void loadFromFile( String fileName ) throws FileNotFoundException {
            
            try (Scanner scanner = new Scanner( new File("resources/" + fileName) ) ) {
                
                while( scanner.hasNext() ) {
                    this.wordsBundle.add( scanner.next() );
                }
                
            }
        }
        
        public String getWord( int index ) {
            String word = new ArrayList<String>(this.wordsBundle).get(index);
            return word.substring(0,1).toUpperCase() + word.substring(1);
        }

        public String getRandomWord() {
            return this.getWord( (int) (Math.random() * this.wordsBundle.size() ) );
        }

        
    }
    
    private static class Builder{
        
        private static void populate(Set<String> codenames){
            for( int i = 1; i <= count; i++) {
                codenames.add(adjectives.getRandomWord() + nouns.getRandomWord());
            }
        }
        
        public static void sort(){
            
            if(sortOption == SortOptions.UNSORTED.option){
                codenames = new HashSet<>();
            }else if(sortOption == SortOptions.ASC.option){
                codenames = new TreeSet<>();
            }else{
                codenames = new TreeSet<>(Collections.reverseOrder());
            }
            populate(codenames);
        }
    }
    
    private static class Printer{
        
        public static void clean(){
            for( String codename:codenames) {
                System.out.println( codename );
            }
        }
        
        public static void csv(){
            Integer i = 1;
            for( String codename:codenames) {
                StringJoiner joiner = new StringJoiner(",");
                joiner.add(i.toString()).add(codename);
                System.out.println(  joiner.toString() );
                i++;
            }
        }
        
        public static void grid(){
            Integer i = 1;
            for( String codename:codenames) {
                StringJoiner joiner = new StringJoiner("|\t", "|", "\t|");
                joiner.add(i.toString()).add(codename);
                System.out.println(  joiner.toString() );
                i++;
            }
        }
    }
    
    private static void validateArgs(String[] args) throws InvalidParameterException{
        try {
            if(args.length > 0){
                count = Integer.parseInt(args[0]);
                if(args.length > 1){
                    printOption = Integer.parseInt(args[1]);
                    if(args.length > 2){
                        sortOption = Integer.parseInt(args[2]);
                    }
                }
            }
        } catch(Exception e) {
            throw new InvalidParameterException("Invalid Arguments!");
        }
        
    }
    
    public static void main(String[] args) {
        
        try {
            validateArgs(args);
            
            try {
                nouns = new WordList( "eng_nouns.txt" );
                adjectives = new WordList( "eng_adjectives.txt" );
                
                Builder.sort();
                
                if(printOption == PrintOptions.CLEAN.option){
                    Printer.clean();
                }else if(printOption == PrintOptions.CSV.option){
                    Printer.csv();
                }else{
                    Printer.grid();
                }
                
                
            } catch( Exception e ) {
                System.err.println( "Failed to load word's list: " + e.getMessage() );
            }
        } catch( InvalidParameterException e ) {
            System.err.println( e.getMessage() );
        }
        
    }
}

/* eof */
