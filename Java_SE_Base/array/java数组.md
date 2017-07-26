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

- 数组没有对应具体的类，是一个特殊的类，在编译阶段由编译器生成，并且为每个数组对象多附加一个word字长的空间用来存储数组的长度信息，对数组的操作也是指令级别的。


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

  ![Java一维数组内存模型](https://raw.githubusercontent.com/codeWangHub/JavaNote/base/Java_SE_Base/array/Java%E4%B8%80%E7%BB%B4%E6%95%B0%E7%BB%84%E5%86%85%E5%AD%98%E6%A8%A1%E5%9E%8B.jpg)

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

![二维数组内存模型](https://raw.githubusercontent.com/codeWangHub/JavaNote/base/Java_SE_Base/array/%E4%BA%8C%E7%BB%B4%E6%95%B0%E7%BB%84%E5%86%85%E5%AD%98%E6%A8%A1%E5%9E%8B.jpg)

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

### 6. 专门用于处理数组的工具类Arrays

```java.lang.Arrays``` 类是jdk提供的专门用来处理数组的工具类，学习数组我们有必要详细学习这个工具类。

例如我们想拷贝一个数组，```int[] arr1 = {1,2,3} ; ``` 对其拷贝：```int[] arr2 = arr1;``` 实质上是将arr2也指向了堆内存中的```{1,2,3}```。这种拷贝称为弱拷贝。有了工具类，我们就可以利用工具类来进行我们想要的值拷贝等诸多功能。

在JDK8中这个工具类为我们提供N多静态方法，我们知道数组一旦定义就不能改变他的大小，所以这些方法没有关于增加和删除的方法，只有改、查等，我们只学习常用的几种。

#### （1）改

-  fill : 填充

  | 方法                                       | 描述                                      |
  | ---------------------------------------- | --------------------------------------- |
  | ```void fill(T[] a,T val)```             | 将T型一维数组全部填充为val（Arrays中不是泛型实现的）。        |
  | ```void fill(T[] a,int fromIndex, int toIndex,T val)``` | 将T型一维数组从```fromIndex```到```toIndex```填充 |
  |                                          |                                         |

- copyOf : 拷贝

  | 方法                                       | 描述                                       |
  | ---------------------------------------- | ---------------------------------------- |
  | ```T[] copyOf(T[] a,int newLength)```    | 从数组```a```中拷贝```newLength```的数据组成新的数组返回。 |
  | ```T[] copyOf(U[] a,int length,Class newType)``` | 从数组```a``` 中拷贝```length```并转化为```newType```返回，如果不能转化则以null填充。这个新类型上限是T[] ,一般使用Number[]类。 |
  | ```T[] copyOfRange(T[] a,int from,int to)``` | 从数组```a```的```from```开始到```to```拷贝成新数组，并返回。 |
  | ```void setAll(T[] a,IntFunction generator)``` | 将数组```a```的值全部设置为由函数```generator```函数指定的返回值。具体看API。 |
  |                                          |                                          |

- toString : 转化为字符串

  | 方法                           | 描述                     |
  | ---------------------------- | ---------------------- |
  | ```String toString(T[] a)``` | 将数组```a```组成一个特定的字符串返回 |

  我们看一下toString的源码：

  ```java
      public static String toString(long[] a) {
          if (a == null)            /* 如果数组是空的，返回字符串‘null’ */
              return "null"; 
          int iMax = a.length - 1;    
          if (iMax == -1)           /* 如果数组中没有元素，返回串 '[]' */   
              return "[]";

          StringBuilder b = new StringBuilder();
          b.append('[');
          for (int i = 0; ; i++) {
              b.append(a[i]);
              if (i == iMax)
                  return b.append(']').toString();
              b.append(", ");            /* 返回串 [e1,e2,e3,...] */
          }
      }
  ```

- sort 排序

  - parallelSort 并行排序

    | 方法                                       | 描述                  |
    | ---------------------------------------- | ------------------- |
    | ```void parallelSort(T[] a)```           | 使用并行排序算法，对数组`a` 进行  |
    | ```void  parallelSort(T[] a,int from,int end)``` | 对一定范围的数据进行排序        |
    | ```void  parallelSort(T[] a,Comparator cmp)``` | 使用比较器，排序            |
    | ```void  parallelSort(T[] a,int from,int end Comparator cmp)``` | 对一定范围的数据，使用cmp比较器排序 |

  -  sort 使用默认方法排序

    我们看一下源码：

    ```java
        public static void sort(int[] a) {
            DualPivotQuicksort.sort(a, 0, a.length - 1, null, 0, 0);
        }
    ```

    - 原来sort方法，默认使用的是**快速排序** ，升序！
    - sort方法系列与上述parallelSort 类似。 

#### （2）关于比较器<重要>

​	学习中一直被一个问题所困惑，在JDK8的API中，Arrays类的方法都是支持传入一个```Comparator```比较器来辅助排序算法，但是为什么网上很多文章都说可以穿入```Comparable``` ???。这是怎么回事？看了所有的方法都是传入```Comparator```没有传入```Comparabel```的。。。

- 这两个接口有继承关系？

  ```java
  Comparator 源码：java.util.Comparator
  @FunctionalInterface  
  public interface Comparator<T> {
  	int compare(T o1, T o2);
      ...
  }

  --------------我----是----分----割----线---------------
    
  Comparable 源码：java.lang.Comparable
  public interface Comparable<T> {
       public int compareTo(T o);
  }
  ```

  这两个类没有任何关系！那到底是怎么一回事？看源码：

我们就看两个最简单的排序之间的区别：```sort(Object[] a)``` 和```sort(T[] a,Comparator<? super T[]> c)```

```java
public static void sort(Object[] a)
  	ComparableTimSort.sort(a, 0, a.length, null, 0, 0)
         binarySort(a, lo, lo + force, lo + runLen)
  			 for ( ; start < hi; start++) {
             	 Comparable pivot = (Comparable) a[start];
             	  while (left < right) {
                  	  if (pivot.compareTo(a[mid]) < 0)
                         ...
                  }
             }  	
```

看见没？如果你不穿入Comparator它也要用你的Comparable接口，所以你要么实现Comparable接口，要么传入一个Comparator的实例化对象。

我们在看看 ```sort(T[] a,Comparator<? super T[]> c)```的源码：

```java
public static <T> void sort(T[] a, Comparator<? super T> c) 
  		TimSort.sort(a, 0, a.length, c, null, 0, 0);
			binarySort(a, lo, lo + force, lo + runLen, c);
  				 binarySort(T[] a, int lo, int hi, int start,Comparator<? super T> c)                  {
                   for ( ; start < hi; start++) {
                      while (left < right) {
                          int mid = (left + right) >>> 1;
                          if (c.compare(pivot, a[mid]) < 0)
                              right = mid;
                          else
                              left = mid + 1;
                      }      
  				   }

```

看到没，最终使用传入的Comparator来进行比较！！！。

那么可以总结出，要传入比较器有两种方法：

1. 数组的元素类型Object要实现Comparable接口，覆写comparaTo方法。


2. 传入实现了Comparator的实例，只需用实现compara方法，这个借口只有这一个抽象方法。

两种方式的比较：

1. Comparator是在要比较的对象外部进行比较，Comparable是在要比较的对象的内部。
2. 用Comparable 简单, 只要实现Comparable 接口的对象直接就成为一个可以比较的对象,
   但是需要修改源代码。
3. 用Comparator 的好处是不需要修改源代码, 而是另外实现一个比较器, 当某个自定义
   的对象需要作比较的时候,把比较器和对象一起传递过去就可以比大小了, 并且在Comparator 里面用户可以自
   己实现复杂的可以通用的逻辑,使其可以匹配一些比较简单的对象,那样就可以节省很多重复劳动了。

比较方法的覆写方式：

1. compareTo(Object o);

   ```this   >     o     --->   return 1;```

   ```this   ==   o      --->  return 0;```

   ```this   <     o     --->  return -1;     ```

2. compare(Object o1,Object o2);

   ```o1   >     o2      --->   return 1;```

   ```o1   ==   o2       --->  return 0;```

   ```o1   <     o2      --->  return -1;     ```

 

#### （3） 查

- 搜索

  | 方法                                       | 描述                                       |
  | ---------------------------------------- | ---------------------------------------- |
  | ```int binarySearch(T[] a, T key)```     | 使用二分法在数组中搜索key，返回它的索引<数组需要时升序的>          |
  | ```int binarySearch(T[] a,int from, int to ,T key)``` | 使用二分法在数组的form到to区段搜索key，返回key的索引。<数组需要升序排列> |
  | 同样的上面两种方式，都支持传入Comparator                | 也支持使用Comparable                          |
  |                                          |                                          |

- 判断是否相等

  ```equals(T[] a,T[] b)```.平时我们使用Class类中的equals是默认使用```==```来比较的，实质上是比较两个引用是否指向同一块堆中的内存，或者说是不是指向同一个对象。这个equals比较的是数组的长度，每一个元素是不是一一对应相等。同样的如果数组元素是Object，这个方法不支持传入Comparator，所以元素对象要实现Comparable接口。

#### （4） 其他方法

​	用到的时候在加。











