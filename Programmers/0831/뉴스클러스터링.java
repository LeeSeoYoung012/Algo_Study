import java.util.*;
class Solution {
    public int solution(String[] lines) {
        int answer = 0;
        
        int start[] = new int[lines.length];
        int end[] = new int[lines.length];
        ArrayList<Integer>arr = new ArrayList<>();
        for(int i=0; i<lines.length; i++){
            String s = lines[i];
            String time = s.substring(11);
            String[] temp = time.split(" ");
            int st = (int)(Integer.parseInt(temp[0].substring(0,2))*60*60*1000+Integer.parseInt(temp[0].substring(3,5))*60*1000+Double.parseDouble(temp[0].substring(6))*1000);
            int diff =(int)(Double.parseDouble(temp[1].substring(0,temp[1].length()-1))*1000);
            start[i]=st-diff+1; // 2016-09-15 03:10:33.020 0.011s 는 33.010 부터 33.020까지 처리된 요청
            arr.add(st-diff+1);
            end[i]=st; //끝나는 시간 
            arr.add(st); // 시작시간과 끝나는시간을 모두 arr에 담아준다.
            
            
         }

        for(int i=0; i<arr.size();i++ ){
              
              int t = arr.get(i);

              int cnt =0;
              for(int j=0; j<start.length; j++){

                  if(t+1000<=end[j]){
                    if((t+1000)>start[j]){ //실질적으로는 t+999까지가 처리시간 따라서 t+1000과 시작시간은 같으면 cnt 포함 안함
                            cnt++;
                    }     
                  }
                  else{
                      if(t<=end[j]){cnt++;} //1초전의 시작시간과 끝나는 시간은 같으면 cnt 포함
                  }
             }
        
            if(answer<cnt){answer =cnt;}
            

        }
        

        
        
        return answer;
    }
}