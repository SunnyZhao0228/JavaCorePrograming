package cn.zhaoqw.studygeneric.aboveall;

public class OldBag{
    private Object content;

    public OldBag(Object content) {
        this.content = content;
    }

    public Object get() {
        return this.content;
    }

    public void set(Object content) {
        this.content = content;
    }

    public static void main(String[] args){
        OldBag bag=new OldBag("mybook");
        Object content=(Integer)bag.get(); //运行时抛出ClassCastException
    }
}
