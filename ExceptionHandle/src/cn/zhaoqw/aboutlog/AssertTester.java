
public class AssertTester {
  public int divide(int a,int b){
    assert b!=1 : "����������Ϊ1";  //ʹ�ö��ԣ���ʾ�������е����д��룬���߱���ǰ������
  	return a/b;
  }
  
  public static void main(String[] args) {
	  
	  /*		��������£��������ö��ԡ����򿪷������У����ö��Ի��ƣ����Դ���Ż���Ч
	   * 		java -ea ���ö���
	   * 		java -da �رն���
	   */
  	AssertTester t=new AssertTester();
   	System.out.println(t.divide(3,1));
  } 
}    



 
