package reflection.methodinovke;


/**
 *
 *
 * @author zhaoqw
 * @date 2024/7/16
 */
public class UserA {
    private String username;
    private String password;
    private int age;
    private  double  height;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "UserA{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
