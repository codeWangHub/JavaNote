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





