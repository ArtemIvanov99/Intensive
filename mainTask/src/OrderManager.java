import java.util.*;

public class OrderManager {
    //хранит список доступных заказов.
    Map<String, List<Order>> OrderList = new HashMap<>();

    //добавление новых заказов.
    public void addOrder(Order order) {
        OrderList.computeIfAbsent(order.user.getFirstName(), k-> new ArrayList<>()).add(order);
    }
    //вывод заказов по конкретному user.
    public void printOrders(User user){
        double totalCost = 0;
        List<Order> currentClient = OrderList.get(user.getFirstName());
        if (currentClient == null){
            System.out.println(user + " No orders found");
            return;
        }
        currentClient.sort(Comparator
                .comparing((Order order)->order instanceof SalesOrder ? 0:1)
                .thenComparingDouble(order-> order.getFinalPrice()));
        for (Order order: currentClient){
            System.out.println(order.description() + order.toolName + " " + order.getFinalPrice());
            totalCost += order.getFinalPrice();;
        }
        System.out.println("Client: " + user.getFirstName() + " " + user.getLastName() + ". Total Cost: " + totalCost);
    }
}
