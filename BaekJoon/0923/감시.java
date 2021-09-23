package Samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 감시 {

	static int dx[]= {-1,0,1,0}; //위 오 아래 왼
	static int dy[]= {0,1,0,-1};
	static int [][]arr;
	static int n,m;
	static int min = Integer.MAX_VALUE;
	static int[][] fourdir = {{1,2,3},{0,1,3},{0,1,2},{0,2,3}};
	
	static class Pair{
		int x,y,num;
		Pair(int x, int y, int num){
			this.x =x;
			this.y = y;
			this.num =num;
		}
	}
	
	public static boolean cango(int x, int y) {
		return x>=0 && x<n && y>=0 && y<m;
	}
	
	public static void print() {
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				
				System.out.print(arr[i][j]+" ");
				
			}
			System.out.println();
		}
		
		System.out.println();
		
	}
	
	public static void checkGamsi(int x, int y , int dir) {
		
		
		int nx = x+dx[dir];
		int ny = y+dy[dir];
		
		while(cango(nx,ny)) {
			
			int num = arr[nx][ny];
			if(num==6 ) { // 벽을 만날때만 통과를 못한다.
				return;
			}
			
			if(num==0) {
				arr[nx][ny]=7;
			}
			nx+=dx[dir];
			ny+=dy[dir]; 
			
		}
		
	}
	


	
	public static void find(ArrayList<Pair>list, int idx) {
		
	//	print();
		if(idx==list.size()) {
			
			int cnt =0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(arr[i][j]==0) {
						cnt++;
						if(cnt>min) {return;}
					}
				}
				
			}
			
			min = Math.min(cnt, min);
			return;
		}
		
	
		
		Pair p = list.get(idx);
		int x = p.x;
		int y = p.y;
		
		int[][] tmp = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				tmp[i][j]=arr[i][j];
			}
		}
		
		if(p.num ==1) {
			
			for(int i=0; i<4; i++) {
				checkGamsi(x,y,i);
				find(list, idx+1);
				for(int s=0; s<n; s++) {
					for(int q=0; q<m; q++) {
						arr[s][q]=tmp[s][q];
					}
				}
			}
			
		}
		else if(p.num==2) {
			
			for(int i=0; i<=1; i++) {
				checkGamsi(x,y,i);
				checkGamsi(x,y,i+2);
				find(list, idx+1);
				for(int s=0; s<n; s++) {
					for(int q=0; q<m; q++) {
						arr[s][q]=tmp[s][q];
					}
				}
				
			}
			
		}
		else if(p.num==3) {
			
			for(int i=0; i<4; i++) {
				
				int di = (i+1)%4; // %3이 아니라 %4
				checkGamsi(x,y,i);
				checkGamsi(x,y,di);
				find(list, idx+1);
				for(int s=0; s<n; s++) {
					for(int q=0; q<m; q++) {
						arr[s][q]=tmp[s][q];
					}
				}
				
			}
			
		}
		else if (p.num==4) {
			
			for(int i=0; i<fourdir.length; i++) {
				
				for(int j=0; j<fourdir[i].length; j++) {
					
					checkGamsi(x,y,fourdir[i][j]);
				}
				
				find(list,idx+1);
				
				for(int s=0; s<n; s++) {
					for(int q=0; q<m; q++) {
						arr[s][q]=tmp[s][q];
					}
				}
				
			}
			
			
		}
		else if(p.num==5) {
			
			for(int i=0; i<4; i++) {
				checkGamsi(x,y,i);
			}
			find(list,idx+1);
			for(int s=0; s<n; s++) {
				for(int q=0; q<m; q++) {
					arr[s][q]=tmp[s][q];
				}
			}
			
		}
		
		
		
		
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		
		ArrayList<Pair>list = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				
				arr[i][j]= Integer.parseInt(st.nextToken());
				if(arr[i][j]>=1 && arr[i][j]<6) {
					list.add(new Pair(i,j,arr[i][j]));
				}
			}
		}
	
		find(list,0);
		System.out.println(min);
		
		
	}
	
	
}
