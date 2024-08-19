package com.sagar.lahade.sci.calculator.controller;

import com.sagar.lahade.sci.calculator.model.CalculationHistory;
import com.sagar.lahade.sci.calculator.model.UserInfo;
import com.sagar.lahade.sci.calculator.service.CalculatorService;
import com.sagar.lahade.sci.calculator.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @Autowired
    private UserService userService;

    @GetMapping("/calculator")
    public String calculatorPage(HttpSession session, Model model) {
        UserInfo user = (UserInfo) session.getAttribute("user");
        if (user != null) {
            List<CalculationHistory> history = calculatorService.getHistory(user);
            model.addAttribute("history", history);
            return "calculator";
        }
        return "redirect:/login";
    }

    @PostMapping("/calculate")
    @ResponseBody
    public String calculate(@RequestParam String expression, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("user");
        if (user != null) {
            String result = calculatorService.performCalculation(expression);
            calculatorService.saveCalculation(user, expression + " = " + result);
            return result;
        }
        return "Error";
    }

    @GetMapping("/api/history")
    @ResponseBody
    public List<CalculationHistory> getHistory(HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("user");
        if (user != null) {
            List<CalculationHistory> history = calculatorService.getHistory(user);
            Collections.reverse(history); // Reversing the list
            return history;
        }
        return List.of(); // Return an empty list if the user is null
    }
}
