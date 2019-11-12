package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class _BOJ_5884_감시카메라 {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine().trim());
		HashMap<Integer, ArrayList<Integer>> hori = new HashMap<>();
		HashMap<Integer, ArrayList<Integer>> verti = new HashMap<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(!hori.containsKey(a))
				hori.put(a, new ArrayList<>());
			hori.get(a).add(b);
			
			if(!verti.containsKey(b))
				verti.put(b, new ArrayList<>());
			verti.get(b).add(a);
		}

		solve(hori, verti, 0);
		
		if(flag)
			System.out.println(1);
		else
			System.out.println(0);
	}
	
	static boolean flag = false;
	
	static void solve(HashMap<Integer, ArrayList<Integer>> hori, HashMap<Integer, ArrayList<Integer>> verti, int cnt) {
		if(cnt == 3) {
			if(hori.isEmpty() && verti.isEmpty())
				flag = true;
			return;
		}
		if(flag)
			return;
		
		int hMaxSize = 0;
		ArrayList<Integer> hIdx = new ArrayList<>();
		HashMap<Integer, ArrayList<Integer>> tHori = new HashMap<>(hori);
		HashMap<Integer, ArrayList<Integer>> tVerti = new HashMap<>(verti);
		ArrayList<Integer> hToDel = null;
		for(Integer key: tHori.keySet()) {
			ArrayList<Integer> h = tHori.get(key);
			if(hMaxSize < h.size()) {
				hMaxSize = h.size();
				hToDel = h;
				hIdx.clear();
				hIdx.add(key);
			}
			else if(hMaxSize == h.size()) {
				hIdx.add(key);
			}
		}
		
		int vMaxSize = 0;
		ArrayList<Integer> vIdx = new ArrayList<>();
		ArrayList<Integer> vToDel = null;
		for(Integer key: tVerti.keySet()) {
			ArrayList<Integer> v = tVerti.get(key);
			if(vMaxSize < v.size()) {
				vMaxSize = v.size();
				vToDel = v;
				vIdx.clear();
				vIdx.add(key);
			}
			else if(hMaxSize == v.size()) {
				vIdx.add(key);
			}
		}
		
		if(vIdx.isEmpty() && hIdx.isEmpty()) {
			if(tHori.isEmpty() && tVerti.isEmpty())
				flag = true;
			
			return;
		}
			
		
		if(vMaxSize < hMaxSize) {
			for(int l: hToDel) {
				tVerti.remove(l);
			}
			tHori.remove(hIdx);
			solve(tHori, tVerti, cnt+1);
		}
		else if(vMaxSize > hMaxSize){
			for(int l: vToDel) {
				tHori.remove(l);
			}
			tVerti.remove(vIdx);
			solve(tHori, tVerti, cnt+1);
		}
		else {
			HashMap<Integer, ArrayList<Integer>> ttHori = new HashMap<>(tHori);
			HashMap<Integer, ArrayList<Integer>> ttVerti = new HashMap<>(tVerti);
			for(int l: hToDel) {
				tVerti.remove(l);
			}
			tHori.remove(hIdx);
			solve(tHori, tVerti, cnt+1);
			
			for(int l: vToDel) {
				ttHori.remove(l);
			}
			ttVerti.remove(vIdx);
			solve(ttHori, ttVerti, cnt+1);
		}
	}
	
	static void delete(HashMap<Integer, ArrayList<Integer>> var, ArrayList<Integer> toDel, )
}
