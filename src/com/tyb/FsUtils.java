package com.tyb;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;

public class FsUtils {

	
	// e * d % f = 1 ��e��f�ĳ˷���Ԫ
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
	 * @Des �������Ŀ���
	 * @param bigNum
	 * @return ��������������
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
	 * @Des �ж��Ƿ�Ϊ����
	 * @param number
	 * @author tyb
	 * @return
	 */
	public static Boolean isPrime(BigInteger number){
		BigInteger a1 = new BigInteger("2");
		BigInteger a2 = new BigInteger("2");
		try{
			//С��2��Ϊ����
			if(number.compareTo(a1)==-1){
				return false;
			}
			//����2
			else if(number.compareTo(a1)==0){
				return true;
			}
			//��2����
			if((number.remainder(a2)).compareTo(new BigInteger("0")) == 0){
				return false;
			}
			BigInteger sqrtNum = bigSqrt(number);
			for(int i = 3;  sqrtNum.compareTo(new BigInteger(i+"")) >= 0; i+=2){
				System.out.println("-----"+i+"-------------");
				if((number.remainder(new BigInteger(i+""))).compareTo(new BigInteger("0")) == 0) {
					System.out.println("����"+i);
					return false;
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			System.err.println("FsUtils.isPrime����");
		}
		return true;
	}
	/**
	 * @Des �����ֽ�
	 * @param number
	 * @author tyb
	 * @return
	 */
//	public static Boolean largeNum(int number){
//		Boolean flag = false;
//		for(int i = 2; i <= Math.sqrt(number); i++){
//		//for(int i = 2; i <= (number/2)+1; i++){
//			if(number%i==0) {
//				System.out.println(number+"�������ӣ�"+number/i+"*"+i);
//				if(!isPrime(number/i)){
//					 largeNum(number/i); 
//				}if(!isPrime(i)){
//					 largeNum(i); 
//				}
//				flag = true;
//			}
//		}
//		if(!flag){
//			System.out.println(number+"Ϊ����");
//		}
//		return flag;
//	}
//	
	/**
	 * @Des ƽ��ʣ�ࣺ��һ����ѧ�������p��������a�������� �������һ������xʹ��x^2��a(mod p) 
	 * (��x^2-a���Ա�p����)�� ��ô�ͳ�a��p��ʣ��������ƽ��ʣ��ġ�p��ƽ��ʣ�����Ϊ(p-1)/2
	 * @param p p�Ĵη�ʣ��
	 * @param num ���ٴη�
	 * @author tyb
	 * @return a��ֵ
	 */
	public static int squResidual(int p,int num){
//		if(!isPrime(p)){
//			System.out.println("������");
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
			System.out.println("����p̫С");
		}
		return 0;
		
	}
//	
//	/**
//	 * /**
//	 * @Des �ؾ����㷨,sum=r4*x^4+r3*x^3+r2*x^2+r1*x^1+r0*x^0,
//	 * sum = (((r4*x+r3)x+r2)x+r1)x+r0;
//	 * @author tyb
//	 * @param squ �η�
//	 * @param poly xǰ��Ĳ���
//	 */
//	public static void qinJiuShao(int squ,List<Integer> poly){
//		int n = 8;  //n��ֵ,x
//		int square = squ;   //�η�
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
	 * @Des ŷ������㷨���ֳ�շת�������
	 * @author tyb
	 * @param a �ϴ����
	 * @param b ����
	 * @return a��b���Լ��
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
		
		//System.out.println(isPrime(808)); //�ж��Ƿ�Ϊ����
		//largeNum(32); //�����ֽ�
		//System.out.println(squResidual(809,2));//ƽ��ʣ��
		
		//�ؾ����㷨
//		List<Integer> polynomial = new ArrayList<Integer>();
//		int square = 4;
//		for (int i = 0; i < square; i++) {
//			int ran = (int)(Math.random()*10+1);
//			polynomial.add(ran);
//		}
//
//		qinJiuShao(square,polynomial); 
		
		//ŷ�����
		//System.out.println(euclidean(973,301));
	}
}
