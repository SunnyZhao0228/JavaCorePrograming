package reflection.aopdemo.basic;

public interface TryMapFunction<T, R> {
    R apply(T t) throws Throwable;
}