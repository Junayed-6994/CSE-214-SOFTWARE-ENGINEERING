package io;

import model.Expense;
import service.ExpenseRepository;
import service.Summarizer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Writes plain-text expense reports using the Factory Method pattern.
 */
public class TxtReportWriter {
    private Content content;
    private BothFormatter formatter;
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