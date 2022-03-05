package ir.delaramsharifi;

import ir.delaramsharifi.entity.TblAccountInformation;
import ir.delaramsharifi.entity.TblCustomer;
import ir.delaramsharifi.service.AccountInformationDAO;
import ir.delaramsharifi.service.CustomerDAO;
import ir.delaramsharifi.utility.PasswordGenerator;

import java.net.IDN;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    CustomerDAO customerDAO = new CustomerDAO();
    AccountInformationDAO accountInformationDAO = new AccountInformationDAO();

    public static void main(String[] args) {

        String name, family, nationalCode, mobileNumber, comment;
        int customerId;
        Integer accountTypeId, cashAmount, withdrewAmount, depositAmount, result;
        Timestamp withdrewDate, depositDate;
        List<TblAccountInformation> accountInformations;
        TblAccountInformation summaryAccountInformation;

        while (true) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("please enter command number?");
            System.out.println("[1] add new customer");
            System.out.println("[2] search customer by family");
            System.out.println("[3] search customer by national code and password");
            System.out.println("[4] show all customers");
            System.out.println("[5] update customer info");
            System.out.println("[6] delete customer");
            System.out.println("[7] open account (1 for short-period-profitable, 2 for long-period-profitable, 3 for no-profit)");
            System.out.println("[8] withdrew money from account");
            System.out.println("[9] add money to account");
            System.out.println("[10]show all customers account info");
            System.out.println("[11] exit member");

            String inputValue = scanner.nextLine();

            if (!inputValue.matches("^(1[0-1]|[1-9])$")) {
                System.out.println("input value is not correct, try again..");
                continue;
            }

            Integer commandNumber = Integer.valueOf(inputValue);

            switch (commandNumber) {
                case 1:
                    System.out.println("please enter your name");
                    name = scanner.nextLine();
                    System.out.println("please enter your family");
                    family = scanner.nextLine();
                    System.out.println("please enter your national code");
                    nationalCode = scanner.nextLine();
                    System.out.println("please enter your mobile number");
                    mobileNumber = scanner.nextLine();
                    System.out.println("please enter your password");
                    String password = scanner.nextLine();
                    System.out.println("add new person --->");
                    TblCustomer newPerson = new TblCustomer(null, name, family, nationalCode, mobileNumber, password);
                    result = new Main().customerDAO.addNewPerson(newPerson);
                    System.out.println(result + " person added.");
                    break;

                case 2:
                    System.out.println("please enter family for search?");
                    Scanner scanner2 = new Scanner(System.in);
                    family = scanner2.nextLine();
                    System.out.println("search persons by family --->");
                    List<TblCustomer> persons = new Main().customerDAO.searchCustomersByFamily(family);
                    persons.stream().forEach(System.out::println);
                    break;

                case 3:
                    System.out.println("please enter national code");
                    Scanner scanner3 = new Scanner(System.in);
                    nationalCode = scanner3.nextLine();
                    System.out.println("please enter your password");
                    password = scanner.nextLine();
                    System.out.println("find person by national code and password --->");
                    TblCustomer findPerson = new Main().customerDAO.findCustomerByUserPass(nationalCode, password);
                    System.out.println(findPerson.toString());
                    break;

                case 4:
                    System.out.println("find all customers--->");
                    List<TblCustomer> allPersons = new Main().customerDAO.getAllCustomers();
                    allPersons.stream().forEach(System.out::println);
                    break;

                case 5:
                    TblCustomer foundCustomer = getCustomerByIdOrNationalCode();

                    System.out.println("foundCustomer is: " + foundCustomer.toString());
                    System.out.println("enter new values separate by slash(/) (use - for unchanged values): Name,  Family,  Mobile Number, Password");
                    System.out.println("example: Delaram/Sharifi/09101090511/-");

                    String newParameter = new Scanner(System.in).nextLine();
                    String[] newParameters = newParameter.split("/");

                    if (newParameters.length != 4) {
                        System.out.println("input value is not correct, try again..");
                        continue;
                    }

                    String newName = newParameters[0];
                    String newFamily = newParameters[1];
                    String newPhoneNumber = newParameters[2];
                    String newPassword = newParameters[3];

                    if (!newName.equalsIgnoreCase("-"))
                        foundCustomer.setName(newName);

                    if (!newFamily.equalsIgnoreCase("-"))
                        foundCustomer.setFamily(newFamily);

                    if (!newPhoneNumber.equalsIgnoreCase("-"))
                        foundCustomer.setMobileNumber(newPhoneNumber);

                    if (!newPassword.equalsIgnoreCase("-"))
                        foundCustomer.setPassword(new PasswordGenerator().hashPassword(newPassword));

                    int result1 = new Main().customerDAO.updateCustomerInfo(foundCustomer);

                    if (result1 == 1) {
                        System.out.println("Database updated successfully ");
                    } else {
                        System.out.println("Database updated was not successful ");
                    }
                    break;

                case 6:
                    System.out.println("please enter customer national code");
                    Scanner scanner6 = new Scanner(System.in);
                    nationalCode = scanner6.nextLine();
                    System.out.println("find customer---------> ready to delete");
                    TblCustomer deleteCustomer = new Main().customerDAO.findCustomerByNationalCode(nationalCode, "find");
                    Integer resultDelete = new Main().customerDAO.deleteCustomer(deleteCustomer);
                    System.out.println("Record deleted successfully");
                    break;

                case 7:
                    System.out.println("please enter customer id");
                    Scanner scanner4 = new Scanner(System.in);
                    customerId = (int) scanner4.nextInt();
                    TblCustomer foundedCustomer = new Main().customerDAO.findCustomerByCustomerNumber(customerId);
                    System.out.println(foundedCustomer);
                    System.out.println("1 for short-period-profitable, 2 for long-period-profitable, 3 for no-profit");
                    System.out.println("please enter your account Type Id");
                    accountTypeId = scanner.nextInt();
                    System.out.println("please enter your deposit Amount");
                    depositAmount = scanner.nextInt();
                    cashAmount = depositAmount;
                    depositDate = new Timestamp(Date.from(Instant.now()).getTime());
                    System.out.println("please enter comment");
                    comment = new Scanner(System.in).nextLine();

                    System.out.println("add new account information --->");
                    TblAccountInformation newAccountInformation = new TblAccountInformation(null, customerId, accountTypeId, cashAmount, null, depositAmount, null, depositDate, comment);
                    result = new Main().accountInformationDAO.addAccountInformation(newAccountInformation);
                    System.out.println(result + " account information added.");
                    break;

                case 8:
                    System.out.println("please enter customer id");
                    customerId = new Scanner(System.in).nextInt();
                    accountInformations = new Main().accountInformationDAO.findAccountInformationByCustomerId(customerId);
                    accountInformations = accountInformations.stream().sorted(Comparator.comparing(TblAccountInformation::getId)).collect(Collectors.toList());
                    System.out.println(accountInformations);
                    System.out.println("please enter account Type?");
                    accountTypeId = new Scanner(System.in).nextInt();
                    summaryAccountInformation = summaryAccountInformationByAccountTypeId(accountInformations, accountTypeId);
                    System.out.println("summary: "+ summaryAccountInformation);
                    System.out.println("please enter amount for withdraw?");
                    withdrewAmount = new Scanner(System.in).nextInt();
                    System.out.println("please enter comment");
                    comment = new Scanner(System.in).nextLine();

                    if (summaryAccountInformation.getCashAmount() < withdrewAmount) {
                        System.out.println(String.format("cash amount (%s) is not enough for withdraw (%s)", summaryAccountInformation.getCashAmount(), withdrewAmount));
                        continue;
                    }

                    summaryAccountInformation.setCustomerId(customerId);
                    summaryAccountInformation.setCashAmount(summaryAccountInformation.getCashAmount() - withdrewAmount);
                    summaryAccountInformation.setWithdrewAmount(withdrewAmount);
                    summaryAccountInformation.setWithdrewDate(new Timestamp(Date.from(Instant.now()).getTime()));
                    summaryAccountInformation.setDepositAmount(null);
                    summaryAccountInformation.setDepositDate(null);
                    summaryAccountInformation.setComment(comment);

                    result = new Main().accountInformationDAO.addAccountInformation(summaryAccountInformation);

                    System.out.println("transaction commit successfully");

                    break;
                case 9:

                    System.out.println("please enter customer id");
                    customerId = new Scanner(System.in).nextInt();
                    accountInformations = new Main().accountInformationDAO.findAccountInformationByCustomerId(customerId);
                    accountInformations = accountInformations.stream().sorted(Comparator.comparing(TblAccountInformation::getId)).collect(Collectors.toList());
                    System.out.println(accountInformations);
                    System.out.println("please enter account Type?");
                    accountTypeId = new Scanner(System.in).nextInt();
                    summaryAccountInformation = summaryAccountInformationByAccountTypeId(accountInformations, accountTypeId);
                    System.out.println("summary: "+ summaryAccountInformation);
                    System.out.println("please enter amount for deposit?");
                    depositAmount = new Scanner(System.in).nextInt();
                    System.out.println("please enter comment");
                    comment = new Scanner(System.in).nextLine();

                    summaryAccountInformation.setCustomerId(customerId);
                    summaryAccountInformation.setCashAmount(summaryAccountInformation.getCashAmount() + depositAmount);
                    summaryAccountInformation.setDepositAmount(depositAmount);
                    summaryAccountInformation.setDepositDate(new Timestamp(Date.from(Instant.now()).getTime()));
                    summaryAccountInformation.setWithdrewAmount(null);
                    summaryAccountInformation.setWithdrewDate(null);
                    summaryAccountInformation.setComment(comment);

                    result = new Main().accountInformationDAO.addAccountInformation(summaryAccountInformation);

                    System.out.println("transaction commit successfully");
                    break;

                case 10:
                    System.out.println("find all customers account information--->");
                    List<TblAccountInformation> allCustomers = new Main().accountInformationDAO.getAllAccountInformation();
                    allCustomers.stream().forEach(System.out::println);
                    break;

                case 11:
                    System.out.println("you exit the program");
                    System.exit(0);
                    break;
                default:
                    System.out.println("enter command number?");
            }
        }
    }

    private static TblCustomer getCustomerByIdOrNationalCode() {
        String inputValue;
        String nationalCode;
        System.out.println("for update by customer-number(press:1), by customer-national code(press:2) ");

        inputValue = new Scanner(System.in).nextLine();


        if (!inputValue.matches("[1,2]")) {
            System.out.println("input value is not correct, run the program again");
            System.exit(0);
        }

        TblCustomer foundCustomer;

        if (Integer.valueOf(inputValue) == 1) {
            System.out.println("please enter customer-number");
            int customerNumber = new Scanner(System.in).nextInt();
            foundCustomer = new Main().customerDAO.findCustomerByCustomerNumber(customerNumber);

        } else {
            System.out.println("please enter customer national code");
            nationalCode = new Scanner(System.in).nextLine();
            foundCustomer = new Main().customerDAO.findCustomerByNationalCode(nationalCode, "find");
        }
        return foundCustomer;
    }

    private static TblAccountInformation summaryAccountInformationByAccountTypeId(List<TblAccountInformation> accountInformations, Integer accountTypeId) {
        TblAccountInformation summaryAccountInformation = new TblAccountInformation();

        Integer customerId = accountInformations.get(0).getCustomerId();
        Integer sumDeposit = accountInformations.stream()
                .filter(a -> a.getAccountTypeId() != null)
                .filter(a -> a.getAccountTypeId().equals(accountTypeId))
                .map(TblAccountInformation::getDepositAmount)
                .reduce(0, Integer::sum);

        Integer sumWithDraw = accountInformations.stream()
                .filter(a -> a.getAccountTypeId() != null)
                .filter(a -> a.getAccountTypeId().equals(accountTypeId))
                .map(TblAccountInformation::getWithdrewAmount)
                .reduce(0, Integer::sum);

        Integer cashAmount = sumDeposit - sumWithDraw;

        summaryAccountInformation = new TblAccountInformation(null, customerId, accountTypeId, cashAmount,
                sumWithDraw, sumDeposit, null, null, null);

        return summaryAccountInformation;
    }



}
