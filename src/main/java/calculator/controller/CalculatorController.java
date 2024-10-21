package calculator.controller;

import calculator.model.StringCalculator;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {
    private final InputView inputView;
    private final OutputView outputView;

    public CalculatorController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        String input = inputView.getUserInput();
        StringCalculator stringCalculator = new StringCalculator(input);
        int result = stringCalculator.add();
        outputView.displayResult(result);
    }
}
