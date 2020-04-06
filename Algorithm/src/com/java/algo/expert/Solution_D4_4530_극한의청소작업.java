package com.java.algo.expert;

import java.util.Scanner;

//1의 자릿수, 10의 자릿수.....등 전체 자릿수의 표현에서 4제외
//제시된 층수 표현을 9진수로 이해
//답안 계산은 10진수로
//123 9진수 == 10진수 56789 9진수 --> 10진수 전환

public class Solution_D4_4530_극한의청소작업 {
	public static void main(String[] args) {
     long[] four = new long[13];	// 9진수에 각 자릿수에 대응하는 10진수, 754(9) -> 3* four[
     four[1]=1;
     for (int i = 2; i < four.length; i++) {
         four[i] = 9*four[i-1] + (long) Math.pow(10, i-1);
     }
     
     Scanner sc = new Scanner(System.in);

     int T=sc.nextInt();
     for (int i = 1; i <=T; i++) {
         boolean check=false;
         long a1 = sc.nextLong();
         long b1 = sc.nextLong();
         
         // 두 수의 부호가 같은 지 확인 
         if((a1 > 0 && b1 > 0) || ( a1 < 0 && b1 < 0)) { // 모두 양수 또는 모두 음수 : check = false;
             check=false;
         }else {
             check=true;
         }
         
         String a = "" + Math.abs(a1);
         String b = "" + Math.abs(b1);
          
         String ss = b;
         long sum1 = 0;
         
         // 각 자릿수별로 9진수
         for (int j = 0; j < ss.length(); j++) {
             int s = Integer.parseInt(ss.substring(j, j+1));
             int index = ss.length()-j-1;
             if(s>4) {
             	// 4 가 빠진 숫자이므로 -1 이 원래 숫자
                 sum1 = sum1 + (s-1)*four[index] + (long)Math.pow(10, index);
             }else {
                 sum1 = sum1 + s*four[index];
             }
         }
         
         ss = a;
         long sum2=0;
         for (int j = 0; j < ss.length(); j++) {
             int s=Integer.parseInt(ss.substring(j, j+1));
             int index=ss.length()-j-1;
             if(s>4) {
                 sum2 = sum2 + (s-1)*four[index] + (long)Math.pow(10, index); // 하나 작은 수로 계산 후 X 10진수로 자릿수 계산
             }else {
                 sum2 = sum2 + s*four[index]; // 자릿수 그대로 9진수 계산만 
             }
         }
         
         // 두 수의 부호가 다르다. 0 이 없으므로 -1
         if(check) {
         	System.out.println("#"+i+" "+(Long.parseLong(b)+Long.parseLong(a)-(sum1+sum2)-1));
         }else {
             System.out.println("#"+i+" "+(Math.abs((Long.parseLong(b)-sum1)-(Long.parseLong(a)-sum2))));  
         }
     }
 }
}

