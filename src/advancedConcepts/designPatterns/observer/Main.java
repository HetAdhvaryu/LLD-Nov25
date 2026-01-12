package advancedConcepts.designPatterns.observer;

import advancedConcepts.designPatterns.observer.subscriber.InventoryService;
import advancedConcepts.designPatterns.observer.subscriber.InvoiceGenerator;
import advancedConcepts.designPatterns.observer.subscriber.NotificationService;

public class Main {
    public static void main(String[] args) {
        OrderPlacedSubscriber os = new InventoryService();
        InvoiceGenerator generator = new InvoiceGenerator();
        NotificationService notificationService = new NotificationService();

        AmazonOrderService amazonOrderService = AmazonOrderService.getInstance();
        amazonOrderService.orderPlace();
    }
}
