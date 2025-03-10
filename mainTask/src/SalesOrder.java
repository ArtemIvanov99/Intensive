public class SalesOrder extends Order {
    SalesOrder(User user, String toolName){
        super(user, toolName);
    }
    @Override
    public double getBasePrice(){
        return ToolList.toolHub.get(toolName).salesPrice;
    }
    @Override
    public double getDiscount(){
        if (user.getAge()>=60)
            return 0.9;
        else return 1;
    }
    @Override
    public String description() {
        return "Sale: ";
    }
}
