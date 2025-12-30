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
import io.ConcreteFactories.HtmlContent;

/**
 * Writes HTML expense reports using the Factory Method pattern.
 */
public class HtmlReportWriter implements ReportWriter {
    private Content content;
    private Formatter formatter;
    private Writer writer;

    public HtmlReportWriter() {
        this.content = new HtmlContent();
        this.formatter = content.createFormatter();
        this.writer = content.createWriter(formatter);
    }

    @Override
    public void writeReport(String filePath, ExpenseRepository repository) throws IOException {
        List<Expense> allExpenses = repository.findAll();
        Summarizer summarizer = new Summarizer(allExpenses);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            writer.writeHeader(bw);
            writer.writeMonthlySummary(bw, summarizer);
            writer.writeCategoryBreakdown(bw, summarizer);
            writer.writeGrandTotal(bw, summarizer);
            writer.writeRecentEntries(bw, allExpenses);
            writer.writeFooter(bw);
        }

        System.out.println("HTML report written to: " + filePath);
    }
}