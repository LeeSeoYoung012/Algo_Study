import java.util.*;
class Solution {
    
    static int FLOOR = 1;
    static int GI = 2;
    static int BO =3;

    static int[][]bmap; // 기둥과 보가 같은 좌표 일때를 고려하여 기둥 배열과 보배열을 따로 만들어준다.
    static int[][]gmap;
    
    static int sz;
    
    public boolean cango(int x, int y){
        return x>=0 && x<=sz && y>=0 && y<=sz; 
    }
    
    
    public boolean checkBo(int x, int y, int[][]bmap, int[][]gmap){
        
        if(!cango(x,y+1)){return false;}
        if(x==0){return false;}
        if(cango(x-1,y) && gmap[x-1][y]==GI){ //왼기둥 확인
            return true;
   
   
         }
        else if(cango(x-1,y+1)&&gmap[x-1][y+1]==GI){//오른기둥 확인
             return true;
   
        }
        else if(cango(x,y-1)&&cango(x,y+1)&&bmap[x][y-1]==BO && bmap[x][y+1]==BO){ //양 쪽이 보
            return true;
       
            
        }
        
        return false;
    }
    
    public boolean checkGd(int x, int y, int[][]bmap, int[][]gmap,boolean mode){
    
        if(!cango(x+1,y)){  return false;}
        
        if(mode ==true && x==0){return true;}
        if(gmap[x][y]==FLOOR || bmap[x][y]==BO){ //바닥 혹은 보
        
            return true;
        }
        else if(cango(x,y-1) && bmap[x][y-1]==BO){ //보의 시작점이 왼쪽
  
            return true;
        }
        else if(cango(x-1,y) && gmap[x-1][y]==GI){ //아래가 기둥일때
       
            return true;
        }
         return false;
    
    }
        

    
    
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer;
        ArrayList<int[]>arr = new ArrayList<>();
        sz=n;
        bmap = new int[n+1][n+1];
        gmap = new int[n+1][n+1];
        for(int i=0; i<=n; i++){
            bmap[0][i]= FLOOR; 
            gmap[0][i]= FLOOR;
        }
        
        for(int i=0; i<build_frame.length; i++){
            
            int[] ip = build_frame[i];
            int y = ip[0];
            int x = ip[1];
            int a = ip[2];
            int b = ip[3];
            
            if(b==1){ //설치
                if(a==1){ //보설치
                    if(checkBo(x,y,bmap,gmap)){
                        bmap[x][y]=BO;
                    }
                }
                else{ //기둥설치
                     if(checkGd(x,y,bmap,gmap,false)){
                         gmap[x][y]=GI;
                     }
                }
          
            } 
            else{ //삭제
                  
                
                if(a==1){//보를 삭제 할때
                    int prev = bmap[x][y];
              
                    if(x==0){ bmap[x][y]=1;} //바닥이라면 1로(사실 보는 바닥에 있을리 없으므로 이부분 삭제해도됨) 
                    else{ bmap[x][y]=0;}  //아니라면 삭제
 
                    
                    for(int p=0; p<=n; p++){
                        boolean tg =false;
                        for(int q=0; q<=n; q++){
                            if(bmap[p][q]==BO){ //보가 있다면
                                if(!checkBo(p,q,bmap,gmap)){ //체크했을때 해당 보가 위치할 수 없으면 
                                     bmap[x][y]=prev; //원래대로 복구
                                    tg=true; //이중 for 문 나가기 위한 toggle 
                                     break; 
                                }
                                
                            }
                            if( gmap[p][q]==GI){ //기둥이 있다면
                                  if(!checkGd(p,q,bmap,gmap,true)){//체크했을때 해당 기둥이 위치할 수 없으면 
                                     bmap[x][y]=prev; //원래대로 복구
                                      tg=true;
                                     break;
                                }
                            }
                        }
                        if(tg)break;
                    }
                }
                else{ //기둥삭제 -> 보와 같이 해준다.
                    int prev = gmap[x][y];
              
                    if(x==0){ gmap[x][y]=1;} //바닥은 1로 
                    else{ gmap[x][y]=0;} 
          
                    
                    for(int p=0; p<=n; p++){
                        boolean tg =false;
                        for(int q=0; q<=n; q++){
                            if(bmap[p][q]==BO){
                                if(!checkBo(p,q,bmap,gmap)){
                                 
                                     gmap[x][y]=prev;
                                    tg=true;
                                     break;
                                }
                                
                            }
                           if( gmap[p][q]==GI){
                                  if(!checkGd(p,q,bmap,gmap,true)){
                                  
                                     gmap[x][y]=prev;
                                      tg=true;
                                     break;
                                }
                            }
                        }
                        if(tg)break;
                    }
                }
                
        
                //     print();
                }
           
            }
            
    
        
        for(int j=0; j<=n; j++){ //문제에서의 xy좌표 기준과 나의 xy 좌표 기준이 반대이기 때문에 반대로 출력을 해야한다
            for(int i=0; i<=n; i++){
                
              
                if(gmap[i][j]==GI){ //기둥이 0 이니까 먼저 넣어줘야함 (작은 수가 앞으로)
                    int[] a = new int[3];
                    a[0]=j;
                    a[1]=i;
                    a[2]=0;
                    arr.add(a);
                }
                
                  if(bmap[i][j]==BO){ //보라면
                    int[] a = new int[3];
                    a[0]=j;
                    a[1]=i;
                    a[2]=1;
                    arr.add(a);
                }
                
            }
        }
        
        answer = new int[arr.size()][3];
        for(int i=0; i<arr.size(); i++){
            answer[i] = arr.get(i);
        }
        
        return answer;
    }
}