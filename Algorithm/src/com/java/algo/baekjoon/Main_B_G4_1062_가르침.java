package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class Main_B_G4_1062_가르침 {

	private static int N,K;
	private static char[] alpha;
	private static int size;
	private static char[] chars;
	private static List<Character> origin;
	private static List<Character>[] list;
	private static int MAX;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		MAX = 0;
		
		origin = new ArrayList<Character>();
		origin.add('a');
		origin.add('c');
		origin.add('i');
		origin.add('n');
		origin.add('t');
		
		list = new ArrayList[N];
		
		for(int i=0; i<N; i++) {
			list[i] = new ArrayList<Character>();
		}
		
		
		String input;
		char c;
		int len;
		for(int i=0; i<N; i++) {
			input = br.readLine();
			len = input.length();
			for(int j=0; j<len; j++) {
				c = input.charAt(j);
				if(!origin.contains(c)) {
					list[i].add(c);
				}
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			len = list[i].size();
//			for (int j = 0; j < len; j++) {
//				System.out.print(list[i].get(j));
//			}
//			System.out.println();
//		}
		
		//alpha(acint)제외
		alpha = new char[]{'b','d','e','f','g','h','j','k','l','m','o','p','q','r','s','u','v','w','x','y','z'};
		
		size = K-5;
		chars = new char[size];
		//acint와 K-5개수의 알파벳을 포함한 조합string 만들기
		combi(0,0);
		
		System.out.println(MAX);
	}

	private static void combi(int index, int count) {
		
		if(count == size) {
			//System.out.println(Arrays.toString(chars));
			//chars에 있는 문자들 제거
			remove(chars);
			
			return;
		}
		
		for(int i=index; i<alpha.length; i++) {
			chars[count] = alpha[i];
			combi(i+1, count+1);
		}
	}

	private static void remove(char[] chars) {
		int result = 0;
		System.out.println(Arrays.toString(chars));
		List<Character> temp = new ArrayList<Character>();
		List<Character> charList = new ArrayList<Character>();
		
		for(int i=0; i<chars.length; i++) {
			charList.add(chars[i]);
		}
		
//		for(int i=0; i<charList.size(); i++) {
//			System.out.print(charList.get(i)+" ");
//		}
//		System.out.println();
		
		//list돌면서 chars에 있는 문자 지우기
		for (List<Character> l : list) {
			
			for(int i=0; i<l.size(); i++) {
				System.out.print(l.get(i)+" ");
			}
			System.out.println();
			
			for(int i=0; i<l.size(); i++) {
				
				if(charList.contains(l.get(i))) {
					l.remove(l.get(i));
					temp.add(l.get(i));
				}
			}
			if(l.size() == 0) {
				result++;
			}
			
			//list원상복귀
			for (Character c : temp) {
				l.add(c);
			}
			
		}
		
		MAX = Math.max(MAX, result);
	}

}
