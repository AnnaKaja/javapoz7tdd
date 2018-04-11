package com.sda.service.bookstore;

import com.sda.services.bookstore.Book;
import com.sda.services.bookstore.Bookstore;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class BookstoreSteps {
    private Bookstore bookstore;
    private Book book;
    private Object bookstore$;

    @Given("^I instantiate bookstore$")
    public void I_instantiate_bookstore() {
        this.bookstore = new Bookstore();
    }

    @And("^I create book$")
    public void I_create_book() {
        this.book = new Book("Ogniem i mieczem", 800,
                "Henryk Sienkiewicz", "abc123");

    }

    @When("^I add book to bookstore$")
    public void I_add_book_to_bookstore() {
        this.bookstore.add(this.book);

    }

    @And("^I edit title of book$")
    public void I_edit_title_of_book() {
        this.bookstore.updateTitle(book, "Krzyzacy");
    }

    @Then("^Book is present in bookstore$")
    public void Book_is_present_in_bookstore() {
        Assert.assertEquals(1, this.bookstore.getBooks().size());
    }

    @Then("^Book's title has changed$")
    public void Books_title_has_changed() {
        Assert.assertEquals("Krzyzacy", this.bookstore.getBooks().get(0).getTitle());

    }
}
