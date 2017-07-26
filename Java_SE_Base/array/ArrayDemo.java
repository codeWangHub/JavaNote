 
 import java.lang.reflect.*;
 import java.lang.annotation.*;
 
 public class ArrayDemo {
	 public static void main(String[] args) {
		 
			int[] a1 = {1,2,3,4,5};   // 长度为 5
			int[] a2 = {2,3,4};       // 长度为 3
			int[] a3 = {9,8,7,6,5,4,3}; //长度为 7

			int[][] a = {a1,a2,a3};	
			
			for(int i=0;i<a.length ; ++i) {
				for(int j=0; j<7 ;j++) {   /* 最长的一个子数组 */
					System.out.print(a[i][j]);
				}
				System.out.println("");
			}	
	 }
	 
	 
	 private void test3() {
		 	int[] a1 = {1,2,3,4,5};   // 长度为 5
			int[] a2 = {2,3,4};       // 长度为 3
			int[] a3 = {9,8,7,6,5,4,3}; //长度为 7

			int[][] a = {a1,a2,a3};	
			
			for(int i=0;i<a.length ; ++i) {
				for(int j=0; j<a[i].length ;j++) {
					System.out.print(a[i][j]);
				}
				System.out.println("");
			}	
		 
	 }
	 
	 private void test2() 
	 {
		 	 
		int[][] a = new int[][]{{1,2,3},{4,5,6},{7,8,9}};	
		int[]  a1 = a[0];
		int[]  a2 = a[1];
		int[]  a3 = a[2];			
	//	int[3][3] b = new int[3][3];				 
	 }
		 
	 
	 
	 
	 private void test1()
	 {		 
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