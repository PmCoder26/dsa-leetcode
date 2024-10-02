import java.util.*;

public class Strings {


    /*
            Alice and Bob are playing a game. Initially, Alice has a string word = "a".
            You are given a positive integer k.
            Now Bob will ask Alice to perform the following operation forever:
                Generate a new string by changing each character in word to its next character in the English alphabet,
                and append it to the original word.
            For example, performing the operation on "c" generates "cd" and performing the operation on "zb" generates
            "zbac".

            Return the value of the kth character in word, after enough operations have been done for word to have at
            least k characters.
            Input: k = 5
            Output: "b"
            Explanation:
                Initially, word = "a". We need to do the operation three times:
                    Generated string is "b", word becomes "ab".
                    Generated string is "bc", word becomes "abbc".
                    Generated string is "bccd", word becomes "abbcbccd".
     */

    private static char kthCharacter(int k) {
        if(k == 1){
            return 'a';
        }
        else{
            String ans = "a";
            while(ans.length() < k){
                String temp = "";
                for(int x = 0; x < ans.length(); x++){
                    temp += Character.toString((char)((int) ans.charAt(x) + 1));
                }
                ans += temp;
            }
            return ans.charAt(k - 1);
        }
    }

    /*
            Given a Roman numeral, convert it to an integer. The Roman numerals are based on the following symbols and
            their values I -> 1, V -> 5, X -> 10, L -> 50, C -> 100, D -> 500, M -> 1000, IV = 4 (5 - 1), IX = 9 (10 - 1),
            XL = 40 (50 - 10), XC = 90 (100 - 10), CD = 400 (500 - 100), CM = 900 (1000 - 100)
     */

    private static class Result {
        int value = 0;
    }

    private static void processVorX(Stack<Character> st, HashMap<Character, Integer> hm, Result result, char c){
        if(st.isEmpty()){
            st.push(c);
        }
        else{
            char curr = st.pop();
            if(curr == 'I'){
                result.value += hm.get(c) - 1;
            }
            else{
                st.push(curr);
                st.push(c);
            }
        }
    }

    private static void processL(Stack<Character> st, HashMap<Character, Integer> hm, Result result, char c){
        if(st.isEmpty()){
            st.push(c);
        }
        else{
            char curr = st.pop();
            if(curr == 'X'){
                result.value += hm.get(c) - 10;
            }
            else{
                st.push(curr);
                st.push(c);
            }
        }
    }

    private static void processC(Stack<Character> st, HashMap<Character, Integer> hm, Result result, char c){
        if(st.isEmpty()){
            st.push(c);
        }
        else{
            char curr = st.pop();
            if(curr == 'X'){
                result.value += hm.get(c) - 10;
            }
            else{
                st.push(curr);
                st.push(c);
            }
        }
    }

    private static void processD(Stack<Character> st, HashMap<Character, Integer> hm, Result result, char c){
        if(st.isEmpty()){
            st.push(c);
        }
        else{
            char curr = st.pop();
            if(curr == 'C'){
                result.value += hm.get(c) - 100;
            }
            else{
                st.push(curr);
                st.push(c);
            }
        }
    }

    private static void processM(Stack<Character> st, HashMap<Character, Integer> hm, Result result, char c){
        if(st.isEmpty()){
            st.push(c);
        }
        else{
            char curr = st.pop();
            if(curr == 'C'){
                result.value += hm.get(c) - 100;
            }
            else{
                st.push(curr);
                st.push(c);
            }
        }
    }

    private static void romanToInteger() {
        Scanner sc = new Scanner(System.in);
        String roman = sc.next();
        HashMap<Character, Integer> hm = new HashMap<>();
        Stack<Character> st = new Stack<>();
        Result result = new Result();
        hm.put('I', 1);
        hm.put('V', 5);
        hm.put('X', 10);
        hm.put('L', 50);
        hm.put('C', 100);
        hm.put('D', 500);
        hm.put('M', 1000);
        for (int x = 0; x < roman.length(); x++) {
            char c = roman.charAt(x);
            if (c == 'I') {
                st.push(c);
            } else if (c == 'V' || c == 'X') {
                processVorX(st, hm, result, c);
            } else if (c == 'L') {
                processL(st, hm, result, c);
            } else if (c == 'C') {
                processC(st, hm, result, c);
            } else if (c == 'D') {
                processD(st, hm, result, c);
            } else if (c == 'M') {
                processM(st, hm, result, c);
            }
        }
        while (!st.isEmpty()) {
            result.value += hm.get(st.pop());
        }

        System.out.println(result.value);
    }

    public static void main(String[] args){

    }

}
