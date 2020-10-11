public class DataDrivenManger {

    public NewUser userObject = new NewUser();
    public String selectedItem = "";
    public void selectItem(String name, String color, String size){
        selectedItem = name + " - Color : " +  color + ", Size : " + size;
    }
}

