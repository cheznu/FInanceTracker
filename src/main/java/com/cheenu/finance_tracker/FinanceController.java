package com.cheenu.finance_tracker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.util.ArrayList;

@Controller
public class FinanceController {

    @GetMapping("/") // This means "when the user goes to the home page"
    public String home(Model model) {

        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(60.55,"Target"));
        transactions.add(new Transaction(5.89,"Mcdonalds"));
        File file = new File("transactions.csv");
        System.out.println("Does file exist? " + file.exists());
        System.out.println("Path: " + file.getAbsolutePath());

        model.addAttribute("message","Welcome to your Finance Tracker, John Pork!");
        model.addAttribute("transactions",transactions);
        return "index"; // This tells Spring to look for index.html in the templates folder
    }
}