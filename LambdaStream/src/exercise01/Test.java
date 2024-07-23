package exercise01;

import java.util.Comparator;
import java.util.List;

public class Test {

	

		public static void main(String[] args) {
			List<Country> list = new CountryDao().getList();
			
			System.out.println("-----------------------------------所有国家列表-----------------------------------");	
			list.forEach(System.out::println);
			
			
			System.out.println("-----------------------------------按照人口从少到多的顺序输出所有亚洲国家列表-----------------------------------");	
			list.stream().filter(country -> "亚洲".equals(country.getContinent()))
					.sorted(Comparator.comparingInt(Country::getPopulation)).forEach(System.out::println);
			
			
			System.out.println("-----------------------------------统计南美洲国家数量-----------------------------------");
			long count = list.stream().filter(country -> "南美洲".equals(country.getContinent()))
					.count();
			System.out.println("南美洲国家数量:" + count);


			System.out.println("-----------------------------------输出所有欧洲国家的名称及其首都（首府）名称-----------------------------------");	
			list.stream().filter(country -> "欧洲".equals(country.getContinent())).forEach(country -> {
				System.out.println("Capacity: " + country.getCapital() + ", Name: " + country.getName());
			});
			
			System.out.println("-----------------------------------输出所有大洋洲法属国家列表-----------------------------------");	
			
			
			
			System.out.println("-----------------------------------输出陆地面积前10名的国家名称及人口数量-----------------------------------");	
			
			
			
			System.out.println("-----------------------------------输出人口密度前10名的国家名称、人口数量、面积（选做）-----------------------------------");	
			
			
			
			System.out.println("-----------------------------------自己想一个有创意的输出？（认真点，为师眼睛都快瞎了）-----------------------------------");	
			
			
		}



}
