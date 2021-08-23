package demo;

// This java class is intended to be built by GraalVM into a native image
// shared library, whose static "evaluate" methods are exposed
// through a C API , accessible from JAVA via JNI.

import java.io.*;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.word.Pointer;


public final class NativeImpl{

    @CEntryPoint(name="Java_demo_AppDemo_evaluate")
    public static CCharPointer evaluate(
		Pointer jniEnv, Pointer clazz,
		@CEntryPoint.IsolateThreadContext long isolateId,
		CCharPointer inputPtr)
    {
		// C-to-Java
		final String inputString = CTypeConversion.toJavaString(inputPtr);

		final String resultString  =  evaluate_internal(inputString);

		// Java-to-C
		try(final CTypeConversion.CCharPointerHolder holder = CTypeConversion.toCString(resultString)) {
			return holder.get();
		}
    }

    @CEntryPoint(name="Java_demo_AppDemo_add")
    public static long add(
		Pointer jniEnv, Pointer clazz,
		@CEntryPoint.IsolateThreadContext long isolateId,
		long a, long b )
    {
	return add_internal(a,b);
    }

    @CEntryPoint(name="Java_demo_AppDemo_createIsolate" , builtin=CEntryPoint.Builtin.CREATE_ISOLATE)
    public static native IsolateThread createIsolate();

    public static String evaluate_internal(String inputString) {
	return "Foo-"+inputString;
    }

    public static long add_internal(long a, long b) {
      return a + b;	
    }

    // Stub main entry point for testing and to keep Graal Native happy building shared libs
    public static void main( String[] args )
    {
        if (args.length < 1) {
           System.out.format("Usage: java demo.GraalDemo%n");
           return;
        }
        System.out.format("1 + 2 = %d%n",1 + 2);
    }

}
