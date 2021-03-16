package sample.models;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor

public class Order implements Serializable {
    private long orderListId;
    private Date orderDate;
    private String articul;
    private double price;
    private int quanty;
    private String phone;
    private String status;
    private String form;
    private String description;

}
