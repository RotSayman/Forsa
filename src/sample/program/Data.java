package sample.program;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.models.Order;

import java.util.Objects;

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
            list.removeIf(Objects::nonNull);
        }
    }

    public static Data getData(){
        if(data == null){
            data = new Data();
        }
        return data;
    }
}
