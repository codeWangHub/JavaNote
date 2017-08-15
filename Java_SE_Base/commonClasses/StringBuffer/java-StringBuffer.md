# Java 常用类——StringBuffer

- StringBuffer类和String一样，也用来代表字符串，只是由于StringBuffer的内部实现方式和String不同，所以StringBuffer在进行字符串处理时，**不生成新的对象**，在内存使用上要优于String类。

- 如果经常需要对一个字符串进行修改，例如插入、删除等操作，使用StringBuffer要更加适合一些。

- 最显著的区别在于，对于StringBuffer对象的每次修改都会改变对象自身，这点是和String类最大的区别。

- String对象和StringBuffer之间没有任何继承关系，所以不能直接转换，所以像```StringBuffer sb = "abc"; ```就是错误 的。！！！切记！

- 可以看到，StringBuffer是final的，所以不可以继承。

  ```java
   public final class StringBuffer
      extends AbstractStringBuilder
      implements java.io.Serializable, CharSequence
  {
  ```

  ​

## 1. StringBuffer的初始化

StringBuffer的初始化和String类似，只不过不能直接使用字符串(String匿名对象)来初始化。

- ```StringBuffer sb = new StringBuffer();```

  ```java
   public StringBuffer() {
       super(16);
   // 调用父类（AbstractStringBuilder）的构造函数：
   //   AbstractStringBuilder(int capacity) {
   //   	 value = new char[capacity];
   //   }  
   // 默认构造一个16个char大小的数组 
   }

  //类似的：
  public StringBuffer(int capacity) {
      super(capacity);
  }
  ```

  初始化一个内容为空的StringBuffer。

- ```StringBuffer sb = new StringBuffer(String);```

  ```java
   public StringBuffer(String str) {
        super(str.length() + 16);
        append(str);
   }
   /*  分析：
       AbstractStringBuilder append(Object obj) {
          return append(String.valueOf(obj));
       }
   	 --->
   	 public AbstractStringBuilder append(String str) {
          if (str == null)
              return appendNull();
          int len = str.length();
          ensureCapacityInternal(count + len);
          
          // 所以实质上是把String的字符复制了一份 
          str.getChars(0, len, value, count);
          count += len;
          return this;
      }
      --->
      public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)
      {
          if (srcBegin < 0)
              throw new StringIndexOutOfBoundsException(srcBegin);
          if ((srcEnd < 0) || (srcEnd > count))
              throw new StringIndexOutOfBoundsException(srcEnd);
          if (srcBegin > srcEnd)
              throw new StringIndexOutOfBoundsException("srcBegin > srcEnd");
          // 数组的拷贝    
          System.arraycopy(value, srcBegin, dst, dstBegin, srcEnd - srcBegin);
      }
   
   */

  ```

  初始化一个内容为```"abcdef"```的StringBuffer对象，**拷贝** String对象的字符到新的StringBuffer。

## 2. StringBuffer 和 String 对象的相互转化

- String 对象转化为 StringBuffer 对象

  ```StringBuffer sb = new StringBuffer("abc")```

- StringBuffer 对象转换为 String对象

  ```StringBuffer sb = new StringBuffer("abc"); ```

  ```String str = sb.toString();```

  ```java
      @Override
      public synchronized String toString() {
          if (toStringCache == null) {
              toStringCache = Arrays.copyOfRange(value, 0, count);
          }
          return new String(toStringCache, true);
          /* true 表示要拷贝 */
      }
  ```

## 3. StringBuffer 的常用方法

- ```StringBuffer append (xxx)```

  将xxx添加到当前对象字符串的后边，参数可以是String，StringBuffer，基本数据类型等。

  这里要注意返回值还是一个StringBuffer所以可以连续调用次方法，例如：

  ```java
  StringBuffer sb = new StringBuffer();
  sb.append("select * from ")
    .append("user")
    .append("where")
    .append("...");
  ```

- ```StringBuffer delete(start,end)```

  删除start到end之间的字符串。区间是**[start,end)**.

  ```java
  public synchronized StringBuffer delete(int start, int end) {
       toStringCache = null;
       // 不能直接调用delete，这样调用的就是本方法，造成无限递归
       super.delete(start, end);
       return this;
  }

  --->
    
  // 父类的delete  方法删除之后复制了一份，最终修改的还是本对象中集成父类的数组。
   public AbstractStringBuilder delete(int start, int end) {
          if (start < 0)
              throw new StringIndexOutOfBoundsException(start);
          if (end > count)
              end = count;
          if (start > end)
              throw new StringIndexOutOfBoundsException();
          int len = end - start;
          if (len > 0) {
              System.arraycopy(value, start+len, value, start, count-end);
              count -= len;
          }
          return this;
    }  
  ```

- ```deleteCahrAt(index)```

  删除指定位置上的字符。

- ```StringBuffer insert(offset,xxx)```

  在指定位置插入xxx

- ```StringBuffer reverse()```

  字符反转

- ```setCharAt(index,char)```

  在指定位置插入新字符

- ```void trimToSize()```

  次方法将内部数组的大小调整到和当前存储的字符串大小一致，减少空间的浪费

  ```java
      public void trimToSize() {
          if (count < value.length) {
              value = Arrays.copyOf(value, count);
          }
      }
  ```

- ```StringBuffer replace(start,end,str)```

  从start开始用str替换直到end

  ```java
   public AbstractStringBuilder replace(int start, int end, String str) {
          if (start < 0)
              throw new StringIndexOutOfBoundsException(start);
          if (start > count)
              throw new StringIndexOutOfBoundsException("start > length()");
          if (start > end)
              throw new StringIndexOutOfBoundsException("start > end");

          if (end > count)    // 不能超过当前字符串的长度
              end = count;
          int len = str.length();
          int newCount = count + len - (end - start);
          ensureCapacityInternal(newCount);

          System.arraycopy(value, end, value, start + len, count - end);
          str.getChars(value, start);
          count = newCount;
          return this;
      }
  ```

  ​

