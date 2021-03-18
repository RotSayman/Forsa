package sample.program;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.models.Order;


public class Data
{
    private ObservableList<Order> list;
    private static Data data;

    private Data() {}

    public ObservableList<Order> getList(){
        if(list == null) list = FXCollections.observableArrayList();
        return list;
    }

    public void removeAll(){
        if(list != null){
            synchronized (list){
                list.clear();
            }
        }

    }

    public static Data getData(){
        if(data == null){
            data = new Data();
        }
        return data;
    }
}
