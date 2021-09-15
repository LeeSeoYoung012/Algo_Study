package Samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 뱀 {

	
	static int n,k;
	static int[][] arr;
	static StringTokenizer st;
	static HashMap<Integer,Character>hm= new HashMap<>();
	static int[]dx = {1,0,-1,0};
	static int[]dy = {0,-1,0,1}; //아래 왼 위 오
	
	
	static class Pair{
		int t;
		char dir;
		
		Pair(int t, char dir){
		this.t =t;
		this.dir = dir;
		}
	}
	
	static class Pos{
		int x;
		int y;
		
		Pos(int x, int y){
			this.x =x;
			this.y = y;
		}
	}
	
	public static boolean cango(int x, int y) {
		
		return x>=0 && x<n && y>=0 && y<n;
	}
	
	public static void print() { //arr dump 코드
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				
				System.out.print(arr[i][j]+" ");
				
				
			}
			System.out.println();
		}
	}
	
	public static int simulate() {
		
		 
		
		LinkedList<Pos>snake = new LinkedList<>(); //링크드 리스트를 이용해서 뱀 몸의 위치저장
		snake.add(new Pos(0,0));
		arr[0][0]=2; //뱀은 2로 표시
		int dir =3; //오른쪽 방향
		int time =0;
		
		while(true) {
			
		
			
			time++; //시간 카운트
			Pos spos = snake.get(0);
			int nx = spos.x+dx[dir];
			int ny = spos.y+dy[dir];
			
			if(cango(nx,ny)) { //벽이 아니라 갈 수 있을때
				
				if(arr[nx][ny]==1) { //만약 사과가 있을떄
					
					snake.addFirst(new Pos(nx,ny)); //앞에만 늘려준다
					arr[nx][ny]=2; //늘려준 부분 2로저장
				}
				else if(arr[nx][ny]==0) {  // 사과가 없을때
					
					snake.addFirst(new Pos(nx,ny)); //앞에만 늘려준다
					arr[nx][ny]=2; //늘려준 부분 2로저장
					
					Pos epos = snake.get(snake.size()-1); //맨 뒤의 좌표 가져오기
					arr[epos.x][epos.y]=0; //맨 뒤에 부분 0으로 해준다
					snake.removeLast(); //맨뒤에 부분 없애주기
				}
				else if(arr[nx][ny]==2) { // 뱀이 자기자신을 만나면 멈춘다
					break;
				}
				
			}
			else { //벽일때 멈춘다
				
				break;
				
			}
			
			
			if(hm.containsKey(time)) {
				
				char c = hm.get(time);
				if(c=='L') {
					dir = (dir+3)%4; //오른쪽90도 회전하는 모듈러 연산
				}
				else if(c=='D') {
					
					dir=(dir+1)%4; //왼쪽 90도 회전하는 모듈러 연산
					
				}
				
				
			}
			
			
			
		}
		
		return time;
		
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		k =Integer.parseInt(br.readLine());
		
		arr = new int[n][n];
		
		for(int i=0; i<k; i++) { //사과 배치하기
			
			st =new StringTokenizer(br.readLine());
			arr[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1]=1;
		}
		
		int l = Integer.parseInt(br.readLine()); 
		
		int sx = 0;
		int sy =0;
		
	
		
		for(int i=0; i<l; i++) {
			st =new StringTokenizer(br.readLine());
			hm.put(Integer.parseInt(st.nextToken()),st.nextToken().charAt(0));
		}
		
		System.out.println(simulate());
		
		
	}
	
	
}
