package com.tyb;
import java.math.BigInteger; 
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import java.io.*; 
public class Prime extends WindowAdapter implements ActionListener,ItemListener{ 
 
        String st; 
        String en; 
        String re; 
        //��ǩ//////////////////////////////////////////////// 
        Label l1=new Label("��һ������:"),l2=new Label("�ٴθ�����:"),l3=new Label("�Ƿ���������");  
        //Label l4=new Label("�Ƿ���������"); 
        JFrame f; 
        TextField ta1=new TextField(70),ta2=new TextField(70),ta3=new TextField(70); 
        TextField ta4=new TextField(8); 
        //TextField ta5=new TextField(8); 
        Button b1=new Button("��������"),b2=new Button("��������"),b3=new Button("�ٴ�����"); 
        Choice c1; 
        Panel p1,p2,p3,p4,p5,p6,p7,p8,p9; 
        public void display(){ 
        f=new JFrame("�й���ҵ��ѧ����ѧ�γ���� ѧ�ţ�08093739 ŷ��ǿ "); 
        f.setSize(600,300); 
        f.setLocation(200,140); 
        f.setBackground(Color.red);
        f.setLayout(new BorderLayout()); 
        tiajiazujian(); 
        f.setVisible(true); 
    } 
     
    public void tiajiazujian(){  
        //ѡ��ť 
        c1=new Choice(); 
        c1.add("ʵ��һ�������������㷨");//c1.add("�����㷨");c1.add("ά�������㷨");c1.add("�û��㷨");c1.add("ʵ�ַ��������㷨DES  Ĭ����ԿΪ��cumtcs");c1.add("ʵ�ֹ�Կ�����㷨RSA"); 
        c1.addItemListener(this); 
        f.add(c1,"North"); 
        p1=new Panel(); 
        p3=new Panel(); 
        f.add(p3,"Center"); 
        p3.setLayout(new FlowLayout()); 
        p3.add(l1);p3.add(ta1);p3.add(l2);p3.add(ta2);p3.add(l3);p3.add(ta3); 
        //p3.add(l4);p3.add(ta4); 
        p2=new Panel(); 
        f.add(p2,"South"); 
        p2.setLayout(new GridLayout(1,5)); 
        p2.add(b1);p2.add(b2);p2.add(b3);; 
        b1.addActionListener(this); 
        b2.addActionListener(this); 
        b3.addActionListener(this); 
    } 
     
    public void actionPerformed(ActionEvent e){                      
        if (e.getSource()==b3&&c1.getSelectedIndex()==0){//��������ť 
            sushu3(); 
        } 
        if (e.getSource()==b1&&c1.getSelectedIndex()==0)//��һ����ť 
        { 
            sushu1(); 
        } 
         
        if(e.getSource()==b2&&c1.getSelectedIndex()==0){//�ڶ�����ť 
            sushu2(); 
        } 
     
    } 
 
    public void itemStateChanged(ItemEvent e){ 
     
     
    } 
 
    //ʵ��һ�������������㷨 
    public void sushu1(){ 
        int numDigits; 
        try { 
            numDigits = Integer.parseInt("15"); 
        } catch (Exception e) { 
            numDigits = 128; 
        } 
        BigInteger start = bigRandom(numDigits); 
 
        start = nextPrime(start); 
        BigInteger end = bigRandom(5); 
        end = nextPrime(end); 
 
        BigInteger result = start.multiply(end); 
        st=start.toString(); 
        en=start.toString(); 
        re=start.toString(); 
        ta1.setText(st); 
 
    } 
 
