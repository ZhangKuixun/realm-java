/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_realm_internal_Util */

#ifndef _Included_com_realm_internal_Util
#define _Included_com_realm_internal_Util
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_realm_internal_Util
 * Method:    nativeGetMemUsage
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_realm_internal_Util_nativeGetMemUsage
  (JNIEnv *, jclass);

/*
 * Class:     com_realm_internal_Util
 * Method:    nativeSetDebugLevel
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_realm_internal_Util_nativeSetDebugLevel
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_realm_internal_Util
 * Method:    nativeTestcase
 * Signature: (IZJ)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_realm_internal_Util_nativeTestcase
  (JNIEnv *, jclass, jint, jboolean, jlong);

#ifdef __cplusplus
}
#endif
#endif
