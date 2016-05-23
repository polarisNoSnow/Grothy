package com.tyb;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Fiat_Shamir {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BigInteger n = null;
		BigInteger p;
		BigInteger q;
		int k;
		BigInteger[] y;
		BigInteger[] x;
		
		//1.���Ʋ����������������������һ����Ҫ�����⣩
		
		//1.1 ����n������������(p,q�Ǳ��ܵĴ�����)
		p = new BigInteger("12345");
		p = BigMathUtils.getRandom(p);
		q = new BigInteger("12345");
		q = BigMathUtils.getRandom(q);
		p = p.nextProbablePrime();//����p�����һ������
		q = q.nextProbablePrime();//����q�����һ������
		System.out.println("1.1 pq��������--���");
		
		//1.2��������
		if(!(BigMathUtils.isPrime(p) && BigMathUtils.isPrime(q))){
			System.err.println("������������");
			System.exit(0);
		}else{
			n = p.multiply(q);
			System.err.println("p:"+p+",  q:"+q);
			System.err.println("����p*q=n��"+n);
		}
		System.out.println("1.2��������--���");
		
		//1.3 ����k(�̶���������)
		k = 9;
		
		//1.4 ����y1,y2....yk:�û�A�Ĺ�����Կ�����κ�i(1<=i<=k),yi����ģn��ƽ��ʣ��
		y = new BigInteger[k];
		for (int i = 0; i < k; i++) { 
			y[i] = BigMathUtils.squResidual(n);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.err.println("y"+i+"="+y[i]);
		}
		
		//1.5����x1,x2...xk:�û�A��������Կ�����κ�i(1<=i<=k),xi��sqrt(yi����Ԫ)(mod n)
		x = new BigInteger[k];
		//yi����Ԫ
		for (int i = 0; i < k; i++) {
			//TODO ��ԪΪ������ȡ����ֵ�����������nΪ ͬ��
			x[i] = BigMathUtils.bigSqrt(BigMathUtils.euclidean(n,y[i]).abs()).add(n);
			System.out.println("x"+i+"="+x[i]);
		}
		System.out.println("1.���Ʋ���--���");
		
		
		//2ǩ���Ĳ�������
		//2.1���ѡȡһ��������t
		Random rand = new Random();
		int t = rand.nextInt(10);
		
		//2.2���ѡȡt������1��n֮�����r1,r2...ri,�����κ�j(1<=j<=t),����Rj��rj2(mod n)
		BigInteger[] r = new BigInteger[t];
		BigInteger[] R = r;
		
		for (int i = 0; i < t; i++) {
			//TODO ���ѡȡ1-n֮��
			r[i] = n;//?????? 
		}
		
		//��ȡR(1-j��ֵ)
		for (int i = 0; i < t; i++) {
			//TODO ѯ���Ƿ���ȷ
			R[i] = r[i].add(n); //r[i]��R[i]ͬ��
		}
		
		//2.3�����Ӵ�ֵH(m,R1,R2,..Rt),������ȡ��H(m,R1,R2,..Rt)��
		//ǰkt������ֵb11,..b1t,b21,...b2t,....,bk1,...bkt��
		String m = "Hello world";
		String hash_res = BigMathUtils.hash(m,R);
		String[][] b = new String[k][t];
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < t; j++) {
				b[i][j] = hash_res.charAt(i*t+j) + "";
			}
		}
		
		//2.4���κ�j(1<=j<=t),����sj��ri (xi^bij)+(1...k) mod n
		//((b11,....b1t,b21,....,b2t,bk1,...,bkt),(s1.....st))��Ϊ��m������ǩ��
		BigInteger[] s = new BigInteger[t];
		for (int i = 0; i < t; i++) {
			BigInteger sum = new BigInteger("0");
			for (int j = 0; j < k; j++) {
				 sum = sum.add(x[j].pow(Integer.valueOf(b[j][i])));
			}
			s[i] = r[i].multiply(sum).add(n);
		}
		
		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("b", b);
		datas.put("s", s);
		datas.put("m", m);
		
		datas.put("n", n);
		datas.put("y", y);
		
		verification(datas);

	}
	
	/**
	 * @Des ��֤ǩ��
	 * @param datas
	 */
	private static void verification(Map<String, Object> datas) {
		// TODO Auto-generated method stub
		String[][] b = (String[][]) datas.get("b");
		BigInteger[] s = (BigInteger[]) datas.get("s");
		String m = (String) datas.get("m");
		BigInteger[] R_ = new BigInteger[s.length];
		BigInteger n = (BigInteger) datas.get("n");
		BigInteger[] y = (BigInteger[]) datas.get("y");
		//3ǩ�ֵ���֤����
		//���շ��յ���Ϣm��ǩ��((b11,....b1t,b21,....,b2t,bk1,...,bkt),(s1.....st))�������²�������֤
		//3.1���κ�j(1<=j<=t),����R'j��Sj^2 * (yi^bij)+(1...k) mod n
		for (int i = 0; i < s.length; i++) {
			BigInteger sum = new BigInteger("0");
			for (int j = 0; j < b.length; j++) {
				 sum = sum.add(y[j].pow(Integer.valueOf(b[j][i])));
			}
			R_[i] = s[i].pow(2).multiply(sum).add(n);
		}		
				
		//3.2 ����H(m,R'1,R'2,....R't)
		String hashRes = BigMathUtils.hash(m,R_);
		//3.3��֤(b11,....b1t,b21,....,b2t,bk1,...,bkt)�Ƿ�������H(m,R'1,R'2,....R't)��ǰkt�����ء�����ǣ�����������ǩ������Ч��
		boolean flag = true;
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < b.length; j++) {
				//�����һ������λ����ͬ�����жϲ������Ч
				if(b[j][i].equals(hashRes.charAt(i*b.length+j))){
					flag = false;
				}
			}
		}
				
		if(!flag){
			System.out.println("ǩ����Ч");
		}else{
			System.out.println("ǩ����Ч");
		}
		
	}
}
