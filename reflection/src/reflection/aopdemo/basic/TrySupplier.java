package reflection.aopdemo.basic;

public interface TrySupplier<T>{
    T get() throws Throwable;
}
