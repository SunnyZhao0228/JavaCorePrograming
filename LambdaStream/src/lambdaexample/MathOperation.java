package lambdaexample;


/**
 * 四则运算接口
 */
@FunctionalInterface
public interface MathOperation {
    public Float operate(Integer a, Integer b);
}
