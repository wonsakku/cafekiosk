package sample.cafekiosk.unit;

import lombok.Getter;
import sample.cafekiosk.unit.beverage.Beverage;
import sample.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CafeKiosk {

    private final List<Beverage> beverages = new ArrayList<>();
    private final LocalTime SHOP_OPEN_TIME = LocalTime.of(10, 0);
    private final LocalTime SHOP_CLOSE_TIME = LocalTime.of(20, 0);


    public void add(Beverage beverage) {
        this.beverages.add(beverage);
    }

    public void add(Beverage beverage, int count) {

        if(count <= 0){
            throw new IllegalArgumentException("음료는 1잔 이상 주문하실 수 있습니다.");
        }

        for(int i = 0 ; i < count ; i++){
            this.beverages.add(beverage);
        }
    }

    public void remove(Beverage beverage){
        this.beverages.remove(beverage);
    }

    public void clear(){
        this.beverages.clear();
    }

    public int calculateTotalPrice() {
        return this.beverages.stream()
                .mapToInt(Beverage::getPrice)
                .sum();
    }

    public Order createOrder(LocalDateTime currentTime){

        if(currentTime.toLocalTime().isBefore(SHOP_OPEN_TIME) || currentTime.toLocalTime().isAfter(SHOP_CLOSE_TIME)){
            throw new IllegalArgumentException("주문 시간이 아닙니다.");
        }

        return new Order(LocalDateTime.now(), beverages);
    }

}



