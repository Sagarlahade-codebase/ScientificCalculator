package com.sagar.lahade.sci.calculator.service;

import com.sagar.lahade.sci.calculator.model.CalculationHistory;
import com.sagar.lahade.sci.calculator.model.UserInfo;
import com.sagar.lahade.sci.calculator.repository.CalculationHistoryRepository;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculatorService {

    @Autowired
    private CalculationHistoryRepository historyRepository;

    public String performCalculation(String expression) {
        try {
            // Build and evaluate the expression
            double result = new ExpressionBuilder(expression).build().evaluate();
            return String.valueOf(result);
        } catch (Exception e) {
            // Handle exceptions and return an error message
            return "Error: Invalid expression";
        }
    }

    public void saveCalculation(UserInfo user, String expression) {
        CalculationHistory history = new CalculationHistory();
        history.setUser(user);
        history.setCalculation(expression);
        historyRepository.save(history);
        trimHistory(user);
    }

    public List<CalculationHistory> getHistory(UserInfo user) {
        return historyRepository.findByUser(user);
    }

    private void trimHistory(UserInfo user) {
        List<CalculationHistory> history = getHistory(user);
        if (history.size() > 50) {
            history.subList(0, history.size() - 50).forEach(historyRepository::delete);
        }
    }
}
