public class Main {
     public static void main(String[] args) {
          BankAccount acc1 = new BankAccount(1);
          BankAccount acc2 = new BankAccount(2);
          acc1.setLogger(Logger.getInstance());
          acc2.setLogger(Logger.getInstance());

          acc1.logWithdrawal(1000);
          acc2.logDeposit(500);
          acc1.logTransfer(200,acc2);
     }
}
