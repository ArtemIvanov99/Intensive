package collections;
import java.util.ArrayList;

public class creatingCollections {
    public ArrayList<String> createAndAddElements() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        return list;
    }
    // Метод для добавления элемента по индексу
    public void addElementAtIndex(ArrayList<String> list, int index, String element) {
        list.add(index, element);
    }
    // Метод для получения элемента по индексу
    public String getElementByIndex(ArrayList<String> list, int index) {
        return list.get(index);
    }
    // Метод для изменения элемента по индексу
    public void setElementAtIndex(ArrayList<String> list, int index, String element) {
        list.set(index, element);
    }
    // Метод для удаления элемента по значению
    public void removeElementByValue(ArrayList<String> list, String element) {
        list.remove(element);
    }
    // Метод для удаления элемента по индексу
    public void removeElementByIndex(ArrayList<String> list, int index) {
        list.remove(index);
    }
    // Метод для проверки наличия элемента
    public boolean containsElement(ArrayList<String> list, String element) {
        return list.contains(element);
    }
    // Метод для получения размера списка
    public int getSize(ArrayList<String> list) {
        return list.size();
    }
    // Метод для очистки списка
    public void clearList(ArrayList<String> list) {
        list.clear();
    }
    // Метод для вывода элементов списка
    public void printList(ArrayList<String> list) {
        System.out.println("Элементы списка:");
        for (String item : list) {
            System.out.println(item);
        }
    }
}