package DormRoomController;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Dell
 */
import java.math.BigDecimal;
import java.sql.Date;
import java.util.concurrent.TimeUnit;

public class DormCostCalculator {
    private int studentId;
    private int dormRoomId;
    private Date checkInDate;
    private Date checkOutDate;
    private BigDecimal pricePerMonth;

    public DormCostCalculator(int studentId, int dormRoomId, Date checkInDate, Date checkOutDate, BigDecimal pricePerMonth) {
        this.studentId = studentId;
        this.dormRoomId = dormRoomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.pricePerMonth = pricePerMonth;
    }

    public BigDecimal calculateTotalCost() {
        long daysBetween = TimeUnit.DAYS.convert(checkOutDate.getTime() - checkInDate.getTime(), TimeUnit.MILLISECONDS);
        long monthsBetween = daysBetween / 30;
        return pricePerMonth.multiply(BigDecimal.valueOf(monthsBetween));
    }

    @Override
    public String toString() {
        return "DormCostCalculator{" +
                "studentId=" + studentId +
                ", dormRoomId=" + dormRoomId +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", pricePerMonth=" + pricePerMonth +
                ", totalCost=" + calculateTotalCost() +
                '}';
    }
}

