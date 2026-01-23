public class BankAccount {
        private Logger logger;
        private Integer accountNo;

        public BankAccount(Integer num){
            accountNo = num;
        }

        public Integer getAccountNo(){
            return accountNo;
        }

        public void setLogger(Logger logger){
            this.logger = logger;
        }

        public void logDeposit(Integer amount){
            logger.log(amount.toString()+" deposited by "+accountNo.toString());
        }

        public void logWithdrawal(Integer amount){
            logger.log(amount.toString()+" withdrawn by "+accountNo.toString());
        }

        public void logTransfer(Integer amount, BankAccount receiver){
            logger.log(amount.toString()+"transferred from "+accountNo.toString()+" to "+receiver.getAccountNo().toString());
        }

}
