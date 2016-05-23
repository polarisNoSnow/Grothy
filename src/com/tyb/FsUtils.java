package com.tyb;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;

public class FsUtils {

	
	// e * d % f = 1 求e对f的乘法逆元
		public static int egcd(int e, int f) {
			int x2 = 0, x3 = f, y2 = 1, y3 = e, q, t2, t3;
			while (true) {
				if (y3 == 0) return 0;
				if (y3 == 1) {
					if (y2 < 0) y2 += f;
					System.out.println(e + " * " + y2 + " % " + f + " = " + e * y2 % f);		
					return y2;
				}
				q = x3 / y3;
				t2 = x2 - q * y2;
				t3 = x3 - q * y3;
				x2 = y2; x3 = y3;
				y2 = t2; y3 = t3;

				System.out.println("(x2, x3):\t" + x2 + "\t" + x3);
				System.out.println("(y2, y3):\t" + y2 + "\t" + y3);
				System.out.println("");
			}
		}

	
	public static BigDecimal getPrime(){
		BigDecimal bigP = new BigDecimal("123456789");
		BigDecimal bigQ = new BigDecimal("123456789");
		
		int p = 123456789;
		int q = 123456789;
		System.out.println(bigP.multiply(bigQ));
		System.out.println(p*q);
		Random random = new Random();
		int x = random.nextInt(100000);
		System.out.println("random"+x);
		System.err.println(bigSqrt(new BigInteger("123456789")));
		return null;
	}
	
	/**
	 * @Des 正整数的开方
	 * @param bigNum
	 * @return 返回正整数部分
	 */
	private static BigInteger bigSqrt(BigInteger bigNum){
		BigInteger sqrtNum = null;
		int l = 1;
		while(bigNum.compareTo(new BigInteger(l*l+"")) >= 0){
			sqrtNum = new BigInteger(l+"");
			l++;
		}
		return sqrtNum;
	}
	/**
	 * @Des 判断是否为素数
	 * @param number
	 * @author tyb
	 * @return
	 */
	public static Boolean isPrime(BigInteger number){
		BigInteger a1 = new BigInteger("2");
		BigInteger a2 = new BigInteger("2");
		try{
			//小余2则不为素数
			if(number.compareTo(a1)==-1){
				return false;
			}
			//等于2
			else if(number.compareTo(a1)==0){
				return true;
			}
			//被2整除
			if((number.remainder(a2)).compareTo(new BigInteger("0")) == 0){
				return false;
			}
			BigInteger sqrtNum = bigSqrt(number);
			for(int i = 3;  sqrtNum.compareTo(new BigInteger(i+"")) >= 0; i+=2){
				System.out.println("-----"+i+"-------------");
				if((number.remainder(new BigInteger(i+""))).compareTo(new BigInteger("0")) == 0) {
					System.out.println("因子"+i);
					return false;
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			System.err.println("FsUtils.isPrime出错");
		}
		return true;
	}
	/**
	 * @Des 大数分解
	 * @param number
	 * @author tyb
	 * @return
	 */
//	public static Boolean largeNum(int number){
//		Boolean flag = false;
//		for(int i = 2; i <= Math.sqrt(number); i++){
//		//for(int i = 2; i <= (number/2)+1; i++){
//			if(number%i==0) {
//				System.out.println(number+"的质因子："+number/i+"*"+i);
//				if(!isPrime(number/i)){
//					 largeNum(number/i); 
//				}if(!isPrime(i)){
//					 largeNum(i); 
//				}
//				flag = true;
//			}
//		}
//		if(!flag){
//			System.out.println(number+"为素数");
//		}
//		return flag;
//	}
//	
	/**
	 * @Des 平方剩余：是一个数学概念。假设p是素数，a是整数。 如果存在一个整数x使得x^2≡a(mod p) 
	 * (即x^2-a可以被p整除)， 那么就称a在p的剩余类中是平方剩余的。p的平方剩余个数为(p-1)/2
	 * @param p p的次方剩余
	 * @param num 多少次方
	 * @author tyb
	 * @return a的值
	 */
	public static int squResidual(int p,int num){
//		if(!isPrime(p)){
//			System.out.println("非素数");
//			return 0;
//		}
		Random random = new Random();
		int x = random.nextInt(1000);
		System.out.println("x="+x);
		int i = 1;
		int xP = (int) Math.pow(x, num);
		if(xP > p){
			while(xP >= i*p){
				i++;
			}
			int res =  xP-(i-1)*p;
			System.out.println(x+"^"+num+"="+(i-1)+"*"+p+"+" + res);
			return res;
		}else{
			System.out.println("整数p太小");
		}
		return 0;
		
	}
//	
//	/**
//	 * /**
//	 * @Des 秦九韶算法,sum=r4*x^4+r3*x^3+r2*x^2+r1*x^1+r0*x^0,
//	 * sum = (((r4*x+r3)x+r2)x+r1)x+r0;
//	 * @author tyb
//	 * @param squ 次方
//	 * @param poly x前面的参数
//	 */
//	public static void qinJiuShao(int squ,List<Integer> poly){
//		int n = 8;  //n的值,x
//		int square = squ;   //次方
//		List<Integer> polynomial = poly;
//		int sum = 0;
//		for (int i = 0; i < square; i++) {
//			System.out.println(sum+"*"+n +"+"+ polynomial.get(i));
//			sum = n*sum + polynomial.get(i);
//			
//		}
//		System.out.println(sum);
//	}
//	
	/**
	 * @Des 欧几里得算法（又称辗转相除法）
	 * @author tyb
	 * @param a 较大的数
	 * @param b 余数
	 * @return a，b最大公约数
	 */
	public static int euclidean(int a,int b){
		int i = 1;
		while(b!=0 ){	
			while(a>=i*b){
				i++; 
			}
			int c = b;
			b = a-(i-1)*b;
			a= c;
			return euclidean(a,b);
		}
		return a;
	}
	
	public static void main(String[] args) {
		
		System.out.println(egcd(550,1769));
		
		//System.out.println(isPrime(808)); //判断是否为素数
		//largeNum(32); //大数分解
		//System.out.println(squResidual(809,2));//平方剩余
		
		//秦九韶算法
//		List<Integer> polynomial = new ArrayList<Integer>();
//		int square = 4;
//		for (int i = 0; i < square; i++) {
//			int ran = (int)(Math.random()*10+1);
//			polynomial.add(ran);
//		}
//
//		qinJiuShao(square,polynomial); 
		
		//欧几里得
		//System.out.println(euclidean(973,301));
	}
}
