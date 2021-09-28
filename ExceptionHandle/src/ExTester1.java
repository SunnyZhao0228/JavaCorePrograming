public class ExTester1 {

	public void show() throws Exception {
		try {
			Integer.parseInt("Hello");
		} catch (NumberFormatException e1) {
			throw new Exception("无效的数字", e1);
			
		} 
		finally {
			try {
				int result = 2 / 0;
			} catch (ArithmeticException e2) {
				throw new Exception("数学运算出错", e2);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ExTester1 t = new ExTester1();
		t.show();
	}
}
