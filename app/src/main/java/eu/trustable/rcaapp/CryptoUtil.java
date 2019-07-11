package eu.trustable.rcaapp;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.zxing.WriterException;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX500NameUtil;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPublicKey;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CryptoUtil {

    private static final String TAG = "CryptoUtil";

    SecureRandom random = new SecureRandom();
    Provider BC = new BouncyCastleProvider();

    public CryptoUtil(){

        final Provider provider = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
        Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
    }

    public KeyPair createKeyPair(String keyTypeLength) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {

        if( "Curve 25519".equals(keyTypeLength)){
            return createKeyPairEC25519();
        }else if("RSA 2048".equals(keyTypeLength)){
            return createKeyPairRSA(2048);
        }else if("RSA 4096".equals(keyTypeLength)){
            return createKeyPairRSA(4096);
        }else{
            throw new NoSuchAlgorithmException("unexpected algo / length '" + keyTypeLength + "'");
        }
    }

    public KeyPair createKeyPairRSA(final int keyLength) throws NoSuchAlgorithmException {


        String keyAlgo = "RSA";

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(keyAlgo, BC);
        keyGen.initialize(keyLength, random);

        KeyPair pair = keyGen.generateKeyPair();
        return pair;
    }

    public KeyPair createKeyPairEC25519() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {

        // Create a curve25519 parameter spec
        X9ECParameters params = CustomNamedCurves.getByName("curve25519");
        ECParameterSpec ecParameterSpec = new ECParameterSpec(params.getCurve(), params.getG(), params.getN(), params.getH(), params.getSeed());

        KeyPairGenerator g = KeyPairGenerator.getInstance("ECDSA", "BC");
        g.initialize(ecParameterSpec, random);
        KeyPair pair = g.generateKeyPair();

        return pair;
    }

    public X509Certificate buildSelfSignedCertificate(KeyPair pair, X500Name subject, int validityDays, String keyTypeLength)
            throws GeneralSecurityException, OperatorCreationException {

        String signAlg = "SHA256WITHRSA";

        if( "Curve 25519".equals(keyTypeLength)){
            signAlg = "SHA256WITHECDSA";
        }

        // create the certificate e.g. using 'SHA256WITHRSA'
        ContentSigner sigGen = new JcaContentSignerBuilder(signAlg).build(pair.getPrivate());

        X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(
                subject,    //Issuer == subject
                new BigInteger(12, random), // serial
                new Date(System.currentTimeMillis() - 50000),   //Valid from
                new Date((long)(System.currentTimeMillis() + validityDays*24L*3600L*1000L) ),    //Valid to
                subject,            //Subject
                pair.getPublic()    //Publickey to be associated with the certificate
        );

        X509Certificate cert = new JcaX509CertificateConverter().setProvider(BC).getCertificate(certGen.build(sigGen));

        cert.checkValidity(new Date());

        cert.verify(pair.getPublic());
        // check verifies with contained key
        cert.verify(cert.getPublicKey());

        ByteArrayInputStream bIn = new ByteArrayInputStream(cert.getEncoded());
        CertificateFactory fact = CertificateFactory.getInstance("X.509", BC);

        return (X509Certificate) fact.generateCertificate(bIn);
    }

    public X509Certificate signCertificateRequest( X509Certificate issuerCert, PrivateKey privKey, String csrPem) throws IOException, NoSuchAlgorithmException, OperatorCreationException, CertificateException {

        PKCS10CertificationRequest p10CSR = convertPemToPKCS10CertificationRequest(csrPem);

        JcaX500NameUtil nameUtil = new JcaX500NameUtil();
        X500Name issuer = nameUtil.getSubject(issuerCert);
        X500Name subject = p10CSR.getSubject();

        Date dateOfIssuing = new Date();              // time from which certificate is valid
        Calendar expiryCal = Calendar.getInstance();
        expiryCal.add(Calendar.YEAR, 1);             // time after which certificate is not valid
        Date dateOfExpiry = expiryCal.getTime();


        BigInteger serialNumber = BigInteger.valueOf( new Random().nextLong()).abs();

        Log.d(TAG, "certification request for subject '" + subject + "'");

        X509v3CertificateBuilder certBuilder = new X509v3CertificateBuilder(issuer,
                serialNumber,
                dateOfIssuing, dateOfExpiry,
                subject,
                p10CSR.getSubjectPublicKeyInfo());

//    certBuilder.addExtension(Extension.basicConstraints, true, new BasicConstraints(true));

//    KeyUsage usage = new KeyUsage(KeyUsage.keyCertSign | KeyUsage.digitalSignature | KeyUsage.keyEncipherment | KeyUsage.dataEncipherment | KeyUsage.cRLSign);
        KeyUsage usage = new KeyUsage( KeyUsage.digitalSignature | KeyUsage.nonRepudiation | KeyUsage.dataEncipherment | KeyUsage.keyEncipherment );
        certBuilder.addExtension(Extension.keyUsage, false, usage);

        certBuilder.addExtension(Extension.authorityKeyIdentifier, false, new JcaX509ExtensionUtils().createAuthorityKeyIdentifier(issuerCert.getPublicKey()) );


        ContentSigner sigGen = new JcaContentSignerBuilder(issuerCert.getSigAlgName()).build(privKey);

        byte[] certBytes = certBuilder.build(sigGen).getEncoded();

        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        X509Certificate issuedCertificate = getCertificateFromBytes(certBytes);

        Log.d(TAG, "certificate created for CSR for subject '" + issuedCertificate.getSubjectDN().getName() + "'");

        return issuedCertificate;
    }


    private PKCS10CertificationRequest convertPemToPKCS10CertificationRequest(String pem) {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        PKCS10CertificationRequest csr = null;
        ByteArrayInputStream pemStream = null;
        try {
            pemStream = new ByteArrayInputStream(pem.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Log.e(TAG,"UnsupportedEncodingException, convertPemToPublicKey", ex);
        }

        Reader pemReader = new BufferedReader(new InputStreamReader(pemStream));
        PEMParser pemParser = new PEMParser(pemReader);

        try {
            Object parsedObj = pemParser.readObject();

            System.out.println("PemParser returned: " + parsedObj);

            if (parsedObj instanceof PKCS10CertificationRequest) {
                csr = (PKCS10CertificationRequest) parsedObj;

            }
        } catch (IOException ex) {
            Log.e(TAG,"IOException, convertPemToPublicKey", ex);
        }

        return csr;
    }

    public X509Certificate getCertificateFromBytes(byte[] certBytes) throws CertificateException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(certBytes));
        return certificate;
    }

    /**
     *
     * @param pem
     * @return
     * @throws GeneralSecurityException
     */
    public PrivateKey convertPemToPrivateKey(final String pem)
            throws GeneralSecurityException {

        PrivateKey privKey = null;
        ByteArrayInputStream pemStream = null;
        try {
            pemStream = new ByteArrayInputStream(pem.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Log.e(TAG,"UnsupportedEncodingException, PrivateKey", ex);
            throw new GeneralSecurityException(
                    "Parsing of PEM file failed due to encoding problem! Not PEM encoded?");
        }

        Reader pemReader = new InputStreamReader(pemStream);
        PEMParser pemParser = new PEMParser(pemReader);

        try {
            Object parsedObj = pemParser.readObject();

            if (parsedObj == null) {
                throw new GeneralSecurityException(
                        "Parsing of certificate failed! Not PEM encoded?");
            }

            Log.d(TAG,"PemParser returned: " + parsedObj);

            if (parsedObj instanceof PrivateKeyInfo) {
                privKey = new JcaPEMKeyConverter().setProvider("BC")
                        .getPrivateKey((PrivateKeyInfo) parsedObj);
            } else {
                throw new GeneralSecurityException(
                        "Unexpected parsing result: "
                                + parsedObj.getClass().getName());
            }

        } catch (IOException ex) {
            Log.d(TAG,"IOException, convertPemToCertificate", ex);
            throw new GeneralSecurityException(
                    "Parsing of certificate failed! Not PEM encoded?");
        } finally {
            try {
                pemParser.close();
            } catch (IOException e) {
                // just ignore
                Log.d(TAG,"IOException on close()", e);
            }
        }

        return privKey;
    }


    public String certToPem(byte[] cert) {
        try {
            StringWriter swPem = new StringWriter();
            try (PemWriter writer = new PemWriter(swPem)) {
                writer.writeObject(new PemObject("CERTIFICATE", cert));
            }
            String certPem = swPem.toString();
            Log.d(TAG, "writing certificate as PEM:\n" + certPem);
            return certPem;
        } catch (IOException e) {
            Log.e(TAG, "problem encoding QR code", e);
        } //end of catch block

        return "";
    }

}
