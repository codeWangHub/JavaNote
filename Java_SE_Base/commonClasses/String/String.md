# Java常用类——String

```String```类位于```java.lang```包下。是最常用的类之一,也是一个非常特殊的类。

String类是java中专门用于管理字符串常量的类，它具有很高的特殊性。为什么这么说？

首先要明确两个概念：String类对象和字符串常量。对象时String这个类的实实在在的实例化对象，字符串常量是java虚拟机中的常量池中的字符串常量。两者不是一个东西。由String类对象管理字符串常量。我们先抛过这些东西，学习String类。

### 1. String类的源码分析

   String类有很多的方法，源码篇幅很大，我们只关心它内部的实现原理！字符数组。

```java
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence {
    /** The value is used for character storage. */
    private final char value[];

    /** Cache the hash code for the string */
    private int hash; // Default to 0

    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = -6849794470754667710L;
```

可以清楚的看到String类对字符串的管理使用的是字符数组```private final char alue[]``` ，使用final限定也突出了字符串常量的特性——不可变性。

### 2. 定义初始化String对象

由于字符串比较特殊，String对象的初始化有多种方式，如下：

- 直接赋值字符串常量

  ```String str = "hello world";```

- 传入字符串

  ```String str = new String("hello world");```

- 传入字符数组

  ```String str = new String(new char[]{'a',b,c,d});```

- 其他

为了理解其构造原理，我们写下面这个测试代码，分析它的字节码。

```java

public class StringDemo {
	public static void main(String[] args) {
		String str1 = "abc";
		String str2 = new String("abc");
		String str3 = new String("def");
		String str4 = str1;
		String str5 = str2;
		
	}
}
```

然后编译生成class文件，再使用```javap -v StringDemo.class``` 输出字节码信息：

```java
Constant pool:  /* 常量池 */
   #1 = Methodref          #7.#16         // java/lang/Object."<init>":()V
   #2 = String             #17            // abc
   #3 = Class              #18            // java/lang/String
   #4 = Methodref          #3.#19         // java/lang/String."<init>":(Ljava/lang/String;)V
   #5 = String             #20            // def
   #6 = Class              #21            // StringDemo
   #7 = Class              #22            // java/lang/Object
   #8 = Utf8               <init>
   #9 = Utf8               ()V
  #10 = Utf8               Code
  #11 = Utf8               LineNumberTable
  #12 = Utf8               main
  #13 = Utf8               ([Ljava/lang/String;)V
  #14 = Utf8               SourceFile
  #15 = Utf8               StringDemo.java
  #16 = NameAndType        #8:#9          // "<init>":()V
  #17 = Utf8               abc
  #18 = Utf8               java/lang/String
  #19 = NameAndType        #8:#23         // "<init>":(Ljava/lang/String;)V
  #20 = Utf8               def
  #21 = Utf8               StringDemo
  #22 = Utf8               java/lang/Object
  #23 = Utf8               (Ljava/lang/String;)V
{
  public StringDemo();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 2: 0

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=3, locals=6, args_size=1
         0: ldc           #2                  // String abc
         2: astore_1
         3: new           #3                  // class java/lang/String
         6: dup
         7: ldc           #2                  // String abc
         9: invokespecial #4                  // Method java/lang/String."<init>":(Ljava/lang/String;)V
        12: astore_2
        13: new           #3                  // class java/lang/String
        16: dup
        17: ldc           #5                  // String def
        19: invokespecial #4                  // Method java/lang/String."<init>":(Ljava/lang/String;)V
        22: astore_3
        23: aload_1
        24: astore        4
        26: aload_2
        27: astore        5
        29: return
}
SourceFile: "StringDemo.java"
```

我们可以看到常量池中有```abc```也有```def```这就表明了，使用```new String("xxx")```，这个```xxx```也在常量池中，那么内存模型就是：

![String构造内存模型](https://raw.githubusercontent.com/codeWangHub/JavaNote/base/Java_SE_Base/commonClasses/String/String%E7%9A%84%E6%9E%84%E9%80%A0%E6%A8%A1%E5%9E%8B.png)

总结：String类在new操作下，产生的对象在堆内存中，但是其value字符串依然是在常量池，这也就是java中提倡的字符串常量的唯一性！当构造时直接赋值字符串则这个对象直接存储在常量池。

从上面的字节码信息也可以看出：

- 直接赋值字符串，并没有调用String的构造函数，只是String类引用str1的一次赋值。

- 使用new关键字，调用的是String的```(Ljava/lang/String;)V```,其源码如下：

  ``````java
      public String(String original) {
          this.value = original.value;
          this.hash = original.hash;
      }
  ``````

  这也再次证明了我们上述的结论。是引用的拷贝，不是字符串的拷贝。

### 3. 再探String的构造

我们再做一个小实验来探讨String的构造：

```java
public class StringDemo {
	public static void main(String[] args) {
		String str1 = "123";
		String str2 = "456";
		
		String str3 = "1" + "2";
		String str4 = "1" + "23";
		
		String str5 = "1234";
		String str6 = "123456";
		String str7 = "111";
	}
}
```

其字节码为：

```shell
Constant pool:
   #1 = Methodref          #9.#18         // java/lang/Object."<init>":()V
   #2 = String             #19            // 123
   #3 = String             #20            // 456
   #4 = String             #21            // 12
   #5 = String             #22            // 1234
   #6 = String             #23            // 123456
   #7 = String             #24            // 111
```

可以看到：```String str3 = "1" + "2"``` 并没有产生```1```,```2``` 这两个常量，而是直接产生了```12``` 。最后的str7是为了测试同样的字符会不会是产生```"1"+"1"+"1"```这种情况。













### 参考文章

[深入理解java：String   ](http://www.cnblogs.com/ITtangtang/p/3976820.html)

[Java中几种常量池的区分](http://www.cnblogs.com/holos/p/6603379.html)

[java String的经典问题(new String() ,String )](http://blog.csdn.net/niguang09/article/details/7017578)

[Java的String详解](http://longpo.iteye.com/blog/2199493)

[在java中String类为什么要设计成final？](https://www.zhihu.com/question/31345592)

[Java中关于String类型的10个问题](http://www.importnew.com/12845.html)

[为什么Java中的密码优先使用 char[] 而不是String？](https://www.zhihu.com/question/36734157)

[java String hashCode() 设计的道理？](https://www.zhihu.com/question/24381016)

[Java 中new String("字面量") 中 "字面量" 是何时进入字符串常量池的?](https://www.zhihu.com/question/55994121)

