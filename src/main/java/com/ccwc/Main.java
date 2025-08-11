package com.ccwc;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) throws FileNotFoundException {
        List<String> filePaths = Stream.of(args).filter(arg -> !arg.startsWith("-")).filter(arg -> !Ccwc.isValidArgument(arg)).toList();
        List<String> commands = Stream.of(args).filter(arg -> arg.startsWith("-")).toList();

        if(filePaths.isEmpty() && commands.isEmpty()) {
            Scanner lines = new Scanner(System.in); 
            while(lines.hasNextLine()) {
                String line = lines.next(); 
                String w[] = line.split("//s"); 
                System.out.println(Arrays.toString(w)); 
            }
        }
        if(filePaths.isEmpty()) {
            throw new RuntimeException("No files given.");
        }
        Ccwc ccwc = new Ccwc(filePaths);
        if(commands.isEmpty()) {
            System.out.println();
            System.out.println("bytes: " + ccwc.countBytes());
            System.out.println("lines: " + ccwc.countLines());
            System.out.println("chars: " + ccwc.countCharacter());
            System.out.println("words: " + ccwc.countWords());
        }
        for(String arg : commands) {
            if(arg.startsWith("-")) {
                switch (arg) {
                    case "--m", "--bytes":
                        System.out.println(ccwc.countBytes());
                        break; 
                    case "--l", "--lines": 
                        System.out.println(ccwc.countLines());
                        break; 
                    case "--c", "--chars": 
                        System.out.println(ccwc.countCharacter());
                        break;
                    case "--w", "--words": 
                        System.out.println(ccwc.countWords());
                        break;
                    default: 
                        LOG.error("Argument {} is not a valid command", arg); 
                }
            }
        }
    }


}