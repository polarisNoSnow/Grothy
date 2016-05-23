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
		
		//1.体制参数（随机产生素数问题是一个重要的问题）
		
		//1.1 参数n及其素数产生(p,q是保密的大素数)
		p = new BigInteger("12345");
		p = BigMathUtils.getRandom(p);
		q = new BigInteger("12345");
		q = BigMathUtils.getRandom(q);
		p = p.nextProbablePrime();//大于p的最近一个素数
		q = q.nextProbablePrime();//大于q的最近一个素数
		System.out.println("1.1 pq素数产生--完毕");
		
		//1.2素数检验
		if(!(BigMathUtils.isPrime(p) && BigMathUtils.isPrime(q))){
			System.err.println("素数产生出错");
			System.exit(0);
		}else{
			n = p.multiply(q);
			System.err.println("p:"+p+",  q:"+q);
			System.err.println("素数p*q=n："+n);
		}
		System.out.println("1.2素数检验--完毕");
		
		//1.3 参数k(固定的正整数)
		k = 9;
		
		//1.4 参数y1,y2....yk:用户A的公开密钥，对任何i(1<=i<=k),yi都是模n的平方剩余
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
		
		//1.5参数x1,x2...xk:用户A的秘密密钥，对任何i(1<=i<=k),xi三sqrt(yi的逆元)(mod n)
		x = new BigInteger[k];
		//yi的逆元
		for (int i = 0; i < k; i++) {
			//TODO 逆元为负数，取绝对值，开方后加上n为 同余
			x[i] = BigMathUtils.bigSqrt(BigMathUtils.euclidean(n,y[i]).abs()).add(n);
			System.out.println("x"+i+"="+x[i]);
		}
		System.out.println("1.体制参数--完毕");
		
		
		//2签名的产生过程
		//2.1随机选取一个正整数t
		Random rand = new Random();
		int t = rand.nextInt(10);
		
		//2.2随机选取t个介于1和n之间的数r1,r2...ri,并对任何j(1<=j<=t),计算Rj三rj2(mod n)
		BigInteger[] r = new BigInteger[t];
		BigInteger[] R = r;
		
		for (int i = 0; i < t; i++) {
			//TODO 随机选取1-n之间
			r[i] = n;//?????? 
		}
		
		//获取R(1-j的值)
		for (int i = 0; i < t; i++) {
			//TODO 询问是否正确
			R[i] = r[i].add(n); //r[i]与R[i]同余
		}
		
		//2.3计算杂凑值H(m,R1,R2,..Rt),并依次取出H(m,R1,R2,..Rt)的
		//前kt个比特值b11,..b1t,b21,...b2t,....,bk1,...bkt。
		String m = "Hello world";
		String hash_res = BigMathUtils.hash(m,R);
		String[][] b = new String[k][t];
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < t; j++) {
				b[i][j] = hash_res.charAt(i*t+j) + "";
			}
		}
		
		//2.4对任何j(1<=j<=t),计算sj三ri (xi^bij)+(1...k) mod n
		//((b11,....b1t,b21,....,b2t,bk1,...,bkt),(s1.....st))作为对m的数字签名
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
	 * @Des 验证签名
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
		//3签字的验证过程
		//接收方收到消息m和签名((b11,....b1t,b21,....,b2t,bk1,...,bkt),(s1.....st))，用以下步骤来验证
		//3.1对任何j(1<=j<=t),计算R'j三Sj^2 * (yi^bij)+(1...k) mod n
		for (int i = 0; i < s.length; i++) {
			BigInteger sum = new BigInteger("0");
			for (int j = 0; j < b.length; j++) {
				 sum = sum.add(y[j].pow(Integer.valueOf(b[j][i])));
			}
			R_[i] = s[i].pow(2).multiply(sum).add(n);
		}		
				
		//3.2 计算H(m,R'1,R'2,....R't)
		String hashRes = BigMathUtils.hash(m,R_);
		//3.3验证(b11,....b1t,b21,....,b2t,bk1,...,bkt)是否依旧是H(m,R'1,R'2,....R't)的前kt个比特。如果是，则以上数字签名是有效的
		boolean flag = true;
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < b.length; j++) {
				//如果有一个比特位不相同，则中断并标记无效
				if(b[j][i].equals(hashRes.charAt(i*b.length+j))){
					flag = false;
				}
			}
		}
				
		if(!flag){
			System.out.println("签名无效");
		}else{
			System.out.println("签名有效");
		}
		
	}
}
