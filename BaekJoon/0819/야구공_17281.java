package BaekJoon.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 야구공_17281 {

	
	static int n;
	static boolean[] selected;
	static int[][] playerscore;
	static int [] order;
	static StringTokenizer st;
	static int maxscore =0;

	
	//점수 계산 
	public static int getScore() {
		

		int nextidx =1;
		int totalscore =0;
		for(int t=0; t<n; t++) {
			int[] runners = new int[4];
			int out =0;
			int i=nextidx;
			while(true){
			int type = playerscore[t][order[i]];
				
				if(type==0) {
					out++;
				}
				else if(type ==1) {
					totalscore+=runners[3];
					runners[3]=runners[2];
					runners[2]=runners[1];
					runners[1]=1;
				}
				else if(type ==2) {
					totalscore+= runners[3];
					totalscore+= runners[2];
					runners[3]=runners[1];
					runners[1]=0;
					runners[2]=1;
				}
				else if (type ==3) {
					totalscore+= runners[3];
					totalscore+= runners[2];
					totalscore+= runners[1];
					runners[2]=0;
					runners[1]=0;
					runners[3]=1;
				}
				else if(type==4) {
					totalscore+= runners[3];
					totalscore+= runners[2];
					totalscore+= runners[1];
					runners[2]=0;
					runners[1]=0;
					runners[3]=0;
					totalscore+= 1;
				}
				
				if(out==3) {
					if(i==9) {nextidx=1;}
					else {nextidx =i+1;}
					break;
				}
			
				i++;
				if(i>9) {i=1;}
				
			}
		}
		
		return totalscore;
	}
	
	
	
	//순서를 정하는 것 
	public static void findOrders(int cnt) {

		if(cnt ==10 ) {
			int score = getScore();
			if(score>maxscore) {
				maxscore = score;
			}
			return;
		}
		
		
		for(int i=2; i<=9; i++) {
			
	
			if(selected[i]==false) {
				
				order[cnt]= i;
				selected[i]=true;
			
				if(cnt+1==4) {findOrders(cnt+2);}
				else {
				findOrders(cnt+1);
				}
				selected[i]=false;
				
			
			}
		}
		
		
		
	}
	
	
	

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader( System.in));
		n = Integer.parseInt(br.readLine());

		order = new int[10];
		selected = new boolean[10];
		order[4]=1 ;
		selected[1]=true;
		playerscore = new int[n][10];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=9; j++) {
				playerscore[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		findOrders(1);
		System.out.println(maxscore);
		

	}

}
