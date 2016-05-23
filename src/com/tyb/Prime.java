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
        //标签//////////////////////////////////////////////// 
        Label l1=new Label("第一个素数:"),l2=new Label("再次个素数:"),l3=new Label("是否是素数：");  
        //Label l4=new Label("是否是素数："); 
        JFrame f; 
        TextField ta1=new TextField(70),ta2=new TextField(70),ta3=new TextField(70); 
        TextField ta4=new TextField(8); 
        //TextField ta5=new TextField(8); 
        Button b1=new Button("生成素数"),b2=new Button("检验素数"),b3=new Button("再次生成"); 
        Choice c1; 
        Panel p1,p2,p3,p4,p5,p6,p7,p8,p9; 
        public void display(){ 
        f=new JFrame("中国矿业大学密码学课程设计 学号：08093739 欧二强 "); 
        f.setSize(600,300); 
        f.setLocation(200,140); 
        f.setBackground(Color.red);
        f.setLayout(new BorderLayout()); 
        tiajiazujian(); 
        f.setVisible(true); 
    } 
     
    public void tiajiazujian(){  
        //选择按钮 
        c1=new Choice(); 
        c1.add("实现一个大素数生成算法");//c1.add("仿射算法");c1.add("维吉尼亚算法");c1.add("置换算法");c1.add("实现分组密码算法DES  默认密钥为：cumtcs");c1.add("实现公钥密码算法RSA"); 
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
        if (e.getSource()==b3&&c1.getSelectedIndex()==0){//第三个按钮 
            sushu3(); 
        } 
        if (e.getSource()==b1&&c1.getSelectedIndex()==0)//第一个按钮 
        { 
            sushu1(); 
        } 
         
        if(e.getSource()==b2&&c1.getSelectedIndex()==0){//第二个按钮 
            sushu2(); 
        } 
     
    } 
 
    public void itemStateChanged(ItemEvent e){ 
     
     
    } 
 
    //实现一个大素数生成算法 
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
 
 
 
 
    // 下面的 BigInteger.ZERO 和 BigInteger.ONE 在 JDK 1.1 中是无效的 
    private static final BigInteger ZERO = BigInteger.ZERO; 
    private static final BigInteger ONE = BigInteger.ONE; 
    private static final BigInteger TWO = new BigInteger("2"); 
     
    // 产生一个错误素数的概率小于 1/2 的 ERR_VAL 次方，可以将 ERR_VAL 定义为 200，降低其错误率 
    // Java 应该使用的是 Miller-Rabin 测试法，这种错误概率基本上可以认为是无错误。 
    private static final int ERR_VAL = 100; 
    private static StringBuffer[] digits = { new StringBuffer("0"), new StringBuffer("1"), new StringBuffer("2"), new StringBuffer("3"), new StringBuffer("4"), new StringBuffer("5"), 
        new StringBuffer("6"), new StringBuffer("7"), new StringBuffer("8"), new StringBuffer("9") }; 
 
    private static StringBuffer randomDigit(boolean isZeroOK) { 
        // 产生一个随机的数字（字符串形式的），isZeroOK 决定这个数字是否可以为 0 
        int index; 
        if (isZeroOK) 
            index = (int) Math.floor(Math.random() * 10); 
        else 
            index = 1 + (int) Math.floor(Math.random() * 9); 
        return (digits[index]); 
    } 
 
    public static BigInteger bigRandom(int numDigits) { 
        // 产生一个随机大整数，各位上的数字都是随机产生的，首位不为 0 
        StringBuffer s = new StringBuffer(""); 
        for (int i = 0; i < numDigits; i++) 
            if (i == 0) 
                s.append(randomDigit(false)); 
            else 
                s.append(randomDigit(true)); 
        return (new BigInteger(s.toString())); 
    } 
 
    private static boolean isEven(BigInteger n) { 
        // 测试一个大整数是否为偶数 
        return (n.mod(TWO).equals(ZERO)); 
    } 
 
    public static BigInteger nextPrime(BigInteger start) { 
        // 产生一个比给定大整数 start 大的素数，错误率低于 1/2 的 ERR_VAL 次方 
        if (isEven(start)) 
            start= start.add(ONE); 
        else 
            start = start.add(TWO); 
        if (start.isProbablePrime(ERR_VAL)) 
            return (start); 
        else 
            // 采用递归方式（递归的层数会是个天文数字吗？） 
            return (nextPrime(start)); 
    } 
        //租主方法 
    public static void main(String arg[]){ 
        Prime ob=new Prime(); 
        ob.display(); 
    } 
 
 
    //判断素数//////////////////////////////////////////////////////////////////////////////////// 
    public String fun(double n){ 
        boolean isPrime = true; 
        //如果n大于2 继续判断 否则 isPrime的值不变 2素数 
        if(n>2){ 
        //如果n是大于2的偶数 认定不是素数 修改变量值为false 
            if(n%2==0){ 
                isPrime=false;} 
            else{ 
        //循环判断如果找到一个可以整除的数 则判定不是素数跳出循环 因为是判断奇数 因此 2 4 6 ...? 
        //不用考虑 循环递增2 即?3 5 7 ... 
                for(int i=3;i<=(int)Math.sqrt(n);i+=2){
                    if(n%i==0){ 
                    isPrime=false; 
                    break;}} 
        } 
             
} 
        if(isPrime==true) 
            return "是素数"; 
        else 
            return "不是素数"; 
 
    } 
 
 
 
}  