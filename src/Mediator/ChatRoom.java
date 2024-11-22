package Mediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// interface mediator
interface ChatMediator {
    void sendMsg(String msg, User sender);
    void addUserToRoom(String roomName, User user);
    void removeUserFromRoom(String roomName, User user);
}

// concrete mediator
class ChatRoomMediator implements ChatMediator {
    private Map<String, Room> rooms = new HashMap<>();

    public void createRoom(String name) {
        rooms.put(name, new Room(name));
    }

    @Override
    public void sendMsg(String msg, User sender) {
        Room room = rooms.get(sender.getRoomName());
        if (room != null) {
            room.broadcastMsg(msg, sender);
        }
    }

    @Override
    public void addUserToRoom(String roomName, User user) {
        Room room = rooms.get(roomName);
        if (room != null) {
            room.addUser(user);
            user.setRoomName(roomName);
        }
    }

    @Override
    public void removeUserFromRoom(String roomName, User user) {
        Room room = rooms.get(roomName);
        if (room != null) {
            room.removeUser(user);
            user.setRoomName(null);
        }
    }
}

class Room {
    private String name;
    private List<User> users = new ArrayList<>();

    public Room(String name) {
        this.name = name;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void broadcastMsg(String msg, User sender) {
        for (User user : users) {
            if (user.equals(sender) && !user.isDND()) {
                user.receiveMsg(msg);
            }
        }
    }
}

abstract class User {
    protected String name;
    protected boolean dnd = false;
    protected ChatMediator mediator;
    protected String roomName;

    public User(String name, ChatMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public void joinRoom(String roomName) {
        mediator.addUserToRoom(roomName, this);
    }

    public void leaveRoom(String roomName) {
        mediator.removeUserFromRoom(roomName, this);
    }

    public void sendMsg(String msg) {
        mediator.sendMsg(msg, this);
    }

    public void setDND(boolean dnd) {
        this.dnd = dnd;
    }

    public boolean isDND() {
        return dnd;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public abstract void receiveMsg(String msg);
}

// concrete user types
class TextUser extends User {
    public TextUser(String name, ChatMediator mediator) {
        super(name, mediator);
    }

    @Override
    public void receiveMsg(String msg) {
        System.out.println(name + " (Text User) received message: " + msg);
    }
}

class VoiceUser extends User {
    public VoiceUser(String name, ChatMediator mediator) {
        super(name, mediator);
    }

    @Override
    public void receiveMsg(String msg) {
        System.out.println(name + " (Voice user) received message: " + msg);
    }
}

public class ChatRoom {
    public static void main(String[] args) {
        ChatRoomMediator mediator = new ChatRoomMediator();
        mediator.createRoom("General");

        User user1 = new TextUser("Alice", mediator);
        User user2 = new VoiceUser("Bob", mediator);

        user1.joinRoom("General");
        user2.joinRoom("General");

        user1.sendMsg("Hello everyone");
        user2.sendMsg("Hey Alice");
        user1.setDND(true);
        user2.sendMsg("Sup?");
    }
}
