package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoBundle {
    private List<List<LottoNumber>> manualLottoNumbers;
    private List<List<LottoNumber>> randomLottoNumbers;
    private LottoNumberGenerator lottoNumberGenerator;

    public LottoBundle(
            int totalPrice,
            LottoNumberGenerate lottoNumberGenerate
    ) {
        this(Collections.emptyList(), totalPrice, lottoNumberGenerate);
    }

    public LottoBundle(
            List<List<Integer>> manualLottoNumbers,
            int totalPrice,
            LottoNumberGenerate lottoNumberGenerate
    ) {
        this.manualLottoNumbers = manualLottoNumbers.stream()
                .map(numbers -> numbers.stream().map(LottoNumber::new).collect(Collectors.toList()))
                .collect(Collectors.toList());
        this.lottoNumberGenerator = new LottoNumberGenerator(lottoNumberGenerate);
        this.randomLottoNumbers = this.lottoNumberGenerator.run(getCountOfRandomLottoNumbers(totalPrice)).stream()
                .map(numbers -> numbers.stream().map(LottoNumber::new).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }


    private int getCountOfRandomLottoNumbers(int totalPrice) {
        return totalPrice / LottoMachine.PRICE_OF_A_LOTTO - this.manualLottoNumbers.size();
    }

    public int getCountOfRandomLottoNumbers() {
        return this.randomLottoNumbers.size();
    }

    public List<List<Integer>> getLottoNumbers() {
        List<List<LottoNumber>> lottoNumbers = new ArrayList<>(this.manualLottoNumbers);
        lottoNumbers.addAll(this.randomLottoNumbers);
        return lottoNumbers.stream()
                .map(numbers -> numbers.stream().map(LottoNumber::getNumber).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
