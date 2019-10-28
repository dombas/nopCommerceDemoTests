package pl.dominikd.nopcommerce.demo.base;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class AccountTestBase extends TestBase {
    protected final String accountFirstName = "John";
    protected final String accountLastName = "Tester";
    protected final String accountEmail = "ddtestemail@testing.pl";
    protected final String accountPassword = "T@zvKtuvTd8sdqL";
    protected final String accountWrongPassword = "adofiajsif";

    public AccountTestBase() throws JAXBException, FileNotFoundException {
        super();
    }
}
