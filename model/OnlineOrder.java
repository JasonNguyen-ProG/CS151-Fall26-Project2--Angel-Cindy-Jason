package model;

public class OnlineOrder extends Order {
    private String customerEmail;
    private String deliveryAddress;
    private String paymentMethod;

    public OnlineOrder(int orderID, String customerEmail,
            String deliveryAddress, String paymentMethod) {

        super(orderID);

        setCustomerEmail(customerEmail);
        setDeliveryAddress(deliveryAddress);
        setPaymentMethod(paymentMethod);
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setCustomerEmail(String customerEmail) {
        if (customerEmail == null || !customerEmail.contains("@")) {
            throw new IllegalArgumentException("Please enter a valid email address");
        }

        this.customerEmail = customerEmail;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        if (deliveryAddress == null || deliveryAddress.isBlank()) {
            throw new IllegalArgumentException("Delivery address cannot be empty");
        }

        this.deliveryAddress = deliveryAddress;
    }

    public void setPaymentMethod(String paymentMethod) {
        if (paymentMethod == null || paymentMethod.isBlank()) {
            throw new IllegalArgumentException("Payment method cannot be empty");
        }

        this.paymentMethod = paymentMethod;
    }

    public boolean isDeliveryOrder() {
        return true;
    }

    public String getCustomerSummary() {
        return "Online order for " + customerEmail
                + " delivered to " + deliveryAddress;
    }

    @Override
    public String toString() {
        return "OnlineOrder{"
                + "orderID=" + getOrderID()
                + ", customerEmail='" + customerEmail + '\''
                + ", deliveryAddress='" + deliveryAddress + '\''
                + ", paymentMethod='" + paymentMethod + '\''
                + ", status='" + getStatus() + '\''
                + '}';
    }
}