package annotationDemo;

/* ���һЩ������Ϣ��ʹ��ע�� */
@SomeInfo(name="Student" , age=24 , info="this is a Class")
class Student {
	 
	@SomeInfo(name="sayMethod" , age = 25 , info = "this is a Method")
	public void sayMethod() {};
}


public class AnnotationDemo {
	public static void main(String[] args) throws Exception {
			
		/* ��ȡ���ע�⣬���÷��� */
		/* ��Ϊ�����ע�⣬���Դ�����ע�⣬��������ԡ�����ע������ࡢ�������ע�� */
		System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		SomeInfo info = (SomeInfo)Student.class.getAnnotation(SomeInfo.class);  // ���Student������SomeInfoע��
		System.out.println(info.age());
		System.out.println(info.name());
		System.out.println(info.info());	
		
		/* ��ȡ������ע�� */
		info = (SomeInfo)Student.class.getMethod("sayMethod").getAnnotation(SomeInfo.class);  
		System.out.println(info.age());
		System.out.println(info.name());
		System.out.println(info.info());		
	}
}


