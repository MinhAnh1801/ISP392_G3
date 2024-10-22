package Model;

/**
 *
 * @author Dell
 */
public class DormRooms {
    private int id;
    private String roomNumber;
    private int capacity;
    private int availableCapacity;
    private String building;
    private String roomType;
    private String detail; 
    private int price; 

    public DormRooms() {}

    public DormRooms(int id, String roomNumber, int capacity, int availableCapacity, String building, String roomType, String detail, int price) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.availableCapacity = availableCapacity;
        this.building = building;
        this.roomType = roomType;
        this.detail = detail;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(int availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "DormRooms{" + "id=" + id + ", roomNumber=" + roomNumber + ", capacity=" + capacity + ", availableCapacity=" + availableCapacity + ", building=" + building + ", roomType=" + roomType + ", detail=" + detail + ", price=" + price + '}';
    }

    
}
