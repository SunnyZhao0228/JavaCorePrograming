
public class AssertTester {
  public int divide(int a,int b){
    assert b!=1 : "除数不允许为1";  //使用断言，表示下面运行的所有代码，所具备的前提条件
  	return a/b;
  }
  
  public static void main(String[] args) {
	  
	  /*		正常情况下，程序不启用断言。程序开发过程中，启用断言机制，断言代码才会生效
	   * 		java -ea 启用断言
	   * 		java -da 关闭断言
	   */
  	AssertTester t=new AssertTester();
   	System.out.println(t.divide(3,1));
  } 
}    



 
