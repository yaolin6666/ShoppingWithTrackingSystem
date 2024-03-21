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
public class ArgInfo {
    @Property
    private String argInfoID;
    @Property
    private String argId;
    @Property
    private String argInfo;
    @Property
    private String extraArgInfo;
}
