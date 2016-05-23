package com.tyb;

import java.math.BigInteger;
import java.util.Random;

public class BigMathUtils {
	
	
	public static void main(String[] args) {
		System.err.println("shift:"+shift_Left("111010",5));
		BigInteger n =  new BigInteger("55123456");
		//System.err.println(isPrime(n));
		BigInteger[] r = new BigInteger[10];
		hash("my name is tyb",r);
	}
	

	/**
	 * @Des �������Ŀ���
	 * @param bigNum
	 * @return ��������������
	 */
	public static BigInteger bigSqrt(BigInteger bigNum){
		BigInteger sqrtNum = null;
		int l = 1;
		while(bigNum.compareTo(new BigInteger(l*l+"")) >= 0){
			sqrtNum = new BigInteger(l+"");
			l++;
		}
		return sqrtNum;
	}
	/**
	 * @Des Miller-Rabin�ж��Ƿ�Ϊ����,Ϊ�����ĸ�������Ϊ1-2^-(a�ĸ���)
	 * @param a 
	 * @param number ģ������Ҫ�жϵ�����
	 * @author tyb
	 * @return �Ƿ�Ϊ���� falseΪ������
	 */
	public static boolean MR_isPrime(BigInteger a,BigInteger number){
		String binary = number.subtract(new BigInteger("1")).toString(2);
		char[] chBinary = binary.toCharArray(); //��ö����Ƶ�ÿ���ַ�

		BigInteger d = new BigInteger("1");
		BigInteger x ;
		
		//witness���Ĳ���
		for (int i = 0; i < binary.length() ; i++) {
			x = d;
			d = d.pow(2).mod(number);
			if(d.compareTo(new BigInteger("1")) == 0 && x.compareTo(new BigInteger("1")) != 0
					&& x.compareTo(number.subtract(new BigInteger("1"))) != 0){
				return false;
			}
			if(chBinary[i] == 49){  //charֵ49��������1
				d = d.multiply(a).mod(number);
			}
		}
		if(d.compareTo(new BigInteger("1")) != 0){
			return false;
		}
		return true;
	}
	
	/**
	 * @Des ͨ��java�Դ��ķ������0-2^(len-1) ��֮������ֵ
	 * @param number
	 * @return
	 */
	public static BigInteger getBigRandom(BigInteger number){
		BigInteger ran = new BigInteger(number.bitLength()-1,new Random());
		return ran;
	}
	
	/**
	 * @Des �ж��Ƿ�Ϊ����,����miller-Rabin�㷨
	 * @param n
	 * @author tyb
	 * @return false Ϊ������
	 */
	public static boolean isPrime(BigInteger number){
		boolean flag = true;
		
		for (int i = 0; i < 20; i++) {
			if(!MR_isPrime(getBigRandom(number), number)){
				flag = false;  //ֻҪ��һ��Ϊfalse ��Ϊ����
			}
		}
		
//		for (int i = 2; i < bigSqrt(number).intValue(); i++) {
//			if(!MR_isPrime(new BigInteger(i+""), number)){
//				flag = false;  //ֻҪ��һ��Ϊfalse ��Ϊ����
//			}
//		}
		
		return flag;
	}
	
