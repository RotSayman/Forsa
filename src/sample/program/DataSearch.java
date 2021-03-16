package sample.program;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.models.Order;

import java.util.Objects;

public class DataSearch
{
    private ObservableList<Order> list;
    private static DataSearch dataSearch;

    private DataSearch() {}

    public ObservableList<Order> getList(){
        if(list == null) list = FXCollections.observableArrayList();
        return list;
    }

    public void removeAll(){
        if(list != null){
            list.removeIf(Objects::nonNull);
        }
    }

    public static DataSearch getData(){
        if(dataSearch == null){
            dataSearch = new DataSearch();
        }
        return dataSearch;
    }
}
