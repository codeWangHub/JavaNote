# Java常用类——StringBuilder

String类是不可变类，任何对String的改变都会引发新的String对象的生成；

StringBuffer是可变类，任何对它所指代的字符串的改变都不会产生新的对象，线程安全的。

StringBuilder是可变类，线性不安全的，不支持并发操作，不适合多线程中使用，但其在单线程中的性能比StringBuffer高。

String 字符串常量 

StringBuffer 字符串变量（线程安全） 

StringBuilder 字符串变量（非线程安全） 

三者在执行速度方面的比较：

StringBuilder > StringBuffer > String 

1.如果要操作少量的数据用 = String 

2.单线程操作字符串缓冲区下操作大量数据 = StringBuilder 

3.多线程操作字符串缓冲区下操作大量数据 = StringBuffer

参考资料：

http://blog.csdn[.NET](http://lib.csdn.net/base/dotnet)/mad1989/article/details/26389541

http://mars914.iteye.com/blog/1439773