import java.util.*;
class Solution {
    public int[] solution(String msg) {
        
        HashMap<String,Integer>hm = new HashMap<>();
        ArrayList<Integer>arr = new ArrayList<>();
        char c = 'A';
     
        for(int i=1; i<=26; i++){
            String s= Character.toString(c);
            hm.put(s,i);
            c++;
        }
        
        if(msg.length()==1){ //만약에 한글자라면
            int[] answer = new int[1];
            if(hm.containsKey(Character.toString(msg.charAt(0)))){  //그 한글자의 번호를 arr에 넣어준다, 이때 Character->String으로 바꾸는거 중요 
            answer[0] = hm.get(Character.toString(msg.charAt(0)));  //Character->String으로 바꾸는거 중요 
            }
            return answer;
        }
        
        int num =27;
       
        String s ="";
        char nc = msg.charAt(0);
        s+=nc;
        
        
        for(int i=1; i<msg.length(); i++){

          
            while(i<msg.length()){
                
                nc=msg.charAt(i);
                s+=nc;
                if(!hm.containsKey(s)){break;}
                i++;
            }

            if(i==(msg.length())){ //i가 끝부분넘을때 처리하기(hm에 없어서 break로 나온 것인지, 아니면 length()크기만큼 i가되서 while문을 나왔는지 알수 없기 때문에 처리해줘야함)
                if(hm.containsKey(s)){ //이미 hashmap에 있는 문자열이라면
                    arr.add(hm.get(s.substring(0))); //나머지를 모두 arr에 넣어준다
                }
                else{ //hashmap에 있지 않다면 마지막 글자를 제외한 나머지를 모두 arr에 넣어준다
                    arr.add(hm.get(s.substring(0,s.length()-1)));
                     hm.put(s,num);
                     num++;
                }
            }
            else{arr.add(hm.get(s.substring(0,s.length()-1))); hm.put(s,num); //i가 끝부분 넘은게아니라면 s는 무조건 hashmap에 없으니까 넣어준다.
                     num++;}
            s="";
            i--;
            
        }
      
        int[] answer = arr.stream().mapToInt(i->i).toArray(); //ArrayList -> 배열로 고치기 

        
        return answer;
    }
}