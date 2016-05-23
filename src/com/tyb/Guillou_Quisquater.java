package com.tyb;

import java.math.BigInteger;
import java.util.Random;

public class Guillou_Quisquater {
	public static void main(String[] args) {
		BigInteger n = null;
		BigInteger p;
		BigInteger q;
		int v;
		BigInteger[] y;
		BigInteger[] x;
		
		//1.���Ʋ����������������������һ����Ҫ�����⣩
		
		//1.1 ����n������������(p,q�Ǳ��ܵĴ�����)
		p = new BigInteger("1234567");
		q = new BigInteger("23156456");
		p = p.nextProbablePrime();//����p�����һ������
		q = q.nextProbablePrime();//����q�����һ������
		System.out.println("1.1 pq��������--���");
		
		//1.2��������
		if(!(BigMathUtils.isPrime(p) && BigMathUtils.isPrime(q))){
			System.err.println("������������");
			System.exit(0);
		}else{
			n = p.multiply(q);
			System.out.println("����p*q=n��"+n);
		}
		System.out.println("1.2��������--���");
		
		//1.3 ����v:gcd(v,(p-1)(p-1))=1
		Random random = new Random();
		v = random.nextInt(10000);
		BigInteger bigV = new BigInteger(v+"");
		while(n.gcd(bigV).compareTo(new BigInteger("1")) != 0){
			v = random.nextInt(10000);
			bigV = new BigInteger(v+"");
		};
		//1.4 x:�û�A������Կ��x����RZn^*;
		
		//1.5 y:�û�A�Ĺ���Կ��y����RZn^*,��x^v *y��1(mod n);
		
		
		//2ǩ�ֵĲ�������
		//���ڴ�ǩ��Ϣm��A�������²��裺
		//2.1���ѡ��һ����k����Zn^*,����T��k^v(mod n)
		
		//2.2�����Ӵ�ֵ��e=H(m,T),��ʹ1<e<v;���򣬷��ز���2.1
		
		//2.3����s��kx^e mod n
		
		//��(e,s)��Ϊ��m��ǩ��
		
		//3ǩ�ֵ���֤����
		//���շ����յ���Ϣm������ǩ��(e,s)�������²�������֤
		//3.1�����T_��s^v *y^e(mod n)
		
		//3.2�����e_ = H(m,T)
		
		//3.3��֤��Ver(y,(e,s),m)=True <=> e_ = e��
	}
}
