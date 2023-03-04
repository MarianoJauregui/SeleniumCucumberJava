package pages;

public class AmazonSearchPage extends BasePage {

    private String searchBox = "//input[@id='twotabsearchtextbox']";
    private String searchButton = "//input[@id='nav-search-submit-button']";
    private String thirdResult = "(//span[@class='a-size-medium a-color-base a-text-normal']) [1]";
    private String addToCartButton = "//input[@id='add-to-cart-button']";
    private String addedMessageText = "//*[contains(text(), 'Agregado al carrito')]";
    
    public AmazonSearchPage(){
        super(driver);
    }

    public void navigateToAmazon(){
        navigateTo("https://www.amazon.com/");
    }

    public void enterSearchCriteria(String criteria){
        write(searchBox, criteria);
    }

    public void clickSearch(){
        clickElement(searchButton);
    }

    public void goToPage(String pageNumber){
        goToLinkText(pageNumber);
    }

    public void pickThirdItem(){
        clickElement(thirdResult);
    }

    public void addToCart(){
        clickElement(addToCartButton);
    }

    public String addToCartMessage(){
        return textFromElement(addedMessageText);
    }
}
