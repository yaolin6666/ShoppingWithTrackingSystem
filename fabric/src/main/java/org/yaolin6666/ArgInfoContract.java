package org.yaolin6666;

import com.alibaba.fastjson2.JSON;
import org.apache.commons.lang3.StringUtils;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.*;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;

@Contract(
        name = "ArgInfo",
        info = @Info(
                title = "ArgInfo contract",
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
public class ArgInfoContract implements ContractInterface {
    @Transaction
    public void initLedger(final Context ctx){
        ChaincodeStub stub= ctx.getStub();
        for(int i= 0;i<10;i++){
            ArgInfo argInfo=new ArgInfo().setArgInfoID(Integer.toString(i));
            stub.putStringState(argInfo.getArgInfoID(),JSON.toJSONString(argInfo));
        }
    }
    /**
     * key 使用uuid生成
     */
    @Transaction
    public ArgInfo queryArgInfo(final Context ctx,final String key){
        ChaincodeStub stub= ctx.getStub();
        String argInfoState=stub.getStringState(key);
        if(StringUtils.isBlank(argInfoState)){
            String errorMessage=String.format("ArgInfo %s does not exist",key);
            throw new ChaincodeException(errorMessage);
        }

        return JSON.parseObject(argInfoState, ArgInfo.class);
    }

    @Transaction
    public ArgInfo createArgInfo(final Context ctx,final String key,String argId,String argInfo,String extraArgInfo){
        ChaincodeStub stub= ctx.getStub();
        String argInfoState=stub.getStringState(key);
        if(StringUtils.isNotBlank(argInfoState)){
            String errorMessage=String.format("ArgInfo %s already exists",key);
            throw new ChaincodeException(errorMessage);
        }
        ArgInfo input=new ArgInfo().setArgInfoID(key).setArgId(argId).setArgInfo(argInfo).setExtraArgInfo(extraArgInfo);
        stub.putStringState(key,JSON.toJSONString(input));
        return input;
    }

    @Transaction
    public ArgInfo updateArgInfo(final Context ctx,final String key,String argId,String argInfo,String extraArgInfo){
        ChaincodeStub stub= ctx.getStub();
        String argInfoState=stub.getStringState(key);
        if(StringUtils.isBlank(argInfoState)){
            String errorMessage=String.format("ArgInfo %s does not exist",key);
            throw new ChaincodeException(errorMessage);
        }
        ArgInfo input=new ArgInfo().setArgInfoID(key).setArgId(argId).setArgInfo(argInfo).setExtraArgInfo(extraArgInfo);
        stub.putStringState(key,JSON.toJSONString(input));
        return input;
    }

    @Transaction
    public ArgInfo deleteArgInfo(final Context ctx,final String key){
        ChaincodeStub stub= ctx.getStub();
        String argInfoState=stub.getStringState(key);
        if(StringUtils.isBlank(argInfoState)){
            String errorMessage=String.format("ArgInfo %s does not exist",key);
            throw new ChaincodeException(errorMessage);
        }
        stub.delState(key);

        return JSON.parseObject(argInfoState, ArgInfo.class);
    }
}
