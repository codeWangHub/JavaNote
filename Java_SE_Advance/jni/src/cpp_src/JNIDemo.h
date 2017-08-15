/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class JNIDemo */

#ifndef _Included_JNIDemo
#define _Included_JNIDemo
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     JNIDemo
 * Method:    add
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_JNIDemo_add
  (JNIEnv *, jclass, jint, jint);

/*
 * Class:     JNIDemo
 * Method:    sum
 * Signature: ([I[I)[I
 */
JNIEXPORT jintArray JNICALL Java_JNIDemo_sum
  (JNIEnv *, jclass, jintArray, jintArray);

/*
 * Class:     JNIDemo
 * Method:    subString
 * Signature: (Ljava/lang/String;II)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_JNIDemo_subString
  (JNIEnv *, jclass, jstring, jint, jint);

/*
 * Class:     JNIDemo
 * Method:    changeStudentInfo
 * Signature: (LStudent;)LStudent;
 */
JNIEXPORT jobject JNICALL Java_JNIDemo_changeStudentInfo
  (JNIEnv *, jclass, jobject);

/*
 * Class:     JNIDemo
 * Method:    changeAll
 * Signature: ([LStudent;)[LStudent;
 */
JNIEXPORT jobjectArray JNICALL Java_JNIDemo_changeAll
  (JNIEnv *, jclass, jobjectArray);

#ifdef __cplusplus
}
#endif
#endif