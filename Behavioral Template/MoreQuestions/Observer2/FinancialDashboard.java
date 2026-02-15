
import java.util.ArrayList;
import java.util.List;

// Observer Interface(Subscriber)
interface StockObserver {
    void update(String stockSymbol, double newPrice);
}

// Subject Interface(Publisher)
interface Subject {
    void attach(StockObserver observer);
    void detach(StockObserver observer);
    void notifyObservers(String stockSymbol, double newPrice);
}

// Concrete Subject - StockData Feed
class StockDataFeed implements Subject {
    private List<StockObserver> observers;
    
    public StockDataFeed() {
        this.observers = new ArrayList<>();
    }
    
    @Override
    public void attach(StockObserver observer) {
        observers.add(observer);
        System.out.println(observer.getClass().getSimpleName() + " widget added to dashboard");
    }
    
    @Override
    public void detach(StockObserver observer) {
        observers.remove(observer);
        System.out.println(observer.getClass().getSimpleName() + " widget removed from dashboard");
    }
    
    @Override
    public void notifyObservers(String stockSymbol, double newPrice) {
        System.out.println("\n📊 STOCK UPDATE: " + stockSymbol + " → $" + newPrice);
        System.out.println("================================================");
        for (StockObserver observer : observers) {
            observer.update(stockSymbol, newPrice);
        }
        System.out.println("================================================\n");
    }
    
    // Simulate price change
    public void setStockPrice(String stockSymbol, double newPrice) {
        System.out.println("💰 Price change detected for " + stockSymbol);
        notifyObservers(stockSymbol, newPrice);
    }
}

// Concrete Observer 1 - Ticker Tape Widget
class TickerTape implements StockObserver {
    private String widgetId;
    
    public TickerTape(String widgetId) {
        this.widgetId = widgetId;
    }
    
    @Override
    public void update(String stockSymbol, double newPrice) {
        System.out.println("📺 TickerTape [" + widgetId + "]: Scrolling → " + 
                         stockSymbol + ": $" + newPrice);
    }
}

// Concrete Observer 2 - Graph Widget
class GraphWidget implements StockObserver {
    private String widgetId;
    
    public GraphWidget(String widgetId) {
        this.widgetId = widgetId;
    }
    
    @Override
    public void update(String stockSymbol, double newPrice) {
        System.out.println("📈 Graph [" + widgetId + "]: Plotting new data point → " + 
                         stockSymbol + ": $" + newPrice);
    }
}

// Concrete Observer 3 - Buy/Sell Bot
class BuySellBot implements StockObserver {
    private String botName;
    private double buyThreshold;
    private double sellThreshold;
    
    public BuySellBot(String botName, double buyThreshold, double sellThreshold) {
        this.botName = botName;
        this.buyThreshold = buyThreshold;
        this.sellThreshold = sellThreshold;
    }
    
    @Override
    public void update(String stockSymbol, double newPrice) {
        System.out.print("🤖 Buy/Sell Bot [" + botName + "]: Evaluating " + 
                        stockSymbol + " at $" + newPrice + " → ");
        
        if (newPrice < buyThreshold) {
            System.out.println("✅ BUY SIGNAL TRIGGERED!");
        } else if (newPrice > sellThreshold) {
            System.out.println("⚠️  SELL SIGNAL TRIGGERED!");
        } else {
            System.out.println("HOLD (within normal range)");
        }
    }
}

// Main Class - Financial Dashboard Demo
public class FinancialDashboard {
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════");
        System.out.println("   FINANCIAL TRADING DASHBOARD SYSTEM");
        System.out.println("═══════════════════════════════════════════\n");
        
        // Create the StockData feed (Subject)
        StockDataFeed stockFeed = new StockDataFeed();
        
        // Create widgets (Observers)
        TickerTape tickerTape = new TickerTape("Main-Display");
        GraphWidget graphWidget = new GraphWidget("Chart-1");
        BuySellBot tradingBot = new BuySellBot("AlphaBot", 145.0, 155.0);
        
        System.out.println("--- Adding Widgets to Dashboard ---");
        // Attach observers to the feed
        stockFeed.attach(tickerTape);
        stockFeed.attach(graphWidget);
        stockFeed.attach(tradingBot);
        
        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("        REAL-TIME TRADING SESSION");
        System.out.println("═══════════════════════════════════════════");
        
        // Simulate stock price changes
        stockFeed.setStockPrice("AAPL", 150.25);
        
        stockFeed.setStockPrice("GOOGL", 142.80);
        
        // Add another widget dynamically
        System.out.println("\n--- Adding New Widget ---");
        GraphWidget graphWidget2 = new GraphWidget("Chart-2");
        stockFeed.attach(graphWidget2);
        
        stockFeed.setStockPrice("TSLA", 157.50);
        
        // Remove a widget dynamically
        System.out.println("\n--- Removing Widget ---");
        stockFeed.detach(graphWidget);
        
        stockFeed.setStockPrice("MSFT", 143.20);
        
        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("        END OF TRADING SESSION");
        System.out.println("═══════════════════════════════════════════");
    }
}









