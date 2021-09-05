import java.util.*;

class Solution {
    
    int[] dx = {-1,1,0,0};
    int[] dy ={0,0,-1,1};
    int[][] dlist = new int[5][5];
    

    
    class Pair{
        int x,y;
        Pair(){
            
        }
        Pair(int x, int y){
            this.x =x;
            this.y = y;
        }
    }
    
    public boolean cango(int x , int y){
        return x>=0 && x<4 && y>=0 &&y<4;
        
    }
    
    public int bfs(Pair node, int [][]board, boolean mode, Pair dest , ArrayList<Pair>minlist){ // 최소거리에 있는 다른 카드 or 짝을 찾는 bfs
        
        Queue<Pair>q = new LinkedList<>();
        q.add(new Pair(node.x, node.y));
       
        boolean[][]check = new boolean[4][4];
        int snum = board[node.x][node.y];
        check[node.x][node.y]=true;
        
        int click =1;
        
        
        while(!q.isEmpty()){
            
            int sz = q.size();
        
            boolean toggle =false;
            for(int t=0; t<sz; t++){
            
            Pair p = q.poll();
            int x = p.x;
            int y = p.y;
            int num = board[x][y];

            
            if(mode){
            if(click!=1 && snum==num){ //짝을 고를때 
                dest.x =x;
                dest.y =y;
                toggle =true;
                break;
            }
            }
            else{ // 다음 카드를 고를 때 
                if(click!=1 && num!=0 && snum!=num){ //click 횟수 여러번, 시작지점과 다른 카드 일때
                    minlist.add(new Pair(x,y));
                    toggle =true; // 최소 클릭횟수 찾음
                    
                }
                else if(click==1 && num!=0){ //click 횟수 0번(현재 자리 그대로) 카드가 있을때
                    minlist.add(new Pair(x,y));
                    toggle = true; // 최소 클릭횟수 찾음
                    break;
                }
                
            }
                
            
            boolean tg = false;
            for(int i=0; i<4; i++){ // 상하좌우 탐색
                
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(cango(nx,ny)&&!check[nx][ny]){
                   
                    q.add(new Pair(nx,ny));
                    check[nx][ny]=true; //방문했던 부분 체크
                }
             }
              
            for(int i=0; i<4; i++){ // ctrl + 상하좌우
                
                int nnx = x+dx[i];
                int nny = y+dy[i];
                
                boolean tg2 = false;
                while(cango(nnx,nny)){
                    
                    if(board[nnx][nny]!=0){
                        tg2=true;
                        break;
                    }
                    nnx+=dx[i];
                    nny+=dy[i];
                       
                }
                
                if(!tg2){nnx-=dx[i]; nny-=dy[i];}
                if(!check[nnx][nny]){ 
                    
                    q.add(new Pair(nnx,nny));
                    check[nnx][nny]=true; //방문했던 부분 체크
                }
                
                
            }
                
           
                
            }
            if(toggle==true){break;}
                 click++;
        }
        
        if(mode){
        return click+1;//짝을 찾는 경우는 두개의 카드를 클릭하므로 1을 더해줌 (click 변수 시작이 1이라서 이미 하나는 더해준 상태)
        }
        return click-1;//다음 카드를 찾는 경우는 카드 클릭을 하지 않으므로 1을 뺌 (click 변수 시작이 1이라서 하나는 빼줘야함)
    }
    
    boolean isClean(int[][] board){ // 카드들이 완전히 없어졌는지 확인 하는 함수
        
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board.length; j++){
                if(board[i][j]!=0){
                    return false;
                }
            }
        }
        return true;
    }
   
    int minanswer = Integer.MAX_VALUE; //최솟값 변수
  
    public void getAnswer(int answer, int[][]board, int x, int y){ //DFS
     
            if(isClean(board)){ //모두 짝을 찾고 카드가 없을 경우
                minanswer = Math.min(minanswer,answer); //최소값 결정
                return;
            }
          
            ArrayList<Pair>list=new ArrayList<>(); //최소거리에 있는 다음카드를 담을 리스트
            int othercard=bfs(new Pair(x,y),board,false,new Pair(-1,-1),list); //다른 카드 찾기

        
            for(int i=0; i<list.size(); i++){ // 최소거리에 있는 다음 카드를 담은 리스트 순회
              
            Pair dest = new Pair(-1,-1);
            int jjak=bfs(new Pair(list.get(i).x,list.get(i).y),board,true,dest,new ArrayList<>()); //짝찾기
            int num=board[dest.x][dest.y];
          
            board[list.get(i).x][list.get(i).y]=0; board[dest.x][dest.y]=0;  // 짝찾은 거는 0으로 board setting
            getAnswer(answer+jjak+othercard,board,dest.x,dest.y); //깊이 탐색
            board[list.get(i).x][list.get(i).y]=num; board[dest.x][dest.y]=num;  // 탐색 후에 원래 대로 board setting
              
            }
            
            
    }
        
        
    
    
    
    public int solution(int[][] board, int r, int c) {
        
        int answer = 0;
        int id =1;
       
        int x =r;
        int y =c;
        
        getAnswer(0,board,r,c);
        answer = minanswer;

        
        return answer;
    }
}