# Java 常用类——StringBuffer

[TOC]

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

我们使用StringBuffer就是为了干String干不了的事，例如增删操作等。

###  a . StringBuffer 增加操作

- ```public StringBuffer append(xxx)```

  append方法是将参数指定的字符串追加到当前StringBuffer后面，返回。参数可以使StringBuffer、String、基本数据类型等，此方法会自动转换。

  ​



