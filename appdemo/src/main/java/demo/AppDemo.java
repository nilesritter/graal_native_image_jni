package demo;

import java.io.File;

public class AppDemo {

  public AppDemo(String path) {
    System.out.format("AppDemo: loading path %s..%n",path);
    System.load(path);
    System.out.format("AppDemo: success.%n",path);
  }

  public long public_add(long a, long b) {
        long isolateId = createIsolate();
	return add(isolateId, a,b);
  }

  public String public_evaluate(final String expression) {
      long isolateId = createIsolate();
      String result = evaluate(isolateId,expression);
      return result;
  }

  private native String evaluate(long isolateId,String expression);

  private native long add(long isolateId, long a,long b);

  private native long createIsolate();

  public static void main(String[] args) {
	final var app = new AppDemo(args[0]);
 	if ("ADD".equals(args[1])) {
	  System.out.format("Adding-Result...%n");
	  long result = app.public_add(3,4);
	  System.out.format("Add-Result=%d%n",result);
	} else {
	  System.out.format("String-Result...%n");
	  final String result = app.public_evaluate(args[1]);
	  System.out.format("Evaluate-Result=%d%n",result);
	}
  }
}

