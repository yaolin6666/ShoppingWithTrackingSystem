package org.yaolin6666;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

@DataType
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class OriginInfo {
    @Property
    private String originInfoID;
    @Property
    private String originInfo;
}
