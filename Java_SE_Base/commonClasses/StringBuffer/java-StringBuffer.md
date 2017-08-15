# Java 常用类——StringBuffer

- StringBuffer类和String一样，也用来代表字符串，只是由于StringBuffer的内部实现方式和String不同，所以StringBuffer在进行字符串处理时，**不生成新的对象**，在内存使用上要优于String类。
- 如果经常需要对一个字符串进行修改，例如插入、删除等操作，使用StringBuffer要更加适合一些。
- 最显著的区别在于，对于StringBuffer对象的每次修改都会改变对象自身，这点是和String类最大的区别。
- String对象和StringBuffer之间没有任何继承关系，所以不能直接转换，所以像```StringBuffer sb = "abc"; ```就是错误 的。！！！切记！

## 1. StringBuffer的初始化

StringBuffer的初始化和String类似，只不过不能直接使用字符串(String匿名对象)来初始化。

- ```StringBuffer sb = new StringBuffer();```

  初始化一个内容为空的StringBuffer。

- ```StringBuffer sb = new StringBuffer("abcdef");```

  初始化一个内容为```"abcdef"```的StringBuffer对象。

## 2. StringBuffer 和 String 对象的相互转化

- String 对象转化为 StringBuffer 对象

  ```StringBuffer sb = new StringBuffer("abc")```

- StringBuffer 对象转换为 String对象

  ```StringBuffer sb = new StringBuffer("abc"); ```

  ```String str = sb.toString();```

## 3. StringBuffer 的常用方法

### a . ```StringBuffer append (xxx)```

将xxx添加到当前对象字符串的后边，参数可以是String，StringBuffer，基本数据类型等。



