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
		
		//1.体制参数（随机产生素数问题是一个重要的问题）
		
		//1.1 参数n及其素数产生(p,q是保密的大素数)
		p = new BigInteger("1234567");
		q = new BigInteger("23156456");
		p = p.nextProbablePrime();//大于p的最近一个素数
		q = q.nextProbablePrime();//大于q的最近一个素数
		System.out.println("1.1 pq素数产生--完毕");
		
		//1.2素数检验
		if(!(BigMathUtils.isPrime(p) && BigMathUtils.isPrime(q))){
			System.err.println("素数产生出错");
			System.exit(0);
		}else{
			n = p.multiply(q);
			System.out.println("素数p*q=n："+n);
		}
		System.out.println("1.2素数检验--完毕");
		
		//1.3 参数v:gcd(v,(p-1)(p-1))=1
		Random random = new Random();
		v = random.nextInt(10000);
		BigInteger bigV = new BigInteger(v+"");
		while(n.gcd(bigV).compareTo(new BigInteger("1")) != 0){
			v = random.nextInt(10000);
			bigV = new BigInteger(v+"");
		};
		//1.4 x:用户A的秘密钥，x属于RZn^*;
		
		//1.5 y:用户A的公开钥，y属于RZn^*,且x^v *y三1(mod n);
		
		
		//2签字的产生过程
		//对于待签消息m，A进行以下步骤：
		//2.1随机选择一个数k属于Zn^*,计算T三k^v(mod n)
		
		//2.2计算杂凑值：e=H(m,T),且使1<e<v;否则，返回步骤2.1
		
		//2.3计算s三kx^e mod n
		
		//以(e,s)作为对m的签名
		
		//3签字的验证过程
		//接收方在收到消息m和数字签名(e,s)后，用以下步骤来验证
		//3.1计算出T_三s^v *y^e(mod n)
		
		//3.2计算出e_ = H(m,T)
		
		//3.3验证：Ver(y,(e,s),m)=True <=> e_ = e。
	}
}
