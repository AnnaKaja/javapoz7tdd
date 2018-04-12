package com.sda.bank;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.mockito.Mockito;

public class BankSteps {

    private Bank bank;

    private BankUser user;

    private BankAccount account;

    private BankDatabase bankDatabase = new BankDatabase();

    private boolean userInsertResult;

    private boolean accountCreationResult;

    private boolean depositResult;

    @Given("^I mock bank database for this case$")
    public void iMockBankDatabaseForThisCase() {
        this.bankDatabase = Mockito.mock(BankDatabase.class);
        Mockito.when(bankDatabase.addBankUser(Mockito.any())).then(e -> true);
        Mockito.when(bankDatabase.createAccountFor(Mockito.any(), Mockito.anyString())).then(e -> true);
        Mockito.when(bankDatabase.getAccountWithId(Mockito.anyString())).then(e -> BankAccount.instanceOf(null, 1000));
        Mockito.when(bankDatabase.deposit(Mockito.any(), Mockito.anyInt())).then(e -> true);
    }

    @Given("^I instantiate bank$")
    public void iInstantiateBank() {
        this.bank = new Bank(bankDatabase);
    }

    @And("^I create user with name '(.*)' and pesel '(.*)'$")
    public void iCreateUserWithNameNameAndPeselPesel(String name, String pesel) {
        this.user = new BankUser(name, pesel);
    }


    @When("^I insert user to bank$")
    public void iInsertUserToBank() {
        userInsertResult = bank.addBankUser(new BankUser(user.getName(), user.getPesel()));
    }

    @And("^I insert account to bank$")
    public void iInsertAccountToBank() {
        accountCreationResult = bank.createAccountFor(user, account.getId());
    }

    @And("^I create account with name '(.*)'$")
    public void iCreateAccountWithNameName(String name) {
        this.account = new BankAccount(name);
    }

    @And("^I deposit '(\\d+)' to account with name '(.*)'$")
    public void iDepositToAccountWithNameName(int value, String accountName) {
        this.depositResult = bank.deposit(accountName, value);
    }

    @Then("^User is present in bank$")
    public void userIsPresentInBank() {
        Assert.assertTrue(userInsertResult);
        Assert.assertEquals(1, bank.numberOfUsers());
    }

    @Then("^User is not present in bank$")
    public void userIsNotPresentInBank() {
        Assert.assertFalse(userInsertResult);
        Assert.assertEquals(1, bank.numberOfUsers());
    }

    @Then("^Account is present in bank$")
    public void accountIsPresentInBank() {
        Assert.assertEquals(1, bank.numberOfAccounts());
        Assert.assertTrue(accountCreationResult);
    }

    @Then("^Account is not present in bank$")
    public void accountIsNotPresentInBank() {
        Assert.assertEquals(0, bank.numberOfAccounts());
        Assert.assertFalse(accountCreationResult);
    }

    @Then("^User has only '(\\d+)' account in bank$")
    public void userHasOnlyAccountInBank(int numberOfAccounts) {
        Assert.assertEquals(numberOfAccounts, bank.getNumberOfAccountsFor(user));
    }

    @Then("^Account with name '(.*)' has amount of '(\\d+)'$")
    public void accountWithNameNameHasAmountOf(String accountName, int amount) {
        Assert.assertTrue(depositResult);
        Assert.assertEquals(amount, bank.getAccountWithId(accountName).getAmount());
    }
}
