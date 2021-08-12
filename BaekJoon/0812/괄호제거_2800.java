package BaekJoon.0812;

import java.io.*;
import java.util.*;
public class 괄호제거_2800 {
		
	static class Pair{
		
		public int x,y;
		Pair(){}
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		int getx() {return x;}
		int gety() {return y;}
	}
	public static ArrayList<Pair>arr;
	public static String str;
	
	public static HashSet<String> answer = new HashSet<String>();
	// set으로 받아야 하는 이유는 (((3)))의 경우 첫번째 두번째 세번째 괄호쌍을 지운 각각의 경우가 모두 ((3))으로 같기 때문이다.
	public static void print(int[]chk) {

		String s = new String(str);//깊은복사
		ArrayList<Integer>list = new ArrayList<>();
		//boolean tg = false;
		for(int i=0; i<chk.length; i++) {
			
			if(chk[i]==1) {
				
				int a = arr.get(i).getx();
				int b = arr.get(i).gety();
				list.add(a);
				list.add(b);				
			}
		}
		
		Collections.sort(list);
		StringBuilder ans =new StringBuilder();
		int j =0;
		for(int i=0; i<s.length(); i++) {
			if(j<list.size() && list.get(j)==i) {j++;continue;}
			ans.append(s.charAt(i));
		}
		
		answer.add(ans.toString());
	}
	
	
	public static void findAll(int[]chk, int idx ,int cnt) {
	
		if(cnt == 0) {
			print(chk);
			return;
		}

		for(int i=idx; i<arr.size(); i++) {
			
			chk[i]=1;
			findAll(chk,i+1,cnt-1);
			chk[i]=0;

		}
}
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader( System.in));
		str = br.readLine();
		
		Stack<Integer> st = new Stack<>();
		arr = new ArrayList<>();
		
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			if(c==')') {
				int t = st.peek();
				arr.add(new Pair(t,i));
				st.pop();
			}
			else if(c=='('){
				st.add(i);
			}
		}
		int[]chk = new int[arr.size()];
		
		
		for(int i=1; i<=arr.size(); i++) {
		findAll(chk,0,i); //combination, 여러개중 i개 선택
		}
	//	Collections.sort(answer);
		List<Integer> ls = new ArrayList(answer); // hashmap arraylist로  copy 가능
		Collections.sort(ls);
		for(int i=0; i<ls.size(); i++) {
		
			System.out.println(ls.get(i));
		}
		
	}
}
