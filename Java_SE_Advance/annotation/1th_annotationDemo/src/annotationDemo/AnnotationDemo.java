package annotationDemo;

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
		System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		SomeInfo info = (SomeInfo)Student.class.getAnnotation(SomeInfo.class);  // 获得Student类上面SomeInfo注解
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


