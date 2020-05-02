package edu.miu.waa.onlineshopping.data.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDER_ITEM")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ITEM_ID")
    private Integer OrderItemId;

    private Integer quantity;

    private Double price;

    @OneToOne(fetch = FetchType.EAGER)
    private SellerProductEntity product;

    //Constructors, getters and setters removed for brevity

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItemEntity)) return false;
        return OrderItemId != null && OrderItemId.equals(((OrderItemEntity) o).getOrderItemId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
