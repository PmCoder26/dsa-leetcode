import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Backtracking {

    /*
            Given a string containing digits from 2-9 inclusive,
            return all possible letter combinations that the number
            could represent. Return the answer in any order. A mapping
            of digits to letters (just like on the telephone buttons) is
            given below. Note that 1 does not map to any letters.
     */

    private void helper1(String digits, HashMap<Character, String> charSet,
                         List<String> result, String combo){        // part 1.
        if(digits.length() == 0){
            return;
        }
        else{
            char digit = digits.charAt(0);
            String words = charSet.get(digit);
            for(int i = 0; i < words.length(); i++){
                if(digits.length() != 0){
                    helper1(digits.substring(1, digits.length()),
                            charSet, result, combo + words.charAt(i));
                }
                else{
                    result.add(combo + words.charAt(i));
                }
            }
            return;
        }
    }

    public List<String> letterCombinations(String digits) {     // part 2.
        HashMap<Character, String> charSet = new HashMap<>();
        List<String> result = new ArrayList<String>();
        charSet.put('2', "abc");
        charSet.put('3', "def");
        charSet.put('4', "ghi");
        charSet.put('5', "jkl");
        charSet.put('6', "mno");
        charSet.put('7', "pqrs");
        charSet.put('8', "tuv");
        charSet.put('9', "wxyz");
        helper1(digits, charSet, result, "");
        return result;
    }

    public static void main(String[] args){

    }
}
