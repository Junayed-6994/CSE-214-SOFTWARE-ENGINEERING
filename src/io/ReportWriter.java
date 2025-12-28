package io;

import service.ExpenseRepository;
import java.io.IOException;

public interface ReportWriter {
    void writeReport(String filePath, ExpenseRepository repository) throws IOException;
}