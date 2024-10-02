import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        int z = 0;
        for(int x = 0; x < n; x++){
            nums[x] = sc.nextInt();

        }
        for(int x = 0; x < n; x++){
            if(nums[x] == 0){
                z++;
            }
        }
        int i = 0;
        int j = 0;
        while(z > 0 && i < n){
            if(nums[i] != 0){
                nums[j++] = nums[i];
                z--;
            }
            i++;
        }
        while(i < n){
            nums[i++] = 0;
        }
        for(int x = 0; x < n; x++){
            System.out.print(nums[x] + " ");
        }
    }

}
