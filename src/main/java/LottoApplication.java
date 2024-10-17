import model.LottoBundle;
import model.LottoMachine;
import model.LottoNumber;
import model.RandomLottoNumberGenerateImpl;
import view.InputView;
import view.ResultView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoApplication {
    public static void main(String[] args) {
        int totalPrice = InputView.getPaidMoney();

        int countOfManualLottoNumbers = InputView.getCountOfManualLottoNumbers();
        List<List<Integer>> manualLottoNumbers = InputView.getManualLottoNumbers(countOfManualLottoNumbers);

        LottoBundle lottoBundle = new LottoBundle(
                manualLottoNumbers,
                totalPrice,
                new RandomLottoNumberGenerateImpl()
        );

        ResultView.printNumberOfLotto(lottoBundle.getCountOfRandomLottoNumbers(), countOfManualLottoNumbers);
        ResultView.printLottoNumbers(lottoBundle.getLottoNumbers());

        List<Integer> winningNumbers = InputView.getWinningNumbers().stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList());
        LottoNumber bonusNumber = InputView.getBonusNumber();

        LottoMachine lottoMachine = new LottoMachine(
                winningNumbers,
                totalPrice,
                lottoBundle.getLottoNumbers(),
                bonusNumber.getNumber()
        );

        ResultView.printLottoStatistics(lottoMachine.getStatistics());
    }
}