    public void sushu2(){ 
        double do1=Double.parseDouble(st); 
        String ss21=fun(do1); 
        ta3.setText(en+"-->"+ss21); 
         
    } 
    public void sushu3(){ 
        int numDigits; 
        try { 
            numDigits = Integer.parseInt("15"); 
        } catch (Exception e) { 
            numDigits = 128; 
        } 
        BigInteger start = bigRandom(numDigits); 
 
        start = nextPrime(start); 
        BigInteger end = bigRandom(5); 
        end = nextPrime(end); 
        BigInteger result = start.multiply(end); 
        st=start.toString(); 
        en=start.toString(); 
        re=start.toString(); 
        ta2.setText(en); 
 
    } 
 
 
 
 
    // ����� BigInteger.ZERO �� BigInteger.ONE �� JDK 1.1 ������Ч�� 
    private static final BigInteger ZERO = BigInteger.ZERO; 
    private static final BigInteger ONE = BigInteger.ONE; 
    private static final BigInteger TWO = new BigInteger("2"); 
     
    // ����һ�����������ĸ���С�� 1/2 �� ERR_VAL �η������Խ� ERR_VAL ����Ϊ 200������������� 
    // Java Ӧ��ʹ�õ��� Miller-Rabin ���Է������ִ�����ʻ����Ͽ�����Ϊ���޴��� 
    private static final int ERR_VAL = 100; 
    private static StringBuffer[] digits = { new StringBuffer("0"), new StringBuffer("1"), new StringBuffer("2"), new StringBuffer("3"), new StringBuffer("4"), new StringBuffer("5"), 
        new StringBuffer("6"), new StringBuffer("7"), new StringBuffer("8"), new StringBuffer("9") }; 
 
    private static StringBuffer randomDigit(boolean isZeroOK) { 
        // ����һ����������֣��ַ�����ʽ�ģ���isZeroOK ������������Ƿ����Ϊ 0 
        int index; 
        if (isZeroOK) 
            index = (int) Math.floor(Math.random() * 10); 
        else 
            index = 1 + (int) Math.floor(Math.random() * 9); 
        return (digits[index]); 
    } 
 
    public static BigInteger bigRandom(int numDigits) { 
        // ����һ���������������λ�ϵ����ֶ�����������ģ���λ��Ϊ 0 
        StringBuffer s = new StringBuffer(""); 
        for (int i = 0; i < numDigits; i++) 
            if (i == 0) 
                s.append(randomDigit(false)); 
            else 
                s.append(randomDigit(true)); 
        return (new BigInteger(s.toString())); 
    } 
 
    private static boolean isEven(BigInteger n) { 
        // ����һ���������Ƿ�Ϊż�� 
        return (n.mod(TWO).equals(ZERO)); 
    } 
 
    public static BigInteger nextPrime(BigInteger start) { 
        // ����һ���ȸ��������� start ��������������ʵ��� 1/2 �� ERR_VAL �η� 
        if (isEven(start)) 
            start= start.add(ONE); 
        else 
            start = start.add(TWO); 
        if (start.isProbablePrime(ERR_VAL)) 
            return (start); 
        else 
            // ���õݹ鷽ʽ���ݹ�Ĳ������Ǹ����������𣿣� 
            return (nextPrime(start)); 
    } 
        //�������� 
    public static void main(String arg[]){ 
        Prime ob=new Prime(); 
        ob.display(); 
    } 
 
 
    //�ж�����//////////////////////////////////////////////////////////////////////////////////// 
    public String fun(double n){ 
        boolean isPrime = true; 
        //���n����2 �����ж� ���� isPrime��ֵ���� 2���� 
        if(n>2){ 
        //���n�Ǵ���2��ż�� �϶��������� �޸ı���ֵΪfalse 
            if(n%2==0){ 
                isPrime=false;} 
            else{ 
        //ѭ���ж�����ҵ�һ�������������� ���ж�������������ѭ�� ��Ϊ���ж����� ��� 2 4 6 ...? 
        //���ÿ��� ѭ������2 ��?3 5 7 ... 
                for(int i=3;i<=(int)Math.sqrt(n);i+=2){
                    if(n%i==0){ 
                    isPrime=false; 
                    break;}} 
        } 
             
} 
        if(isPrime==true) 
            return "������"; 
        else 
            return "��������"; 
 
    } 
 
 
 
}  