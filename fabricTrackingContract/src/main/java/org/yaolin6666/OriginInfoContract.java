package org.yaolin6666;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.*;
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
            OriginInfo originInfo = new OriginInfo().setOriginInfo(Integer.toString(i));
            stub.putStringState(Integer.toString(i),new String("Test"+Integer.toString(i)));
        }
    }

    /**
     * key 使用uuid生成
     */
    @Transaction
    public String queryOriginInfo(final Context ctx, final String key) {
        ChaincodeStub stub = ctx.getStub();
        String argInfoState = stub.getStringState(key);
        return argInfoState;
    }

    @Transaction
    public OriginInfo createOriginInfo(final Context ctx, final String key, String originInfo) {
        ChaincodeStub stub = ctx.getStub();
        String argInfoState = stub.getStringState(key);
        OriginInfo input = new OriginInfo().setOriginInfoID(key).setOriginInfo(originInfo);
        stub.putStringState(key, originInfo);
        return input;
    }

    @Transaction
    public String updateOriginInfo(final Context ctx, final String key, String originInfo) {
        ChaincodeStub stub = ctx.getStub();
        String argInfoState = stub.getStringState(key);
        OriginInfo input = new OriginInfo().setOriginInfoID(key).setOriginInfo(originInfo);
        stub.putStringState(key,originInfo);
        return argInfoState;
    }

    @Transaction
    public String deleteOriginInfo(final Context ctx, final String key) {
        ChaincodeStub stub = ctx.getStub();
        String argInfoState = stub.getStringState(key);
        stub.delState(key);
        return argInfoState;
    }
}
