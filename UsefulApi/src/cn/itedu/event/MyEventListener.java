package cn.itedu.event;

public class MyEventListener implements ApplicationEventListener<MyEvent> {

    @Override
    public void onEvent(MyEvent event) {
        System.out.println("MyEventListener 处理事件 : " + event);
    }
}
