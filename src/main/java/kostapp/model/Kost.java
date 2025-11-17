package kostapp.model;

import java.util.ArrayList;
import java.util.List;

public class Kost {
    private String kostId;
    private String name;
    private String address;
    private String description;
    private Owner owner;
    private List<Room> rooms;

    public Kost(String kostId, String name, String address, String description, Owner owner) {
        this.kostId = kostId;
        this.name = name;
        this.address = address;
        this.description = description;
        this.owner = owner;
        this.rooms = new ArrayList<Room>();
    }

    public void addRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }

        if (!rooms.contains(room)) {
            rooms.add(room);
        }
    }

    public boolean removeRoom(Room room) {
        if (room == null) {
            return false;
        }
        return rooms.remove(room);
    }

    public List<Room> getVacantRooms() {
        List<Room> vacantRooms = new ArrayList<>();

        for (Room room : rooms) {
            if (room.getRoomStatus() != null &&
                    room.getRoomStatus().equalsIgnoreCase("VACANT")) {
                vacantRooms.add(room);
            }
        }

        return vacantRooms;
    }

    public String getKostId() {
        return kostId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public String toString() {
        return "Kost{" +
                "kostId='" + kostId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", owner=" + (owner != null ? owner.getName() : "null") +
                ", totalRooms=" + (rooms != null ? rooms.size() : 0) +
                '}';
    }
}
