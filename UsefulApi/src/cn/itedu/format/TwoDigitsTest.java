package cn.itedu.format;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TwoDigitsTest {

	public static void main(String[] args) {
		double   f   =   111231.5585;
		BigDecimal   b   =   new BigDecimal(f);
		double   f1   =   b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(f1); //111231.56

		DecimalFormat df   =new DecimalFormat("#.00");
		String f2 = df.format(f);
		System.out.println(f2); //111231.56

		String f3 = String.format("%.2f",f);
		System.out.println(f3); //111231.56

		NumberFormat ddf1=NumberFormat.getInstance() ;
		System.out.println(ddf1.getClass().getName());
		ddf1.setMaximumFractionDigits(2);
		String f4= ddf1.format(f) ;
		System.out.println(f4);  //111,231.56

		double pi=3.1415927;//圆周率
		//取一位整数
		System.out.println(new DecimalFormat("0").format(pi));//3
		//取一位整数和两位小数
		System.out.println(new DecimalFormat("0.00").format(pi));//3.14
		//取两位整数和三位小数，整数不足部分以0填补。
		System.out.println(new DecimalFormat("00.000").format(pi));//03.142
		//取所有整数部分
		System.out.println(new DecimalFormat("#").format(pi));//3
		//以百分比方式计数，并取两位小数
		System.out.println(new DecimalFormat("#.##%").format(pi));//314.16%

		long c=299792458;//光速
		//显示为科学计数法，并取五位小数
		System.out.println(new DecimalFormat("#.#####E0").format(c));//2.99792E8
		//显示为两位整数的科学计数法，并取四位小数
		System.out.println(new DecimalFormat("00.####E0").format(c));//29.9792E7
		//每三位以逗号进行分隔。
		System.out.println(new DecimalFormat(",###").format(c));//299,792,458
		//将格式嵌入文本
		System.out.println(new DecimalFormat("光速大小为每秒,###米").format(c)); //光速大小为每秒299,792,458米
	}
}
