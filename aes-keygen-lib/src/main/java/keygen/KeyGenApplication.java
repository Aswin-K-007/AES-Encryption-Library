package keygen;

import java.io.File;
import java.security.KeyPair;
import java.security.cert.X509Certificate;

public class KeyGenApplication {

    public static void main(String[] args) throws Exception {

        if (args.length < 3) {
            System.out.println("Usage:");
            System.out.println("java -jar keygen-tool.jar <output-folder> <public-cert-file> <private-key-file> [validity-days]");
            System.exit(1);
        }

        String outputFolder = args[0];
        String publicCertFile = args[1];
        String privateKeyFile = args[2];

        int validityDays = 365; // default
        if (args.length >= 4) {
            validityDays = Integer.parseInt(args[3]);
        }

        // Create folder if not exists
        File folder = new File(outputFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Generate keys
        KeyPair keyPair = KeyGeneratorUtil.generateRSAKeyPair();
        X509Certificate cert =
                CertificateUtil.createSelfSignedCertificate(keyPair, validityDays);

        // Full file paths
        String publicPath = outputFolder + File.separator + publicCertFile;
        String privatePath = outputFolder + File.separator + privateKeyFile;

        // Write files
        CertificateUtil.writePemFile(
                publicPath,
                "CERTIFICATE",
                cert.getEncoded()
        );

        CertificateUtil.writePemFile(
                privatePath,
                "PRIVATE KEY",
                keyPair.getPrivate().getEncoded()
        );

        System.out.println("======================================");
        System.out.println("Keys Generated Successfully!");
        System.out.println("Folder      : " + outputFolder);
        System.out.println("Public Cert : " + publicPath);
        System.out.println("Private Key : " + privatePath);
        System.out.println("Expiry      : " + cert.getNotAfter());
        System.out.println("======================================");
    }
}