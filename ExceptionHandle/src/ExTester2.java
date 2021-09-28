public class ExTester2 {

	public void show() throws Exception {
		Exception myException = null; // 引用当前异常，并且能保存差点丢失的异常
		try {
			Integer.parseInt("Hello");
		} catch (NumberFormatException e1) {
			myException = e1;
		} finally {
		
			try {
				int result = 2 / 0;
			} catch (ArithmeticException e2) {
				if (myException != null)				
					myException.addSuppressed(e2); // 保存差点被丢失的异常
				throw e2;
			}
			
			
		
		}
	}

	public static void main(String[] args) {
		ExTester2 t = new ExTester2();
		try {
			t.show();
		} catch (Exception ex) {
			System.out.println("当前异常信息：" + ex.getMessage());   //捕获到算术异常  /zero 

			Throwable[] exs = ex.getSuppressed(); // 获得差点丢失的异常
			for (Throwable e : exs)
				System.out.println("差点丢失的异常信息：" + e.getMessage());
		}
	}
}
