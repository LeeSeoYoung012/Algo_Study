package BackJoon.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class 이진검색트리_5639 {
	
	static Node root;
	static StringBuilder sb = new StringBuilder();
	static class Node{
		
		int num;
		Node left;
		Node right;
		
		Node(int num){
			this.num = num;
		}
		
	}
	
	
	public static void putNode(Node startNode, Node inputNode) {
		if(startNode.num > inputNode.num) {
			if(startNode.left!=null) {
				putNode(startNode.left, inputNode);
			}
			else {
				startNode.left = inputNode;
			}
		}
		else {
			if(startNode.right != null) {
				putNode(startNode.right, inputNode);
			}
			else {
				startNode.right = inputNode;
			}
		}
	}
	
	public static void postOrder(Node node) {
		
		if(node.left!=null) {postOrder(node.left);}
		if(node.right!=null) {postOrder(node.right);}
		sb.append(node.num+"\n");
		
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Scanner sc = new Scanner(System.in);
		root = new Node( sc.nextInt());

		while(sc.hasNext()) {
			putNode(root, new Node(sc.nextInt()));
		}
		postOrder(root);
		System.out.println(sb);
	}
}
