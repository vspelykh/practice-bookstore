package ua.hillel.bookstore.persistence.entity;

/*status of order*/
public enum Status {
    NEW("New"), PENDING("Pending"), SHIPPED("Shipped"),
    DELIVERED("Delivered"), CANCELED("Canceled");

    final String camelCase;

    Status(String camelCase) {
        this.camelCase = camelCase;
    }

    public String getCamelCase() {
        return camelCase;
    }
}
