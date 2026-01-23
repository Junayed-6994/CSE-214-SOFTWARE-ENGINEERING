package SimpleFactory;

import java.util.Scanner;

interface DocumentProcessor{
    public void loadDocument();
    public void saveDocument();
}

class DocxProcessor implements DocumentProcessor{
    private String filename;

    public DocxProcessor(String filename) {
        this.filename = filename;
    }

    public void loadDocument(){
        System.out.println("Loading DocX file: " + filename);
    }

    public void saveDocument(){
        System.out.println("Saving DocX file: " + filename);
    }  
    
}

class PdfProcessor implements DocumentProcessor{
    private String filename;

    public PdfProcessor(String filename) {
        this.filename = filename;
    }

    public void loadDocument(){
        System.out.println("Loading Pdf file: " + filename);
    }

    public void saveDocument(){
        System.out.println("Saving Pdf file: " + filename);
    }  
}

class TxtProcessor implements DocumentProcessor{
    private String filename;

    public TxtProcessor(String filename) {
        this.filename = filename;
    }

    public void loadDocument(){
        System.out.println("Loading Txt file: " + filename);
    }

    public void saveDocument(){
        System.out.println("Saving Txt file: " + filename);
    }  
}

class DocumentProcessorFactory {
    public static DocumentProcessor createProcessor(String fileName) {
        if (fileName.endsWith(".docx")) {
            return new DocxProcessor(fileName);
        } else if (fileName.endsWith(".pdf")) {
            return new PdfProcessor(fileName);
        } else if (fileName.endsWith(".txt")) {
            return new TxtProcessor(fileName);
        }
        return null;
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter file name: ");
        String fileName = scanner.nextLine();
        
        DocumentProcessor processor = DocumentProcessorFactory.createProcessor(fileName);
        
        if (processor != null) {
            processor.loadDocument();
            processor.saveDocument();
        } else {
            System.out.println("Unsupported file format!");
        }
        
        scanner.close();
    }
}