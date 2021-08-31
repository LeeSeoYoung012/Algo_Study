import java.util.*;

class Solution {
    
    static int answer =0;
    class Node{ //노드정의
        int cnt; //해당 문자가 포함이 된 단어가 몇개인지
        Map<Character,Node>children; //자식 노드가 여러개 일수도 있으므로
        
        public Node(){
            children = new HashMap<>(); 
        }

    }
    
    
    public void check(Node curr, String word){ // 어떤 글자부터 cnt가 1개가 되는지 확인을 한다
       
        char[] letters = word.toCharArray(); // 문자열을 문자로 바꿈
        for(int i=0; i<letters.length; i++){ 
            
            curr = curr.children.get(letters[i]); //children 중 letters[i]에 해당하는 문자의 노드를 가져온다
            answer++; // 지나온 문자 만큼 answer값을 더해준다
            if(curr.cnt==1){break;} //만약 cnt 가 1이라면 더이상 문자를 더 탐색하지 않고 끝낸다
            
        }
        
        
    }
    
    public void insert(Node curr, String word){ //각 문자열의 문자들을 노드로 넣어준다.
       
        
       char[] letters = word.toCharArray();
        for(int i=0; i<letters.length; i++){
            
            char c = letters[i];
            curr = curr.children.computeIfAbsent(c,l -> new Node()); // 자식 노드중에 c에 해당하는 문자의 노드를 가져온다 만약없으면 새로 만들어서 가져온다
            curr.cnt++; //가져온 노드의 cnt 값을 증가시켜준다.
            
        }
    }
    
    
    
    public int solution(String[] words) {

        Node root = new Node();
        for(int i=0; i<words.length;i++){
        
            insert(root, words[i]); //words에 있는 문자들을 모두 insert
            
        }
        
        for(int i=0; i<words.length;i++){
        
            check(root, words[i]); // insert 끝난 트리에서 몇번을 탐색하면 모든 글자가 나올 수 있는지 구한다.
            
        }
      return answer;
    }
}