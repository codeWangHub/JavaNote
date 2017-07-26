 
 import java.lang.reflect.*;
 import java.lang.annotation.*;
 
 public class ArrayDemo {
	 public static void main(String[] args) {
			
			int[] arr1 = {1,2,3};
			int[] arr2 = {0,1,2,3,4};
			char[] arr3 = {'a','b'};
			
			System.out.println(arr1);	
			System.out.println(arr2);
			System.out.println(arr3);
			
			System.out.println(arr1.toString());
			
			System.out.println(arr1.getClass().getName());
			
			
			System.out.println("Array is Object ? " + arr1 instanceof Object);
			
			Constructor[] cons = arr1.getClass().getConstructors();
			System.out.println("Int Array has " + cons.length + " Constructors");
		
			Annotation[] anns  = arr1.getClass().getAnnotations();
			System.out.println("Int Array has " + cons.length + " Annotations");
			
			Field[]  feilds  = arr1.getClass().getFields();
			System.out.println("Int Array has " + cons.length + " Fields");	

			Method[]  methods = arr1.getClass().getMethods();
			System.out.println("Int Array has " + cons.length + " Methods");				
			
			Class[]  interfaces = arr1.getClass().getInterfaces();
			System.out.println("Int Array has " + cons.length + " interfaces");
		
			Class  father  = arr1.getClass().getSuperclass();
			System.out.println("Int Array's super Class is " + father.getName());
		
			ClassLoader	loader =  arr1.getClass().getClassLoader();
			System.out.println("Int Array's ClassLoader  is " + loader);
		
	 }
 }
 
 
 // 基本数据类型 ， int char 。。 
 // 引用数据类型 ， 数组 ，类 接口，自定义的
 // [I@15db9742   --->
 // 类型@哈希码   