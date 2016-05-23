package com.tyb;
/**
 * 单例模式
 * @author tyb
 *
 */
//懒汉式非线程安全
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

//懒汉式线程安全（每次资源同步，造成性能损耗）
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

//懒汉式线程安全（减少99%的性能损耗）
class PersonFactory2{
	private static PersonFactory2 personFactory;
	private PersonFactory2(){};
	public static PersonFactory2 getInstance(){
		if(personFactory == null){
			synchronized (PersonFactory2.class) {   //只在实例化的时候同步锁
			personFactory = new PersonFactory2();
	}
		}
		return personFactory;
	}
}

//懒汉式线程安全，性能优化(类加载器)
class SingFactory{
	private static class DeLoad{
		private static final  SingFactory instance = new SingFactory();
	}
	
	private SingFactory(){};
	
	public static SingFactory getInstance(){
		return DeLoad.instance;
	}
}

//饿汉式(天生线程安全，但是第一次启动较慢)
class Singleton2{
	private static Singleton2 singleton = new Singleton2();
	private Singleton2(){};
	public static Singleton2 getInstance(){		
		return singleton;
	}
}

