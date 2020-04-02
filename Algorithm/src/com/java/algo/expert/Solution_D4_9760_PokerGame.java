package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 9760_PokerGame
 * 메모리 : 19380KB
 * 시간 : 106ms
 * 풀이
 * 1. 노가다..?
 * 2. 조건 챙기는게 어려웠음
 * 3. 동기코드 참조
 *
 */

public class Solution_D4_9760_PokerGame {


	private static int[] suit = new int[4];		// S,D,H,C
	private static int[] rank = new int[13];	// 2,3,4,5,6,7,8,9,T,J,Q,K,A
	static int pair = 0;
	static boolean triple = false;
	static boolean fourCard = false;
	static boolean straight = false;
	static boolean flush = false;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			//초기화
			Arrays.fill(suit, 0);
			Arrays.fill(rank, 0);
			pair = 0;
			triple = false;
			fourCard = false;
			straight = false;
			flush = false;
			
			for(int i=0; i<5; i++) {
				String str = st.nextToken();
				char s = str.charAt(0);
				char r = str.charAt(1);
				
				
				//suit
				switch (s) {
				case 'S': suit[0]++; break;
				case 'D': suit[1]++; break;
				case 'H': suit[2]++; break;
				case 'C': suit[3]++; break;
				}
				
				//rank
				if(Character.isDigit(r)) rank[r-'2']++;
				else {
					switch (r) {
					case 'T': rank[8]++; break;
					case 'J': rank[9]++; break;
					case 'Q': rank[10]++; break;
					case 'K': rank[11]++; break;
					case 'A': rank[12]++; break;
					}
				}
			}
			
			flush = isFlush();	//플러쉬 확인
			pair();				//페이 & 스트레이트 확인
			
			sb.append("#"+test+" ");
			if(flush && straight) sb.append("Straight Flush\n");
			else if(flush) sb.append("Flush\n");
			else if(straight) sb.append("Straight\n");
			else if(fourCard) sb.append("Four of a Kind\n");
			else if(pair == 1 && triple) sb.append("Full House\n");
			else if(triple)sb.append("Three of a kind\n");
			else if(pair == 2) sb.append("Two pair\n");
			else if(pair == 1) sb.append("One pair\n");
			else sb.append("High card\n");
		}
		System.out.println(sb);
	}

	private static void pair() {
		for(int i=0; i<13; i++) {
			if(!straight && i<=rank.length-5 && rank[i] == 1) {
				if(straight(i)) return;
			}
			if(rank[i] == 2)pair++;
			if(rank[i] == 3)triple = true;
			if(rank[i] == 4){fourCard = true; return;}
		}
		
	}

	private static boolean isFlush() {	//모두 동일한 모양인지 확인
		for (int i : suit) {
			if(i == 5)return true;
		}
		return false;
	}
	
	private static boolean straight(int idx) {
		if(idx == 0 && rank[rank.length-1] == 1) {	//2,3,4,5,A
			for(int i=idx; i<idx+4; i++) {
				if(rank[i] != 1) return false;
			}
		}else {
			for(int i=idx; i<idx+5; i++) {
				if(rank[i] != 1) return false;
			}
		}
		return straight = true;
	}

}
