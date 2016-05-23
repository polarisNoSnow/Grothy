package com.tyb;

import java.math.BigInteger;
import java.util.Random;

public class Test {
	
	public static void main(String[] args) {
		BigInteger test = new BigInteger("15");
		System.out.println(test.toString(2).length());
		BigInteger ran = new BigInteger(test.bitLength()-1,new Random());
		System.err.println(ran);
		
		
		Long lon = Long.valueOf("11010", 2); 
		System.out.println(lon);
		System.out.println(new BigInteger(lon+"").shiftLeft(1).toString(2));
		
		String A = "01234567";
		String B = "89ABCDEF";
		String C = "FEDCBA98";
		String D = "76543210";
		
		System.out.println(Long.parseLong(A+B+C+D, 2));
		String x = "0123456789";
		String a = x.substring(0,2);
		System.out.println(a);	
		Long b_ = Long.parseLong("5ad2d", 16); 
		System.out.println(b_);
		System.out.println((1+5*(6+1))%16);
	}
}
