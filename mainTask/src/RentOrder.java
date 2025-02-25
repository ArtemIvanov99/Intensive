public class RentOrder extends Order {
    int days;
    RentOrder(User user, String toolName, int days){
        super(user, toolName);
        this.days = days;
    }
    public double getBasePrice(){
        return ToolList.toolHub.get(toolName).rentPrice * days;
    }
    public double getDiscount(){
        if (days >= 10)
            return 0.95;
        else return 1;
    }
    @Override
    public String description() {
        return "Rent: ";
    }
}
