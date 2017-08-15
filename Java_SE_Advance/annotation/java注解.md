# Java 注解 Annotation

[TOC]

### 参考博客

[1. Java中注解是如何工作的？](http://www.importnew.com/10294.html)

[2. Java注解（Annotation）原理详解 <原理，写的很好,很强大>](http://blog.csdn.net/lylwo317/article/details/52163304)

## 1. Java 注解简介

用一个词就可以描述注解，那就是元数据，即一种描述数据的数据。所以，可以说注解就是源代码的元数据。

注解（Annotation）是java5.0版本之后引入的，成为了Java平台中非常重要的一部分。开发过程中，我们也时常在应用代码中会看到诸如@Override，@Deprecated这样的注解。

```java
@Override
public String toString() {
	 return "This is String Representation of current object.";
}
```

- 例如上面这段代码，注解 “@Override” 就会在编译时起作用，提示编译器这是一个覆写的方法，编译器就会检查其父类有没有次方法，和父类的方法是否一致，如果存在问题就会报警告。 

- 但是，即使我不使用@Override注解标记代码，程序也能够正常执行。

- 这么写有什么好处吗？   

  如果我不小心拼写错误，例如将toString()写成了toStrring(){double r}，而且我也没有使用@Override注解，那程序依然能编译运行。但运行结果会和我期望的大不相同。现在我们了解了什么是注解，并且使用注解有助于阅读程序。

## 2. 为什么要引入注解

- 使用Annotation之前(甚至在使用之后)，XML被广泛的应用于描述元数据。不知何时开始一些应用开发人员和架构师发现XML的维护越来越糟糕了。他们希望使用一些和代码紧耦合的东西，而不是像XML那样和代码是松耦合的(在某些情况下甚至是完全分离的)代码描述。如果你在Google中搜索“XML vs. annotations”，会看到许多关于这个问题的辩论。最有趣的是XML配置其实就是为了分离代码和配置而引入的。上述两种观点可能会让你很疑惑，两者观点似乎构成了一种循环，但各有利弊。下面我们通过一个例子来理解这两者的区别。
- 假如你想为应用设置很多的常量或参数，这种情况下，XML是一个很好的选择，因为它不会同特定的代码相连。如果你想把某个方法声明为服务，那么使用Annotation会更好一些，因为这种情况下需要注解和方法紧密耦合起来，开发人员也必须认识到这点
- 另一个很重要的因素是Annotation定义了一种标准的描述元数据的方式。在这之前，开发人员通常使用他们自己的方式定义元数据。例如，使用标记interfaces，注释，transient关键字等等。每个程序员按照自己的方式定义元数据，而不像Annotation这种标准的方式。
- 目前，许多框架将XML和Annotation两种方式结合使用，平衡两者之间的利弊。

## 3. Java 内置注解

在java.lang包下，JAVA提供了5个基本注解：   

- **@Override**

  限定重写父类方法。对于子类中被```@Override``` 修饰的方法，如果存在对应的被重写的父类方法，则正确；如果不存在，则报错。```@Override ```只能作用于方法，不能作用于其他程序元素。

- **@Deprecated**

  用于表示某个程序元素（类、方法等）已过时。如果使用了被```@Deprecated```修饰的类或方法等，编译器会发出警告。

- **@SuppressWarnings**

  抑制编译器警告。指示被```@SuppressWarnings```修饰的程序元素（以及该程序元素中的所有子元素，例如类以及该类中的方法.....）取消显示指定的编译器警告。例如，常见的```@SuppressWarnings（value="unchecked"）```

  ```SuppressWarnings```注解的常见参数值的简单说明：

  - ```deprecation```：使用了不赞成使用的类或方法时的警告(使用```@Deprecated```使得编译器产生的警告)；   
  - ```unchecked```：执行了未检查的转换时的警告，例如当使用集合时没有用泛型 (Generics) 来指定集合保存的类型; 关闭编译器警告   
  - ```fallthrough```：当``` Switch``` 程序块直接通往下一种情况而没有 Break 时的警告;   
  - ```path```：在类路径、源文件路径等中有不存在的路径时的警告;  
  - ```serial```：当在可序列化的类上缺少 ```serialVersionUID``` 定义时的警告;  
  - ```finally```：任何 ```finally``` 子句不能正常完成时的警告;
  - ```all```：关于以上所有情况的警告.

- **@SafeVarargs**

  ```SafeVarargs```是JDK 7 专门为抑制“堆污染”警告提供的。

- **@FunctionalIterface   （Java 8 新增的）**

  函数式接口。Java8规定：如果接口中只有一个抽象方法（可以包含多个默认方法或多个static方法），该接口称为函数式接口，```FunctionalInterface```就是用来指定某个接口必须是函数式接口，否则就会编译出错。

## 4. 自定义注解

- 以```@interface```关键字定义

- 注解需要标明注解的生命周期，注解的修饰目标等信息，这些信息是通过元注解实现。上面的语法不容易理解，下面通过例子来说明一下，这个例子就是自定的的Target注解的源码.

  ```java
  @Retention(value=RetentionPolicy.RUNTIME)
  @Target(value={ElementType.ANNOTATION_TYPE})
  public  @interface  Target
  {
  	 ElementType[] value();
  }
  ```

  源码分析如下：

  - 第一：元注解```@Retention```，成员```value```的值为```RetentionPolicy.RUNTIME```。   

  - 第二：元注解```@Target```，成员```value```是个数组，用```{}```形式赋值，值为```ElementType.ANNOTATION_TYPE   ```

  - 第三：成员名称为```value```，类型为```ElementType[]```

  - 另外，需要注意一下，如果成员名称是```value```，在赋值过程中可以简写。如果成员类型为数组，但是只赋值一个元素，则也可以简写。如上面的简写形式为：

    ```@Retention(RetentionPolicy.RUNTIME)```

    ```@Target(ElementType.ANNOTATION_TYPE) ```

- 注解参数的可支持数据类型

  - 1.所有基本数据类型（int,float,boolean,byte,double,char,long,short) 
  - 2.String类型  
  - 3.Class类型 
  - 4.enum类型
  - 5.Annotation类型
  - 6.以上所有类型的数组  

- Annotation类型里面的参数该怎么设定:

  - 第一,只能用public或默认(default)这两个访问权修饰.例如,String value();这里把方法设为defaul默认类型； 
  - 第二,参数成员只能用基本类型byte,short,char,int,long,float,double,boolean八种基本数据类型和String,Enum,Class,annotations等数据类型,以及这一些类型的数组.例如,String value();这里的参数成员就为String;     　
  - 第三,如果只有一个参数成员,最好把参数名称设为"value",后加小括号.例:下面的例子FruitName注解就只有一个参数成员。    

- J2SE5.0版本在 java.lang.annotation提供了四种元注解，**专门注解其他的注解**：

  | 元注解               | 功能               |
  | ----------------- | ---------------- |
  | ```@Documented``` | 注解是否将包含在JavaDoc中 |
  | ```@Retention```  | 什么时候使用该注解        |
  | ```@Target```     | 注解用于什么地方         |
  | ```@Inherited```  | 是否允许子类继承该注解      |

  - **@Documented** 是一个简单的标记注解，表示是否将注解3种：

    | 取值                            | 功能                                       |
    | ----------------------------- | ---------------------------------------- |
    | ```RetentionPolicy.SOURCE```  | 在编译阶段丢弃。这些注解在编译结束之后就不再有任何意义，所以它们不会写入字节码。@Override, @SuppressWarnings都属于这类注解。 |
    | ```RetentionPolicy.CLASS```   | 在类加载的时候丢弃。在字节码文件的处理中有用。**注解默认使用这种方式**。   |
    | ```RetentionPolicy.RUNTIME``` | 始终不会丢弃，运行期也保留该注解，因此可以**使用反射机制读取该注解的信息**。我们自定义的注解通常使用这种方式. |
    |                               |                                          |

  - **@Target** 表示该注解用于什么地方。如果不明确指出，该注解可以放在任何地方。以下是一些可用的参数。需要说明的是：属性的注解是兼容的，如果你想给7个属性都添加注解，仅仅排除一个属性，那么你需要在定义target包含所有的属性。多个target使用数组形式```Target({ElementType.TYPE,ElementType.METHOD})```

    | 取值                               | 功能              |
    | -------------------------------- | --------------- |
    | ```ElementType.TYPE```           | 用于描述类、接口或enum声明 |
    | ```ElementType.FIELD```          | 用于描述实例变量        |
    | ```ElementType.CONSTRUCTOR```    | 用于描述构造器         |
    | ```ElementType.LOCAL_VARIABLE``` | 用于描述局部变量        |
    | ```ElementType.METHOD```         | 用于描述方法          |
    | ```ElementType.PACKAGE```        | 用于描述包           |
    | ```ElementType.PARAMETER```      | 用于描述参数          |
    |                                  |                 |

  - **@Inherited**  定义该注释和子类的关系，是一个标记注解

## 5. 自定义注解实例

- 自定义注解

  ```java
  import java.lang.annotation.*;

  @Target(ElementType.FIELD)    /* 作用目标：FILED ----> 用于描述实例变量 */
  @Retention(RetentionPolicy.RUNTIME)  /* 生命周期:RUNTIME  ----> 一直有效 */
  @Documented    /* 将注解信息也写入api文档 */
  public @interface FruitName {   /* 声明注解名： FruitName */
  	String value() default "";  /* 注解属性，类型String 名称value 默认值为“” */
  }
  ```

- 使用这个注解

  ```java
  public class Friut {

  @FruitName(value="Aplle")  // 如果注解中的属性名为value那么可以省略，  		@FruitName("Apple")						
   private String name;
  }
  /* 这个注解放在这是没有任何作用，只是给Friut类的name属性添加了一些附加属性。那么注解有什么      用？ 
    答：当然有用，我们可以把注解拿出来使用，利用反射，在实例化这个类的对象时，可以使用这些信息来初始化对象。这不就和我们使用xml结合IOC一个道理吗，xml是吧附加信息放在了xml文件中，注解是把信息与具体元素绑定，放在一起。
  */
  ```

- 如何获得注解的数据

  注解处理器类库(```java.lang.reflect.AnnotatedElement```)。

  Java使用Annotation接口来代表程序元素前面的注解，该接口是所有Annotation类型的父接口。除此之外，Java在java.lang.reflect 包下新增了AnnotatedElement接口，该接口代表程序中可以接受注解的程序元素，该接口主要有如下几个实现类：

  - ```Class：类定义   ```
  - ```Constructor：构造器定义```
  - ```Field：累的成员变量定义   ```
  - ```Method：类的方法定义```
  - ```Package：类的包定义```

  **AnnotatedElement 接口是所有程序元素（Class、Method和Constructor）的父接口，所以程序通过反射获取了某个类的AnnotatedElement对象之后，程序就可以调用该对象的如下四个个方法来访问Annotation信息：** 

  - 方法1：```<T extends Annotation> T getAnnotation(Class<T> annotationClass)``` 
     返回该程序元素上存在的、指定类型的注解，如果该类型注解不存在，则返回null。

  - 方法2：```Annotation[] getAnnotations()```

     返回该程序元素上存在的所有注解。

  - 方法3：```boolean isAnnotationPresent(Class<?extends Annotation> annotationClass)```
     判断该程序元素上是否包含指定类型的注解，存在则返回true，否则返回false.

  - 方法4：```Annotation[] getDeclaredAnnotations()```

     返回直接存在于此元素上的所有注释。与此接口中的其他方法不同，该方法将忽略继承的注释。（如果没有注释直接存在于此元素上，则返回长度为零的一个数组。）该方法的调用者可以随意修改返回的数组；这不会对其他调用者返回的数组产生任何影响。

## 6. 获取类的注解信息—— 测试代码

#### 1. 自定义注解

定义注解，必须单独定义文件，应为这相当于定义类，还是public的。文件名和注解名相同

```java
// SomeInfo.java

package annotationDemo;
		
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
		
/**
 *  @author codewang
 *	自定义注解  someInfo
 */
		
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SomeInfo {			
	public String name() default "codewang";	
	public int  age() default 20;
	public String info() default "hello world!";
} 
```

#### 2. 使用注解

```java
// AnnotationDemo.java

package annotationDemo;

import java.lang.annotation.*;

/* 添加一些附加信息，使用注解 */
@SomeInfo(name="liming",age=25)
class Student {
}


public class AnnotationDemo {
  	public static void main(String[] args) {
      	/* 获取类的注解，利用反射 */
        /* 因为是类的注解，所以从类获得注解，如果是属性、方法注解则从类、方法获得注解 */
      	SomeInfo info  =  // 获得Student类上面SomeInfo注解
            (SomeInfo)Student.class.getAnnotation(SomeInfo.class);  
      
       System.out.println(info.age());
       System.out.println(info.name());
       System.out.println(info.info());	      
    }
}

运行结果：
25
liming
hello world！
```

## 7. 获取方法的注解信息—— 测试代码 

#### 1. 自定义注解

```java
// SomeInfo.java

package annotationDemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Repeatable;
/**
 * @author codewang
 *	自定义注解  someInfo
 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SomeInfo {
  public String name() default "codewang";	
  public int  age() default 20;
  public String info() default "hello world!";
}
```

#### 2. 使用注解

```java
// AnnotationDemo.java

package annotationDemo;

import java.lang.annotation.*;

/* 添加一些附加信息，使用注解 */
@SomeInfo(name="Student" , age=24 , info="this is a Class")
class Student {
  
  @SomeInfo(name="sayMethod" , age = 25 , info = "this is a Method")
  public void sayMethod() {};
}


public class AnnotationDemo {
  	public static void main(String[] args) throws Exception {

    /* 获取类的注解，利用反射 */
    /* 因为是类的注解，所以从类获得注解，如果是属性、方法注解则从类、方法获得注解 */
    SomeInfo info =  // 获得Student类上面SomeInfo注解
        (SomeInfo)Student.class.getAnnotation(SomeInfo.class);  
    System.out.println(info.age());
    System.out.println(info.name());
    System.out.println(info.info());	

    /* 获取方法的注解 */
    info = (SomeInfo)Student.class.getMethod("sayMethod").getAnnotation(SomeInfo.class);  
    System.out.println(info.age());
    System.out.println(info.name());
    System.out.println(info.info());		
  }
}

运行结果：
  24
  Student
  this is a Class
  25
  sayMethod
  this is a Method
```





