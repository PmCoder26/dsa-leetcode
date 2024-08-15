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

    /*
            Find all valid combinations of k numbers that sum up to n
            such that the following conditions are true:
                Only numbers 1 through 9 are used.
                Each number is used at most once.
            Return a list of all possible valid combinations. The list
            must not contain the same combination twice, and the combinations
            may be returned in any order.
     */

    private void helper2(int k , int n, List<List<Integer>> result, int num, int sum, List<Integer> tempList){
        if(k == 0 || sum > n){
            return;
        }
        else{
            for(int x = num; x <= 9; x++){
                tempList.add(x);
                if(k == 1 && (sum + x == n)){
                    List<Integer> temp = new ArrayList<>(tempList);
                    result.add(temp);
                }
                else{
                    helper2(k - 1, n, result, x + 1, sum + x, tempList);
                }
                tempList.removeLast();
            }
            return;
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        helper2(k, n, result, 1, 0, new ArrayList<Integer>());
        return result;
    }

    public static void main(String[] args){
        ArrayList<Integer> al1 = new ArrayList<>();
        List<Integer> l = (List<Integer>) al1.clone();
    }
}
