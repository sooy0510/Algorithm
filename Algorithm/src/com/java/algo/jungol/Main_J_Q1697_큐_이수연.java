package com.java.algo.jungol;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_J_Q1697_큐_이수연 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = sc.nextInt();
		String info;
		Queue<Integer> queue = new LinkedList<Integer>();
		int input = 0;
		int output = 0;
		
		for(int i=0; i<n; i++) {
			info= sc.next();
			switch (info) {
			case "i":
				input = sc.nextInt();
				queue.add(input);
				break;
				
			case "o":
				if(queue.peek() == null) {
					bw.write("empty\n");
				}else {
					output = queue.poll();
					bw.write(output+"\n");
				}
				break;

			default:
				bw.write(queue.size()+"\n");
				break;
			}
		}
		bw.flush();
		bw.close();
		sc.close();
	}

}
