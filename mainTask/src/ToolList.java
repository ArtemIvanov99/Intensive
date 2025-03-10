import java.util.HashMap;
import java.util.Map;

public class ToolList {
    //коллекция для хранения доступных инструментов.
    public static Map<String, Tool> toolHub = new HashMap<>();;
    static{
        toolHub.put("Hummer", new Tool("Hummer", 100, 10));
        toolHub.put("Drill", new Tool("Drill", 200, 20));
    }
    //вложенный классс для создания инструментов.
    public static class Tool {
        String toolName;
        double salesPrice;
        double rentPrice;

        Tool(String toolName, int salesPrice, int rentPrice){
            this.toolName =toolName;
            this.salesPrice = salesPrice;
            this.rentPrice = rentPrice;
        }
        public String toString(){
            return "Instrument: " + toolName + " Price of Sale: " + salesPrice + " Price of Rent: " + rentPrice;
        }
    }
}

