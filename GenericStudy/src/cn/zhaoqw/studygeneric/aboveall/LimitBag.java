/**
 *
 *
 * @author Administrator
 *
 * @param <T>
 * 		extends约束了泛型参数的上界
 * 		泛型参数T extends 类型；     表示T 必须是指定的类或者其子类；类型也可以是一个接口，表示T一定要实现这个接口
 */
public class LimitBag<T extends Number> {
	private T content;

	public LimitBag(T content) {
		this.content = content;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public static void main(String[] args) {
		LimitBag<Number> bag1 = new LimitBag<>(5);
		LimitBag<Integer> bag2 = new LimitBag<>(5);
	}
}
