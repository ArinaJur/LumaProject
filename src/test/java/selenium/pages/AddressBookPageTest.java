package selenium.pages;

import api.models.Address;
import api.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AddressBookPageTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(AddressBookPageTest.class);

    @Test
    @Ignore
    public void testAddressBookPage() {
        User user = new User();
        Address address = new Address();
        MainPage mainPage = new MainPage(getDriver());

        mainPage.clickCreateAnAccountButton()
                .enterFirstName(user.getFirstname())
                .enterLastName(user.getLastname())
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .enterConfirmPassword(user.getPassword())
                .clickCreateAccountButton();

        logger.error(getDriver().getTitle());
        AddressBookPage bookPage = mainPage.gotoAddressBook();
        bookPage.isVisibleHeader();
        bookPage.isVisibleContactInformation();
        bookPage.isVisibleAddress();
        bookPage.changeFirstName(address.getFirstName())
                .changeLastName(address.getLastName())
                .fillCompanyName(address.getCompanyName())
                .changePhoneNumber(address.getPhoneNumber())
                .changeStreetAddress(address.getStreetAddress())
                .changeCity(address.getCity())
                .changeZipPostalCode(address.getZipPostalCode())
                .fillStreetAddress2(address.getStreetAddress())
                .fillStreetAddress3(address.getStreetAddress())
                .changeCity(address.getCity())
                .changeZipPostalCode(address.getZipPostalCode())
                .selectCountryByName("Australia") //Alaska
                .selectRegionByValue("569")
                .saveAddress();
        bookPage.isVisibleMessage();

        if (mainPage.clickShevron().isLoggedIn()) {
            mainPage.clickLogoutAccount().verifyLogOut();
            logger.error(mainPage.getSignOutText());
        }
    }
}
