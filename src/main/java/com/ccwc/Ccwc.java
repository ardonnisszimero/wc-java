package com.ccwc;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ccwc {
    
    public static final Logger LOG = LogManager.getLogger(Ccwc.class);
    private List<FileReader> filePaths; 
    Collection<String> files = new ArrayList<>();

    public Ccwc(List<String> filePaths) {
        for(String filePath : filePaths) {
            try {
                files.add(filePath);
            } catch (Exception ex) {
                LOG.error("Error working with file.", ex);
            }
        }
    }

    public int countBytes() {
        int count = 0;
        for(String filePath: files) {
            try(FileInputStream fileInputStream = new FileInputStream(filePath)) {
                while(fileInputStream.read() != -1) {
                    count++;
                }
                fileInputStream.getChannel().position(0);
            } catch(Exception ex) {
                LOG.error("Error counting bytes. Not including file in count.", ex);
            }
        }
        return count;
    }  
    
    public int countCharacter() throws FileNotFoundException {
        int count = 0; 
        for(String filePath : files) {
            FileReader fileReader = new FileReader(filePath); 
            try {
                while(fileReader.read() != -1) {
                    count++;
                }
            } catch(Exception ex) {
                LOG.error("Error counting characters.", ex);
            }

        }
        return count;
    }
    
    public int countLines() throws FileNotFoundException {
        int count = 0; 
        for(String filePath : files) {
            FileReader fileReader = new FileReader(filePath); 
            try(BufferedReader br = new BufferedReader(fileReader)) {
                String str = br.readLine();
                while(str != null) {
                    count++; 
                    str = br.readLine();  
                }
            } catch(Exception ex) {
                LOG.error("Error reading file. Not including file in count.", ex);
            } 
        }
        return count;
    }

    public int countWords() throws FileNotFoundException {
        int count = 0; 
        for(String filePath : files) {
            FileReader fileReader = new FileReader(filePath); 
            try(BufferedReader br = new BufferedReader(fileReader)) {
                String str = br.readLine();
                while(str != null) { 
                    if(str.isEmpty()) {
                        str = br.readLine();
                    } else {
                        String[] words = str.trim().split("\\s");
                        count += Stream.of(words).filter(word -> !word.isBlank()).count();
                        str = br.readLine();
                    }
                } 
            } catch(Exception ex) {
                LOG.error("Error counting words. Not including file in count.", ex);
            } 
        }
        return count;
    }
    

    public static boolean isValidArgument(String arg) {
        return arg.equals("-c") || arg.equals("--bytes") || arg.equals("--lines") || arg.equals("-l") || arg.equals("-m") 
                || arg.equals("--chars") || arg.equals("-w") || arg.equals("--words");
    }
}
