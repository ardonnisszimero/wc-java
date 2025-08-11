import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ccwc.Ccwc;

public class CcwcTest {
    List<String> pathToTestFile = List.of("/home/ardy/Dev/wc/wc/src/main/resources/test.txt");
    
    private final Ccwc ccwc = new Ccwc(pathToTestFile);

    @Test
    public void countBytes_correctCount() throws FileNotFoundException {
        assertEquals(342190, ccwc.countBytes());
    }

    @Test 
    public void countLines_correctLineCount() throws FileNotFoundException {
        assertEquals(7145, ccwc.countLines());
    }

    @Test
    public void countCharacter_correctCharacterCount() throws FileNotFoundException {
        assertEquals(339292, ccwc.countCharacter());
    }

    @Test
    public void countWords_correctWordCount() throws FileNotFoundException {
        assertEquals(58164, ccwc.countWords());
    }

    @Test
    public void countAll_correctCount() throws FileNotFoundException {
        assertEquals(7145, ccwc.countLines());
        assertEquals(342190, ccwc.countBytes());
        assertEquals(58164, ccwc.countWords());
        assertEquals(339292, ccwc.countCharacter());
    }

    @Test
    public void isValidArgument(){
        String arg1 = "-c"; 
        String arg2 = "--chars";
        String arg3 = "-w"; 
        String arg4 = "--totalz"; 

        boolean consume1 = Ccwc.isValidArgument(arg1); 
        boolean consume2 = Ccwc.isValidArgument(arg2); 
        boolean consume3 = Ccwc.isValidArgument(arg3); 
        boolean consume4 = Ccwc.isValidArgument(arg4); 

        assertEquals(consume1, true);
        assertEquals(consume2, true);
        assertEquals(consume3, true);
        assertEquals(consume4, false);
        
    }
}
