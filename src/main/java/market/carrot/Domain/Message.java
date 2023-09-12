package market.carrot.Domain;

import lombok.Getter;

@Getter
public class Message {
    private String name;
    private Long room_id;

    public Message() {
    }

    public Message(String name, Long room_id) {
        this.name = name;
        this.room_id = room_id;
    }
}
