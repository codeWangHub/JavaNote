## Java 数组

[TOC]

### 1. 一个有意思的现象

```java
 public class ArrayDemo {
	 public static void main(String[] args) {
			
			int[] arr1 = {1,2,3};			
			System.out.println(arr1);
			System.out.println(arr1.toString());		 
	 }
 }

 运行结果：
 [I@15db9742
 [I@15db9742 
```

- Java 中数组是一种引用数据类型，那么它为什么会有toString方法？为什么会有length属性？这不是类的特性吗？**所以说Java的数组是一个类，一种特殊的类**。


- 我们可以验证一下：

  ```java
  System.out.println("Array is Object ? " + arr1 instanceof Object);
  运行结果：
  Array is Object ? true 
  ```

### 2. 获得数组这个类的信息

在Java的API中我们是找不到数组的源码，程序编译完成之后也没有数组对应的class文件，那么我们可以利用反射来获得数组类的一些信息。

```java
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
			/* 是不是对象 */
			System.out.println("Array is Object ? " + arr1 instanceof Object);
			/* 有多少个构造方法 */
			Constructor[] cons = arr1.getClass().getConstructors();
			System.out.println("Int Array has " + cons.length + " Constructors");
			/* 有什么属性 */
			Field[]  feilds  = arr1.getClass().getFields();
			System.out.println("Int Array has " + cons.length + " Fields");	
			/* 有哪些方法 */	
			Method[]  methods = arr1.getClass().getMethods();
			System.out.println("Int Array has " + cons.length + " Methods");					/* 实现了什么接口 */			
			Class[]  interfaces = arr1.getClass().getInterfaces();
			System.out.println("Int Array has " + cons.length + " interfaces");
			/* 集成了哪些类 */
			Class  father  = arr1.getClass().getSuperclass();
			System.out.println("Int Array's super Class is " + father.getName());
			/* 使用什么类加载器 */
			ClassLoader	loader =  arr1.getClass().getClassLoader();
			System.out.println("Int Array's ClassLoader  is " + loader);
		
	 }
 }
 
 运行结果：
[I@15db9742
[I@6d06d69c
ab
[I@15db9742
[I
true
Int Array has 0 Constructors
Int Array has 0 Annotations
Int Array has 0 Fields
Int Array has 0 Methods
Int Array has 0 interfaces
Int Array's super Class is java.lang.Object
Int Array's ClassLoader  is null  
```

可以看出：数组类型没有属性，没有构造函数，没有方法<继承了Object的方法>，没实现任何借口，他有唯一一个父类Object。

### 3. 关于数组类型的解析

- 数组的length即不是方法，也不是字段。数组的 length 属性是 Java 编译器在编译时给加上的，为其分配了 1 个字长的内存空间，用于存储数组的长度。
- 在一个数组对象上调用length，会被Java编译器编译成一条arraylength指令（Java binary code）。
- Java 中还有一个跟数组 length 属性类似的，也就是类的 class 属性，比如 String.class，这个 class 也是编译器在编译时动态给加上的。
- 数组的length属性就相当于我们创建类时编译器给我们创建的Class对象一样。
- java.lang.reflect.Array这个反射类中getLength(Object array)方法也是用来获取一个数组的长度属性，是一个``静态的native方法``。这就说明数组类型是指令级的。

### 4. 数组的创建和初始化

java数组的初始化和创建分为静态初始化和动态初始化。

- 声明数组    格式：DataType[<size>]   arrayName;

  ```java
  int[]  arr1;    // 声明一个int型数组，不分配空间，只是一个引用
  int[5] arr2;    // 声明一个int型5元素的数组，不分配空间，只是一个引用
  String[] args;  

  int[] arr4 = new int[5];  // 声明并分陪5个int型元素的数组，初始化默认为0

  int arr5[] = xxx;   // 这种格式也是可以的，但是不推荐使用。
  ```


- 这里有一点需要**注意**：例如上面的```int[5] arr2;``` 声明了一个int型的数组，它仅仅是一个数组的引用，只是在java栈中分配了arr2 用来指向一个数组，并没有分配任何堆空间。所以这个是用的arr2是不能使用的。```arr1 ，args``` 一样。

- 数组的初始化

  - 静态初始化

    ```java
    int[] arr1 = {1,2,3,4,5};
    int[] arr2 = new int[]{1,2,3,4,5};    /* 和上面一样 */
    int[] arr3 = new int[3];   /* 初始化为该类型<int>的默认值<0> */
    ```

  - 动态初始化

    ```java
    int[] arr1 = new int[10];

    for(int i=0; i<arr1.length; ++i) {
      	arr1[i] = i;
    }
    ```

- 数字的内存模型

  ![Java一维数组内存模型](.\Java一维数组内存模型.jpg)

  ### 5. 多维数组

  - 多维数组和C/C++中的多维数组相似。以二维数组为例：

    ```java
    int[][] a = new int[][]{{1,2,3},{4,5,6},{7,8,9}}; 
    /*
     *   		a[][0]  a[][1]  a[][2]
     *  a[0][]    1        2       3  
     *  a[1][]    4        5       6
     *  a[2][]    7        8       9  
    */
    ```

    ​


  - 像上面的```int[][] a;``` 就是声明一个int型的二维数组，实质上它相当于：声明一个一维数组，只不过它的元素类型是int型一维数组。

    ```java
    int[][] a = new int[][]{{1,2,3},{4,5,6},{7,8,9}};	
    int[]  a1 = a[0];
    int[]  a2 = a[1];
    int[]  a3 = a[2];

    /* 注意！ 定义时不能写成 int[3][3] a = new int[3][3]{xxx} 
     *       因为你既然初始化了，那么数组的大小是由编译器推算出来的，而不应该由程序指定。
     *       只有在只声明不初始化时才可以指定大小，表示要分配多大的空间。
     *       例如： int[][] a = new int[3][3]; 前面的数组类型永远不要带大小。
    */
    ```

  ![二维数组内存模型](.\二维数组内存模型.jpg)

  - 既然Java中所有的int数组都是同一个"类"的实例。那么我的任意一个二维数组就可以存储任意的一维数组，即可以长短不一。不想上面的例子中三个一维数组长度都是3.

    ```java
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
    		运行结果：
            12345
    		234
    		9876543
    ```

    那么这种不规则二维数组内部到底是把短的数组补长还是直接引用？我们换种方式打印，就可以完全推出这种不规则二维数组的内存结构了！假设是补长，那么我们可以这么写：

    ```java
    		for(int i=0;i<a.length ; ++i) {
    			for(int j=0; j<7 ;j++) {   /* 最长的一个子数组 */
    				System.out.print(a[i][j]);
    			}
    			System.out.println("");
    		}	
    		运行结果：
            12345Exception in thread "main" 	java.lang.ArrayIndexOutOfBoundsException: 5
            at ArrayDemo.main(ArrayDemo.java:16)
    ```

    所以说，Java中二维数组实质上就是一维数组的一维数组！！！

    ​

  ​

  ​

  ​

  ​

  ​

  ​

  ​





