package koreatech.cse.domain.rest;

public class Book {
    private String title;
    private String author;
    private String imageUrl;
    private String categoryId;
    private String categoryName;
    private String description;
    private String publisher;
    private String link;
    private String mobileLink;
    private Long price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMobileLink() {
        return mobileLink;
    }

    public void setMobileLink(String mobileLink) {
        this.mobileLink = mobileLink;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setAll(String title, String author, String imageUrl, String description, String publisher, String link, String mobileLink, String categoryId, String categoryName, long price) {
        this.title = title;
        this.author = author;
        this.imageUrl = imageUrl;
        this.description = description;
        this.publisher = publisher;
        this.link = link;
        this.mobileLink = mobileLink;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", publisher='" + publisher + '\'' +
                ", link='" + link + '\'' +
                ", mobileLink='" + mobileLink + '\'' +
                ", price=" + price +
                '}';
    }
}
