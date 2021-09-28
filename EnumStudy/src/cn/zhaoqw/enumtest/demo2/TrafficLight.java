package cn.zhaoqw.enumtest.demo2;

import java.util.TimerTask;

enum Signal {
  GREEN(35), YELLOW(5), RED(40);

  private final int delayTime;


  Signal(int delayTime) {
    this.delayTime = delayTime;
  }


  public int getDelayTime() {
    return this.delayTime;
  }
}


public class TrafficLight {
  Signal color = Signal.RED;
  public void change() throws InterruptedException {
    Thread.sleep(color.getDelayTime() * 100);
    switch(color) {
      // Note that you don't have to say Signal.RED
      // in the case statement:
      case RED:
        color = Signal.GREEN;
        break;
      case GREEN:
        color = Signal.YELLOW;
        break;
      case YELLOW:
        color = Signal.RED;
        break;
    }
  }
  public String toString() {
    return "The traffic light is " + color;
  }
  public static void main(String[] args) throws InterruptedException {
    TrafficLight t = new TrafficLight();
    for(int i = 0; i < 7; i++) {
      System.out.println(t);
      t.change();
    }
  }
} 
