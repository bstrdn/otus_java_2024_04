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

  private final Map<Long, Message> history = new HashMap<>();

  @Override
  public void onUpdated(Message msg) {
    history.put(msg.getId(), cloneMessage(msg));
  }

  private ObjectForMessage copyObjectForMessage(ObjectForMessage objectForMessage) {
    ObjectForMessage objectForMessageCopy = new ObjectForMessage();
    if (objectForMessage != null && objectForMessage.getData() != null) {
      List<String> field13Data = new ArrayList<>(objectForMessage.getData());
      objectForMessageCopy.setData(field13Data);
    }
    return objectForMessageCopy;
  }

  @Override
  public Optional<Message> findMessageById(long id) {
    return Optional.of(history.get(id));
  }

  private Message cloneMessage(Message msg) {
    ObjectForMessage objectForMessageCopy = copyObjectForMessage(msg.getField13());
    return new Message.Builder(msg.getId()).field1(msg.getField1()).field2(msg.getField2())
        .field3(msg.getField3())
        .field4(msg.getField4()).field5(msg.getField5()).field6(msg.getField6())
        .field7(msg.getField7())
        .field8(msg.getField8()).field9(msg.getField9()).field10(msg.getField10())
        .field11(msg.getField11())
        .field12(msg.getField12())
        .field13(objectForMessageCopy).build();
  }
}