	/**
	 * @Des ƽ��ʣ�ࣺ��һ����ѧ�������p��������a�������� �������һ������xʹ��x^2��a(mod p) 
	 * (��x^2-a���Ա�p����)�� ��ô�ͳ�a��p��ʣ��������ƽ��ʣ��ġ�p��ƽ��ʣ�����Ϊ(p-1)/2
	 * @param p p��ƽ��ʣ��
	 * @author tyb
	 * @return ���p��ƽ��ʣ��
	 */
	public static BigInteger squResidual(BigInteger p){
//		if(!isPrime(p)){
//			System.out.println("������");
//			return new BigInteger("0");
//		}
		BigInteger a = null;//���ص�aֵ
		try{
			BigInteger exponent = (p.subtract(new BigInteger("1"))).divide(new BigInteger("2"));
			boolean flag = true;
			while(flag){
			    a = getBigRandom(p); //a��ֵ
			    System.out.println("ran="+a);
				BigInteger r = fastExponent(a, exponent, p);//1(ƽ��ʣ��),-1,0
				if(r.compareTo(new BigInteger("1")) == 0){
					flag = false;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return a;	
	}
	
	/**
	 * @Des ŷ������㷨���ֳ�շת���������gcb(a,b)=1�Ŵ�����Ԫ
	 * @author tyb
	 * @param a �ϴ����
	 * @param b ����
	 * @return a��b���Լ��
	 */
	public static BigInteger euclidean(BigInteger a,BigInteger b){
		//��ʼ������
		BigInteger x1 = new BigInteger("1"), x2 = new BigInteger("0"), 
				y1 = new BigInteger("0"), y2 = new BigInteger("1");
		BigInteger Q;
		BigInteger t1, t2, t3;
		
			//a,b���Լ��Ϊ1,˵������
			while(true){
				//��Ϊ���������������Լ������0
				if(b.compareTo(new BigInteger("0")) == 0){
					//return a.gcd(b);
					return new BigInteger("0");
				}
				//�ֽ⵽b=1,�򷵻���Ԫy2
				if(b.compareTo(new BigInteger("1")) == 0){
					return y2;
				}
				Q = a.divide(b);
				t1 = x1.subtract(Q.multiply(y1));
				t2 = x2.subtract(Q.multiply(y2));
				t3 = a.subtract(Q.multiply(b));
				
				x1 = y1;
				x2 = y2;
				a = b;
				
				y1 = t1;
				y2 = t2;
				b = t3;
			}
	}
	
	/**
	 * @Des ����ָ���㷨
	 * @param a ����a
	 * @param m ָ����m
	 * @param n ģn
	 * @return a^m(mod n) 
	 */
	public static BigInteger fastExponent(BigInteger a,BigInteger m,BigInteger n){
		String binary = m.toString(2);
		char[] biChar = binary.toCharArray(); //�����ָ��m�Ķ�����char����
		BigInteger c = new BigInteger("0");
		BigInteger d = new BigInteger("1");
		for (int i =0; i <  binary.length(); i++) {
			c = c.multiply(new BigInteger("2"));
			d = d.pow(2).mod(n);
			if(biChar[i] == '1'){  //asciiֵ49Ϊ����1(biChar[i] == 49)Ҳ����
				c = c.add(new BigInteger("1"));
				d = d.multiply(a).mod(n);
			}
		}
		
		return d;
	}
	
	/**
	 * @Des ��ȡ�����(ǰ��������ƴ�ӣ����Կ��ܵ���һ���ֳ���int�ı�ʾ��Χ)
	 * @param n ������n
	 * @return ����С��n�����������
	 */
	public static BigInteger getRandom(BigInteger n){
		int len = n.toString().length()-1;
		Random random = new Random();
		int xx = 1;
		for (int i = 0; i < len/2; i++) {
			 xx = xx*10;
		}
		int ranFront = (int) (random.nextDouble()*xx);
		int ranBottom = (int) (random.nextDouble()*xx);
		
		return new BigInteger(ranFront+""+ranBottom);
	}
	
	/**
	 * @Des MD5�Ӵ��㷨���ֳ�hash�㷨
	 * @param m  ��Ϣm
	 * @param r
	 * @return ǰkt����ֵ
	 */
	public static String hash(String m, BigInteger[] r) {

		//׼���׶Σ�����Ϣת��Ϊ��������Ϣ
		char[] mess =  m.toCharArray();
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < mess.length; i++) {
			Integer x = (int) mess[i];
			result.append(Integer.toBinaryString(x)+"");    
		}
		System.out.println(result.toString());
		
		//1.����Ϣ�������
		int messLen = result.toString().length(); //��Ϣ�ĳ���
		System.out.println(messLen/512);
		int lastLen = ((messLen/512)+1)*512;  //���ճ��ȣ�Ϊ512�ı��� ��
		BigInteger mesBig = new BigInteger(messLen+"");
		
		//�����ģ512�ĳ��ȳ���448�򲻹�����64 ��Ҫ���
		if((mesBig.mod(new BigInteger("512"))).compareTo(new BigInteger("448")) >= 0){
			lastLen+=512;
		}
		System.out.println("lastLen:"+lastLen);
		System.out.println("messLen:"+messLen);
		System.out.println("��Ҫ��䣺" + (lastLen-64-messLen));
		result.append(1);//��һλΪ1
		for (int i = 0, j = lastLen-64-messLen-1; i < j; i++) {
			result.append(0);
		}
		System.err.println(result.toString());
		
		//2.������Ϣ�ĳ���
		int exLen = messLen; 
		if(messLen > Math.pow(2, 64)){
			exLen = new BigInteger(messLen+"").mod(new BigInteger(Math.pow(2, 64)+"")).intValue();
		}
		String exMess = Integer.toBinaryString(exLen);
		System.out.println("������Ϣ��"+exMess);
		//�͵�ַλ���
		for (int i = 0; i < 64-exMess.length(); i++) {
			result.append(0);
		}
		result.append(Integer.toBinaryString(messLen));
		System.out.println("res.len:"+result.length());
		
		//��Ϊ512*L ,L��;���ֱ�ʾΪM[0,1,.....N-1],32����Ϊһ��
		int L = result.length()/512;
		String[] Y = new String[L];
		for (int i = 0; i < Y.length; i++) {
			Y[i] = result.toString().substring(i*512, (i+1)*512-1);
		}
		int N = L*16;
		String[] M = new String[N];
		for (int i = 0; i < M.length; i++) {
			M[i] = result.substring(i*32, (i+1)*32);
		}
		
		
		//1.3 ��MD��������ʼ��
		String A = "01234567";
		String B = "89ABCDEF";
		String C = "FEDCBA98";
		String D = "76543210";
		String A_ = fillSeats(Long.toBinaryString(Long.valueOf(A,16)), 32);
		String B_ = fillSeats(Long.toBinaryString(Long.valueOf(B,16)), 32);
		String C_ = fillSeats(Long.toBinaryString(Long.valueOf(C,16)), 32);
		String D_ = fillSeats(Long.toBinaryString(Long.valueOf(D,16)), 32);
		String[] CV = new String[L];
		CV[0] = A_ + B_ + C_ + D_;
		String[] ABCD = new String[4];
		//1.4�Է���Ϊ��λ����Ϣ���д���
		StringBuffer x = new StringBuffer();
		StringBuffer binaRes = new StringBuffer();
		for (int i = 0; i < L; i++) {
			System.out.println("��"+(i+1)+"��");
			String outFour = MD5(A_,B_,C_,D_,M,i);
			
			//CV[0] ÿ����������ֵ���� MD5(A,B,C,D,M,i)��ģ2^32���
			
			for (int j = 0; j < 4; j++) {
				ABCD[j] = mod2Add(CV[0].substring(j*32, (j+1)*32), outFour);
				binaRes.append(ABCD[j]);
			}
			System.out.println();
		}
		
		//1.5���
		//ת����16����
		for (int i = 0; i < ABCD.length; i++) {
			x.append(new BigInteger(Long.parseLong(ABCD[i], 2)+"").toString(16));
		}
		System.err.print("�����������"+binaRes.toString());
		System.err.println("���������"+x.toString());
		return binaRes.toString();
	}
	
	/**
	 * @param a,b,c,d ��������4���� (������)
	 * @param m ���ֱ�ʾ��Ϣ��M
	 * @param q ��ǰ���� 
	 * @Des MD5ѹ��������a<-b+CLSs(a+g(b,c,d)+X[k]+T[i])
	 */
	private static String MD5(String a, String b, String c, String d, String[] m, int q) {
		//TODO �û����⣬M��ֵ��λ�ø����ˣ��´θ���������һ�θ��ĺ��λ�á�
//		a = fillSeats(Long.toBinaryString(Long.valueOf(a,16)), 32);
//		b = fillSeats(Long.toBinaryString(Long.valueOf(b,16)), 32);
//		c = fillSeats(Long.toBinaryString(Long.valueOf(c,16)), 32);
//		d = fillSeats(Long.toBinaryString(Long.valueOf(d,16)), 32);
		for (int i = 0; i < 64; i++) {
			//16��F����
			if(i<16){
				String cls = mod2Add(a,mod2Add(fillSeats(BaseFunction.F(b, c, d),32),
						mod2Add(m[q*16+i],fillSeats(Long.toBinaryString(Long.valueOf(BaseFunction.T_Con.T[i],16)),32))));
				//����s����b ��ģ2^32�ӷ�
				a = mod2Add(b, shift_Left(cls, BaseFunction.S_Con.s[0][i]).toString());
				//a = b + (a + BaseFunction.F(b, c, d) + m[q*16+i] + BaseFunction.T_Con.T[i]);
			}
			//16��G����
			else if(i >= 16 && i < 32){
				for (int j = 0; j < 16; j++) {
					m[q*16+j] = m[q*16+(1+5*(j+1))%16];
				}
				String cls = mod2Add(a,mod2Add(fillSeats(BaseFunction.G(b, c, d),32),
						mod2Add(m[q*16+(i-16)],fillSeats(Long.toBinaryString(Long.valueOf(BaseFunction.T_Con.T[i],16)),32))));
				//����s����b ��ģ2^32�ӷ�
				a = mod2Add(b, shift_Left(cls, BaseFunction.S_Con.s[1][i-16]).toString());
				//a = b + (a + BaseFunction.G(b, c, d) + m[q*16+(i-16)] + BaseFunction.T_Con.T[i]);
			}
			//16��H����
			else if(i >= 32 && i < 48){
				for (int j = 0; j < 16; j++) {
					m[q*16+j] = m[q*16+(5+3*(j+1))%16];
				}	
				String cls = mod2Add(a,mod2Add(fillSeats(BaseFunction.H(b, c, d),32),
						mod2Add(m[q*16+(i-32)],fillSeats(Long.toBinaryString(Long.valueOf(BaseFunction.T_Con.T[i],16)),32))));
				//����s����b ��ģ2^32�ӷ�
				a = mod2Add(b, shift_Left(cls, BaseFunction.S_Con.s[2][i-32]).toString());
				//a = b + (a + BaseFunction.H(b, c, d) + m[q*16+(i-32)] + BaseFunction.T_Con.T[i]);
			}
			//16��I����
			else if(i >= 48 && i < 64){
				for (int j = 0; j < 16; j++) {
					m[q*16+j] = m[q*16+(7*(j+1))%16];
				}
				String cls = mod2Add(a,mod2Add(fillSeats(BaseFunction.I(b, c, d),32),
						mod2Add(m[q*16+(i-48)],fillSeats(Long.toBinaryString(Long.valueOf(BaseFunction.T_Con.T[i],16)),32))));
				//����s����b ��ģ2^32�ӷ�
				a = mod2Add(b, shift_Left(cls, BaseFunction.S_Con.s[1][i-48]).toString());
				//a = b + (a + BaseFunction.I(b, c, d) + m[q*16+(i-48)] + BaseFunction.T_Con.T[i]);
			}
		}
		return a;
		
	}
	
	/**
	 * @Des ����ѭ����λb
	 * @param a
	 * @param b
	 * @return
	 */
	public static String shift_Left(String a,int b){
		int len = a.length();
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < len; i++) {
			res.append(a.charAt((i+b)%len));
		}
		return res.toString();
	}
	/**
	 * @Des ģ2 �ӷ�
	 * @param a
	 * @param b
	 * @return
	 */
	private static String mod2Add(String a, String b) {
		StringBuffer c = new StringBuffer();
		char a_;
		char b_;
		for (int i = 0; i < a.length(); i++) {
			a_ = a.charAt(i);
			b_ = b.charAt(i);
			if(a_ == '1' || b_ == '1'){
				c.append(1);
			}else{
				c.append(0);
			}
		}
	
		return c.toString();
	}
	
	/**
	 * @Des ����xλ��ǰ���0
	 * @param mess ����������
	 * @param x Ҫ��ﵽ��λ��
	 * @return
	 */
	public static String fillSeats(String mess,int x){
		if(mess.length() > x){
			System.err.println("----fillSeats error---");
			return mess;
		}
		for (int i = 0, j = 32-mess.length(); i < j; i++) {
			mess = "0" + mess;
		}
		return mess;
	}

}
