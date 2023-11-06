package sample.cafekiosk.unit.beverage;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AmericanoTest {

    @Test
    void getName(){
        final Americano americano = new Americano();
        Assertions.assertThat(americano.getName()).isEqualTo("아메리카노");
    }

    @Test
    void getPrice(){
        final Americano americano = new Americano();
        Assertions.assertThat(americano.getPrice()).isEqualTo(4000);
    }



}