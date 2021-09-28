import java.util.List;
import java.util.ArrayList;
import java.io.PrintStream;
import java.io.PrintWriter;

public class BaseException extends Exception{
	//多样化异常处理，将同一业务逻辑中出现的各种异常进行打包处理
  protected Throwable cause = null;
  private List<Throwable> exceptions = new ArrayList<Throwable>();

  public BaseException(){}

  public BaseException(String msg){super(msg);}

  public BaseException( Throwable cause ) {
    this.cause = cause;
  }

  public BaseException(String msg,Throwable cause){
    super(msg);
    this.cause = cause;
  }

  public List getExceptions() {
    return exceptions;
  }

  public void addException( BaseException ex ){
    exceptions.add( ex );
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

    if ( getCause() != null ) {
      getCause().printStackTrace(writer);
    }
    writer.flush();
  }
}



 
