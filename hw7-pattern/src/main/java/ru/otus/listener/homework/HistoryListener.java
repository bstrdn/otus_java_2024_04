package ru.otus.listener.homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import ru.otus.listener.Listener;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;

public class HistoryListener implements Listener, HistoryReader {

  Map<Long, Message> history = new HashMap<>();

  @Override
  public void onUpdated(Message msg) {
    ObjectForMessage historyField13 = new ObjectForMessage();
    ObjectForMessage field13 = msg.getField13();
    if (field13 != null && field13.getData() != null) {
      List<String> field13Data = new ArrayList<>(field13.getData());
      historyField13.setData(field13Data);
    }
    history.put(msg.getId(),
        new Message.Builder(msg.getId()).field1(msg.getField1()).field2(msg.getField2())
            .field3(msg.getField3())
            .field4(msg.getField4()).field5(msg.getField5()).field6(msg.getField6())
            .field7(msg.getField7())
            .field8(msg.getField8()).field9(msg.getField9()).field10(msg.getField10())
            .field11(msg.getField11())
            .field12(msg.getField12())
            .field13(historyField13).build());
  }

  @Override
  public Optional<Message> findMessageById(long id) {
    return Optional.of(history.get(id));
  }
}
