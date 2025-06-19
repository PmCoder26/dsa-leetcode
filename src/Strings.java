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

    /*
            You are given a string s.

            Your task is to remove all digits by doing this operation repeatedly:
            Delete the first digit and the closest non-digit character to its left.
            Return the resulting string after removing all digits.

            Example 1:
                Input: s = "abc"
                Output: "abc"
            Explanation:
                There is no digit in the string.
            Example 2:
                Input: s = "cb34"
                Output: ""
            Explanation:
                First, we apply the operation on s[2], and s becomes "c4".
                Then we apply the operation on s[1], and s becomes "".
     */

    private static String clearDigits(String s) {
        if(s.length() == 1) {
            if(s.charAt(0) < '9' && s.charAt(0) > '0') {
                return "";
            }
            return s;
        }
        else {
            StringBuilder ans = new StringBuilder("");
            for(int x = 0, y = -1; x < s.length(); x++) {
                char c = s.charAt(x);
                if(c >= 'a' && c <= 'z') {
                    ans.append(c);
                    y++;
                }
                else {
                    ans.deleteCharAt(y);
                    y--;
                }
            }
            return ans.toString();
        }
    }

    /*
            Given an array of characters chars, compress it using the following algorithm:

            Begin with an empty string s. For each group of consecutive repeating characters in chars:
                If the group's length is 1, append the character to s.
                Otherwise, append the character followed by the group's length.

            The compressed string s should not be returned separately, but instead, be stored in the input character
            array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.
            After you are done modifying the input array, return the new length of the array.

            You must write an algorithm that uses only constant extra space.

            Example 1:
                Input: chars = ["a","a","b","b","c","c","c"]
                Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
                Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".

            Example 2:
                Input: chars = ["a"]
                Output: Return 1, and the first character of the input array should be: ["a"]
                Explanation: The only group is "a", which remains uncompressed since it's a single character.
            Example 3:
                Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
                Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
                Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".
     */

    public int compress(char[] chars) {
        if(chars.length == 1) {
            return 1;
        }
        else {
            Integer count = 1;
            char ele = ' ';
            int y = 0;
            for(int x = 0; x < chars.length; x++) {
                char c = chars[x];
                if(ele == ' ') {
                    ele = c;
                }
                else {
                    if(ele == c) {
                        count++;
                    }
                    else {
                        y++;
                        if(count != 1) {
                            StringBuilder str = new StringBuilder(count.toString());
                            if(str.length() > 1) {
                                for(int z = 0; z < str.length(); z++) {
                                    chars[y++] = str.charAt(z);
                                }
                            }
                            else {
                                chars[y] = str.charAt(0);
                                y++;
                            }
                        }
                        ele = c;
                        chars[y] = ele;
                        count = 1;
                    }
                }
            }
            if(count != 1) {
                y++;
                StringBuilder str = new StringBuilder(count.toString());
                for(int z = 0; z < str.length(); z++) {
                    chars[y++] = str.charAt(z);
                }
                return y;
            }
            return y + 1;
        }
    }

    /*
            The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
                countAndSay(1) = "1"
                countAndSay(n) is the run-length encoding of countAndSay(n - 1).
            Run-length encoding (RLE) is a string compression method that works by replacing consecutive identical
            characters (repeated 2 or more times) with the concatenation of the character and the number marking the
            count of the characters (length of the run). For example, to compress the string "3322251" we replace "33"
            with "23", replace "222" with "32", replace "5" with "15" and replace "1" with "11". Thus the compressed
            string becomes "23321511".

            Given a positive integer n, return the nth element of the count-and-say sequence.

            Example 1:
                Input: n = 4
                Output: "1211"
                Explanation:
                    countAndSay(1) = "1"
                    countAndSay(2) = RLE of "1" = "11"
                    countAndSay(3) = RLE of "11" = "21"
                    countAndSay(4) = RLE of "21" = "1211"

            Example 2:
                Input: n = 1
                Output: "1"
                Explanation:
                    This is the base case.
     */

    public String countAndSay(int n) {
        if(n == 1){
            return "1";
        }
        else{
            StringBuilder str = new StringBuilder(countAndSay(n - 1));
            StringBuilder result = new StringBuilder();
            int x = 1;
            int count = 1;
            char ele = str.charAt(0);
            while(x < str.length()) {
                char c = str.charAt(x);
                if(c == ele) {
                    count++;
                }
                else {
                    result.append(count).append(ele);
                    count = 1;
                    ele = c;
                }
                x++;
            }
            result.append(count).append(ele);
            return result.toString();
        }
    }

    /*
            Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

            An interleaving of two strings s and t is a configuration where s and t are divided into n and m
            substrings respectively, such that:
                s = s1 + s2 + ... + sn
                t = t1 + t2 + ... + tm
                |n - m| <= 1
                The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
                Note: a + b is the concatenation of strings a and b.
            Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
            Output: true
            Explanation: One way to obtain s3 is:
                Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
                Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
                Since s3 can be obtained by interleaving s1 and s2, we return true.
            Example 2:
                Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
                Output: false
                Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.
            Example 3:
                Input: s1 = "", s2 = "", s3 = ""
                Output: true
     */

    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.isBlank() && s2.isBlank() && s3.isBlank()) return true;
        if(s1.length() + s2.length() != s3.length()) return false;
        else {
            boolean s1True = false;
            boolean s2True = false;
            if(!s1.isBlank() && s3.charAt(0) == s1.charAt(0)) s1True = isInterleave(s1.substring(1), s2, s3.substring(1));
            if(!s2.isBlank() && !s1True && s3.charAt(0) == s2.charAt(0)) s2True = isInterleave(s1, s2.substring(1), s3.substring(1));
            return s1True || s2True;
        }
    }

    // optimized version with memoization.
    private Map<String, Boolean> tabs = new HashMap<>();

    private boolean helper(String s1, String s2, String s3, int i, int j, int k) {
        int s1Len = s1.length();
        int s2Len = s2.length();
        int s3Len = s3.length();
        if(k == s3.length()) return i == s1Len && j == s2Len;
        if(s1Len + s2Len != s3Len) return false;
        String key = Integer.toString(i) + "," + Integer.toString(j);
        if(tabs.containsKey(key)) return tabs.get(key);
        else {
            boolean s1True = false;
            boolean s2True = false;
            if(i < s1Len && s3.charAt(k) == s1.charAt(i)) s1True = helper(s1, s2, s3, i + 1, j, k + 1);
            if(j < s2Len && s3.charAt(k) == s2.charAt(j)) s2True = helper(s1, s2, s3, i, j + 1, k + 1);
            tabs.put(key, s1True || s2True);
            return s1True || s2True;
        }
    }

    public boolean isInterleaveMemo(String s1, String s2, String s3) {
        return helper(s1, s2, s3, 0, 0, 0);
    }

    public static void main(String[] args){}

}
