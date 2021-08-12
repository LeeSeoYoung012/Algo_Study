package BackJoon.0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 가장가까운공통조상_3584 {
	
	Stack<Integer> st = new Stack<>();
	static int n,t;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
	for(int k=0; k<t; k++) {
		
		n = Integer.parseInt(br.readLine());
		int [] map = new int[n+1];
		
		for(int i=0; i<n-1;i++) {
			
			String str = br.readLine();
			String[] a = str.split(" ");
			
			map[Integer.parseInt(a[1])]= Integer.parseInt(a[0]);
			}
		String s = br.readLine();
		String []sa = s.split(" ");
		int a = Integer.parseInt(sa[0]);
		int b = Integer.parseInt(sa[1]);
		int tb =b;
	
		while(a!=0) {
		
			boolean toggle = false;
			b=tb;
			while(b!=0) {
				if(a==b) {toggle = true; break;}
				b=map[b];
			}
			if(toggle ==true) {	sb.append(a+"\n"); break;}
			a=map[a];
			
		}
	}
	
	System.out.println(sb);
	
	}

}
