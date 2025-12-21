package com.cheenu.finance_tracker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FinanceController {

    private final TransactionService transactionService;

    // Spring sees this and "plugs in" your @Service automatically
    public FinanceController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/") // This means "when the user goes to the home page"
    public String home(Model model) {

        // 1. Get the data from our 'Chef'
        List<Transaction> data = transactionService.getAllTransactions();

        model.addAttribute("transactions", data);
        return "index";

    }
}