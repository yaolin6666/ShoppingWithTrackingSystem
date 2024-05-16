### 安装教程
https://blog.csdn.net/qq_43707926/article/details/125140732

### 安装指令
~~~
sudo chmod -R a=rwx ./

git clone https://github.com/hyperledger/fabric.git

cd fabric/scripts
git clone -b main https://github.com/hyperledger/fabric-samples.git



wget https://github.com/hyperledger/fabric/releases/download/v2.5.6/hyperledger-fabric-linux-amd64-2.5.6.tar.gz
tar -xzvf hyperledger-fabric-linux-amd64-2.5.6.tar.gz -C ./


sudo ./bootstrap.sh

cd fabric-samples
mkdir chaincode/fabricTrackingContract



cd test-network

sudo ./network.sh up
sudo ./network.sh createChannel


export PATH=${PWD}/../bin:$PATH
export FABRIC_CFG_PATH=$PWD/../config/



peer lifecycle chaincode package fabricTrackingContract.tar.gz --path ../chaincode/fabricTrackingContract/ --lang java --label fabricTrackingContractv1


export CORE_PEER_TLS_ENABLED=true
export CORE_PEER_LOCALMSPID="Org1MSP"
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt
export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp
export CORE_PEER_ADDRESS=localhost:7051

peer lifecycle chaincode install fabricTrackingContract.tar.gz


export CORE_PEER_LOCALMSPID="Org2MSP"
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls/ca.crt
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls/ca.crt
export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org2.example.com/users/Admin@org2.example.com/msp
export CORE_PEER_ADDRESS=localhost:9051

peer lifecycle chaincode install fabricTrackingContract.tar.gz


peer lifecycle chaincode queryinstalled

Package ID: fabricTrackingContractv1:7ab1b9e70b4a1396fcbebd72ecc218df36cabac350217f8d5107045f8b2ca8c2, Label: fabricTrackingContractv1



export CC_PACKAGE_ID=fabricTrackingContractv1:7ab1b9e70b4a1396fcbebd72ecc218df36cabac350217f8d5107045f8b2ca8c2

peer lifecycle chaincode approveformyorg -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --channelID mychannel --name fabricTrackingContract --version 1.0 --package-id $CC_PACKAGE_ID --sequence 1 --tls --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem


export CORE_PEER_LOCALMSPID="Org1MSP"
export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt
export CORE_PEER_ADDRESS=localhost:7051


peer lifecycle chaincode approveformyorg -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --channelID mychannel --name fabricTrackingContract --version 1.0 --package-id $CC_PACKAGE_ID --sequence 1 --tls --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem


peer lifecycle chaincode checkcommitreadiness --channelID mychannel --name fabricTrackingContract --version 1.0 --sequence 1 --tls --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem --output json


peer lifecycle chaincode commit -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --channelID mychannel --name fabricTrackingContract --version 1.0 --sequence 1 --tls --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem --peerAddresses localhost:7051 --tlsRootCertFiles ${PWD}/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt --peerAddresses localhost:9051 --tlsRootCertFiles ${PWD}/organizations/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls/ca.crt


peer lifecycle chaincode querycommitted --channelID mychannel --name fabricTrackingContract --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem



peer chaincode invoke -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --tls --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem -C mychannel -n fabricTrackingContract --peerAddresses localhost:7051 --tlsRootCertFiles ${PWD}/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt --peerAddresses localhost:9051 --tlsRootCertFiles ${PWD}/organizations/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls/ca.crt -c '{"function":"createOriginInfo","Args":["test" , "test"]}'

peer chaincode query -C mychannel -n fabricTrackingContract -c '{"Args":["queryOriginInfo" , "test"]}'


~~~