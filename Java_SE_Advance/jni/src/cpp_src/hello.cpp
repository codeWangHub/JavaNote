
/*
 *  hello.cpp jvav JNI 测试用 动态库源文件
 *           by codewang 2017.7.17 @heu
*/

#include <stdio.h>
#include <stdlib.h>

/*
 *  对动态库的一些封装和对java JNI库函数的支持
*/
#include <jni.h>
#include "JNIDemo.h"


/* 对于java中的int add(int int) */
JNIEXPORT jint JNICALL Java_JNIDemo_add
  (JNIEnv *env, jclass cls, jint a, jint b)
  {
      /* 基本数据类型，直接使用 */
      return a+b;
  }

/* 对应java中的 int[] sum(int[] ,int[]) */
JNIEXPORT jintArray JNICALL Java_JNIDemo_sum
  (JNIEnv *env, jclass cls, jintArray a, jintArray b)
  {
      jint *arr_a,*arr_b;
      jint len = env->GetArrayLength(a);
      printf("arr len = %d\n",len);
      // 注意，这里不释放会造成内存泄漏。
      // 一般我们在java层提供一个释放native方法，在cpp中释放内存
      jint* ret = new jint[len];

      arr_a = env->GetIntArrayElements(a,JNI_FALSE);
      arr_b = env->GetIntArrayElements(b,JNI_FALSE);

      for(int i=0;i<len;i++) {
          ret[i] = arr_a[i] + arr_b[i];
      }

      /* 构造 jintArray返回给java */
      jintArray jintarray = env->NewIntArray(len);
      env->SetIntArrayRegion(jintarray,0,len,ret);

      return jintarray;
  }

/* 对应java中的 String subString(String ,int ,int) */
JNIEXPORT jstring JNICALL Java_JNIDemo_subString
  (JNIEnv *env, jclass cls, jstring str, jint start, jint len)
  {
      /* 从jstring中获取字符串 */
      char *src = (char*)env->GetStringUTFChars(str,JNI_FALSE);
      /* 分离子串 */
      char* subStr = new char[len+1];

      for(jint i=start,j=0;j<len;i++,j++) {
           subStr[j] = src[i];
      }
      subStr[len] = '\0';

      /* 封装为jstring 返回 */
      jstring retString = env->NewStringUTF(subStr);

      /* 释放 */
      env->ReleaseStringUTFChars(str,src);
      return retString;
  }

/* 对应java中的 Student changeStudentInfo(Student ) */
JNIEXPORT jobject JNICALL Java_JNIDemo_changeStudentInfo
  (JNIEnv *env, jclass cls, jobject student)
  {
      /* 先获得类 */
      jclass classStudent = env->GetObjectClass(student);

      /* 获得方法 */
      jmethodID getName = env->GetMethodID(classStudent,
                                           "getName",
                                           "()Ljava/lang/String;");

      /* 执行方法 */
      jstring name = (jstring)env->CallObjectMethod(student,getName,NULL);
      char *src = (char*)env->GetStringUTFChars(name,JNI_FALSE);
      printf("int cpp  ：  get name = %s\n",src);


      /* 返回新对象 */
      jobject newObject = env->AllocObject(classStudent);
      /* 可以通过调用方法设置属性，也可以直接设置 */

      jfieldID nameID = env->GetFieldID(classStudent,"name","Ljava/lang/String;");
      jfieldID ageID  = env->GetFieldID(classStudent,"age","I");

      jstring codewang = env->NewStringUTF("codewang");

      env->SetObjectField(newObject,nameID,codewang);
      env->SetIntField(newObject,ageID,22);

      return newObject;

  }


JNIEXPORT jobjectArray JNICALL Java_JNIDemo_changeAll
  (JNIEnv *, jclass, jobjectArray)
  {
      /* 和 intArray操作 一样，省略了。。。 */

  }
