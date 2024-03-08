package org.yaolin6666;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

@DataType
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OrderInfo {
    @Property
    private String OrderInfoId;
    @Property
    private String orderId;
    @Property
    private String orderInfo;
}
