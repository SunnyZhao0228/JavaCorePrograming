package cn.zhaoqw.studygeneric.demo2;

public class FourTuple<A,B,C,D> extends ThreeTuple<A,B,C> {
    private D fourth;
    public FourTuple(A a, B b, C c,D d) {
        super(a, b, c);
        this.fourth = d;
    }

    @Override
    public String toString() {
        return "FourTuple [Fourth" + fourth +",third=" + third + ", first=" + first + ", second=" + second + "]";
    }

    public static void main(String[] args) {
        FourTuple<Integer,Integer,Integer,Integer> tuple = new FourTuple<>(1,2,3,4);
        System.out.println(tuple);

    }
}
