package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1062_가르침
 * 메모리 : 13488KB
 * 시간 : 114ms
 * 길이 : 1452B
 * 풀이
 * 1. 비트마스킹
 */

public class Main_B_G4_1062_가르침_bitmasking {

	private static int N,K;
	private static int[] words;
	private static int MAX;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		words = new int[N];
		MAX = 0;
		
		//0b:binary  t     n    i     c a 는 이미 배운 문자
		int mask = 0b10000010000100000101;
		
		String input;
		int len, nWord;
		for(int i=0; i<N; i++) {
			input = br.readLine();
			len = input.length();
			nWord = mask;
			for(int j=4; j<len-4; j++) {
				nWord |= (1 << (input.charAt(j)-'a'));
			}
			words[i] = nWord;
		}
		
		go('a',mask,5);	//a부터 출발
		System.out.println(MAX);
	}

	private static void go(int c, int mask, int cnt) {
		if(cnt == K) {
			check(mask);
			return;
		}
		
		if(c > 'z') {	//z넘으면 종료
			return;
		}
		
		//안배울때
		go(c+1, mask, cnt);
		
		
		//배울때
		if((mask & (1 << c - 'a')) == 0) {	//아직 안배운 글자면
			mask |= (1 << c - 'a');
			go(c+1, mask, cnt+1);
		}
	}

	private static void check(int mask) {
		int matched = 0;
		for(int i=0; i<N; i++) {
			if((mask & words[i]) == words[i]) {
				matched++;
			}
		}
		
		MAX = Math.max(MAX, matched);
	}

}
