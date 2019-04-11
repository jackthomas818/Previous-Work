// V00829624 Thomas, Jack

import java.util.*;
class GraphColor{
	static int largestColor;

	public static void main(String []args){

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Node [] vertices = new Node[n];
		for (int i =0;i<n ;i++ ) {
			vertices[i] = new Node(i);
		}
		sc.nextLine();
		for (int i=0;i<n ;i++ ) {
			String[] line = sc.nextLine().split(" ");
			for (int j =0;j<line.length ;j++ ) {
				vertices[i].neighbours.add(vertices[Integer.parseInt(line[j])]);
			}
		}
		/*
		for (int i = 0;i<vertices.length ;i++ ) {
		vertices[i].printNeighbours();
	}*/


	for (int i = 2;i<vertices.length+1 ;i++ ) {
		/*for (int j = 0;j<vertices.length ;j++ ) {
			vertices[j].color = -1;
		}*/
		vertices[0].color = 0;
		vertices[0].neighbours.element().color = 1;
		Color(i,2,vertices);

		//System.out.println(i+"----------------------------");
	}

	/*
	for (int i=0;i<vertices.length ;i++ ) {
		System.out.print(vertices[i].color+" ");
	}
	System.out.println();*/

}
public static void Color(int nColors, int node, Node [] vertices){

	for (int i =0;i<nColors ;i++ ) {
		//System.out.println("node: "+node);
		vertices[node].color = i;
		//System.out.println("i: "+i);
		boolean check = Check(vertices[node]);
		/*
		for (int j=0;j<vertices.length ;j++ ) {
			System.out.print(vertices[j].color+" ");
		}
		System.out.println();*/
		if(check){
			//System.out.println("this");
			if(node < vertices.length-1 ){
				Color(nColors,node+1,vertices);
			}else{
				System.out.println(nColors);
				System.exit(0);
			}


		}

	}
	vertices[node].color = -1;

}

public static boolean Check(Node node){

	ListIterator<Node> iterator = node.neighbours.listIterator();
	while(iterator.hasNext()){
		Node current = iterator.next();
		if(current.color==node.color){
			return false;
		}
	}
	return true;
}

public static void printArray(int [] array){
	for (int i = 0;i<array.length ;i++ ) {
		System.out.print(array[i]+" ");
	}
}
}
class Node{
	int num;
	int color;
	LinkedList<Node> neighbours;

	Node(int nnum){
		num =nnum;
		neighbours = new LinkedList<Node>();
		color = -1;
	}
	void printNeighbours(){
		ListIterator<Node> iterator = neighbours.listIterator();
		while(iterator.hasNext()){
			Node current = iterator.next();
			System.out.print(current.num+" ");
		}
		System.out.println();
	}


}
