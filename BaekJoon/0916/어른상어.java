package Samsung;

import java.io.*;
import java.util.*;
public class 어른상어 {

	
	
	static int n,m,k;//m상어개수, 냄새k번이동후 사라짐
	
	static Info[][]arr;
	static int[][][]wayorder;
	
	static int[]dx = {0,-1,1,0,0};
	static int[]dy = {0,0,0,-1,1};

	
	static class Pos {
		
		int x,y;
		
		Pos(int x, int y){
			
			this.x = x;
			this.y = y;
		
			
		}
		
	}
	
	static class Info{
		
		int num;
		int kcnt;
		
		Info(int num, int kcnt){
			this.num = num;
			this.kcnt = kcnt;
		}
		
		
	}
	
	public static void print() { //arr 덤프용
		
		
		for(int i=0; i<arr.length; i++) {
			
			for(int j=0; j<arr[i].length; j++) {
				
				if(arr[i][j]!=null) {
				System.out.print("("+arr[i][j].num+" "+arr[i][j].kcnt+")");
				}
				else {
					System.out.print("("+0+" "+0+")");
				}
			}
			System.out.println();
			
		}
		System.out.println();
		
		
	}
	
	public static boolean cango(int x, int y) {
		
		return x>=0 && x<n && y>=0 && y<n;
		
	}
	
	public static boolean checkAll(Pos[] sharkpos) { //상어1을 제외한 나머지 상어들이 모두 없는지 확인
		 
		for(int i=2; i<sharkpos.length; i++) { //상어 위치 배열이 1빼고 모두 null인지를 통해 확인을 한다.
			if(sharkpos[i]!=null) {
				return false;
			}
		}
		return true;
		
	}
	
	public static void move(Pos[] sharkpos) {
		
		
		for(int i=0; i<arr.length; i++) { //나머지냄새나는곳의 냄새(kcnt)를 1줄여준다
			for(int j=0; j<arr[i].length; j++) {
				
				if(arr[i][j]!=null) { //만약 냄새나는 곳일 경우
					arr[i][j].kcnt-=1;
					if(arr[i][j].kcnt==0) {arr[i][j]=null;}
				}
				
			}
			
		}
		
		
		for(int i=1; i<=m; i++) {
			
			Pos p = sharkpos[i];
			
			if(p!=null) { //i번째상어가 아직 존재하는 경우
			Info f = arr[p.x][p.y]; 
			if(f!=null && f.num!=i) { //이미 배치하고자 하는 자리에 다른 상어가 있다면
				sharkpos[i]=null; //i 번째 상어를 퇴출 ( 작은 번호가 차지해야하기 떄문)
				continue; 
			}
			
			arr[p.x][p.y]= new Info(i,k); //자리에 아무도 없으면 배치해줌
			}
		}
		
	}
	
	
	public static void simulate(int[] sharkway , Pos[] sharkpos ) {
		
		
		/*어려웠던 부분2: 상어의 다음 방향과 위치를 찾는것*/
		for(int i=1; i<=m; i++) {
			
			if(sharkpos[i]!=null) {
			
			Pos p = sharkpos[i];
			
			int x = p.x;
			int y = p.y;
			
			int dir = sharkway[i];
			int ndir =dir;
			int nx =x;
			int ny =y;
			
			boolean tg = false;
			for(int d=1; d<=4; d++) { 
				
				int ddir = wayorder[i][dir][d];
				nx=x+dx[ddir];
				ny=y+dy[ddir];
				if(cango(nx,ny)&&arr[nx][ny]==null) { //4방향 중 null 인 것(냄새가 없는곳)을 찾는다
					ndir =ddir;
					tg = true;
					break;
				}
				
			}
			
			if(tg==false) { //냄새가 없는 곳을 찾지 못했을때
				
			
				for(int d=1; d<=4; d++) {
					
					int ddir = wayorder[i][dir][d];
					nx=x+dx[ddir];
					ny=y+dy[ddir];
					if(cango(nx,ny)&&arr[nx][ny].num==i) { //4방향 중 현재와 냄새가 같은 곳 을 찾는다.
						ndir =ddir;
						tg = true;
						break;
					}
					
				}
			}
			
			if(tg==false)continue; //냄새가 없는 곳 , 냄새가 같은 곳 둘다 못찾았을때 
			
			sharkway[i]=ndir;
			sharkpos[i] = new Pos(nx,ny);
			
		
			}
		}
		
		
		
		
		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		
	
		
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		//어려웠던부분3 : 자료구조를 어떻게 해야할지
		arr = new Info[n][n]; 
		
		int[] sharkway = new int[m+1]; //상어의 방향 저장
		Pos[] sharkpos = new Pos[m+1]; //상어의 위치 저장
		
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				
				int nn = Integer.parseInt(st.nextToken()); 
				if(nn!=0) { //만약 0이아닌 숫자가 있다면 Info저장을 해준다. 
					arr[i][j] = new Info(nn,k); 
					sharkpos[arr[i][j].num]= new Pos(i,j); 
				}
			}
		}
	
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=m; i++) {
			
			sharkway[i] = Integer.parseInt(st.nextToken());
			
		}
		
		
		/*어려웠던 부분1: 방향순서 처리하는 것*/
		wayorder = new int[m+1][5][5];
		
		for(int i=1; i<=m; i++) {
			
		
			for(int j=1; j<=4; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=1; k<=4; k++) {	
				wayorder[i][j][k] =Integer.parseInt(st.nextToken());
				//i번째 상어가 현재 방향 j일때의 방향 순서 4가지(k)
				}
			}
		}
		
		
		int time =0;
		while(!checkAll(sharkpos)) {
			if(time>=1000) {time=-1; break;} //시간이 1000인데도 checkAll이 false라는 건 시간이 1000을 넘어가서도 상어가 남아있다는것

			simulate(sharkway,sharkpos);
			move(sharkpos);
			time ++;
			
		}
		System.out.println(time);
		
		
	}
	
	
	
	
}
