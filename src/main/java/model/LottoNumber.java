package model;

import java.util.Objects;

public class LottoNumber {
    private final Integer number;

    public LottoNumber(int number) {
        checkLottoNumberRange(number);
        this.number = number;
    }

    public LottoNumber(String str) {
        this(Integer.parseInt(str));
    }

    public boolean equals(LottoNumber n) {
        return Objects.equals(this.number, n.number);
    }

    public int getNumber() {
        return this.number;
    }

    private void checkLottoNumberRange(Integer number) {
        if (number < Lotto.MIN_LOTTO_NUMBER || number > Lotto.MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("Bonus number must be between 1 and 45");
        }
    }
}
