package sample.cafekiosk.unit;

import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;
import sample.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CafeKioskTest {


    @Test
    void add(){
        final CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        assertThat(cafeKiosk.getBeverages().size()).isEqualTo(1);
        assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }


    @Test
    void addSeveralBeverages(){
        final CafeKiosk cafeKiosk = new CafeKiosk();
        final Americano americano = new Americano();

        cafeKiosk.add(americano, 2);

        assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
        assertThat(cafeKiosk.getBeverages().get(1)).isEqualTo(americano);
    }



    @Test
    void addZeroBeverages(){
        final CafeKiosk cafeKiosk = new CafeKiosk();
        final Americano americano = new Americano();

        assertThatThrownBy(() -> {
            cafeKiosk.add(americano, 0);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음료는 1잔 이상 주문하실 수 있습니다.");
    }



    @Test
    void remove(){
        final CafeKiosk cafeKiosk = new CafeKiosk();
        final Americano americano = new Americano();
        cafeKiosk.add(americano);

        assertThat(cafeKiosk.getBeverages()).hasSize(1);

        cafeKiosk.remove(americano);
        assertThat(cafeKiosk.getBeverages()).hasSize(0);
    }

    @Test
    void clear(){
        final CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());
        cafeKiosk.add(new Latte());

        assertThat(cafeKiosk.getBeverages()).hasSize(2);

        cafeKiosk.clear();
        assertThat(cafeKiosk.getBeverages()).hasSize(0);
    }

    @Test
    void createOrder(){
        final CafeKiosk kiosk = new CafeKiosk();
        final Americano americano = new Americano();
        kiosk.add(americano);

        final Order order = kiosk.createOrder(LocalDateTime.of(2023, 1, 17, 10, 00));

        assertThat(order.getBeverages()).hasSize(1);
        assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @Test
    void createOrderOutsideOpenTIme(){
        final CafeKiosk kiosk = new CafeKiosk();
        final Americano americano = new Americano();
        kiosk.add(americano);

        assertThatThrownBy(() -> {
            kiosk.createOrder(LocalDateTime.of(2023, 1, 17, 9, 59));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문 시간이 아닙니다.");

    }



}


