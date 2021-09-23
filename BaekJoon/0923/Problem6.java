package Line;

import java.util.*;

public class Problem6 {

	
/*	static String[] records = {"2020-02-02 uid141 pid141", "2020-02-03 uid141 pid32",
			"2020-02-04 uid32 pid32", "2020-02-05 uid32 pid141"
	};
	static int k = 10;
	static String date= "2020-02-05";*/
	
	
	/*static String[] records = {
		"2020-02-02 uid1 pid1",	"2020-02-26 uid1 pid1",
		"2020-02-26 uid2 pid1",	"2020-02-27 uid3 pid2",	
		"2020-02-28 uid4 pid2",	"2020-02-29 uid3 pid3",	
		"2020-03-01 uid4 pid3",	"2020-03-03 uid1 pid1",	
		"2020-03-04 uid2 pid1",	"2020-03-05 uid3 pid2",	
		"2020-03-05 uid3 pid3",	"2020-03-05 uid3 pid3",	
		"2020-03-06 uid1 pid4"
	};
	static int k = 10;
	static String date= "2020-03-05";*/
	
	static String[] records = {"2020-01-01 uid1000 pid5000"};
	static int k =10;
	static String date= "2020-01-11";
	
	static class Product implements Comparable<Product>{
		
		int pid;
		int firstbuycnt;
		int totalcnt;
		int rebuy;
		int rebuyperc;
	
		HashMap<String,Integer>hm;
		
		Product(int pid){
			
			this.pid= pid;
			
			hm = new HashMap<>();
		}

		@Override
		public int compareTo(Product o) {
			
			if(o.rebuyperc == this.rebuyperc) {
				
				if(this.totalcnt == o.totalcnt) {
					
					return this.pid-o.pid;
					
				}
				
				return o.totalcnt - this.totalcnt;
			}
			
			return o.rebuyperc-this.rebuyperc;
		}
		
		
	}
	
	
	public static int changedate(String date) {
		String[] infos = date.split("-");
		return Integer.parseInt(infos[1])*30 + Integer.parseInt(infos[2]);
	}
	
	
	public static void main(String[] args) {
		
	
		int edate = changedate(date);
		int sdate = edate-k+1;
		HashMap<Integer,Product>phm = new HashMap<>();
		
		for(int i=0; i<records.length; i++) {
			
			
			String[] s = records[i].split(" ");
			int idate = changedate(s[0]);
			
			if(idate>=sdate && idate<=edate) {
				
				int pid = Integer.parseInt(s[2].substring(3));
				String uid = s[1];
				if(!phm.containsKey(pid)) { //제품 구매 이루어진 경우
					phm.put(pid,new Product(pid));
				}
					
					Product p = phm.get(pid);
					if(p.hm.containsKey(uid)) {
						
						if(p.hm.get(uid)==1) {
							p.rebuy++;
						}
						p.hm.put(uid, p.hm.get(uid)+1);
						p.totalcnt++;
						
					}
					else {
						
						p.hm.put(uid, 1);
						p.firstbuycnt++;
						p.totalcnt++;
						
					}
					
				}
				
				
			}
		
		if(phm.size()==0) {
			System.out.println("no result");
			return;
		}
		
		ArrayList<Product>arr = new ArrayList<>();
		Set<Integer>key = phm.keySet();
		
		for(Iterator it = key.iterator();it.hasNext();) {
			
			Integer keyvalue = (Integer)it.next();
			Product p = phm.get(keyvalue);
			p.rebuyperc = (p.rebuy/p.firstbuycnt)*100;
			arr.add(p);
		}
		
		
		
		Collections.sort(arr);
		
		String[]result = new String[arr.size()];
		
		for(int i=0; i<arr.size(); i++) {
			
			
			result[i] = "pid"+Integer.toString(arr.get(i).pid);
			
		}
		
		for(int i=0; i<result.length; i++) {
			System.out.println(result[i] );
		}
		}
		
		
		
		
		
		
	}
	
	
	

