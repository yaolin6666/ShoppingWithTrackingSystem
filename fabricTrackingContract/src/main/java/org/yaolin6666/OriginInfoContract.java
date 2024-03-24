package org.yaolin6666;

import com.alibaba.fastjson2.JSON;
import org.apache.commons.lang3.StringUtils;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.*;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;

@Contract(
        name = "OriginInfoContract",
        info = @Info(
                title = "OriginInfo contract",
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
public class OriginInfoContract implements ContractInterface {
    @Transaction
    public void initLedger(final Context ctx) {
        ChaincodeStub stub = ctx.getStub();
        for (int i = 0; i < 10; i++) {
            OriginInfo originInfo = new OriginInfo().setOriginInfoID(Integer.toString(i));
            stub.putStringState(originInfo.getOriginInfoID(), JSON.toJSONString(originInfo));
        }
    }

    /**
     * key 使用uuid生成
     */
    @Transaction
    public OriginInfo queryOriginInfo(final Context ctx, final String key) {
        ChaincodeStub stub = ctx.getStub();
        String argInfoState = stub.getStringState(key);
        if (StringUtils.isBlank(argInfoState)) {
            String errorMessage = String.format("OriginInfo %s does not exist", key);
            throw new ChaincodeException(errorMessage);
        }

        return JSON.parseObject(argInfoState, OriginInfo.class);
    }

    @Transaction
    public OriginInfo createOriginInfo(final Context ctx, final String key, String originInfo) {
        ChaincodeStub stub = ctx.getStub();
        String argInfoState = stub.getStringState(key);
        if (StringUtils.isNotBlank(argInfoState)) {
            String errorMessage = String.format("OriginInfo %s already exists", key);
            throw new ChaincodeException(errorMessage);
        }
        OriginInfo input = new OriginInfo().setOriginInfoID(key).setOriginInfo(originInfo);
        stub.putStringState(key, JSON.toJSONString(input));
        return input;
    }

    @Transaction
    public OriginInfo updateOriginInfo(final Context ctx, final String key, String originInfo) {
        ChaincodeStub stub = ctx.getStub();
        String argInfoState = stub.getStringState(key);
        if (StringUtils.isBlank(argInfoState)) {
            String errorMessage = String.format("OriginInfo %s does not exist", key);
            throw new ChaincodeException(errorMessage);
        }
        OriginInfo input = new OriginInfo().setOriginInfoID(key).setOriginInfo(originInfo);
        stub.putStringState(key, JSON.toJSONString(input));
        return input;
    }

    @Transaction
    public OriginInfo deleteOriginInfo(final Context ctx, final String key) {
        ChaincodeStub stub = ctx.getStub();
        String argInfoState = stub.getStringState(key);
        if (StringUtils.isBlank(argInfoState)) {
            String errorMessage = String.format("OriginInfo %s does not exist", key);
            throw new ChaincodeException(errorMessage);
        }
        stub.delState(key);
        return JSON.parseObject(argInfoState, OriginInfo.class);
    }
}
