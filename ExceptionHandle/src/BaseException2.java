import java.io.*;

public class BaseException2 extends Exception {
	//异常转译
	protected Throwable cause = null;

	public BaseException2() {
	}

	public BaseException2(String msg) {
		super(msg);
	}

	public BaseException2(Throwable cause) {
		this.cause = cause;
	}

	public BaseException2(String msg, Throwable cause) {
		super(msg);
		this.cause = cause;
	}

	public Throwable initCause(Throwable cause) {
		this.cause = cause;
		return this;
	}

	public Throwable getCause() {
		return cause;
	}

	public void printStackTrace() {
		printStackTrace(System.err);
	}

	public void printStackTrace(PrintStream outStream) {
		printStackTrace(new PrintWriter(outStream));
	}

	public void printStackTrace(PrintWriter writer) {
		super.printStackTrace(writer);

		if (getCause() != null) {
			getCause().printStackTrace(writer);
		}
		writer.flush();
	}
}
