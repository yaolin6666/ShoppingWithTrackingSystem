package org.yaolin6666;

import com.alibaba.fastjson2.JSON;
import org.apache.commons.lang3.StringUtils;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.*;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;

@Contract(
        name = "OrderInfo",
        info = @Info(
                title = "OrderInfo contract",
                description = "The hyperlegendary asset transfer",
                version = "0.0.1-SNAPSHOT",
                license = @License(
                        name = "Apache 2.0 License",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"),
                contact = @Contact(
                        email = "a.transfer@example.com",
                        name = "Adrian Transfer",
                        url = "https://hyperledger.example.com")))
@Default
public class DeliverInfoContract implements ContractInterface {
    @Transaction
    public void initLedger(final Context ctx){
        ChaincodeStub stub= ctx.getStub();
        for(int i= 0;i<10;i++){
            OrderInfo orderInfo=new OrderInfo().setOrderInfoId(Integer.toString(i));
            stub.putStringState(orderInfo.getOrderInfoId(), JSON.toJSONString(orderInfo));
        }
    }
    /**
     * key 使用uuid生成
     */
    @Transaction
    public DeliverInfo queryOrderInfo(final Context ctx,final String key){
        ChaincodeStub stub= ctx.getStub();
        String orderInfoState=stub.getStringState(key);
        if(StringUtils.isBlank(orderInfoState)){
            String errorMessage=String.format("DeliverInfo %s does not exist",key);
            throw new ChaincodeException(errorMessage);
        }

        return JSON.parseObject(orderInfoState, DeliverInfo.class);
    }

    @Transaction
    public DeliverInfo createDeliverInfo(final Context ctx,final String key,String deliverId,String deliverInfo,String orderId,String extraDeliverInfo){
        ChaincodeStub stub= ctx.getStub();
        String deliverInfoState=stub.getStringState(key);
        if(StringUtils.isNotBlank(deliverInfoState)){
            String errorMessage=String.format("DeliverInfo %s already exists",key);
            throw new ChaincodeException(errorMessage);
        }
        DeliverInfo input=new DeliverInfo().setDeliverInfoId(key).setDeliverId(deliverId).setOrderId(orderId).setDeliverInfo(deliverInfo).setExtraDeliverInfo(extraDeliverInfo);
        stub.putStringState(key,JSON.toJSONString(input));
        return input;
    }

    @Transaction
    public DeliverInfo updateDeliverInfo(final Context ctx,final String key,String deliverId,String deliverInfo,String orderId,String extraDeliverInfo){
        ChaincodeStub stub= ctx.getStub();
        String deliverInfoState=stub.getStringState(key);
        if(StringUtils.isBlank(deliverInfoState)){
            String errorMessage=String.format("DeliverInfo %s does not exist",key);
            throw new ChaincodeException(errorMessage);
        }
        DeliverInfo input=new DeliverInfo().setDeliverInfoId(key).setDeliverId(deliverId).setOrderId(orderId).setDeliverInfo(deliverInfo).setExtraDeliverInfo(extraDeliverInfo);
        stub.putStringState(key,JSON.toJSONString(input));
        return input;
    }

    @Transaction
    public DeliverInfo deleteDeliverInfo(final Context ctx,final String key){
        ChaincodeStub stub= ctx.getStub();
        String deliverInfoState=stub.getStringState(key);
        if(StringUtils.isBlank(deliverInfoState)){
            String errorMessage=String.format("DeliverInfo %s does not exist",key);
            throw new ChaincodeException(errorMessage);
        }
        stub.delState(key);
        return JSON.parseObject(deliverInfoState, DeliverInfo.class);
    }
}
