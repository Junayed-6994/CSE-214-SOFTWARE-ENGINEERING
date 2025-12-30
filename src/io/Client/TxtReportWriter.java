package io.Client;

import model.Expense;
import service.ExpenseRepository;
import service.Summarizer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import io.AbstractFactory.Content;
import io.AbstractProducts.Formatter;
import io.AbstractProducts.Writer;
import io.ConcreteFactories.TxtContent;

/**
 * Writes plain-text expense reports using the Factory Method pattern.
 */
public class TxtReportWriter {
    private Content content;
    private Formatter formatter;
    private Writer writer;

    public TxtReportWriter() {
        this.content = new TxtContent();
        this.formatter = content.createFormatter();
        this.writer = content.createWriter(formatter);
    }

    public void writeReport(String filePath, ExpenseRepository repository) throws IOException {
        List<Expense> allExpenses = repository.findAll();
        Summarizer summarizer = new Summarizer(allExpenses);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            writer.writeHeader(bw);
            writer.writeMonthlySummary(bw, summarizer);
            writer.writeCategoryBreakdown(bw, summarizer);
            writer.writeGrandTotal(bw, summarizer);
            writer.writeRecentEntries(bw, allExpenses);
        }

        System.out.println("Text report written to: " + filePath);
    }
}