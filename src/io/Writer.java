package io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import model.Expense;
import service.Summarizer;

public interface Writer 
{
    void writeHeader(BufferedWriter writer) throws IOException;
    void writeMonthlySummary(BufferedWriter writer, Summarizer summarizer) throws IOException;
    void writeCategoryBreakdown(BufferedWriter writer, Summarizer summarizer) throws IOException;
    void writeGrandTotal(BufferedWriter writer, Summarizer summarizer) throws IOException;
    void writeRecentEntries(BufferedWriter writer,List<Expense>  allExpenses) throws IOException;
    String createBar(double value, double maxValue);

    default void writeFooter(BufferedWriter writer) throws IOException{

    }
}

