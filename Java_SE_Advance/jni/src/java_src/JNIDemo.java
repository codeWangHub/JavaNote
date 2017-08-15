
/* java jni 数据传递，综合测试 */

class Student {
  private String name;
  private int age;

  /* constructor */
  public Student (String name ,int age) {
      this.name = name;
      this.age  = age;
  }

  public Student() {

  }

  /* setter and getter */
  public void setName(String name ) { this.name = name; }
  public String getName() { return this.name; }
  public void setAge(int age) { this.age = age; }
  public int getAge() { return this.age; }

  /* show method */
  public String show() {
      return "[ " + this.name + "," +this.age + " ]";
  }

}


public class JNIDemo {

  static {
      System.loadLibrary("hello");
  }


  /* 1. 基本数据类型 , 计算两个数的和并返回 */
  public static native int add(int a,int b);

  /* 2. 基本数据类型数组，每一项对应相加，存入数组返回 */
  public static native int[] sum(int[] a, int[] b);

  /* 3. String -- char *  ，提取子串并返回 */
  public static native String subString(String src,int start,int len);

  /* 4. 对象传递 ， 修改对象属性，构造新的对象并返回  */
  public static native Student changeStudentInfo(Student stu);

  /* 5. 对象数组 ，修改多个 */
  public static native Student[] changeAll(Student[] studets);


  public static void sop(Object o) {
    System.out.println("[ in java ] : " + o );
  }



  public static void main(String[] args) {
        sop("add(5 , 6) = " + add(5,6));

        int[] a = {1,2,3};
        int[] b = {3,2,1};
        int[] c =  sum(a,b);
        sop("sum( [1,2,3] , [3,2,1] ) = " + c[0] + ","+ c[1]+ ","+c[2]);

        sop("subString(abcdef,2,3) = " +  subString("abcdef",2,3));


        Student liming = new Student("liming",25);
        sop("Student(liming,25) ---> after change()  ..."  );
        Student ret = changeStudentInfo(liming);
        sop("before change ..." + ret.show());

        Student[]  students = { new Student("zhangsan",20) ,
                                new Student("lisi",21),
                                new Student("wangwu",20)};


  }

}
