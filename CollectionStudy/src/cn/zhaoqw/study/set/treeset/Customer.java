package cn.zhaoqw.study.set.treeset;

public class Customer {
    private String name;
    private int age;
    
    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public int compareTo(Object o){
//      Customer other=(Customer)o;
//
//      //先按照name属性排序
//      if(this.name.compareTo(other.getName())>0)return 1;
//      if(this.name.compareTo(other.getName())<0)return -1;
//
//      //再按照age属性排序
//      if(this.age>other.getAge())return 1;
//      if(this.age<other.getAge())return -1;
//      return 0;
//   }
 
//   public boolean equals(Object o){
//	if(this==o)return true;			//指向的是同一个对象
//	
//  	if(! (o instanceof Customer)) return false;		//o不是Customer对象，  判断o是不是Customer对象 
//  	
//  	final Customer other=(Customer)o;
//
//	if(this.name.equals(other.getName()) && this.age==other.getAge())
//     	return true;
//  	else
//     	return false;
//    }
    
    
    

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())			//说明obj就是Customer对象
			return false;
	
		Customer other = (Customer) obj;
//		if (age != other.age)
//			return false;
//		
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
		return this.hashCode() == obj.hashCode();
	}
}


