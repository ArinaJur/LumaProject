package vfespw.runner;

public class TestData {
    public static final String BASE_URL = "https://magento.softwaretestingboard.com";
    public static final String HOME_END_POINT = "/";
    public static final String SIGN_OUT_END_POINT = "/customer/account/logoutSuccess/";
    public static final String MY_ACCOUNT_END_POINT = "/customer/account/";


    public static final String HOME_TITLE = "Home Page";
    public static final String SIGN_OUT_TITLE = "";
    public static final String  MY_ACCOUNT_TITLE = "My Account";


    public static final String USER_NAME = ProjectUtils.getProperties().getProperty("username");
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Doe";
    public static final String EMAIL = ProjectUtils.getProperties().getProperty("email");
    public static final String PASSWORD = ProjectUtils.getProperties().getProperty("password");
    public static final String FAILED_LOGIN_ERROR_MESSAGE = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
    public static final String EMPTY_CARD_MESSAGE = "You have no items in your shopping cart.";
}