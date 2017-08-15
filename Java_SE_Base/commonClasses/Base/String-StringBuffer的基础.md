#  学习String、StringBuffer、StringBuilder的基础

我们看到String类的定义：

```java
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence 
```

StringBuffer的定义：

```java
 public final class StringBuffer
    extends AbstractStringBuilder
    implements java.io.Serializable, CharSequence
```

StringBuilder的定义：

```java
public final class StringBuilder
    extends AbstractStringBuilder
    implements java.io.Serializable, CharSequence
```

三者读实现了CharSequence接口，StringBuilder和StringBuffer两个类继承了AbstractStringBuilder类，所以我们要先掌握CharSquence接口然后掌握AbstractStringBuilder类再去分析String、StringBuffer、StringBuilder类。



## 1. CharSquence接口源码分析

```java
public interface CharSequence {
	int length();  // 获取长度的方法
   	char charAt(int index);  // 获取指定位置字符的方法
  	CharSequence subSequence(int start, int end); // 获取子串的方法
    public String toString();   // 要求必须覆写toString方法
    
  	// ...
}
```

## 2. AbstractStringBuilder类源码分析

```java
// 是一个抽象类，同时也实现了CharSquence接口
abstract class AbstractStringBuilder implements Appendable, CharSequence {
 	char[] value;  // 用来存放字符
    int count;     // 存放了多少个字符
  
  	AbstractStringBuilder()   // 默认构造方法
    {}
  
  	AbstractStringBuilder(int capacity) {   // 创建指定大小的数组
        value = new char[capacity];
    }
  	
  	public int length() {  // 实现接口方法，返回长度
        return count;
    }
    
    public int capacity() {      /* 返回“数组大小”（容量） */
        return value.length;
    }
  
    /* ==============================================   */	
   
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
  	
   private int hugeCapacity(int minCapacity) {
        /* 如果这个容量比int型的最大值还大，直接抛出异常 */
        if (Integer.MAX_VALUE - minCapacity < 0) { // overflow
            throw new OutOfMemoryError();
        }
     	
     	/* 返回maxOf（minCapacity，MaxArraySize） */
        return (minCapacity > MAX_ARRAY_SIZE)
            ? minCapacity : MAX_ARRAY_SIZE;
    } 
  
    private int newCapacity(int minCapacity) {
        // overflow-conscious code
        // 先尝试将数组大小变为原来大小的2倍 + 2 
        int newCapacity = (value.length << 1) + 2;
        
        /* 如果还是不够，直接使用传入的大小 */
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
      
      	/* 如果新容量有问题，调用hugeCapacity处理
           如果符合要求返回新容量
        */
        return (newCapacity <= 0 || MAX_ARRAY_SIZE - newCapacity < 0)
            ? hugeCapacity(minCapacity)
            : newCapacity;
    }
  

  
    /* 如果当前数组大小不够，就重新申请内存，拷贝数组 */
    private void ensureCapacityInternal(int minimumCapacity) {
        // overflow-conscious code
        if (minimumCapacity - value.length > 0) {
            value = Arrays.copyOf(value,
                    newCapacity(minimumCapacity));
        }
    }
  
    /* 确保数组最小尺寸 */
    public void ensureCapacity(int minimumCapacity) {
        if (minimumCapacity > 0)
            ensureCapacityInternal(minimumCapacity);
    }
  
    /* ==============================================   */
  
  
    /* 设置数组的大小 */   
    public void setLength(int newLength) {
        if (newLength < 0)
            throw new StringIndexOutOfBoundsException(newLength);
        ensureCapacityInternal(newLength);

        if (count < newLength) {
            Arrays.fill(value, count, newLength, '\0');
        }
        count = newLength;
    }
  
   /* 实现chatAt方法 */
   public char charAt(int index) {
        if ((index < 0) || (index >= count))
            throw new StringIndexOutOfBoundsException(index);
        return value[index];
    }   
  
   public void setCharAt(int index, char ch) {
        if ((index < 0) || (index >= count))
            throw new StringIndexOutOfBoundsException(index);
        value[index] = ch;
   }
  
   public AbstractStringBuilder append(Object obj) {
        return append(String.valueOf(obj));
        // 看上去像是递归，但是实质上里面这个append调用的不同参数的append，具体实现
     	// 不写了
   }
 	 
      @Override
    public abstract String toString();
    // 这才是重点，要求所有子类必须实现toString！
  
}
```



