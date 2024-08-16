


public class Arrays {

    /*
            You are given a large integer represented as an integer array digits, where
            each digits[i] is the ith digit of the integer. The digits are ordered from
            most significant to the least significant in left-to-right order. The large
            integer does not contain any leading 0's. Increment the large integer by one
            and return the resulting array of digits.
     */

    public int[] plusOne(int[] digits) {
        if (digits.length == 1 && digits[0] < 9) {
            digits[0] = digits[0] + 1;
            return digits;
        }
        if (digits.length == 1 && digits[0] == 9) {
            return new int[]{1, 0};
        } else {
            int carry = 0;
            int tempSum = 0;
            int ele = -1;
            boolean start = true;
            for (int i = digits.length - 1; i >= 0; i--) {
                if (start) {
                    if (digits[i] + 1 != 10) {
                        digits[i] = digits[i] + 1;
                        break;
                    } else {
                        carry = 1;
                    }
                    start = !start;
                }
                tempSum = digits[i] + carry;
                if (tempSum == 10) {
                    ele = digits[i];
                    digits[i] = tempSum % 10;
                    carry = tempSum / 10;
                } else {
                    digits[i] = tempSum;
                    carry = 0;
                    break;
                }
            }
            if (carry != 0) {
                ele += carry;
                if (ele != 10) {
                    digits[0] = carry;
                    return digits;
                } else {
                    int[] temp = new int[digits.length + 1];
                    for (int x = 0; x < digits.length; x++) {
                        temp[x + 1] = digits[x];
                    }
                    temp[0] = carry;
                    return temp;
                }
            }
            return digits;
        }
    }


    public static void main(String[] args) {

    }
}
