

public class StringDemo {
	public static void main(String[] args) {
		test7();	
	}
	

	
	
	private static void test7(){
		String str = "0123456789";
		
		sop(str.substring(2,6));
		sop(str.substring(5));
	
	}
	
	private static void test6(){
		String str = "446042453@qq.com";
		
		sop("replace('4','s') --> " + str.replace('4' , 's'));
	
	}
	
	private static void test5(){
		String str1 = "abc";    
		String str2 = new String("abc");   
		
		sop("str1 == str2 --> " + (str1 == str2));
	
		String str3 = new String("abc").intern();
		
		sop("str1 == str3 --> " + (str1 == str3));	
		
	}
	
	
	private static void test4(){
		String str = "aabbccdd";
		
		sop("str.indexOf('c') = " + str.indexOf('c'));
		sop("str.indexOf(\"bbc\") = "  + str.indexOf("bbc"));
		
	}
	
	private static void test3(){
		String str1 = "hello";
		String str2 = "world";
		
		sop("str1.concat(str2) = " + str1.concat(str2));
		
	}

	private static void test2(){
		String str1 = "abc";
		String str2 = "abd";
		String str3 = "abcd";
		
		sop("str1.compareTo(str2) = " + str1.compareTo(str2));
		sop("str1.compareTo(str3) = " + str1.compareTo(str3));
		sop("str2.compareTo(str3) = " + str2.compareTo(str3));
	}
	
	private static void test1(){
		String str = "hello world.txt";
		
		sop("charAt(0) = " + str.charAt(0));
		sop("charAt(2) = " + str.charAt(2));
		sop("charAt(100) = " + str.charAt(100));
	}
	
	private static void sop(Object o) {
		System.out.println(o);
	}
}





















