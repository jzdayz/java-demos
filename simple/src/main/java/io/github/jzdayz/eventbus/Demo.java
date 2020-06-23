package io.github.jzdayz.eventbus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Demo {

  public static void main(String[] args) {
    EventBus.getDefault().register(new Demo());
    EventBus.getDefault().post(new MessageEvent("1111"));
  }

  // This method will be called when a MessageEvent is posted (in the UI thread for Toast)
  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onMessageEvent(MessageEvent event) {
    System.out.println(event);
  }

}
