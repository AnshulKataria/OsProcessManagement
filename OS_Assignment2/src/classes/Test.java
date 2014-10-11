package classes;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		
		ArrayList a= new ArrayList();
		a.add(10);
		a.add(20);
		a.add(30);
		int c= (Integer)a.remove(1);
		System.out.println(a);
		System.out.println(c);
	}
}
