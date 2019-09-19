package boj;
import java.util.Arrays;
import java.util.Scanner;
public class BOJ_2783_삼각김밥 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		double a=(double)sc.nextInt()/sc.nextInt();
		int N=sc.nextInt();
		double[] arr=new double[N+1];
		arr[0]=a;
		for(int i=1; i<=N; i++)
			arr[i] = (double)sc.nextInt()/sc.nextInt();
		Arrays.sort(arr);
		System.out.printf("%.2f", arr[0]*1000);
	}
}
