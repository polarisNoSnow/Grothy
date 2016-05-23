package com.tyb;
/**
 * ����ģʽ
 * @author tyb
 *
 */
//����ʽ���̰߳�ȫ
public class Singleton {
	private static Singleton singleton;
	private Singleton(){}
	public static Singleton getInstance(){
		if(singleton==null){
			singleton = new Singleton();
		}
		return singleton;
	}
	
}

//����ʽ�̰߳�ȫ��ÿ����Դͬ�������������ģ�
class PersonFactory1{
	private static PersonFactory1 personFactory;
	private PersonFactory1(){};
	public static synchronized PersonFactory1 getInstance(){
		if(personFactory == null){ 
			personFactory = new PersonFactory1();
		}
		return personFactory;
	}
}

//����ʽ�̰߳�ȫ������99%��������ģ�
class PersonFactory2{
	private static PersonFactory2 personFactory;
	private PersonFactory2(){};
	public static PersonFactory2 getInstance(){
		if(personFactory == null){
			synchronized (PersonFactory2.class) {   //ֻ��ʵ������ʱ��ͬ����
			personFactory = new PersonFactory2();
	}
		}
		return personFactory;
	}
}

//����ʽ�̰߳�ȫ�������Ż�(�������)
class SingFactory{
	private static class DeLoad{
		private static final  SingFactory instance = new SingFactory();
	}
	
	private SingFactory(){};
	
	public static SingFactory getInstance(){
		return DeLoad.instance;
	}
}

//����ʽ(�����̰߳�ȫ�����ǵ�һ����������)
class Singleton2{
	private static Singleton2 singleton = new Singleton2();
	private Singleton2(){};
	public static Singleton2 getInstance(){		
		return singleton;
	}
}

