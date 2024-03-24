package com.Shopping.config;

import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hyperledger.fabric.client.CallOption;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.Gateway;
import org.hyperledger.fabric.client.Network;
import org.hyperledger.fabric.client.identity.Identities;
import org.hyperledger.fabric.client.identity.Signers;
import org.hyperledger.fabric.client.identity.X509Identity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;


@Configuration
@AllArgsConstructor
@Slf4j
public class HyperLedgerFabricGatewayConfig {

    private static final String MSP_ID = "Org1MSP";
    private static final String CHANNEL_NAME = "mychannel";
    private static final String CHAINCODE_NAME = "fabricTrackingContract";
    private static final String CONTRACT_NAME = "OriginInfoContract";
    private static final Path CRYPTO_PATH = Paths.get(System.getProperty("user.dir")+"\\backend\\src\\main\\resources\\crypto-config\\peerOrganizations\\org1.example.com\\");
    // Path to user certificate.
    private static final Path CERT_DIR_PATH = CRYPTO_PATH.resolve(Paths.get("users\\User1@org1.example.com\\msp\\signcerts\\User1@org1.example.com-cert.pem"));
    // Path to user private key directory.
    private static final Path KEY_DIR_PATH = CRYPTO_PATH.resolve(Paths.get("users\\User1@org1.example.com\\msp\\keystore\\priv_sk"));
    // Path to peer tls certificate.
    private static final Path TLS_CERT_PATH = CRYPTO_PATH.resolve(Paths.get("peers\\peer0.org1.example.com\\tls\\ca.crt"));

    // Gateway peer end point.
    private static final String PEER_ENDPOINT = "192.168.42.131:7051";
    private static final String OVERRIDE_AUTH = "peer0.org1.example.com";

    @Bean
    public Gateway gateway() throws Exception {

        BufferedReader certificateReader = Files.newBufferedReader(CERT_DIR_PATH, StandardCharsets.UTF_8);

        X509Certificate certificate = Identities.readX509Certificate(certificateReader);

        BufferedReader privateKeyReader = Files.newBufferedReader(KEY_DIR_PATH, StandardCharsets.UTF_8);

        PrivateKey privateKey = Identities.readPrivateKey(privateKeyReader);

        Gateway gateway = Gateway.newInstance()
                .identity(new X509Identity(MSP_ID , certificate))
                .signer(Signers.newPrivateKeySigner(privateKey))
                .connection(newGrpcConnection())
                .evaluateOptions(CallOption.deadlineAfter(5, TimeUnit.SECONDS))
                .endorseOptions(CallOption.deadlineAfter(15, TimeUnit.SECONDS))
                .submitOptions(CallOption.deadlineAfter(5, TimeUnit.SECONDS))
                .commitStatusOptions(CallOption.deadlineAfter(1, TimeUnit.MINUTES))
                .connect();

        log.info("connected fabric gateway success");
        return gateway;
    }
    private ManagedChannel newGrpcConnection() throws IOException, CertificateException {
        Reader tlsCertReader = Files.newBufferedReader(TLS_CERT_PATH);
        X509Certificate tlsCert = Identities.readX509Certificate(tlsCertReader);

        return NettyChannelBuilder.forTarget(PEER_ENDPOINT)
                .sslContext(GrpcSslContexts.forClient().trustManager(tlsCert).build())
                .overrideAuthority(OVERRIDE_AUTH)
                .build();
    }

    @Bean
    public Network network(Gateway gateway) {
        return gateway.getNetwork(CHANNEL_NAME);
    }

    @Bean
    public Contract catContract(Network network) {
        return network.getContract(CHAINCODE_NAME);
    }

}