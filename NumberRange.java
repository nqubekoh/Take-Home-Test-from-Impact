package numberrangesummarizer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class NumberRange implements NumberRangeSummarizer {
    //method that returns Integer Collection or null
    public Collection<Integer> collect(String input) {

        //validating data
        if(input == null){
            return null;
        }else if(input.isEmpty()){
            return new ArrayList<Integer>();
        }else if (hasSpecialCharacterOrLetters(input) || !isCommaDelimited(input)){
            return null;
        }else {
            input = stripLastComma(input);
            input = stripStartComma(input);

            //splitting string into arraylist
            //then converting to Integer list from String list
            ArrayList<Integer> lstInt = convertStringListToIntegerList(Arrays.asList(input.split(",")));
            Collections.sort(lstInt); //sorting list after converting

            return lstInt;
        }
    }


    public String summarizeCollection(Collection<Integer> input){

        if(input == null){
            return("ERROR: Collection null");
        }else if(input.isEmpty()){
            return ("ERROR: Collection empty");
        }else {
            Integer[] arrInput = input.toArray(new Integer[0]);    //converted Collection to array

            List<List<Integer>> lstMain = new ArrayList<>(); // main List
            List<Integer> lstTemp = new ArrayList<>();        // inner list
            lstTemp.add(arrInput[0]);                    // adding first element to main list

            for (int i = 0; i < arrInput.length - 1; i++) {    // -1 to avoid index out of bounds exception

                if (arrInput[i + 1] == arrInput[i] + 1) {    // checking if current and following index are sequential
                    lstTemp.add(arrInput[i + 1]);

                } else {
                    lstMain.add(lstTemp);                //adds list to main list
                    lstTemp = new ArrayList<>();        //creates new list
                    lstTemp.add(arrInput[i + 1]);        //adds index into list
                }
            }

            lstMain.add(lstTemp);        //finally adds last list to main list
            return display(lstMain);
        }
    }


    //converts List to Integer List
    private ArrayList<Integer> convertStringListToIntegerList(List<String> input){

        ArrayList <Integer> lst = new ArrayList<>();
        for(String x : input){
            lst.add(Integer.parseInt(x.trim()));	// strips white spaces before converting to int
        }
        return lst;

    }


    //formats string for displaying
    private String display (List<List<Integer>> x){

        String result = "";
        for (int i = 0; i < x.size(); i++ ) {

            if(x.get(i).size() ==1){
                result = result+  x.get(i).get(0);
            }else{
                result = result + x.get(i).get(0) + "-" + x.get(i).get(x.get(i).size()-1);
            }
            result = result + ", ";
        }

        return stripLastComma(result);
    }


    //helper function to cut last comma off of string
    private String stripLastComma(String x){
        x=x.trim();
        if(x.lastIndexOf(",") == x.length()-1)
            return x.substring(0,x.length()-1);	//exclude last comma
        return x;
    }


    //helper function to cut comma off start of string (if any)
    private String stripStartComma(String x){
        x=x.trim();
        if(x.substring(0,1).equals(","))
            return x.substring(1); // exclude first index since it is a comma
        return x;
    }


    private boolean isCommaDelimited(String input){

        input = stripLastComma(input.trim());
        input = stripStartComma(input);

        if(input.length() == 1){
            return true;
        }else {
            Scanner scn = new Scanner(input).useDelimiter(",");
            while(scn.hasNext()){
                try{
                    String s = scn.next();
                    s = s.trim();
                    int x = Integer.parseInt(s);

                }catch (Exception e){
                    return false;
                }
            }
            return true;
        }
    }


    //helper function to check if string has special characters or letters (excluding comma)
    private boolean hasSpecialCharacterOrLetters(String x){

        Pattern p = Pattern.compile("[^0-9, ]");
        Matcher m = p.matcher(x);

        return m.find();

    }
}
