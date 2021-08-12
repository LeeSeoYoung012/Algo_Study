package BaekJoon.Stack;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class 후위표기식_1918 {
	
	
	public static int findlevel(char c) {
		
		if(c=='-'||c=='+') {return 1;}
		else if(c=='*'||c=='/') {return 2;}
	
		return -1;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		Stack<Character>st = new Stack<>();
		ArrayDeque<Character>dq = new ArrayDeque<>();
		
		Queue<Integer>q = new LinkedList<Integer>();
		for(int i=0; i<str.length();i++) {
			
			char c = str.charAt(i);
		
			if(c>='A'&& c<='Z') {
				dq.push(c);
			}
			else {
		
					int cl = findlevel(c); //새로 온애
				//	int tl = findlevel(st.peek()); // 스택에 있던애
					
					if(cl!=-1 ) { 
						
						if(c=='*'||c=='/') {
							while(!st.isEmpty()&&(st.peek()=='*'||st.peek()=='/')) {
								dq.push(st.pop());
							}
						}
						else if(c=='+'||c=='-') {
							while(!st.isEmpty()&&st.peek()!='(') {
								dq.push(st.pop());
							}
						}
						st.add(c);
						
					}
					else {
						if(c=='(')st.add(c);
						else if(c==')') {
							while(!st.isEmpty() && st.peek()!='(')
							{
								dq.push(st.pop());
							}
							st.pop();
						}
				}
			}
			
		}
		
		while(!st.isEmpty()) {
			dq.push(st.pop());
		}
		
		StringBuilder sb = new StringBuilder();
		while(!dq.isEmpty()){
			sb.append(dq.pollLast());
		}
		
		System.out.println(sb);
	}
	
	

}
