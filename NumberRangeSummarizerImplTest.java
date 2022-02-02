import numberrangesummarizer.NumberRangeSummarizerImpl;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


// it is assumed that collect() and summarizeCollection() are called in tandem
class NumberRangeSummarizerImplTest {

    NumberRangeSummarizerImpl summarizer;

    @BeforeEach
    void setUp() {
        summarizer = new NumberRangeSummarizerImpl();
    }

    @AfterEach
    void tearDown() {
        summarizer = null;
    }

    @Test
    public void testCollectEmptyString(){
        //if the inputted string is empty the method returns an empty collection
        Collection <Integer> expected = new ArrayList<Integer>();
        Collection<Integer> actual = summarizer.collect("");

        assertEquals(expected,actual);

    }

    @Test
    public void testCollectNotCommaDelimited(){

        //method returns null if collection is not properly comma delimited
        Collection <Integer> expected = null;
        Collection<Integer> actual = summarizer.collect("1 , 2 , 3 , 4  5 , 6");

        assertEquals(expected,actual);


    }
    @Test
    public void testCollectionNull(){

        //method returns null if input is null
        Collection <Integer> expected = null;
        Collection<Integer> actual = summarizer.collect(null);

        assertEquals(expected,actual);
    }

    @Test
    public void testCollectionNotNumbers(){

        //method returns null if string contains letters
        Collection <Integer> expected = null;
        Collection<Integer> actual = summarizer.collect("1,2,3,a,3,4");

        assertEquals(expected,actual);

    }

    @Test
    public void testCollectionSpecialCharacters(){

        //method returns null if string contains special characters
        Collection <Integer> expected = null;
        Collection<Integer> actual = summarizer.collect("1,2,3,},3,4");

        assertEquals(expected,actual);

    }
    @Test
    public void testSummarizeCollectionSimple(){

        ArrayList <Integer> expected = new ArrayList<Integer>();
        expected.add(1);
        expected.add(3);
        expected.add(6);
        expected.add(7);
        expected.add(8);
        expected.add(12);
        expected.add(13);
        expected.add(14);
        expected.add(15);
        expected.add(21);
        expected.add(22);
        expected.add(23);
        expected.add(24);
        expected.add(31);

        Collection<Integer> actual = summarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");

        assertEquals(expected,actual);

    }

    @Test
    public void testSummarizeCollectionNull(){

        String expected = "ERROR: Collection null";

        String actual = summarizer.summarizeCollection(summarizer.collect(null));

        assertEquals(expected,actual);

    }

    @Test
    public void testSummarizeCollectionEmpty(){

        String expected = "ERROR: Collection empty";

        String actual = summarizer.summarizeCollection(summarizer.collect(""));

        assertEquals(expected,actual);

    }
    @Test
    public void testSummarizeCollectionSingleRange(){

        String expected = "1, 3-5";

        String actual = summarizer.summarizeCollection(summarizer.collect("1,3,4,5"));

        assertEquals(expected,actual);

    }
    @Test
    public void testSummarizeCollectionMultipleSingleRange(){

        String expected = "1, 3, 6-8, 12-15, 21-24, 31";

        String actual = summarizer.summarizeCollection(summarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31"));

        assertEquals(expected,actual);

    }
}