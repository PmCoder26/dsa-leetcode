import java.util.Stack;

public class Stacks {

    /*
            Given two integer arrays pushed and popped each with distinct values, return true if this could have
            been the result of a sequence of push and pop operations on an initially empty stack, or false otherwise.

            Example 1:
                Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
                Output: true
                Explanation: We might do the following sequence:
                    push(1), push(2), push(3), push(4),
                    pop() -> 4,
                    push(5),
                    pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
            Example 2:
                Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
                Output: false
                Explanation: 1 cannot be popped before 2.
     */

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if(pushed.length == 0 && popped.length == 0) return true;
        if(pushed.length == 0 || popped.length == 0) return false;
        else {
            Stack<Integer> st = new Stack<>();
            int i = 0;
            int j = 0;
            while(i < pushed.length && j < popped.length) {
                if(st.isEmpty()) {
                    st.push(pushed[i++]);
                }
                else {
                    if(st.peek() == popped[j]) {
                        st.pop();
                        j++;
                    }
                    else {
                        st.push(pushed[i++]);
                    }
                }
            }
            if(j < popped.length && !st.isEmpty()) {
                while(!st.isEmpty() && st.peek() == popped[j++]) {
                    st.pop();
                }
            }
            return st.isEmpty();
        }
    }

    public static void main(String[] args) {}

}
