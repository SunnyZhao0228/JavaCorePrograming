package threadlocal;

/**
 * 描述：     演示ThreadLocal用法2：避免传递参数的麻烦
 */
public class ThreadLocalNormalUsage06 {

    public static void main(String[] args) {

        new Service1().process("niu die wei");

    }
}

class Service1 {

    public void process(String name) {
        User user = new User(name);
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}

class Service2 {

    public void process() {
        User user = UserContextHolder.holder.get();
        ThreadSafeFormatter.dateFormatThreadLocal.get();
        System.out.println("Service2 get user: " + user.name);
        new Service3().process();
    }
}

class Service3 {

    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service3 get user: " + user.name);


        UserContextHolder.holder.remove();
    }
}

class UserContextHolder {
    public static ThreadLocal<User> holder = new ThreadLocal<>();

}

class User {
    String name;
    public User(String name) {
        this.name = name;
    }
}