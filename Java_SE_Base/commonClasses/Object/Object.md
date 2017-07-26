# Java 常用类—— Object

[TOC]

Java把现实中的任何事物都当做一个对象(Object), Java是面向对象的，就是Object Orentied 简称OO 。此处的Object在Java中被定义为一个顶级父类，它是任何类父类，也是所有类中唯一一个没有父类的类，我们可以显示的继承它，也可以隐式继承。

```java
public class Dog extends Object{
}
与
public class Dog{
}
```

这两个类完全等价，Java为什么要设计这么一个类，让所有类去继承它，因为它封装了所有类最最最常用的方法，比如toString，hashCode，equals等。

### 分析Object类的源码

```java
package java.lang;
public class Object {
  /* 哇！ 居然有这种操作，要理解这些需要jvm虚拟机知识的支撑。这里先不扯了 */
  private static native void registerNatives();
  static {
        registerNatives();
  }
  
  /* 获得Class的对象，也是本地方法，这是必然的。数组和Class都是指令级别的类 */
  public final native Class<?> getClass();
  
  /* 获得hashCode也是本地方法，肯定的！哈希值实在运行时由JVM计算出来的，关于hashCode
   * 在学习jvm是要好好学一下。
   * 1. 如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用
   *    hashCode 方法都必须生成相同的整数结果。	
   * 2. 如果根据 equals(java.lang.Object) 方法，两个对象不相等那么对这两个对象中的任
   *    一对象上调用 hashCode 方法“不要求”一定生成不同的整数结果。
   * 3. 但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。   
   */
  public native int hashCode();
  
  /* 默认的判断相等的方法，就是使用==判断
   * 这也就是是为什么说，判断两个引用时默认判断的是这
   * 这两个应用是不是指向同一个实例。
   */
  public boolean equals(Object obj) {
        return (this == obj);
  }
  
  /*  用于对象的复制，本地方法。
   *  clone函数返回的是一个引用，指向的是新的clone出来的对象，此对象与原对象分别占用不同的堆空间。
   *  以前这个函数不是本地方法，需要实现Cloneable接口。覆写时
   *  还要改为public的权限。
   */
  protected native Object clone() throws CloneNotSupportedException;
  
  
  /*  老生常谈的toString。
   *  默认情况下是 “类型@哈希值<hex>”。
   */
  public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
  }
  
  /*   下面三个方法，使用在多线程编程，Java的多线程依赖于底层的实现，所以
   *   必然是native方法。
   *
   *    notify    方法用于唤醒在当前这个对象上等待的单个线程<最需要被唤醒的>。
   *    notifyAll 方法用于唤醒在当前这个对象上等待的所有线程。
   *    wait(long)方法，你就在我这个对象上等吧，直到时间到了或者有人调用了我的notify。
   *              时间单位 ms   
   */
  public final native void notify();  
  public final native void notifyAll();
  public final native void wait(long timeout) throws InterruptedException;
  
  /* nanos是额外的时间纳秒，范围0~999999 ，但是只要nanos符合这个范围，timeout只+1。
   * 没搞懂有什么鸟用。 
   */
  public final void wait(long timeout, int nanos) throws InterruptedException {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (nanos < 0 || nanos > 999999) {
            throw new IllegalArgumentException(
                                "nanosecond timeout value out of range");
        }

        if (nanos > 0) {
            timeout++;
        }

        wait(timeout);
   }
  
  /* 死等，没有notify不醒。 */
  public final void wait() throws InterruptedException {
        wait(0);
  }
  
  /* 对象被垃圾回收机制回收释放内存是调用 */
  protected void finalize() throws Throwable { }
}
```

### 覆写equals方法和hashCode方法

在覆写equals方法时应该遵循以下几点：

- 自反性：任何非空引用x ： x.equals(x)  应该返回true。
- 对称性：任何非空引用x,y ：x.equals(y) 和 y.equals(x) 返回结果应该一致。 
- 传递性：任意非空引用x,y ：x.equals(y)  <==> y.equals(z) <==> z.equals(x)。
- 一致性：任意非空引用x,y ：x.equals(y),多次执行，结果应该一致。
- 约定    ：任意非空引用x ：x.equals(null) ，应该返回false。
- 覆写equals方法时，应该同时覆写hashCode方法，如果两个对象被equals()方法判断为相等，那么它们就应该拥有同样的hash code。















