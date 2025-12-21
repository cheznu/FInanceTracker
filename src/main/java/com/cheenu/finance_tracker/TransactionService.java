package com.cheenu.finance_tracker;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;

@Service
public class TransactionService {

    public List<Transaction> getAllTransactions() {
        List<Transaction> list = new ArrayList<>();
        // Ensure transactions.csv is in your main project folder (next to pom.xml)
        String path = "transactions.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            // 1. Skip the header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] cols = line.split(",");
                if (cols.length < 4) continue;

                // 1. Get the Name (Everything before the numbers start)
                String name = cols[3].replace("\"", "").trim();

                double finalAmount = 0;
                boolean found = false;

                // 2. SEARCH the columns to find the first thing that looks like a number
                // We start from index 4 and look right
                for (int i = 4; i < cols.length; i++) {
                    String cleanVal = cols[i].replace("\"", "").trim();

                    if (!cleanVal.isEmpty() && !cleanVal.equalsIgnoreCase("Posted")) {
                        try {
                            double val = Double.parseDouble(cleanVal);

                            // If we found a number and it's not the 'Balance' (which is usually last)
                            // We assume the first number found is the transaction amount
                            finalAmount = val;

                            // Logic: If it was in the 'Spending' area, make it negative
                            // (In your file, spending is usually the column right after the name)
                            if (i == 4 || (i == 5 && cols[4].trim().isEmpty())) {
                                // Keep it negative if it's a withdrawal
                                if (name.contains("Withdrawal") || name.contains("Debit")) {
                                    finalAmount = -Math.abs(val);
                                }
                            }

                            found = true;
                            break; // Stop looking once we found the amount
                        } catch (NumberFormatException e) {
                            // Not a number? Keep looking in the next column!
                            name += " " + cleanVal; // Add the '2 Park Plaza' back to the name
                        }
                    }
                }

                if (found) {
                    list.add(new Transaction(finalAmount, name));
                }
            }
        } catch (IOException e) {
            System.err.println("CRITICAL ERROR: Could not find transactions.csv in the project root.");
        }

        System.out.println("SERVICE SUCCESS: Loaded " + list.size() + " transactions.");
        return list;
    }
}