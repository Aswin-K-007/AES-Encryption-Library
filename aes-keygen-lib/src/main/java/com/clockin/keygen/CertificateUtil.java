package com.clockin.keygen;


import java.io.FileWriter;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.sql.Date;
import java.util.Base64;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

public class CertificateUtil {

    public static X509Certificate createSelfSignedCertificate(
            KeyPair keyPair, int daysValid) throws Exception {

        long now = System.currentTimeMillis();
        Date startDate = new Date(now);
        Date expiryDate = new Date(now + (long) daysValid * 86400000);

        X500Name dnName = new X500Name("CN=DepositService, O=Bank, C=IN");
        BigInteger serial = BigInteger.valueOf(now);

        JcaX509v3CertificateBuilder certBuilder =
                new JcaX509v3CertificateBuilder(
                        dnName, serial, startDate, expiryDate,
                        dnName, keyPair.getPublic());

        ContentSigner signer =
                new JcaContentSignerBuilder("SHA256WithRSA")
                        .build(keyPair.getPrivate());

        X509CertificateHolder holder = certBuilder.build(signer);

        return new JcaX509CertificateConverter().getCertificate(holder);
    }

    public static void writePemFile(String fileName,
                                    String header,
                                    byte[] content) throws Exception {

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("-----BEGIN " + header + "-----\n");
            writer.write(Base64.getMimeEncoder(64,
                    new byte[]{'\n'}).encodeToString(content));
            writer.write("\n-----END " + header + "-----\n");
        }
    }
}
