package cn.zhaoqw.enumtest.menu;
//Course.java
public enum Course {
  APPETIZER(Food.Appetizer.class), //枚举类型的菜单
  MAINCOURSE(Food.MainCourse.class),  //MAINCOURSE    Food[] values   LASAGNE, BURRITO, PAD_THAI,  LENTILS, HUMMOUS, VINDALOO;
  DESSERT(Food.Dessert.class),  //
  COFFEE(Food.Coffee.class);    // 咖啡
	
  private Food[] values;	
  
  private Course(Class<? extends Food> kind) {
    values = kind.getEnumConstants();
  }
  
  public Food randomSelection() {
    return Enums.random(values);
  }
} 
