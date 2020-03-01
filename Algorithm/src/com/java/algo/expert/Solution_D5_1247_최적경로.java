package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 
 * 1247_최적경로
 * 메모리 : 31236KB
 * 시간 : 1536ms(배열), 2276ms(Arrayslist)
 * 풀이
 * 1. np로 고객순열만들고, 순열 하나씩 완성될때마다 거리비교하기
 * 2. Arraylist가 배열보다 오래걸림
 *
 */

public class Solution_D5_1247_최적경로{
	 
    private static int N;
    private static Client company;
    private static Client home;
    private static int MIN;
    private static int dist;
    private static Client[] clients;
 
    static class Client implements Comparable<Client>{
 
        private int i;
        private int j;
 
        public Client(int i, int j) {
            this.i = i;
            this.j = j;
        }
 
        @Override
        public int compareTo(Client o) {
            if(o.i-this.i == 0) {
                return this.j - o.j;
            }else {
                return this.i - o.i;
            }
            //return 0;
        }
         
    }
     
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
 
        for(int test=1; test<=T; test++) {
            MIN = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
             
            StringTokenizer st = new StringTokenizer(br.readLine());
             
            company = new Client(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            home = new Client(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
             
            clients = new Client[N];
             
            for(int s=0; s<N; s++) {
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                Client client = new Client(i,j);
                clients[s] = client;
            }
             
            Arrays.sort(clients);
             
            do {
                getDistance(clients);
            } while (np(clients));
         
            sb.append("#"+test+" "+MIN+"\n");
        }
         
        System.out.println(sb);
    }
 
    private static void getDistance(Client[] clients) {
        dist = 0;
        dist += Math.abs(clients[0].i-company.i) + Math.abs(clients[0].j-company.j);
         
        for(int i=1; i<clients.length; i++) {
            dist += Math.abs(clients[i].i-clients[i-1].i) + Math.abs(clients[i].j-clients[i-1].j);
        }
         
        dist += Math.abs(clients[N-1].i-home.i) +Math.abs(clients[N-1].j-home.j);
         
        MIN = Math.min(MIN, dist);
        //System.out.println(MIN);
         
    }
 
    private static boolean np(Client[] clients) {
        int N = clients.length;
         
        //1. 교환할 i-1위치 찾기
        int i = N-1;
        while(i>0 && clients[i-1].i >= clients[i].i)--i;
        if(i == 0)return false;
         
         
        //2. 교환할 j위치 찾기
        int j = N-1;
        while(clients[i-1].i >= clients[j].i)--j;
         
        //3. i-1의값과 j위치의 값 교환
        Client temp = clients[i-1];
        clients[i-1] = clients[j];
        clients[j] = temp;
 
        //4. i부터 오름차순으로 정렬
        j = N-1;
        while(i < j) {
            temp = clients[i];
            clients[i] = clients[j];
            clients[j] = temp;
            i++;
            j--;
        }
         
        getDistance(clients);
         
        return true;
    }
     
 
}