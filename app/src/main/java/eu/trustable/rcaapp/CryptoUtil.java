package eu.trustable.rcaapp;

import android.util.Log;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DERBMPString;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.pkcs.Attribute;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.CRLReason;
import org.bouncycastle.asn1.x509.ExtendedKeyUsage;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509v2CRLBuilder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX500NameUtil;
import org.bouncycastle.cert.jcajce.JcaX509CRLConverter;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaContentVerifierProviderBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCSException;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import eu.trustable.rcaapp.crypto.OidNameMapper;

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

        return keyGen.generateKeyPair();
    }

    public KeyPair createKeyPairEC25519() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {

        // Create a curve25519 parameter spec
        X9ECParameters params = CustomNamedCurves.getByName("curve25519");
        ECParameterSpec ecParameterSpec = new ECParameterSpec(params.getCurve(), params.getG(), params.getN(), params.getH(), params.getSeed());

        KeyPairGenerator g = KeyPairGenerator.getInstance("ECDSA", "BC");
        g.initialize(ecParameterSpec, random);
        return g.generateKeyPair();
    }

    public X509Certificate buildSelfSignedCertificate(KeyPair pair, X500Name subject, int validityDays, String keyTypeLength)
            throws GeneralSecurityException, OperatorCreationException {

        String signAlg = getSigningAlgoForKeyType(keyTypeLength);

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

    String getSigningAlgoForKeyType(String keyTypeLength) {
        String signAlg = "SHA256WITHRSA";

        if( "Curve 25519".equals(keyTypeLength)){
            signAlg = "SHA256WITHECDSA";
        }
        return signAlg;
    }

    public X509Certificate signCertificateRequest( RootCertificateItem rci, Map<Integer, char[]> passwordMap, String csrPem) throws IOException, GeneralSecurityException, OperatorCreationException, PKCSException {

        X509Certificate issuerCert = getCertificateFromBytes(rci.getCert());

        PKCS10CertificationRequest p10CSR = convertPemToPKCS10CertificationRequest(csrPem);

        if( !isValidSelfsignedCSR(p10CSR) ){
            throw new IOException("Request not self-signed with the new key!");
        }

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


        // retrieve the private key
        PrivateKey privKey = rci.getPrivateKey(passwordMap);
        ContentSigner sigGen = new JcaContentSignerBuilder(rci.getSignAlgo()).build(privKey);

        byte[] certBytes = certBuilder.build(sigGen).getEncoded();

        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        X509Certificate issuedCertificate = getCertificateFromBytes(certBytes);

        Log.d(TAG, "certificate created for CSR for subject '" + issuedCertificate.getSubjectDN().getName() + "'");

        return issuedCertificate;
    }

    public Map<String, String> explainCertificateRequestAttributes(String csrPem) throws IOException, NoSuchAlgorithmException, OperatorCreationException, CertificateException, InvalidKeySpecException, NoSuchProviderException, PKCSException {

        Map<String, String> attrMap = new HashMap<>();

        PKCS10CertificationRequest p10CSR = convertPemToPKCS10CertificationRequest(csrPem);

        Log.d(TAG, "CSR has #" + p10CSR.getAttributes().length + " attributes");

        for( Attribute attr: p10CSR.getAttributes()){

            String attrOid = attr.getAttrType().getId();
            String attrReadableName = OidNameMapper.lookupOid(attrOid);

            if( PKCSObjectIdentifiers.pkcs_9_at_extensionRequest.equals(attr.getAttrType()) ) {
                Log.d(TAG, "CSR contains extensionRequest");
                explainExtensionRequests(attrMap, attr);

            } else if( "certReqExtensions".equals(attrReadableName)){
                Log.d(TAG, "CSR contains attrReadableName");
                explainExtensionRequests(attrMap, attr );
            }else {
                String value = getASN1ValueAsString(attr);
                attrMap.put(attrReadableName, value);
            }
        }

        return attrMap;
    }

    private String getASN1ValueAsString(Attribute attr) {
        return  getASN1ValueAsString(attr.getAttrValues().toArray());
    }

    private String getASN1ValueAsString(ASN1Encodable[] asn1EncArr ) {
        String value = "";
        for (ASN1Encodable asn1Enc : asn1EncArr) {
            if (value.length() > 0) {
                value += ", ";
            }
            value += asn1Enc.toString();
        }
        return value;
    }

    void explainExtensionRequests(Map<String, String> attrMap, Attribute attrExtension ){
        ASN1Set valueSet = attrExtension.getAttrValues();
        Log.d(TAG, "ExtensionRequest / AttrValues has " + valueSet.size() + " elements" );
        for (ASN1Encodable asn1Enc : valueSet) {
            DERSequence derSeq = (DERSequence)asn1Enc;

            Log.d(TAG, "ExtensionRequest / DERSequence has "+derSeq.size()+" elements" );

            for( ASN1Encodable asn1Enc2 : derSeq.toArray()) {

                Log.d(TAG, "ExtensionRequest / asn1Enc2 is a " + asn1Enc2.getClass().getName());

                DERSequence derSeq2 = (DERSequence) asn1Enc2;
                int seq2Size = derSeq2.size();
                Log.d(TAG, "ExtensionRequest / DERSequence2 has " + derSeq2.size() + " elements");
                Log.d(TAG, "ExtensionRequest / DERSequence2[0] is a " + derSeq2.getObjectAt(0).getClass().getName());

                ASN1Encodable asn1EncValue = derSeq2.getObjectAt(1);
                Log.d(TAG, "ExtensionRequest / DERSequence2[1] (asn1EncValue)is a " + asn1EncValue.getClass().getName());


                ASN1ObjectIdentifier objId = (ASN1ObjectIdentifier) (derSeq2.getObjectAt(0));
                String attrReadableName = OidNameMapper.lookupOid(objId.getId());


                if (Extension.subjectAlternativeName.equals(objId)) {
                    DEROctetString derStr = (DEROctetString) derSeq2.getObjectAt(1);
                    byte[] valBytes = derStr.getOctets();

                    GeneralNames names = GeneralNames.getInstance(valBytes);
                    Log.d(TAG, "Attribute value SAN" + names);
                    Log.d(TAG, "SAN values #" + names.getNames().length);

                    for (GeneralName gnSAN : names.getNames()) {
                        Log.d(TAG, "GN " + gnSAN.toString());
                        attrMap.put(attrReadableName, gnSAN.toString());

                    }
                } else if(Extension.basicConstraints.equals(objId)){

                    Log.d(TAG, "second element is of type " + derSeq2.getObjectAt(1).getClass().getName());

                    DEROctetString derStr = (DEROctetString) derSeq2.getObjectAt(seq2Size-1);
                    byte[] valBytes = derStr.getOctets();

                    BasicConstraints bc = BasicConstraints.getInstance(valBytes);

                    String bcString = "";

                    if( bc.isCA()){
                        bcString = "CA ";
                    }
                    BigInteger pathLen = bc.getPathLenConstraint();
                    if( pathLen != null){
                        bcString = "path len " + pathLen;
                    }
                    Log.i(TAG, "Basic constraints has value '" + bcString + "'");
                    attrMap.put(attrReadableName, bcString);

                } else if(Extension.keyUsage.equals(objId)){

                    Log.d(TAG, "second element is of type " + derSeq2.getObjectAt(1).getClass().getName());

                    DEROctetString derStr = (DEROctetString) derSeq2.getObjectAt(seq2Size-1);
                    byte[] valBytes = derStr.getOctets();

                    KeyUsage ku = KeyUsage.getInstance(valBytes);

                    byte[] kuBytes = ku.getBytes();

                    int usageInt = kuBytes[0];
                    if( kuBytes.length > 1){
                        usageInt += kuBytes[1]>>8;
                    }
                    String usageString = usageAsString(usageInt);
                    Log.i(TAG, "key usage has value '" + usageString + " / " + ku.toString() + "'");
                    attrMap.put(attrReadableName, usageString);

                } else if(Extension.extendedKeyUsage.equals(objId)){
                    DEROctetString derStr = (DEROctetString) derSeq2.getObjectAt(1);
                    byte[] valBytes = derStr.getOctets();

                    ExtendedKeyUsage eku = ExtendedKeyUsage.getInstance(valBytes);

                    String exUsageString = "";
                    for(KeyPurposeId kpi: eku.getUsages()){
                        exUsageString += OidNameMapper.lookupOid(kpi.getId()) + " ";
                    }
                    Log.i(TAG, "extended key usage has value '" + exUsageString + "' / '" + eku.toString() + "'");
                    attrMap.put(attrReadableName, exUsageString);

                } else if(Extension.subjectKeyIdentifier.equals(objId)){
                    DEROctetString derStr = (DEROctetString) derSeq2.getObjectAt(1);
                    byte[] valBytes = derStr.getOctets();

                    SubjectKeyIdentifier ski = SubjectKeyIdentifier.getInstance(valBytes);
//                    String skiValue = Base64.encodeToString(ski.getKeyIdentifier(), Base64.NO_PADDING | Base64.NO_WRAP);
                    String skiValue = bytesToHex(ski.getKeyIdentifier());
                    Log.i(TAG, "ski has value '" + skiValue + "'");
                    attrMap.put(attrReadableName, skiValue);

                } else if("enrollCerttypeExtension".equals(attrReadableName)){
                    DEROctetString derStr = (DEROctetString) derSeq2.getObjectAt(1);
                    byte[] valBytes = derStr.getOctets();
                    DERBMPString bmpString = DERBMPString.getInstance(valBytes);

                    Log.i(TAG, "enrollCerttypeExtension is '" + bmpString.getString() + "'");
                    attrMap.put(attrReadableName, bmpString.getString());

                } else if("cAKeyCertIndexPair".equals(attrReadableName)){
                    DEROctetString derStr = (DEROctetString) derSeq2.getObjectAt(1);
                    byte[] valBytes = derStr.getOctets();
                    ASN1Integer asn1Int = ASN1Integer.getInstance(valBytes);

                    Log.i(TAG, "cAKeyCertIndexPair is '" + asn1Int.getValue() + "'");
                    attrMap.put(attrReadableName, asn1Int.getValue().toString());


                } else {
                    String stringValue = asn1EncValue.toString();

//                    Log.d(TAG, "asn1EncValue.toASN1Primitive " + asn1EncValue.toASN1Primitive().getClass().getName());
                    Method[] methods = asn1EncValue.getClass().getMethods();

                    for( Method m: methods){
//                        Log.d(TAG, "checking method " + m.getName());
                        try {

                            if( "getString".equals(m.getName())){
                                stringValue = (String)m.invoke(asn1EncValue);
                                break;
                            }else if( "getOctets".equals(m.getName())){
                                stringValue = new String((byte[])m.invoke(asn1EncValue));
                                break;
                            }else if( "getValue".equals(m.getName())){
                                stringValue = (String)m.invoke(asn1EncValue);
                                break;
                            }else if( "getId".equals(m.getName())){
                                stringValue = OidNameMapper.lookupOid((String)m.invoke(asn1EncValue));
                                break;
                            }else if( "getAdjustedDate".equals(m.getName())){
                                stringValue = (String)m.invoke(asn1EncValue);
                                break;
                            }
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            Log.d(TAG, "invoking " + m.getName(), e);
                        }
                    }

/*
                    try {
                        Method getStringMethod = asn1Pri.getClass().getMethod("getString", null);
                        stringValue = (String)getStringMethod.invoke(asn1Pri);
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        Log.d(TAG, "getString", e);
                        try{
                            Method getValueMethod = asn1Pri.getClass().getMethod("getValue");
                            stringValue = (String)getValueMethod.invoke(asn1Pri);
                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e2) {
                            Log.d(TAG, "getValue", e2);
                            try{
                                Method getIdMethod = asn1Pri.getClass().getMethod("getId");
                                stringValue = OidNameMapper.lookupOid((String)getIdMethod.invoke(asn1Pri));
                            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e3) {
                                Log.d(TAG, "getId", e3);
                                try{
                                    Method getAdjustedDateMethod = asn1Pri.getClass().getMethod("getAdjustedDate");
                                    stringValue = (String)getAdjustedDateMethod.invoke(asn1Pri);
                                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e4) {
                                    Log.d(TAG, "getAdjustedDate", e4);
                                    stringValue = asn1Pri.toString();
                                }
                            }
                        }
                    }
*/
 //                   Log.i(TAG, "Extensions Attribute '" + attrReadableName + "' has value '" + stringValue + "' of type " + derSeq2.getObjectAt(1).getClass().getName());
                    attrMap.put(attrReadableName, stringValue);
                }
            }
        }

    }

    /**
     * convert the usage-bits to a readable string
     * @param usage
     * @return descriptive text representing the key usage
     */
    public static String usageAsString( int usage ){


        String desc = "";
        if ( (usage & KeyUsage.digitalSignature) > 0) desc += "digitalSignature ";
        if ( (usage & KeyUsage.nonRepudiation) > 0) desc += "nonRepudiation ";
        if ( (usage & KeyUsage.keyEncipherment) > 0) desc += "keyEncipherment ";
        if ( (usage & KeyUsage.dataEncipherment) > 0) desc += "dataEncipherment ";
        if ( (usage & KeyUsage.keyAgreement) > 0) desc += "keyAgreement ";
        if ( (usage & KeyUsage.keyCertSign) > 0) desc += "keyCertSign ";
        if ( (usage & KeyUsage.cRLSign) > 0) desc += "cRLSign ";
        if ( (usage & KeyUsage.encipherOnly) > 0) desc += "encipherOnly ";
        if ( (usage & KeyUsage.decipherOnly) > 0) desc += "decipherOnly ";

        return (desc);
    }


    public static int crlReasonFromString(final String revocationReasonStr) {

        int revReason = IssuedCertificateItem.CRL_REASON_NOT_REVOKED;
        if ("keyCompromise".equalsIgnoreCase(revocationReasonStr)) {
            revReason = CRLReason.keyCompromise;
        } else if ("cACompromise".equalsIgnoreCase(revocationReasonStr)) {
            revReason = CRLReason.cACompromise;
        } else if ("affiliationChanged".equalsIgnoreCase(revocationReasonStr)) {
            revReason = CRLReason.affiliationChanged;
        } else if ("superseded".equalsIgnoreCase(revocationReasonStr)) {
            revReason = CRLReason.superseded;
        } else if ("cessationOfOperation".equalsIgnoreCase(revocationReasonStr)) {
            revReason = CRLReason.cessationOfOperation;
        } else if ("privilegeWithdrawn".equalsIgnoreCase(revocationReasonStr)) {
            revReason = CRLReason.privilegeWithdrawn;
        } else if ("aACompromise".equalsIgnoreCase(revocationReasonStr)) {
            revReason = CRLReason.aACompromise;
        } else if ("unspecified".equalsIgnoreCase(revocationReasonStr)) {
            revReason = CRLReason.unspecified;
        }
        return revReason;
    }

    public static String crlReasonAsString(final int crlReason) {

        switch( crlReason ){
            case CRLReason.keyCompromise:
                return "keyCompromise";
            case CRLReason.cACompromise:
                return "cACompromise";
            case CRLReason.affiliationChanged:
                return "affiliationChanged";
            case CRLReason.superseded:
                return "superseded";
            case CRLReason.cessationOfOperation:
                return "cessationOfOperation";
            case CRLReason.privilegeWithdrawn:
                return "privilegeWithdrawn";
            case CRLReason.aACompromise:
                return "aACompromise";
            default:
                return "unspecified";
        }
    }

/*
    public PKCS10CertificationRequest convertPemToPKCS10CertificationRequest(String pem) {

        PKCS10CertificationRequest csr = null;
        ByteArrayInputStream pemStream = null;
        try {
            pemStream = new ByteArrayInputStream(pem.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Log.e(TAG,"UnsupportedEncodingException in convertPemToPKCS10CertificationRequest", ex);
        }

        Reader pemReader = new BufferedReader(new InputStreamReader(pemStream));
        PEMParser pemParser = new PEMParser(pemReader);

        try {
            Object parsedObj = pemParser.readObject();

            Log.d(TAG, "PemParser returned: " + parsedObj);

            if (parsedObj instanceof PKCS10CertificationRequest) {
                csr = (PKCS10CertificationRequest) parsedObj;

            }
        } catch (IOException ex) {
            Log.e(TAG,"IOException, convertPemToPKCS10CertificationRequest", ex);
        }

        return csr;
    }
*/

    public PKCS10CertificationRequest convertPemToPKCS10CertificationRequest(String pem) throws IOException {

        Log.d(TAG, "converting PEM: " + pem);

        PKCS10CertificationRequest csr = null;
        ByteArrayInputStream pemStream = new ByteArrayInputStream(pem.getBytes(StandardCharsets.UTF_8));

        Reader pemReader = new BufferedReader(new InputStreamReader(pemStream));
        PEMParser pemParser = new PEMParser(pemReader);

        try {
            Object parsedObj = pemParser.readObject();

            if (parsedObj == null) {
                throw new IOException("No PEM content found");
            }

//            Log.d(TAG, "PemParser returned: " + parsedObj);

            if (parsedObj instanceof PKCS10CertificationRequest) {

                csr = (PKCS10CertificationRequest) parsedObj;

                try {
                    if( !isValidSelfsignedCSR(csr) ){
                        throw new IOException("Request not self-signed with the new key!");
                    }
                } catch (GeneralSecurityException | OperatorCreationException | PKCSException e) {
                    Log.i(TAG, "problem processing CSR", e);
                    throw new IOException("Problem interpreting the PKCS10 Request content: " + e.getLocalizedMessage());
                }
            } else {
                throw new IOException("Unexpected content of type " + parsedObj.getClass().getName());
            }

            return csr;
        }catch(org.bouncycastle.util.encoders.DecoderException de){
            throw new IOException("Problem parsing PEM content " + de.getLocalizedMessage());
        }
    }

    public boolean isValidSelfsignedCSR(PKCS10CertificationRequest p10CertReq) throws IOException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, OperatorCreationException, PKCSException {

        SubjectPublicKeyInfo subjectPKInfo = p10CertReq.getSubjectPublicKeyInfo();
        X509EncodedKeySpec xspec = new X509EncodedKeySpec(new DERBitString(subjectPKInfo).getBytes());

        String algoId = subjectPKInfo.getAlgorithm().getAlgorithm().getId();
        Log.d(TAG, "subjectPKInfo algorithm: " + algoId);

        PublicKey pubKey = KeyFactory.getInstance(algoId, "BC").generatePublic(xspec);
        ContentVerifierProvider verifier = new JcaContentVerifierProviderBuilder().setProvider(BC).build(pubKey);

        return p10CertReq.isSignatureValid(verifier);
    }

    public X509Certificate getCertificateFromBytes(byte[] certBytes) throws CertificateException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        return (X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(certBytes));
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
        Reader pemReader = new InputStreamReader(new ByteArrayInputStream(pem.getBytes(StandardCharsets.UTF_8)));
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

    public String crlToPem( X509CRL crl ) {
        try {
            StringWriter swPem = new StringWriter();
            try (PemWriter writer = new PemWriter(swPem)) {
                writer.writeObject(new PemObject("X509 CRL", crl.getEncoded()));
            }
            String certPem = swPem.toString();
            Log.d(TAG, "writing CRL as PEM:\n" + certPem);
            return certPem;
        } catch (IOException | CRLException e) {
            Log.e(TAG, "problem encoding CRL as QR code", e);
        } //end of catch block

        return "";
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

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public X509CRL signCRL(RootCertificateItem rci, HashMap<Integer,char[]> passwordMap, List<IssuedCertificateItem> revokedCertificates, long validitySeconds) throws GeneralSecurityException, OperatorCreationException {

        Date now = new Date();
        X509v2CRLBuilder builder = new X509v2CRLBuilder(
                new X500Name(rci.getSubject()),
                now
        );

        Date nextUpdate = new Date(System.currentTimeMillis() + (1000L * validitySeconds));
        builder.setNextUpdate(nextUpdate);

        for (IssuedCertificateItem ici : revokedCertificates) {

            BigInteger serial = new BigInteger(ici.getCertId(), 16);

            if( ici.isRevocationPending()){
                ici.setRevocationPending(false);
                ici.setRevocationDate(now);
                Log.d(TAG, "adding freshly revoked certificate '"+serial+"' to CRL, setting revocation date to 'now'");
            }else{
                Log.d(TAG, "adding revoked certificate '"+serial+"' to CRL, revoked on " + ici.getRevocationDate());
            }

            builder.addCRLEntry(serial, ici.getRevocationDate(), ici.getRevocationReason());
        }

        JcaContentSignerBuilder contentSignerBuilder = new JcaContentSignerBuilder(rci.getSignAlgo());

        contentSignerBuilder.setProvider("BC");

        PrivateKey privKey = rci.getPrivateKey(passwordMap);
        X509CRLHolder crlHolder = builder.build(contentSignerBuilder.build(privKey));

        JcaX509CRLConverter converter = new JcaX509CRLConverter();

        converter.setProvider("BC");

        return converter.getCRL(crlHolder);
    }
}
