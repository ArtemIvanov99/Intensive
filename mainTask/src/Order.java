public abstract class Order {
    User user;
    String toolName;

    Order(User user, String toolName) {
        if (!ToolList.toolHub.containsKey(toolName)){
            throw new IllegalArgumentException("Инструмента " + toolName + " не существует");
        }
        this.user = user;
        this.toolName = toolName;
    }
    public abstract double getBasePrice();
    public abstract double getDiscount();
    public abstract String description();

    public double getFinalPrice(){
        return getBasePrice() * getDiscount();
    }

}