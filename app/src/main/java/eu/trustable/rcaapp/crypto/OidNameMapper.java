package eu.trustable.rcaapp.crypto;

import java.util.HashMap;
import java.util.Map;

public class OidNameMapper {

  static Map<String,String> oidToName = new HashMap<String,String>();
  static Map<String,String> nameToOid = new HashMap<String,String>();
  
  public OidNameMapper(){}
  
  
  static void insertMap(String oid, String name) {

    String dottedOid = oid.replace(' ', '.');
    
    oidToName.put(oid, name);
    oidToName.put(dottedOid, name);
    nameToOid.put(name, dottedOid);
  }

  public static String lookupOid(String oid) {
    if( oidToName.containsKey(oid.trim()) ){
      return(oidToName.get(oid));
    }
    return oid;
  }

  static {
    insertMap("0 2 262 1 10", "Telesec");

    insertMap("0 2 262 1 10 0", "extension");

    insertMap("0 2 262 1 10 1", "mechanism");

    insertMap("0 2 262 1 10 1 0", "authentication");

    insertMap("0 2 262 1 10 1 0 1", "passwordAuthentication");

    insertMap("0 2 262 1 10 1 0 2", "protectedPasswordAuthentication");

    insertMap("0 2 262 1 10 1 0 3", "oneWayX509Authentication");

    insertMap("0 2 262 1 10 1 0 4", "twoWayX509Authentication");

    insertMap("0 2 262 1 10 1 0 5", "threeWayX509Authentication");

    insertMap("0 2 262 1 10 1 0 6", "oneWayISO9798Authentication");

    insertMap("0 2 262 1 10 1 0 7", "twoWayISO9798Authentication");

    insertMap("0 2 262 1 10 1 0 8", "telekomAuthentication");

    insertMap("0 2 262 1 10 1 1", "signature");

    insertMap("0 2 262 1 10 1 1 1", "md4WithRSAAndISO9697");

    insertMap("0 2 262 1 10 1 1 2", "md4WithRSAAndTelesecSignatureStandard");

    insertMap("0 2 262 1 10 1 1 3", "md5WithRSAAndISO9697");

    insertMap("0 2 262 1 10 1 1 4", "md5WithRSAAndTelesecSignatureStandard");

    insertMap("0 2 262 1 10 1 1 5",
        "ripemd160WithRSAAndTelekomSignatureStandard");

    insertMap("0 2 262 1 10 1 1 9", "hbciRsaSignature");

    insertMap("0 2 262 1 10 1 2", "encryption");

    insertMap("0 2 262 1 10 1 2 0", "none");

    insertMap("0 2 262 1 10 1 2 1", "rsaTelesec");

    insertMap("0 2 262 1 10 1 2 2", "des");

    insertMap("0 2 262 1 10 1 2 2 1", "desECB");

    insertMap("0 2 262 1 10 1 2 2 2", "desCBC");

    insertMap("0 2 262 1 10 1 2 2 3", "desOFB");

    insertMap("0 2 262 1 10 1 2 2 4", "desCFB8");

    insertMap("0 2 262 1 10 1 2 2 5", "desCFB64");

    insertMap("0 2 262 1 10 1 2 3", "des3");

    insertMap("0 2 262 1 10 1 2 3 1", "des3ECB");

    insertMap("0 2 262 1 10 1 2 3 2", "des3CBC");

    insertMap("0 2 262 1 10 1 2 3 3", "des3OFB");

    insertMap("0 2 262 1 10 1 2 3 4", "des3CFB8");

    insertMap("0 2 262 1 10 1 2 3 5", "des3CFB64");

    insertMap("0 2 262 1 10 1 2 4", "magenta");

    insertMap("0 2 262 1 10 1 2 5", "idea");

    insertMap("0 2 262 1 10 1 2 5 1", "ideaECB");

    insertMap("0 2 262 1 10 1 2 5 2", "ideaCBC");

    insertMap("0 2 262 1 10 1 2 5 3", "ideaOFB");

    insertMap("0 2 262 1 10 1 2 5 4", "ideaCFB8");

    insertMap("0 2 262 1 10 1 2 5 5", "ideaCFB64");

    insertMap("0 2 262 1 10 1 3", "oneWayFunction");

    insertMap("0 2 262 1 10 1 3 1", "md4");

    insertMap("0 2 262 1 10 1 3 2", "md5");

    insertMap("0 2 262 1 10 1 3 3", "sqModNX509");

    insertMap("0 2 262 1 10 1 3 4", "sqModNISO");

    insertMap("0 2 262 1 10 1 3 5", "ripemd128");

    insertMap("0 2 262 1 10 1 3 6", "hashUsingBlockCipher");

    insertMap("0 2 262 1 10 1 3 7", "mac");

    insertMap("0 2 262 1 10 1 3 8", "ripemd160");

    insertMap("0 2 262 1 10 1 4", "fecFunction");

    insertMap("0 2 262 1 10 1 4 1", "reedSolomon");

    insertMap("0 2 262 1 10 2", "module");

    insertMap("0 2 262 1 10 2 0", "algorithms");

    insertMap("0 2 262 1 10 2 1", "attributeTypes");

    insertMap("0 2 262 1 10 2 2", "certificateTypes");

    insertMap("0 2 262 1 10 2 3", "messageTypes");

    insertMap("0 2 262 1 10 2 4", "plProtocol");

    insertMap("0 2 262 1 10 2 5", "smeAndComponentsOfSme");

    insertMap("0 2 262 1 10 2 6", "fec");

    insertMap("0 2 262 1 10 2 7", "usefulDefinitions");

    insertMap("0 2 262 1 10 2 8", "stefiles");

    insertMap("0 2 262 1 10 2 9", "sadmib");

    insertMap("0 2 262 1 10 2 10", "electronicOrder");

    insertMap("0 2 262 1 10 2 11", "telesecTtpAsymmetricApplication");

    insertMap("0 2 262 1 10 2 12", "telesecTtpBasisApplication");

    insertMap("0 2 262 1 10 2 13", "telesecTtpMessages");

    insertMap("0 2 262 1 10 2 14", "telesecTtpTimeStampApplication");

    insertMap("0 2 262 1 10 3", "objectClass");

    insertMap("0 2 262 1 10 3 0", "telesecOtherName");

    insertMap("0 2 262 1 10 3 1", "directory");

    insertMap("0 2 262 1 10 3 2", "directoryType");

    insertMap("0 2 262 1 10 3 3", "directoryGroup");

    insertMap("0 2 262 1 10 3 4", "directoryUser");

    insertMap("0 2 262 1 10 3 5", "symmetricKeyEntry");

    insertMap("0 2 262 1 10 4", "package");

    insertMap("0 2 262 1 10 5", "parameter");

    insertMap("0 2 262 1 10 6", "nameBinding");

    insertMap("0 2 262 1 10 7", "attribute");

    insertMap("0 2 262 1 10 7 0", "applicationGroupIdentifier");

    insertMap("0 2 262 1 10 7 1", "certificateType");

    insertMap("0 2 262 1 10 7 2", "telesecCertificate");

    insertMap("0 2 262 1 10 7 3", "certificateNumber");

    insertMap("0 2 262 1 10 7 4", "certificateRevocationList");

    insertMap("0 2 262 1 10 7 5", "creationDate");

    insertMap("0 2 262 1 10 7 6", "issuer");

    insertMap("0 2 262 1 10 7 7", "namingAuthority");

    insertMap("0 2 262 1 10 7 8", "publicKeyDirectory");

    insertMap("0 2 262 1 10 7 9", "securityDomain");

    insertMap("0 2 262 1 10 7 10", "subject");

    insertMap("0 2 262 1 10 7 11", "timeOfRevocation");

    insertMap("0 2 262 1 10 7 12", "userGroupReference");

    insertMap("0 2 262 1 10 7 13", "validity");

    insertMap("0 2 262 1 10 7 14", "zert93");

    insertMap("0 2 262 1 10 7 15", "securityMessEnv");

    insertMap("0 2 262 1 10 7 16", "anonymizedPublicKeyDirectory");

    insertMap("0 2 262 1 10 7 17", "telesecGivenName");

    insertMap("0 2 262 1 10 7 18", "nameAdditions");

    insertMap("0 2 262 1 10 7 19", "telesecPostalCode");

    insertMap("0 2 262 1 10 7 20", "nameDistinguisher");

    insertMap("0 2 262 1 10 7 21", "telesecCertificateList");

    insertMap("0 2 262 1 10 7 22", "teletrustCertificateList");

    insertMap("0 2 262 1 10 7 23", "x509CertificateList");

    insertMap("0 2 262 1 10 7 24", "timeOfIssue");

    insertMap("0 2 262 1 10 7 25", "physicalCardNumber");

    insertMap("0 2 262 1 10 7 26", "fileType");

    insertMap("0 2 262 1 10 7 27", "ctlFileIsArchive");

    insertMap("0 2 262 1 10 7 28", "emailAddress");

    insertMap("0 2 262 1 10 7 29", "certificateTemplateList");

    insertMap("0 2 262 1 10 7 30", "directoryName");

    insertMap("0 2 262 1 10 7 31", "directoryTypeName");

    insertMap("0 2 262 1 10 7 32", "directoryGroupName");

    insertMap("0 2 262 1 10 7 33", "directoryUserName");

    insertMap("0 2 262 1 10 7 34", "revocationFlag");

    insertMap("0 2 262 1 10 7 35", "symmetricKeyEntryName");

    insertMap("0 2 262 1 10 7 36", "glNumber");

    insertMap("0 2 262 1 10 7 37", "goNumber");

    insertMap("0 2 262 1 10 7 38", "gKeyData");

    insertMap("0 2 262 1 10 7 39", "zKeyData");

    insertMap("0 2 262 1 10 7 40", "ktKeyData");

    insertMap("0 2 262 1 10 7 41", "ktKeyNumber");

    insertMap("0 2 262 1 10 7 51", "timeOfRevocationGen");

    insertMap("0 2 262 1 10 7 52", "liabilityText");

    insertMap("0 2 262 1 10 8", "attributeGroup");

    insertMap("0 2 262 1 10 9", "action");

    insertMap("0 2 262 1 10 10", "notification");

    insertMap("0 2 262 1 10 11", "snmp-mibs");

    insertMap("0 2 262 1 10 11 1", "securityApplication");

    insertMap("0 2 262 1 10 12", "certAndCrlExtensionDefinitions");

    insertMap("0 2 262 1 10 12 0", "liabilityLimitationFlag");

    insertMap("0 2 262 1 10 12 1", "telesecCertIdExt");

    insertMap("0 2 262 1 10 12 2", "Telesec policyIdentifier");

    insertMap("0 2 262 1 10 12 3", "telesecPolicyQualifierID");

    insertMap("0 2 262 1 10 12 4", "telesecCRLFilteredExt");

    insertMap("0 2 262 1 10 12 5", "telesecCRLFilterExt");

    insertMap("0 2 262 1 10 12 6", "telesecNamingAuthorityExt");

    insertMap("0 4 0 127 0 7", "bsi");

    insertMap("0 4 0 127 0 7 1", "bsiEcc");

    insertMap("0 4 0 127 0 7 1 1", "bsifieldType");

    insertMap("0 4 0 127 0 7 1 1 1", "bsiPrimeField");

    insertMap("0 4 0 127 0 7 1 1 2", "bsiCharacteristicTwoField");

    insertMap("0 4 0 127 0 7 1 1 2 2", "bsiECTLVKeyFormat");

    insertMap("0 4 0 127 0 7 1 1 2 2 1", "bsiECTLVPublicKey");

    insertMap("0 4 0 127 0 7 1 1 2 3", "bsiCharacteristicTwoBasis");

    insertMap("0 4 0 127 0 7 1 1 2 3 1", "bsiGnBasis");

    insertMap("0 4 0 127 0 7 1 1 2 3 2", "bsiTpBasis");

    insertMap("0 4 0 127 0 7 1 1 2 3 3", "bsiPpBasis");

    insertMap("0 4 0 127 0 7 1 1 4 1", "bsiEcdsaSignatures");

    insertMap("0 4 0 127 0 7 1 1 4 1 1", "bsiEcdsaWithSHA1");

    insertMap("0 4 0 127 0 7 1 1 4 1 2", "bsiEcdsaWithSHA224");

    insertMap("0 4 0 127 0 7 1 1 4 1 3", "bsiEcdsaWithSHA256");

    insertMap("0 4 0 127 0 7 1 1 4 1 4", "bsiEcdsaWithSHA384");

    insertMap("0 4 0 127 0 7 1 1 4 1 5", "bsiEcdsaWithSHA512");

    insertMap("0 4 0 127 0 7 1 1 4 1 6", "bsiEcdsaWithRIPEMD160");

    insertMap("0 4 0 127 0 7 1 1 5 1 1", "bsiEckaEgX963KDF");

    insertMap("0 4 0 127 0 7 1 1 5 1 1 1", "bsiEckaEgX963KDFWithSHA1");

    insertMap("0 4 0 127 0 7 1 1 5 1 1 2", "bsiEckaEgX963KDFWithSHA224");

    insertMap("0 4 0 127 0 7 1 1 5 1 1 3", "bsiEckaEgX963KDFWithSHA256");

    insertMap("0 4 0 127 0 7 1 1 5 1 1 4", "bsiEckaEgX963KDFWithSHA384");

    insertMap("0 4 0 127 0 7 1 1 5 1 1 5", "bsiEckaEgX963KDFWithSHA512");

    insertMap("0 4 0 127 0 7 1 1 5 1 1 6", "bsiEckaEgX963KDFWithRIPEMD160");

    insertMap("0 4 0 127 0 7 1 1 5 1 2", "bsiEckaEgSessionKDF");

    insertMap("0 4 0 127 0 7 1 1 5 1 2 1", "bsiEckaEgSessionKDFWith3DES");

    insertMap("0 4 0 127 0 7 1 1 5 1 2 2", "bsiEckaEgSessionKDFWithAES128");

    insertMap("0 4 0 127 0 7 1 1 5 1 2 3", "bsiEckaEgSessionKDFWithAES192");

    insertMap("0 4 0 127 0 7 1 1 5 1 2 4", "bsiEckaEgSessionKDFWithAES256");

    insertMap("0 4 0 127 0 7 1 1 5 2", "bsiEckaDH");

    insertMap("0 4 0 127 0 7 1 1 5 2 1", "bsiEckaDHX963KDF");

    insertMap("0 4 0 127 0 7 1 1 5 2 1 1", "bsiEckaDHX963KDFWithSHA1");

    insertMap("0 4 0 127 0 7 1 1 5 2 1 2", "bsiEckaDHX963KDFWithSHA224");

    insertMap("0 4 0 127 0 7 1 1 5 2 1 3", "bsiEckaDHX963KDFWithSHA256");

    insertMap("0 4 0 127 0 7 1 1 5 2 1 4", "bsiEckaDHX963KDFWithSHA384");

    insertMap("0 4 0 127 0 7 1 1 5 2 1 5", "bsiEckaDHX963KDFWithSHA512");

    insertMap("0 4 0 127 0 7 1 1 5 2 1 6", "bsiEckaDHX963KDFWithRIPEMD160");

    insertMap("0 4 0 127 0 7 1 1 5 2 2", "bsiEckaDHSessionKDF");

    insertMap("0 4 0 127 0 7 1 1 5 2 2 1", "bsiEckaDHSessionKDFWith3DES");

    insertMap("0 4 0 127 0 7 1 1 5 2 2 2", "bsiEckaDHSessionKDFWithAES128");

    insertMap("0 4 0 127 0 7 1 1 5 2 2 3", "bsiEckaDHSessionKDFWithAES192");

    insertMap("0 4 0 127 0 7 1 1 5 2 2 4", "bsiEckaDHSessionKDFWithAES256");

    insertMap("0 4 0 127 0 7 1 2", "bsiEcKeyType");

    insertMap("0 4 0 127 0 7 1 2 1", "bsiEcPublicKey");

    insertMap("0 4 0 127 0 7 1 5 1", "bsiKaeg");

    insertMap("0 4 0 127 0 7 1 5 1 1", "bsiKaegWithX963KDF");

    insertMap("0 4 0 127 0 7 1 5 1 2", "bsiKaegWith3DESKDF");

    insertMap("0 4 0 127 0 7 2 2 1", "bsiPK");

    insertMap("0 4 0 127 0 7 2 2 1 1", "bsiPK_DH");

    insertMap("0 4 0 127 0 7 2 2 1 2", "bsiPK_ECDH");

    insertMap("0 4 0 127 0 7 2 2 2", "bsiTA");

    insertMap("0 4 0 127 0 7 2 2 2 1", "bsiTA_RSA");

    insertMap("0 4 0 127 0 7 2 2 2 1 1", "bsiTA_RSAv1_5_SHA1");

    insertMap("0 4 0 127 0 7 2 2 2 1 2", "bsiTA_RSAv1_5_SHA256");

    insertMap("0 4 0 127 0 7 2 2 2 1 3", "bsiTA_RSAPSS_SHA1");

    insertMap("0 4 0 127 0 7 2 2 2 1 4", "bsiTA_RSAPSS_SHA256");

    insertMap("0 4 0 127 0 7 2 2 2 1 5", "bsiTA_RSAv1_5_SHA512");

    insertMap("0 4 0 127 0 7 2 2 2 1 6", "bsiTA_RSAPSS_SHA512");

    insertMap("0 4 0 127 0 7 2 2 2 2", "bsiTA_ECDSA");

    insertMap("0 4 0 127 0 7 2 2 2 2 1", "bsiTA_ECDSA_SHA1");

    insertMap("0 4 0 127 0 7 2 2 2 2 2", "bsiTA_ECDSA_SHA224");

    insertMap("0 4 0 127 0 7 2 2 2 2 3", "bsiTA_ECDSA_SHA256");

    insertMap("0 4 0 127 0 7 2 2 2 2 4", "bsiTA_ECDSA_SHA384");

    insertMap("0 4 0 127 0 7 2 2 2 2 5", "bsiTA_ECDSA_SHA512");

    insertMap("0 4 0 127 0 7 2 2 3", "bsiCA");

    insertMap("0 4 0 127 0 7 2 2 3 1", "bsiCA_DH");

    insertMap("0 4 0 127 0 7 2 2 3 1 1", "bsiCA_DH_3DES_CBC_CBC");

    insertMap("0 4 0 127 0 7 2 2 3 1 2", "bsiCA_DH_AES_CBC_CMAC_128");

    insertMap("0 4 0 127 0 7 2 2 3 1 3", "bsiCA_DH_AES_CBC_CMAC_192");

    insertMap("0 4 0 127 0 7 2 2 3 1 4", "bsiCA_DH_AES_CBC_CMAC_256");

    insertMap("0 4 0 127 0 7 2 2 3 2", "bsiCA_ECDH");

    insertMap("0 4 0 127 0 7 2 2 3 2 1", "bsiCA_ECDH_3DES_CBC_CBC");

    insertMap("0 4 0 127 0 7 2 2 3 2 2", "bsiCA_ECDH_AES_CBC_CMAC_128");

    insertMap("0 4 0 127 0 7 2 2 3 2 3", "bsiCA_ECDH_AES_CBC_CMAC_192");

    insertMap("0 4 0 127 0 7 2 2 3 2 4", "bsiCA_ECDH_AES_CBC_CMAC_256");

    insertMap("0 4 0 127 0 7 2 2 4", "bsiPACE");

    insertMap("0 4 0 127 0 7 2 2 4 1", "bsiPACE_DH_GM");

    insertMap("0 4 0 127 0 7 2 2 4 1 1", "bsiPACE_DH_GM_3DES_CBC_CBC");

    insertMap("0 4 0 127 0 7 2 2 4 1 2", "bsiPACE_DH_GM_AES_CBC_CMAC_128");

    insertMap("0 4 0 127 0 7 2 2 4 1 3", "bsiPACE_DH_GM_AES_CBC_CMAC_192");

    insertMap("0 4 0 127 0 7 2 2 4 1 4", "bsiPACE_DH_GM_AES_CBC_CMAC_256");

    insertMap("0 4 0 127 0 7 2 2 4 2", "bsiPACE_ECDH_GM");

    insertMap("0 4 0 127 0 7 2 2 4 2 1", "bsiPACE_ECDH_GM_3DES_CBC_CBC");

    insertMap("0 4 0 127 0 7 2 2 4 2 2", "bsiPACE_ECDH_GM_AES_CBC_CMAC_128");

    insertMap("0 4 0 127 0 7 2 2 4 2 3", "bsiPACE_ECDH_GM_AES_CBC_CMAC_192");

    insertMap("0 4 0 127 0 7 2 2 4 2 4", "bsiPACE_ECDH_GM_AES_CBC_CMAC_256");

    insertMap("0 4 0 127 0 7 2 2 4 3", "bsiPACE_DH_IM");

    insertMap("0 4 0 127 0 7 2 2 4 3 1", "bsiPACE_DH_IM_3DES_CBC_CBC");

    insertMap("0 4 0 127 0 7 2 2 4 3 2", "bsiPACE_DH_IM_AES_CBC_CMAC_128");

    insertMap("0 4 0 127 0 7 2 2 4 3 3", "bsiPACE_DH_IM_AES_CBC_CMAC_192");

    insertMap("0 4 0 127 0 7 2 2 4 3 4", "bsiPACE_DH_IM_AES_CBC_CMAC_256");

    insertMap("0 4 0 127 0 7 2 2 4 4", "bsiPACE_ECDH_IM");

    insertMap("0 4 0 127 0 7 2 2 4 4 1", "bsiPACE_ECDH_IM_3DES_CBC_CBC");

    insertMap("0 4 0 127 0 7 2 2 4 4 2", "bsiPACE_ECDH_IM_AES_CBC_CMAC_128");

    insertMap("0 4 0 127 0 7 2 2 4 4 3", "bsiPACE_ECDH_IM_AES_CBC_CMAC_192");

    insertMap("0 4 0 127 0 7 2 2 4 4 4", "bsiPACE_ECDH_IM_AES_CBC_CMAC_256");

    insertMap("0 4 0 127 0 7 2 2 5", "bsiRI");

    insertMap("0 4 0 127 0 7 2 2 5 1", "bsiRI_DH");

    insertMap("0 4 0 127 0 7 2 2 5 1 1", "bsiRI_DH_SHA1");

    insertMap("0 4 0 127 0 7 2 2 5 1 2", "bsiRI_DH_SHA224");

    insertMap("0 4 0 127 0 7 2 2 5 1 3", "bsiRI_DH_SHA256");

    insertMap("0 4 0 127 0 7 2 2 5 1 4", "bsiRI_DH_SHA384");

    insertMap("0 4 0 127 0 7 2 2 5 1 5", "bsiRI_DH_SHA512");

    insertMap("0 4 0 127 0 7 2 2 5 2", "bsiRI_ECDH");

    insertMap("0 4 0 127 0 7 2 2 5 2 1", "bsiRI_ECDH_SHA1");

    insertMap("0 4 0 127 0 7 2 2 5 2 2", "bsiRI_ECDH_SHA224");

    insertMap("0 4 0 127 0 7 2 2 5 2 3", "bsiRI_ECDH_SHA256");

    insertMap("0 4 0 127 0 7 2 2 5 2 4", "bsiRI_ECDH_SHA384");

    insertMap("0 4 0 127 0 7 2 2 5 2 5", "bsiRI_ECDH_SHA512");

    insertMap("0 4 0 127 0 7 2 2 6", "bsiCardInfo");

    insertMap("0 4 0 127 0 7 2 2 7", "bsiEidSecurity");

    insertMap("0 4 0 127 0 7 2 2 8", "bsiPT");

    insertMap("0 4 0 127 0 7 3 1 2", "bsiEACRoles");

    insertMap("0 4 0 127 0 7 3 1 2 1", "bsiEACRolesIS");

    insertMap("0 4 0 127 0 7 3 1 2 2", "bsiEACRolesAT");

    insertMap("0 4 0 127 0 7 3 1 2 3", "bsiEACRolesST");

    insertMap("0 4 0 127 0 7 3 1 3", "bsiTAv2ce");

    insertMap("0 4 0 127 0 7 3 1 3 1", "bsiTAv2ceDescription");

    insertMap("0 4 0 127 0 7 3 1 3 1 1", "bsiTAv2ceDescriptionPlainText");

    insertMap("0 4 0 127 0 7 3 1 3 1 2", "bsiTAv2ceDescriptionIA5String");

    insertMap("0 4 0 127 0 7 3 1 3 1 3", "bsiTAv2ceDescriptionOctetString");

    insertMap("0 4 0 127 0 7 3 1 3 2", "bsiTAv2ceTerminalSector");

    insertMap("0 4 0 127 0 7 3 1 4", "bsiAuxData");

    insertMap("0 4 0 127 0 7 3 1 4 1", "bsiAuxDataBirthday");

    insertMap("0 4 0 127 0 7 3 1 4 2", "bsiAuxDataExpireDate");

    insertMap("0 4 0 127 0 7 3 1 4 3", "bsiAuxDataCommunityID");

    insertMap("0 4 0 127 0 7 3 1 5", "bsiDefectList");

    insertMap("0 4 0 127 0 7 3 1 5 1", "bsiDefectAuthDefect");

    insertMap("0 4 0 127 0 7 3 1 5 1 1", "bsiDefectCertRevoked");

    insertMap("0 4 0 127 0 7 3 1 5 1 2", "bsiDefectCertReplaced");

    insertMap("0 4 0 127 0 7 3 1 5 1 3", "bsiDefectChipAuthKeyRevoked");

    insertMap("0 4 0 127 0 7 3 1 5 1 4", "bsiDefectActiveAuthKeyRevoked");

    insertMap("0 4 0 127 0 7 3 1 5 2", "bsiDefectEPassportDefect");

    insertMap("0 4 0 127 0 7 3 1 5 2 1", "bsiDefectEPassportDGMalformed");

    insertMap("0 4 0 127 0 7 3 1 5 2 2", "bsiDefectSODInvalid");

    insertMap("0 4 0 127 0 7 3 1 5 3", "bsiDefectEIDDefect");

    insertMap("0 4 0 127 0 7 3 1 5 3 1", "bsiDefectEIDDGMalformed");

    insertMap("0 4 0 127 0 7 3 1 5 3 2", "bsiDefectEIDIntegrity");

    insertMap("0 4 0 127 0 7 3 1 5 4", "bsiDefectDocumentDefect");

    insertMap("0 4 0 127 0 7 3 1 5 4 1", "bsiDefectCardSecurityMalformed");

    insertMap("0 4 0 127 0 7 3 1 5 4 2", "bsiDefectChipSecurityMalformed");

    insertMap("0 4 0 127 0 7 3 1 5 4 3", "bsiDefectPowerDownReq");

    insertMap("0 4 0 127 0 7 3 1 6", "bsiListContentDescription");

    insertMap("0 4 0 127 0 7 3 2 1", "bsiSecurityObject");

    insertMap("0 4 0 127 0 7 3 2 2", "bsiBlackList");

    insertMap("0 4 0 1862", "etsiQcsProfile");

    insertMap("0 4 0 1862 1", "etsiQcs");

    insertMap("0 4 0 1862 1 1", "etsiQcsCompliance");

    insertMap("0 4 0 1862 1 2", "etsiQcsLimitValue");

    insertMap("0 4 0 1862 1 3", "etsiQcsRetentionPeriod");

    insertMap("0 4 0 1862 1 4", "etsiQcsQcSSCD");

    insertMap("0 9 2342 19200300 100 1 1", "userID");

    insertMap("0 9 2342 19200300 100 1 3", "rfc822Mailbox");

    insertMap("0 9 2342 19200300 100 1 25", "domainComponent");

    insertMap("1 0 10118 3 0 49", "ripemd160");

    insertMap("1 0 10118 3 0 50", "ripemd128");

    insertMap("1 0 10118 3 0 55", "whirlpool");

    insertMap("1 2 36 1 3 1 1 1", "qgpki");

    insertMap("1 2 36 1 3 1 1 1 1", "qgpkiPolicies");

    insertMap("1 2 36 1 3 1 1 1 1 1", "qgpkiMedIntermedCA");

    insertMap("1 2 36 1 3 1 1 1 1 1 1", "qgpkiMedIntermedIndividual");

    insertMap("1 2 36 1 3 1 1 1 1 1 2", "qgpkiMedIntermedDeviceControl");

    insertMap("1 2 36 1 3 1 1 1 1 1 3", "qgpkiMedIntermedDevice");

    insertMap("1 2 36 1 3 1 1 1 1 1 4", "qgpkiMedIntermedAuthorisedParty");

    insertMap("1 2 36 1 3 1 1 1 1 1 5", "qgpkiMedIntermedDeviceSystem");

    insertMap("1 2 36 1 3 1 1 1 1 2", "qgpkiMedIssuingCA");

    insertMap("1 2 36 1 3 1 1 1 1 2 1", "qgpkiMedIssuingIndividual");

    insertMap("1 2 36 1 3 1 1 1 1 2 2", "qgpkiMedIssuingDeviceControl");

    insertMap("1 2 36 1 3 1 1 1 1 2 3", "qgpkiMedIssuingDevice");

    insertMap("1 2 36 1 3 1 1 1 1 2 4", "qgpkiMedIssuingAuthorisedParty");

    insertMap("1 2 36 1 3 1 1 1 1 2 5", "qgpkiMedIssuingClientAuth");

    insertMap("1 2 36 1 3 1 1 1 1 2 6", "qgpkiMedIssuingServerAuth");

    insertMap("1 2 36 1 3 1 1 1 1 2 7", "qgpkiMedIssuingDataProt");

    insertMap("1 2 36 1 3 1 1 1 1 2 8", "qgpkiMedIssuingTokenAuth");

    insertMap("1 2 36 1 3 1 1 1 1 3", "qgpkiBasicIntermedCA");

    insertMap("1 2 36 1 3 1 1 1 1 3 1", "qgpkiBasicIntermedDeviceSystem");

    insertMap("1 2 36 1 3 1 1 1 1 4", "qgpkiBasicIssuingCA");

    insertMap("1 2 36 1 3 1 1 1 1 4 1", "qgpkiBasicIssuingClientAuth");

    insertMap("1 2 36 1 3 1 1 1 1 4 2", "qgpkiBasicIssuingServerAuth");

    insertMap("1 2 36 1 3 1 1 1 1 4 3", "qgpkiBasicIssuingDataSigning");

    insertMap("1 2 36 1 3 1 1 1 2", "qgpkiAssuranceLevel");

    insertMap("1 2 36 1 3 1 1 1 2 1", "qgpkiAssuranceRudimentary");

    insertMap("1 2 36 1 3 1 1 1 2 2", "qgpkiAssuranceBasic");

    insertMap("1 2 36 1 3 1 1 1 2 3", "qgpkiAssuranceMedium");

    insertMap("1 2 36 1 3 1 1 1 2 4", "qgpkiAssuranceHigh");

    insertMap("1 2 36 1 3 1 1 1 3", "qgpkiCertFunction");

    insertMap("1 2 36 1 3 1 1 1 3 1", "qgpkiFunctionIndividual");

    insertMap("1 2 36 1 3 1 1 1 3 2", "qgpkiFunctionDevice");

    insertMap("1 2 36 1 3 1 1 1 3 3", "qgpkiFunctionAuthorisedParty");

    insertMap("1 2 36 1 3 1 1 1 3 4", "qgpkiFunctionDeviceControl");

    insertMap("1 2 36 1 3 1 2", "qpspki");

    insertMap("1 2 36 1 3 1 2 1", "qpspkiPolicies");

    insertMap("1 2 36 1 3 1 2 1 2", "qpspkiPolicyBasic");

    insertMap("1 2 36 1 3 1 2 1 3", "qpspkiPolicyMedium");

    insertMap("1 2 36 1 3 1 2 1 4", "qpspkiPolicyHigh");

    insertMap("1 2 36 1 3 1 3 2", "qtmrpki");

    insertMap("1 2 36 1 3 1 3 2 1", "qtmrpkiPolicies");

    insertMap("1 2 36 1 3 1 3 2 2", "qtmrpkiPurpose");

    insertMap("1 2 36 1 3 1 3 2 2 1", "qtmrpkiIndividual");

    insertMap("1 2 36 1 3 1 3 2 2 2", "qtmrpkiDeviceControl");

    insertMap("1 2 36 1 3 1 3 2 2 3", "qtmrpkiDevice");

    insertMap("1 2 36 1 3 1 3 2 2 4", "qtmrpkiAuthorisedParty");

    insertMap("1 2 36 1 3 1 3 2 2 5", "qtmrpkiDeviceSystem");

    insertMap("1 2 36 1 3 1 3 2 3", "qtmrpkiDevice");

    insertMap("1 2 36 1 3 1 3 2 3 1", "qtmrpkiDriverLicense");

    insertMap("1 2 36 1 3 1 3 2 3 2", "qtmrpkiIndustryAuthority");

    insertMap("1 2 36 1 3 1 3 2 3 3", "qtmrpkiMarineLicense");

    insertMap("1 2 36 1 3 1 3 2 3 4", "qtmrpkiAdultProofOfAge");

    insertMap("1 2 36 1 3 1 3 2 3 5", "qtmrpkiSam");

    insertMap("1 2 36 1 3 1 3 2 4", "qtmrpkiAuthorisedParty");

    insertMap("1 2 36 1 3 1 3 2 4 1", "qtmrpkiTransportInspector");

    insertMap("1 2 36 1 3 1 3 2 4 2", "qtmrpkiPoliceOfficer");

    insertMap("1 2 36 1 3 1 3 2 4 3", "qtmrpkiSystem");

    insertMap("1 2 36 1 3 1 3 2 4 4", "qtmrpkiLiquorLicensingInspector");

    insertMap("1 2 36 1 3 1 3 2 4 5", "qtmrpkiMarineEnforcementOfficer");

    insertMap("1 2 36 1 333 1", "australianBusinessNumber");

    insertMap("1 2 36 68980861 1 1 2", "signetPersonal");

    insertMap("1 2 36 68980861 1 1 3", "signetBusiness");

    insertMap("1 2 36 68980861 1 1 4", "signetLegal");

    insertMap("1 2 36 68980861 1 1 10", "signetPilot");

    insertMap("1 2 36 68980861 1 1 11", "signetIntraNet");

    insertMap("1 2 36 68980861 1 1 20", "signetPolicy");

    insertMap("1 2 36 75878867 1 100 1 1", "certificatesAustraliaPolicy");

    insertMap("1 2 392 200011 61 1 1 1", "mitsubishiSecurityAlgorithm");

    insertMap("1 2 392 200011 61 1 1 1 1", "misty1-cbc");

    insertMap("1 2 410 200004 1", "kisaAlgorithm");

    insertMap("1 2 410 200004 1 1", "kcdsa");

    insertMap("1 2 410 200004 1 2", "has160");

    insertMap("1 2 410 200004 1 3", "seedECB");

    insertMap("1 2 410 200004 1 4", "seedCBC");

    insertMap("1 2 410 200004 1 5", "seedOFB");

    insertMap("1 2 410 200004 1 6", "seedCFB");

    insertMap("1 2 410 200004 1 7", "seedMAC");

    insertMap("1 2 410 200004 1 8", "kcdsaWithHAS160");

    insertMap("1 2 410 200004 1 9", "kcdsaWithSHA1");

    insertMap("1 2 410 200004 1 10", "pbeWithHAS160AndSEED-ECB");

    insertMap("1 2 410 200004 1 11", "pbeWithHAS160AndSEED-CBC");

    insertMap("1 2 410 200004 1 12", "pbeWithHAS160AndSEED-CFB");

    insertMap("1 2 410 200004 1 13", "pbeWithHAS160AndSEED-OFB");

    insertMap("1 2 410 200004 1 14", "pbeWithSHA1AndSEED-ECB");

    insertMap("1 2 410 200004 1 15", "pbeWithSHA1AndSEED-CBC");

    insertMap("1 2 410 200004 1 16", "pbeWithSHA1AndSEED-CFB");

    insertMap("1 2 410 200004 1 17", "pbeWithSHA1AndSEED-OFB");

    insertMap("1 2 410 200004 1 20", "rsaWithHAS160");

    insertMap("1 2 410 200004 1 21", "kcdsa1");

    insertMap("1 2 410 200004 2", "npkiCP");

    insertMap("1 2 410 200004 2 1", "npkiSignaturePolicy");

    insertMap("1 2 410 200004 3", "npkiKP");

    insertMap("1 2 410 200004 4", "npkiAT");

    insertMap("1 2 410 200004 5", "npkiLCA");

    insertMap("1 2 410 200004 5 1", "npkiSignKorea");

    insertMap("1 2 410 200004 5 2", "npkiSignGate");

    insertMap("1 2 410 200004 5 3", "npkiNcaSign");

    insertMap("1 2 410 200004 6", "npkiON");

    insertMap("1 2 410 200004 7", "npkiAPP");

    insertMap("1 2 410 200004 7 1", "npkiSMIME");

    insertMap("1 2 410 200004 7 1 1", "npkiSMIMEAlgo");

    insertMap("1 2 410 200004 7 1 1 1", "npkiCmsSEEDWrap");

    insertMap("1 2 410 200004 10", "npki");

    insertMap("1 2 410 200004 10 1", "npkiAttribute");

    insertMap("1 2 410 200004 10 1 1", "npkiIdentifyData");

    insertMap("1 2 410 200004 10 1 1 1", "npkiVID");

    insertMap("1 2 410 200004 10 1 1 2", "npkiEncryptedVID");

    insertMap("1 2 410 200004 10 1 1 3", "npkiRandomNum");

    insertMap("1 2 410 200004 10 1 1 4", "npkiVID");

    insertMap("1 2 410 200046 1 1", "aria1AlgorithmModes");

    insertMap("1 2 410 200046 1 1 1", "aria128-ecb");

    insertMap("1 2 410 200046 1 1 2", "aria128-cbc");

    insertMap("1 2 410 200046 1 1 3", "aria128-cfb");

    insertMap("1 2 410 200046 1 1 4", "aria128-ofb");

    insertMap("1 2 410 200046 1 1 5", "aria128-ctr");

    insertMap("1 2 410 200046 1 1 6", "aria192-ecb");

    insertMap("1 2 410 200046 1 1 7", "aria192-cbc");

    insertMap("1 2 410 200046 1 1 8", "aria192-cfb");

    insertMap("1 2 410 200046 1 1 9", "aria192-ofb");

    insertMap("1 2 410 200046 1 1 10", "aria192-ctr");

    insertMap("1 2 410 200046 1 1 11", "aria256-ecb");

    insertMap("1 2 410 200046 1 1 12", "aria256-cbc");

    insertMap("1 2 410 200046 1 1 13", "aria256-cfb");

    insertMap("1 2 410 200046 1 1 14", "aria256-ofb");

    insertMap("1 2 410 200046 1 1 15", "aria256-ctr");

    insertMap("1 2 410 200046 1 1 21", "aria128-cmac");

    insertMap("1 2 410 200046 1 1 22", "aria192-cmac");

    insertMap("1 2 410 200046 1 1 23", "aria256-cmac");

    insertMap("1 2 410 200046 1 1 31", "aria128-ocb2");

    insertMap("1 2 410 200046 1 1 32", "aria192-ocb2");

    insertMap("1 2 410 200046 1 1 33", "aria256-ocb2");

    insertMap("1 2 410 200046 1 1 34", "aria128-gcm");

    insertMap("1 2 410 200046 1 1 35", "aria192-gcm");

    insertMap("1 2 410 200046 1 1 36", "aria256-gcm");

    insertMap("1 2 410 200046 1 1 37", "aria128-ccm");

    insertMap("1 2 410 200046 1 1 38", "aria192-ccm");

    insertMap("1 2 410 200046 1 1 39", "aria256-ccm");

    insertMap("1 2 410 200046 1 1 40", "aria128-keywrap");

    insertMap("1 2 410 200046 1 1 41", "aria192-keywrap");

    insertMap("1 2 410 200046 1 1 42", "aria256-keywrap");

    insertMap("1 2 410 200046 1 1 43", "aria128-keywrapWithPad");

    insertMap("1 2 410 200046 1 1 44", "aria192-keywrapWithPad");

    insertMap("1 2 410 200046 1 1 45", "aria256-keywrapWithPad");

    insertMap("1 2 643 2 2 3", "gostSignature");

    insertMap("1 2 643 2 2 4", "gost94Signature");

    insertMap("1 2 643 2 2 19", "gostPublicKey");

    insertMap("1 2 643 2 2 20", "gost94PublicKey");

    insertMap("1 2 643 2 2 21", "gostCipher");

    insertMap("1 2 643 2 2 31 0", "testCipherParams");

    insertMap("1 2 643 2 2 31 1", "cryptoProCipherA");

    insertMap("1 2 643 2 2 31 2", "cryptoProCipherB");

    insertMap("1 2 643 2 2 31 3", "cryptoProCipherC");

    insertMap("1 2 643 2 2 31 4", "cryptoProCipherD");

    insertMap("1 2 643 2 2 31 5", "oscar11Cipher");

    insertMap("1 2 643 2 2 31 6", "oscar10Cipher");

    insertMap("1 2 643 2 2 31 7", "ric1Cipher");

    insertMap("1 2 643 2 2 9", "gostDigest");

    insertMap("1 2 643 2 2 30 0", "testDigestParams");

    insertMap("1 2 643 2 2 30 1", "cryptoProDigestA");

    insertMap("1 2 643 2 2 35 0", "testSignParams");

    insertMap("1 2 643 2 2 35 1", "cryptoProSignA");

    insertMap("1 2 643 2 2 35 2", "cryptoProSignB");

    insertMap("1 2 643 2 2 35 3", "cryptoProSignC");

    insertMap("1 2 643 2 2 36 0", "cryptoProSignXA");

    insertMap("1 2 643 2 2 36 1", "cryptoProSignXB");

    insertMap("1 2 643 2 2 14 0", "nullMeshing");

    insertMap("1 2 643 2 2 14 1", "cryptoProMeshing");

    insertMap("1 2 643 2 2 10", "hmacGost");

    insertMap("1 2 643 2 2 13 0", "gostWrap");

    insertMap("1 2 643 2 2 13 1", "cryptoProWrap");

    insertMap("1 2 643 2 2 96", "cryptoProECDHWrap");

    insertMap("1 2 752 34 1", "seis-cp");

    insertMap("1 2 752 34 1 1", "SEIS high-assurance policyIdentifier");

    insertMap("1 2 752 34 1 2", "SEIS GAK policyIdentifier");

    insertMap("1 2 752 34 2", "SEIS pe");

    insertMap("1 2 752 34 3", "SEIS at");

    insertMap("1 2 752 34 3 1", "SEIS at-personalIdentifier");

    insertMap("1 2 840 10040 1", "module");

    insertMap("1 2 840 10040 1 1", "x9f1-cert-mgmt");

    insertMap("1 2 840 10040 2", "holdinstruction");

    insertMap("1 2 840 10040 2 1", "holdinstruction-none");

    insertMap("1 2 840 10040 2 2", "callissuer");

    insertMap("1 2 840 10040 2 3", "reject");

    insertMap("1 2 840 10040 2 4", "pickupToken");

    insertMap("1 2 840 10040 3", "attribute");

    insertMap("1 2 840 10040 3 1", "countersignature");

    insertMap("1 2 840 10040 3 2", "attribute-cert");

    insertMap("1 2 840 10040 4", "algorithm");

    insertMap("1 2 840 10040 4 1", "dsa");

    insertMap("1 2 840 10040 4 2", "dsa-match");

    insertMap("1 2 840 10040 4 3", "dsaWithSha1");

    insertMap("1 2 840 10045 1", "fieldType");

    insertMap("1 2 840 10045 1 1", "prime-field");

    insertMap("1 2 840 10045 1 2", "characteristic-two-field");

    insertMap("1 2 840 10045 1 2 3", "characteristic-two-basis");

    insertMap("1 2 840 10045 1 2 3 1", "onBasis");

    insertMap("1 2 840 10045 1 2 3 2", "tpBasis");

    insertMap("1 2 840 10045 1 2 3 3", "ppBasis");

    insertMap("1 2 840 10045 2", "publicKeyType");

    insertMap("1 2 840 10045 2 1", "ecPublicKey");

    insertMap("1 2 840 10045 3 0 1", "c2pnb163v1");

    insertMap("1 2 840 10045 3 0 2", "c2pnb163v2");

    insertMap("1 2 840 10045 3 0 3", "c2pnb163v3");

    insertMap("1 2 840 10045 3 0 5", "c2tnb191v1");

    insertMap("1 2 840 10045 3 0 6", "c2tnb191v2");

    insertMap("1 2 840 10045 3 0 7", "c2tnb191v3");

    insertMap("1 2 840 10045 3 0 10", "c2pnb208w1");

    insertMap("1 2 840 10045 3 0 11", "c2tnb239v1");

    insertMap("1 2 840 10045 3 0 12", "c2tnb239v2");

    insertMap("1 2 840 10045 3 0 13", "c2tnb239v3");

    insertMap("1 2 840 10045 3 0 16", "c2pnb272w1");

    insertMap("1 2 840 10045 3 0 18", "c2tnb359v1");

    insertMap("1 2 840 10045 3 0 19", "c2pnb368w1");

    insertMap("1 2 840 10045 3 0 20", "c2tnb431r1");

    insertMap("1 2 840 10045 3 1 1", "prime192v1");

    insertMap("1 2 840 10045 3 1 2", "prime192v2");

    insertMap("1 2 840 10045 3 1 3", "prime192v3");

    insertMap("1 2 840 10045 3 1 4", "prime239v1");

    insertMap("1 2 840 10045 3 1 5", "prime239v2");

    insertMap("1 2 840 10045 3 1 6", "prime239v3");

    insertMap("1 2 840 10045 3 1 7", "prime256v1");

    insertMap("1 2 840 10045 4 1", "ecdsaWithSHA1");

    insertMap("1 2 840 10045 4 2", "ecdsaWithRecommended");

    insertMap("1 2 840 10045 4 3", "ecdsaWithSpecified");

    insertMap("1 2 840 10045 4 3 1", "ecdsaWithSHA224");

    insertMap("1 2 840 10045 4 3 2", "ecdsaWithSHA256");

    insertMap("1 2 840 10045 4 3 3", "ecdsaWithSHA384");

    insertMap("1 2 840 10045 4 3 4", "ecdsaWithSHA512");

    insertMap("1 2 840 10046 1", "fieldType");

    insertMap("1 2 840 10046 1 1", "gf-prime");

    insertMap("1 2 840 10046 2", "numberType");

    insertMap("1 2 840 10046 2 1", "dhPublicKey");

    insertMap("1 2 840 10046 3", "scheme");

    insertMap("1 2 840 10046 3 1", "dhStatic");

    insertMap("1 2 840 10046 3 2", "dhEphem");

    insertMap("1 2 840 10046 3 3", "dhHybrid1");

    insertMap("1 2 840 10046 3 4", "dhHybrid2");

    insertMap("1 2 840 10046 3 5", "mqv2");

    insertMap("1 2 840 10046 3 6", "mqv1");

    insertMap("1 2 840 10065 2 2", "?");

    insertMap("1 2 840 10065 2 3", "healthcareLicense");

    insertMap("1 2 840 10065 2 3 1 1", "license?");

    insertMap("1 2 840 10070 ", "iec62351");

    insertMap("1 2 840 10070 8", "iec62351_8");

    insertMap("1 2 840 10070 8 1", "iecUserRoles");

    insertMap("1 2 840 113533 7", "nsn");

    insertMap("1 2 840 113533 7 65", "nsn-ce");

    insertMap("1 2 840 113533 7 65 0", "entrustVersInfo");

    insertMap("1 2 840 113533 7 66", "nsn-alg");

    insertMap("1 2 840 113533 7 66 3", "cast3CBC");

    insertMap("1 2 840 113533 7 66 10", "cast5CBC");

    insertMap("1 2 840 113533 7 66 11", "cast5MAC");

    insertMap("1 2 840 113533 7 66 12", "pbeWithMD5AndCAST5-CBC");

    insertMap("1 2 840 113533 7 66 13", "passwordBasedMac");

    insertMap("1 2 840 113533 7 67", "nsn-oc");

    insertMap("1 2 840 113533 7 67 0", "entrustUser");

    insertMap("1 2 840 113533 7 68", "nsn-at");

    insertMap("1 2 840 113533 7 68 0", "entrustCAInfo");

    insertMap("1 2 840 113533 7 68 10", "attributeCertificate");

    insertMap("1 2 840 113549 1 1", "pkcs-1");

    insertMap("1 2 840 113549 1 1 1", "rsaEncryption");

    insertMap("1 2 840 113549 1 1 2", "md2WithRSAEncryption");

    insertMap("1 2 840 113549 1 1 3", "md4WithRSAEncryption");

    insertMap("1 2 840 113549 1 1 4", "md5WithRSAEncryption");

    insertMap("1 2 840 113549 1 1 5", "sha1WithRSAEncryption");

    insertMap("1 2 840 113549 1 1 7", "rsaOAEP");

    insertMap("1 2 840 113549 1 1 8", "pkcs1-MGF");

    insertMap("1 2 840 113549 1 1 9", "rsaOAEP-pSpecified");

    insertMap("1 2 840 113549 1 1 10", "rsaPSS");

    insertMap("1 2 840 113549 1 1 11", "sha256WithRSAEncryption");

    insertMap("1 2 840 113549 1 1 12", "sha384WithRSAEncryption");

    insertMap("1 2 840 113549 1 1 13", "sha512WithRSAEncryption");

    insertMap("1 2 840 113549 1 1 14", "sha224WithRSAEncryption");

    insertMap("1 2 840 113549 1 1 6", "rsaOAEPEncryptionSET");

    insertMap("1 2 840 113549 1 2", "bsafeRsaEncr");

    insertMap("1 2 840 113549 1 3", "pkcs-3");

    insertMap("1 2 840 113549 1 3 1", "dhKeyAgreement");

    insertMap("1 2 840 113549 1 5", "pkcs-5");

    insertMap("1 2 840 113549 1 5 1", "pbeWithMD2AndDES-CBC");

    insertMap("1 2 840 113549 1 5 3", "pbeWithMD5AndDES-CBC");

    insertMap("1 2 840 113549 1 5 4", "pbeWithMD2AndRC2-CBC");

    insertMap("1 2 840 113549 1 5 6", "pbeWithMD5AndRC2-CBC");

    insertMap("1 2 840 113549 1 5 9", "pbeWithMD5AndXOR");

    insertMap("1 2 840 113549 1 5 10", "pbeWithSHAAndDES-CBC");

    insertMap("1 2 840 113549 1 5 12", "pkcs5PBKDF2");

    insertMap("1 2 840 113549 1 5 13", "pkcs5PBES2");

    insertMap("1 2 840 113549 1 5 14", "pkcs5PBMAC1");

    insertMap("1 2 840 113549 1 7", "pkcs-7");

    insertMap("1 2 840 113549 1 7 1", "data");

    insertMap("1 2 840 113549 1 7 2", "signedData");

    insertMap("1 2 840 113549 1 7 3", "envelopedData");

    insertMap("1 2 840 113549 1 7 4", "signedAndEnvelopedData");

    insertMap("1 2 840 113549 1 7 5", "digestedData");

    insertMap("1 2 840 113549 1 7 6", "encryptedData");

    insertMap("1 2 840 113549 1 7 7", "dataWithAttributes");

    insertMap("1 2 840 113549 1 7 8", "encryptedPrivateKeyInfo");

    insertMap("1 2 840 113549 1 9", "pkcs-9");

    insertMap("1 2 840 113549 1 9 1", "emailAddress");

    insertMap("1 2 840 113549 1 9 2", "unstructuredName");

    insertMap("1 2 840 113549 1 9 3", "contentType");

    insertMap("1 2 840 113549 1 9 4", "messageDigest");

    insertMap("1 2 840 113549 1 9 5", "signingTime");

    insertMap("1 2 840 113549 1 9 6", "countersignature");

    insertMap("1 2 840 113549 1 9 7", "challengePassword");

    insertMap("1 2 840 113549 1 9 8", "unstructuredAddress");

    insertMap("1 2 840 113549 1 9 9", "extendedCertificateAttributes");

    insertMap("1 2 840 113549 1 9 10", "issuerAndSerialNumber");

    insertMap("1 2 840 113549 1 9 11", "passwordCheck");

    insertMap("1 2 840 113549 1 9 12", "publicKey");

    insertMap("1 2 840 113549 1 9 13", "signingDescription");

    insertMap("1 2 840 113549 1 9 14", "extensionRequest");

    insertMap("1 2 840 113549 1 9 15", "sMIMECapabilities");

    insertMap("1 2 840 113549 1 9 15 1", "preferSignedData");

    insertMap("1 2 840 113549 1 9 15 2", "canNotDecryptAny");

    insertMap("1 2 840 113549 1 9 15 3", "receiptRequest");

    insertMap("1 2 840 113549 1 9 15 4", "receipt");

    insertMap("1 2 840 113549 1 9 15 5", "contentHints");

    insertMap("1 2 840 113549 1 9 15 6", "mlExpansionHistory");

    insertMap("1 2 840 113549 1 9 16", "id-sMIME");

    insertMap("1 2 840 113549 1 9 16 0", "id-mod");

    insertMap("1 2 840 113549 1 9 16 0 1", "id-mod-cms");

    insertMap("1 2 840 113549 1 9 16 0 2", "id-mod-ess");

    insertMap("1 2 840 113549 1 9 16 0 3", "id-mod-oid");

    insertMap("1 2 840 113549 1 9 16 0 4", "id-mod-msg-v3");

    insertMap("1 2 840 113549 1 9 16 0 5", "id-mod-ets-eSignature-88");

    insertMap("1 2 840 113549 1 9 16 0 6", "id-mod-ets-eSignature-97");

    insertMap("1 2 840 113549 1 9 16 0 7", "id-mod-ets-eSigPolicy-88");

    insertMap("1 2 840 113549 1 9 16 0 8", "id-mod-ets-eSigPolicy-88");

    insertMap("1 2 840 113549 1 9 16 1", "contentType");

    insertMap("1 2 840 113549 1 9 16 1 1", "receipt");

    insertMap("1 2 840 113549 1 9 16 1 2", "authData");

    insertMap("1 2 840 113549 1 9 16 1 3", "publishCert");

    insertMap("1 2 840 113549 1 9 16 1 4", "tSTInfo");

    insertMap("1 2 840 113549 1 9 16 1 5", "tDTInfo");

    insertMap("1 2 840 113549 1 9 16 1 6", "contentInfo");

    insertMap("1 2 840 113549 1 9 16 1 7", "dVCSRequestData");

    insertMap("1 2 840 113549 1 9 16 1 8", "dVCSResponseData");

    insertMap("1 2 840 113549 1 9 16 1 9", "compressedData");

    insertMap("1 2 840 113549 1 9 16 1 10", "scvpCertValRequest");

    insertMap("1 2 840 113549 1 9 16 1 11", "scvpCertValResponse");

    insertMap("1 2 840 113549 1 9 16 1 12", "scvpValPolRequest");

    insertMap("1 2 840 113549 1 9 16 1 13", "scvpValPolResponse");

    insertMap("1 2 840 113549 1 9 16 1 14", "attrCertEncAttrs");

    insertMap("1 2 840 113549 1 9 16 1 15", "tSReq");

    insertMap("1 2 840 113549 1 9 16 1 16", "firmwarePackage");

    insertMap("1 2 840 113549 1 9 16 1 17", "firmwareLoadReceipt");

    insertMap("1 2 840 113549 1 9 16 1 18", "firmwareLoadError");

    insertMap("1 2 840 113549 1 9 16 1 19", "contentCollection");

    insertMap("1 2 840 113549 1 9 16 1 20", "contentWithAttrs");

    insertMap("1 2 840 113549 1 9 16 1 21", "encKeyWithID");

    insertMap("1 2 840 113549 1 9 16 1 22", "encPEPSI");

    insertMap("1 2 840 113549 1 9 16 1 23", "authEnvelopedData");

    insertMap("1 2 840 113549 1 9 16 1 24", "routeOriginAttest");

    insertMap("1 2 840 113549 1 9 16 1 25", "symmetricKeyPackage");

    insertMap("1 2 840 113549 1 9 16 1 26", "rpkiManifest");

    insertMap("1 2 840 113549 1 9 16 1 27", "asciiTextWithCRLF");

    insertMap("1 2 840 113549 1 9 16 1 28", "xml");

    insertMap("1 2 840 113549 1 9 16 1 29", "pdf");

    insertMap("1 2 840 113549 1 9 16 1 30", "postscript");

    insertMap("1 2 840 113549 1 9 16 1 31", "timestampedData");

    insertMap("1 2 840 113549 1 9 16 1 32", "asAdjacencyAttest");

    insertMap("1 2 840 113549 1 9 16 1 33", "rpkiTrustAnchor");

    insertMap("1 2 840 113549 1 9 16 1 34", "trustAnchorList");

    insertMap("1 2 840 113549 1 9 16 2", "authenticatedAttributes");

    insertMap("1 2 840 113549 1 9 16 2 1", "receiptRequest");

    insertMap("1 2 840 113549 1 9 16 2 2", "securityLabel");

    insertMap("1 2 840 113549 1 9 16 2 3", "mlExpandHistory");

    insertMap("1 2 840 113549 1 9 16 2 4", "contentHint");

    insertMap("1 2 840 113549 1 9 16 2 5", "msgSigDigest");

    insertMap("1 2 840 113549 1 9 16 2 6", "encapContentType");

    insertMap("1 2 840 113549 1 9 16 2 7", "contentIdentifier");

    insertMap("1 2 840 113549 1 9 16 2 8", "macValue");

    insertMap("1 2 840 113549 1 9 16 2 9", "equivalentLabels");

    insertMap("1 2 840 113549 1 9 16 2 10", "contentReference");

    insertMap("1 2 840 113549 1 9 16 2 11", "encrypKeyPref");

    insertMap("1 2 840 113549 1 9 16 2 12", "signingCertificate");

    insertMap("1 2 840 113549 1 9 16 2 13", "smimeEncryptCerts");

    insertMap("1 2 840 113549 1 9 16 2 14", "timeStampToken");

    insertMap("1 2 840 113549 1 9 16 2 15", "sigPolicyId");

    insertMap("1 2 840 113549 1 9 16 2 16", "commitmentType");

    insertMap("1 2 840 113549 1 9 16 2 17", "signerLocation");

    insertMap("1 2 840 113549 1 9 16 2 18", "signerAttr");

    insertMap("1 2 840 113549 1 9 16 2 19", "otherSigCert");

    insertMap("1 2 840 113549 1 9 16 2 20", "contentTimestamp");

    insertMap("1 2 840 113549 1 9 16 2 21", "certificateRefs");

    insertMap("1 2 840 113549 1 9 16 2 22", "revocationRefs");

    insertMap("1 2 840 113549 1 9 16 2 23", "certValues");

    insertMap("1 2 840 113549 1 9 16 2 24", "revocationValues");

    insertMap("1 2 840 113549 1 9 16 2 25", "escTimeStamp");

    insertMap("1 2 840 113549 1 9 16 2 26", "certCRLTimestamp");

    insertMap("1 2 840 113549 1 9 16 2 27", "archiveTimeStamp");

    insertMap("1 2 840 113549 1 9 16 2 28", "signatureType");

    insertMap("1 2 840 113549 1 9 16 2 29", "dvcsDvc");

    insertMap("1 2 840 113549 1 9 16 2 30", "cekReference");

    insertMap("1 2 840 113549 1 9 16 2 31", "maxCEKDecrypts");

    insertMap("1 2 840 113549 1 9 16 2 32", "kekDerivationAlg");

    insertMap("1 2 840 113549 1 9 16 2 33", "intendedRecipients");

    insertMap("1 2 840 113549 1 9 16 2 34", "cmcUnsignedData");

    insertMap("1 2 840 113549 1 9 16 2 35", "fwPackageID");

    insertMap("1 2 840 113549 1 9 16 2 36", "fwTargetHardwareIDs");

    insertMap("1 2 840 113549 1 9 16 2 37", "fwDecryptKeyID");

    insertMap("1 2 840 113549 1 9 16 2 38", "fwImplCryptAlgs");

    insertMap("1 2 840 113549 1 9 16 2 39", "fwWrappedFirmwareKey");

    insertMap("1 2 840 113549 1 9 16 2 40", "fwCommunityIdentifiers");

    insertMap("1 2 840 113549 1 9 16 2 41", "fwPkgMessageDigest");

    insertMap("1 2 840 113549 1 9 16 2 42", "fwPackageInfo");

    insertMap("1 2 840 113549 1 9 16 2 43", "fwImplCompressAlgs");

    insertMap("1 2 840 113549 1 9 16 2 44", "etsAttrCertificateRefs");

    insertMap("1 2 840 113549 1 9 16 2 45", "etsAttrRevocationRefs");

    insertMap("1 2 840 113549 1 9 16 2 46", "binarySigningTime");

    insertMap("1 2 840 113549 1 9 16 2 47", "signingCertificateV2");

    insertMap("1 2 840 113549 1 9 16 2 48", "etsArchiveTimeStampV2");

    insertMap("1 2 840 113549 1 9 16 2 49", "erInternal");

    insertMap("1 2 840 113549 1 9 16 2 50", "erExternal");

    insertMap("1 2 840 113549 1 9 16 2 51", "multipleSignatures");

    insertMap("1 2 840 113549 1 9 16 3 1", "esDHwith3DES");

    insertMap("1 2 840 113549 1 9 16 3 2", "esDHwithRC2");

    insertMap("1 2 840 113549 1 9 16 3 3", "3desWrap");

    insertMap("1 2 840 113549 1 9 16 3 4", "rc2Wrap");

    insertMap("1 2 840 113549 1 9 16 3 5", "esDH");

    insertMap("1 2 840 113549 1 9 16 3 6", "cms3DESwrap");

    insertMap("1 2 840 113549 1 9 16 3 7", "cmsRC2wrap");

    insertMap("1 2 840 113549 1 9 16 3 8", "zlib");

    insertMap("1 2 840 113549 1 9 16 3 9", "pwriKEK");

    insertMap("1 2 840 113549 1 9 16 3 10", "ssDH");

    insertMap("1 2 840 113549 1 9 16 3 11", "hmacWith3DESwrap");

    insertMap("1 2 840 113549 1 9 16 3 12", "hmacWithAESwrap");

    insertMap("1 2 840 113549 1 9 16 3 13", "md5XorExperiment");

    insertMap("1 2 840 113549 1 9 16 3 14", "rsaKEM");

    insertMap("1 2 840 113549 1 9 16 3 15", "authEnc128");

    insertMap("1 2 840 113549 1 9 16 3 16", "authEnc256");

    insertMap("1 2 840 113549 1 9 16 4 1", "certDist-ldap");

    insertMap("1 2 840 113549 1 9 16 5 1", "sigPolicyQualifier-spuri x");

    insertMap("1 2 840 113549 1 9 16 5 2",
        "sigPolicyQualifier-spUserNotice");

    insertMap("1 2 840 113549 1 9 16 6 1", "proofOfOrigin");

    insertMap("1 2 840 113549 1 9 16 6 2", "proofOfReceipt");

    insertMap("1 2 840 113549 1 9 16 6 3", "proofOfDelivery");

    insertMap("1 2 840 113549 1 9 16 6 4", "proofOfSender");

    insertMap("1 2 840 113549 1 9 16 6 5", "proofOfApproval");

    insertMap("1 2 840 113549 1 9 16 6 6", "proofOfCreation");

    insertMap("1 2 840 113549 1 9 16 8 1", "glUseKEK");

    insertMap("1 2 840 113549 1 9 16 8 2", "glDelete");

    insertMap("1 2 840 113549 1 9 16 8 3", "glAddMember");

    insertMap("1 2 840 113549 1 9 16 8 4", "glDeleteMember");

    insertMap("1 2 840 113549 1 9 16 8 5", "glRekey");

    insertMap("1 2 840 113549 1 9 16 8 6", "glAddOwner");

    insertMap("1 2 840 113549 1 9 16 8 7", "glRemoveOwner");

    insertMap("1 2 840 113549 1 9 16 8 8", "glkCompromise");

    insertMap("1 2 840 113549 1 9 16 8 9", "glkRefresh");

    insertMap("1 2 840 113549 1 9 16 8 10", "glFailInfo");

    insertMap("1 2 840 113549 1 9 16 8 11", "glaQueryRequest");

    insertMap("1 2 840 113549 1 9 16 8 12", "glaQueryResponse");

    insertMap("1 2 840 113549 1 9 16 8 13", "glProvideCert");

    insertMap("1 2 840 113549 1 9 16 8 14", "glUpdateCert");

    insertMap("1 2 840 113549 1 9 16 8 15", "glKey");

    insertMap("1 2 840 113549 1 9 16 9", "signatureTypeIdentifier");

    insertMap("1 2 840 113549 1 9 16 9 1", "originatorSig");

    insertMap("1 2 840 113549 1 9 16 9 2", "domainSig");

    insertMap("1 2 840 113549 1 9 16 9 3", "additionalAttributesSig");

    insertMap("1 2 840 113549 1 9 16 9 4", "reviewSig");

    insertMap("1 2 840 113549 1 9 16 11", "capabilities");

    insertMap("1 2 840 113549 1 9 16 11 1", "preferBinaryInside");

    insertMap("1 2 840 113549 1 9 20", "friendlyName (for PKCS #12)");

    insertMap("1 2 840 113549 1 9 21", "localKeyID (for PKCS #12)");

    insertMap("1 2 840 113549 1 9 22", "certTypes (for PKCS #12)");

    insertMap("1 2 840 113549 1 9 22 1", "x509Certificate (for PKCS #12)");

    insertMap("1 2 840 113549 1 9 22 2", "sdsiCertificate (for PKCS #12)");

    insertMap("1 2 840 113549 1 9 23", "crlTypes (for PKCS #12)");

    insertMap("1 2 840 113549 1 9 23 1", "x509Crl (for PKCS #12)");

    insertMap("1 2 840 113549 1 9 24", "pkcs9objectClass");

    insertMap("1 2 840 113549 1 9 25", "pkcs9attributes");

    insertMap("1 2 840 113549 1 9 25 1", "pkcs15Token");

    insertMap("1 2 840 113549 1 9 25 2", "encryptedPrivateKeyInfo");

    insertMap("1 2 840 113549 1 9 25 3", "randomNonce");

    insertMap("1 2 840 113549 1 9 25 4", "sequenceNumber");

    insertMap("1 2 840 113549 1 9 25 5", "pkcs7PDU");

    insertMap("1 2 840 113549 1 9 26", "pkcs9syntax");

    insertMap("1 2 840 113549 1 9 27", "pkcs9matchingRules");

    insertMap("1 2 840 113549 1 12", "pkcs-12");

    insertMap("1 2 840 113549 1 12 1", "pkcs-12-PbeIds");

    insertMap("1 2 840 113549 1 12 1 1", "pbeWithSHAAnd128BitRC4");

    insertMap("1 2 840 113549 1 12 1 2", "pbeWithSHAAnd40BitRC4");

    insertMap("1 2 840 113549 1 12 1 3", "pbeWithSHAAnd3-KeyTripleDES-CBC");

    insertMap("1 2 840 113549 1 12 1 4", "pbeWithSHAAnd2-KeyTripleDES-CBC");

    insertMap("1 2 840 113549 1 12 1 5", "pbeWithSHAAnd128BitRC2-CBC");

    insertMap("1 2 840 113549 1 12 1 6", "pbeWithSHAAnd40BitRC2-CBC");

    insertMap("1 2 840 113549 1 12 2", "pkcs-12-ESPVKID");

    insertMap("1 2 840 113549 1 12 2 1", "pkcs-12-PKCS8KeyShrouding");

    insertMap("1 2 840 113549 1 12 3", "pkcs-12-BagIds");

    insertMap("1 2 840 113549 1 12 3 1", "pkcs-12-keyBagId");

    insertMap("1 2 840 113549 1 12 3 2", "pkcs-12-certAndCRLBagId");

    insertMap("1 2 840 113549 1 12 3 3", "pkcs-12-secretBagId");

    insertMap("1 2 840 113549 1 12 3 4", "pkcs-12-safeContentsId");

    insertMap("1 2 840 113549 1 12 3 5", "pkcs-12-pkcs-8ShroudedKeyBagId");

    insertMap("1 2 840 113549 1 12 4", "pkcs-12-CertBagID");

    insertMap("1 2 840 113549 1 12 4 1", "pkcs-12-X509CertCRLBagID");

    insertMap("1 2 840 113549 1 12 4 2", "pkcs-12-SDSICertBagID");

    insertMap("1 2 840 113549 1 12 5", "pkcs-12-OID");

    insertMap("1 2 840 113549 1 12 5 1", "pkcs-12-PBEID");

    insertMap("1 2 840 113549 1 12 5 1 1",
        "pkcs-12-PBEWithSha1And128BitRC4");

    insertMap("1 2 840 113549 1 12 5 1 2", "pkcs-12-PBEWithSha1And40BitRC4");

    insertMap("1 2 840 113549 1 12 5 1 3",
        "pkcs-12-PBEWithSha1AndTripleDESCBC");

    insertMap("1 2 840 113549 1 12 5 1 4",
        "pkcs-12-PBEWithSha1And128BitRC2CBC");

    insertMap("1 2 840 113549 1 12 5 1 5",
        "pkcs-12-PBEWithSha1And40BitRC2CBC");

    insertMap("1 2 840 113549 1 12 5 1 6", "pkcs-12-PBEWithSha1AndRC4");

    insertMap("1 2 840 113549 1 12 5 1 7", "pkcs-12-PBEWithSha1AndRC2CBC");

    insertMap("1 2 840 113549 1 12 5 2", "pkcs-12-EnvelopingID");

    insertMap("1 2 840 113549 1 12 5 2 1",
        "pkcs-12-RSAEncryptionWith128BitRC4");

    insertMap("1 2 840 113549 1 12 5 2 2",
        "pkcs-12-RSAEncryptionWith40BitRC4");

    insertMap("1 2 840 113549 1 12 5 2 3",
        "pkcs-12-RSAEncryptionWithTripleDES");

    insertMap("1 2 840 113549 1 12 5 3", "pkcs-12-SignatureID");

    insertMap("1 2 840 113549 1 12 5 3 1",
        "pkcs-12-RSASignatureWithSHA1Digest");

    insertMap("1 2 840 113549 1 12 10", "pkcs-12Version1");

    insertMap("1 2 840 113549 1 12 10 1", "pkcs-12BadIds");

    insertMap("1 2 840 113549 1 12 10 1 1", "pkcs-12-keyBag");

    insertMap("1 2 840 113549 1 12 10 1 2", "pkcs-12-pkcs-8ShroudedKeyBag");

    insertMap("1 2 840 113549 1 12 10 1 3", "pkcs-12-certBag");

    insertMap("1 2 840 113549 1 12 10 1 4", "pkcs-12-crlBag");

    insertMap("1 2 840 113549 1 12 10 1 5", "pkcs-12-secretBag");

    insertMap("1 2 840 113549 1 12 10 1 6", "pkcs-12-safeContentsBag");

    insertMap("1 2 840 113549 1 15 1", "pkcs15modules");

    insertMap("1 2 840 113549 1 15 2", "pkcs15attributes");

    insertMap("1 2 840 113549 1 15 3", "pkcs15contentType");

    insertMap("1 2 840 113549 1 15 3 1", "pkcs15content");

    insertMap("1 2 840 113549 2", "digestAlgorithm");

    insertMap("1 2 840 113549 2 2", "md2");

    insertMap("1 2 840 113549 2 4", "md4");

    insertMap("1 2 840 113549 2 5", "md5");

    insertMap("1 2 840 113549 2 7", "hmacWithSHA1");

    insertMap("1 2 840 113549 2 8", "hmacWithSHA224");

    insertMap("1 2 840 113549 2 9", "hmacWithSHA256");

    insertMap("1 2 840 113549 2 10", "hmacWithSHA384");

    insertMap("1 2 840 113549 2 11", "hmacWithSHA512");

    insertMap("1 2 840 113549 3", "encryptionAlgorithm");

    insertMap("1 2 840 113549 3 2", "rc2CBC");

    insertMap("1 2 840 113549 3 3", "rc2ECB");

    insertMap("1 2 840 113549 3 4", "rc4");

    insertMap("1 2 840 113549 3 5", "rc4WithMAC");

    insertMap("1 2 840 113549 3 6", "desx-CBC");

    insertMap("1 2 840 113549 3 7", "des-EDE3-CBC");

    insertMap("1 2 840 113549 3 8", "rc5CBC");

    insertMap("1 2 840 113549 3 9", "rc5-CBCPad");

    insertMap("1 2 840 113549 3 10", "desCDMF");

    insertMap("1 2 840 114021 1 6 1", "Identrus unknown policyIdentifier");

    insertMap("1 2 840 114021 4 1", "identrusOCSP");

    insertMap("1 2 840 113556 1 2 241", "deliveryMechanism");

    insertMap("1 2 840 113556 1 2 281", "ntSecurityDescriptor");

    insertMap("1 2 840 113556 1 3 0", "site-Addressing");

    insertMap("1 2 840 113556 1 3 13", "classSchema");

    insertMap("1 2 840 113556 1 3 14", "attributeSchema");

    insertMap("1 2 840 113556 1 3 17", "mailbox-Agent");

    insertMap("1 2 840 113556 1 3 22", "mailbox");

    insertMap("1 2 840 113556 1 3 23", "container");

    insertMap("1 2 840 113556 1 3 46", "mailRecipient");

    insertMap("1 2 840 113556 1 4 145", "revision");

    insertMap("1 2 840 113556 1 4 1327", "pKIDefaultKeySpec");

    insertMap("1 2 840 113556 1 4 1328", "pKIKeyUsage");

    insertMap("1 2 840 113556 1 4 1329", "pKIMaxIssuingDepth");

    insertMap("1 2 840 113556 1 4 1330", "pKICriticalExtensions");

    insertMap("1 2 840 113556 1 4 1331", "pKIExpirationPeriod");

    insertMap("1 2 840 113556 1 4 1332", "pKIOverlapPeriod");

    insertMap("1 2 840 113556 1 4 1333", "pKIExtendedKeyUsage");

    insertMap("1 2 840 113556 1 4 1334", "pKIDefaultCSPs");

    insertMap("1 2 840 113556 1 4 1335", "pKIEnrollmentAccess");

    insertMap("1 2 840 113556 1 4 1429", "msPKI-RA-Signature");

    insertMap("1 2 840 113556 1 4 1430", "msPKI-Enrollment-Flag");

    insertMap("1 2 840 113556 1 4 1431", "msPKI-Private-Key-Flag");

    insertMap("1 2 840 113556 1 4 1432", "msPKI-Certificate-Name-Flag");

    insertMap("1 2 840 113556 1 4 1433", "msPKI-Minimal-Key-Size");

    insertMap("1 2 840 113556 1 4 1434", "msPKI-Template-Schema-Version");

    insertMap("1 2 840 113556 1 4 1435", "msPKI-Template-Minor-Revision");

    insertMap("1 2 840 113556 1 4 1436", "msPKI-Cert-Template-OID");

    insertMap("1 2 840 113556 1 4 1437", "msPKI-Supersede-Templates");

    insertMap("1 2 840 113556 1 4 1438", "msPKI-RA-Policies");

    insertMap("1 2 840 113556 1 4 1439", "msPKI-Certificate-Policy");

    insertMap("1 2 840 113556 1 4 1674",
        "msPKI-Certificate-Application-Policy");

    insertMap("1 2 840 113556 1 4 1675", "msPKI-RA-Application-Policies");

    insertMap("1 2 840 113556 4 3", "microsoftExcel");

    insertMap("1 2 840 113556 4 4", "titledWithOID");

    insertMap("1 2 840 113556 4 5", "microsoftPowerPoint");

    insertMap("1 2 840 113583 1", "adobeAcrobat");

    insertMap("1 2 840 113583 1 1", "acrobatSecurity");

    insertMap("1 2 840 113583 1 1 1", "pdfPassword");

    insertMap("1 2 840 113583 1 1 2", "pdfDefaultSigningCredential");

    insertMap("1 2 840 113583 1 1 3", "pdfDefaultEncryptionCredential");

    insertMap("1 2 840 113583 1 1 4", "pdfPasswordTimeout");

    insertMap("1 2 840 113583 1 1 5", "pdfAuthenticDocumentsTrust");

    insertMap("1 2 840 113583 1 1 6", "pdfDynamicContentTrust");

    insertMap("1 2 840 113583 1 1 7", "pdfUbiquityTrust");

    insertMap("1 2 840 113583 1 1 8", "pdfRevocationInfoArchival");

    insertMap("1 2 840 113583 1 1 9", "pdfX509Extension");

    insertMap("1 2 840 113583 1 1 9 1", "pdfTimeStamp");

    insertMap("1 2 840 113583 1 1 9 2", "pdfArchiveRevInfo");

    insertMap("1 2 840 113583 1 1 10", "pdfPPLKLiteCredential");

    insertMap("1 2 840 113583 1 2 ", "acrobatCPS");

    insertMap("1 2 840 113583 1 2 1", "pdfAuthenticDocumentsCPS");

    insertMap("1 2 840 113583 1 2 2", "pdfTestCPS");

    insertMap("1 2 840 113583 1 2 3", "pdfUbiquityCPS");

    insertMap("1 2 840 113583 1 2 4", "pdfAdhocCPS");

    insertMap("1 2 840 113583 1 7", "acrobatUbiquity");

    insertMap("1 2 840 113583 1 7 1", "pdfUbiquitySubRights");

    insertMap("1 2 840 113583 1 9", "acrobatExtension");

    insertMap("1 2 840 113628 114 1 7", "adobePKCS7");

    insertMap("1 2 840 113635 100", "appleDataSecurity");

    insertMap("1 2 840 113635 100 1", "appleTrustPolicy");

    insertMap("1 2 840 113635 100 1 1", "appleISignTP");

    insertMap("1 2 840 113635 100 1 2", "appleX509Basic");

    insertMap("1 2 840 113635 100 1 3", "appleSSLPolicy");

    insertMap("1 2 840 113635 100 1 4", "appleLocalCertGenPolicy");

    insertMap("1 2 840 113635 100 1 5", "appleCSRGenPolicy");

    insertMap("1 2 840 113635 100 1 6", "appleCRLPolicy");

    insertMap("1 2 840 113635 100 1 7", "appleOCSPPolicy");

    insertMap("1 2 840 113635 100 1 8", "appleSMIMEPolicy");

    insertMap("1 2 840 113635 100 1 9", "appleEAPPolicy");

    insertMap("1 2 840 113635 100 1 10", "appleSWUpdateSigningPolicy");

    insertMap("1 2 840 113635 100 1 11", "appleIPSecPolicy");

    insertMap("1 2 840 113635 100 1 12", "appleIChatPolicy");

    insertMap("1 2 840 113635 100 1 13", "appleResourceSignPolicy");

    insertMap("1 2 840 113635 100 1 14", "applePKINITClientPolicy");

    insertMap("1 2 840 113635 100 1 15", "applePKINITServerPolicy");

    insertMap("1 2 840 113635 100 1 16", "appleCodeSigningPolicy");

    insertMap("1 2 840 113635 100 1 17", "applePackageSigningPolicy");

    insertMap("1 2 840 113635 100 2", "appleSecurityAlgorithm");

    insertMap("1 2 840 113635 100 2 1", "appleFEE");

    insertMap("1 2 840 113635 100 2 2", "appleASC");

    insertMap("1 2 840 113635 100 2 3", "appleFEE_MD5");

    insertMap("1 2 840 113635 100 2 4", "appleFEE_SHA1");

    insertMap("1 2 840 113635 100 2 5", "appleFEED");

    insertMap("1 2 840 113635 100 2 6", "appleFEEDEXP");

    insertMap("1 2 840 113635 100 2 7", "appleECDSA");

    insertMap("1 2 840 113635 100 3", "appleDotMacCertificate");

    insertMap("1 2 840 113635 100 3 1", "appleDotMacCertificateRequest");

    insertMap("1 2 840 113635 100 3 2", "appleDotMacCertificateExtension");

    insertMap("1 2 840 113635 100 3 3",
        "appleDotMacCertificateRequestValues");

    insertMap("1 2 840 113635 100 4", "appleExtendedKeyUsage");

    insertMap("1 2 840 113635 100 4 1", "appleCodeSigning");

    insertMap("1 2 840 113635 100 4 1 1", "appleCodeSigningDevelopment");

    insertMap("1 2 840 113635 100 4 1 2", "appleSoftwareUpdateSigning");

    insertMap("1 2 840 113635 100 4 1 3", "appleCodeSigningThirdParty");

    insertMap("1 2 840 113635 100 4 1 4", "appleResourceSigning");

    insertMap("1 2 840 113635 100 4 2", "appleIChatSigning");

    insertMap("1 2 840 113635 100 4 3", "appleIChatEncryption");

    insertMap("1 2 840 113635 100 4 4", "appleSystemIdentity");

    insertMap("1 2 840 113635 100 4 5", "appleCryptoEnv");

    insertMap("1 2 840 113635 100 4 5 1", "appleCryptoProductionEnv");

    insertMap("1 2 840 113635 100 4 5 2", "appleCryptoMaintenanceEnv");

    insertMap("1 2 840 113635 100 4 5 3", "appleCryptoTestEnv");

    insertMap("1 2 840 113635 100 4 5 4", "appleCryptoDevelopmentEnv");

    insertMap("1 2 840 113635 100 4 6", "appleCryptoQoS");

    insertMap("1 2 840 113635 100 4 6 1", "appleCryptoTier0QoS");

    insertMap("1 2 840 113635 100 4 6 2", "appleCryptoTier1QoS");

    insertMap("1 2 840 113635 100 4 6 3", "appleCryptoTier2QoS");

    insertMap("1 2 840 113635 100 4 6 4", "appleCryptoTier3QoS");

    insertMap("1 2 840 113635 100 5", "appleCertificatePolicies");

    insertMap("1 2 840 113635 100 5 1", "appleCertificatePolicyID");

    insertMap("1 2 840 113635 100 5 2", "appleDotMacCertificatePolicyID");

    insertMap("1 2 840 113635 100 5 3", "appleADCCertificatePolicyID");

    insertMap("1 2 840 113635 100 6", "appleCertificateExtensions");

    insertMap("1 2 840 113635 100 6 1",
        "appleCertificateExtensionCodeSigning");

    insertMap("1 2 840 113635 100 6 1 1",
        "appleCertificateExtensionAppleSigning");

    insertMap("1 2 840 113635 100 6 1 2",
        "appleCertificateExtensionADCDeveloperSigning");

    insertMap("1 2 840 113635 100 6 1 3",
        "appleCertificateExtensionADCAppleSigning");

    insertMap("1 3 6 1 4 1 311 2 1 4", "spcIndirectDataContext");

    insertMap("1 3 6 1 4 1 311 2 1 10", "spcAgencyInfo");

    insertMap("1 3 6 1 4 1 311 2 1 11", "spcStatementType");

    insertMap("1 3 6 1 4 1 311 2 1 12", "spcSpOpusInfo");

    insertMap("1 3 6 1 4 1 311 2 1 14", "certReqExtensions");

    insertMap("1 3 6 1 4 1 311 2 1 15", "spcPEImageData");

    insertMap("1 3 6 1 4 1 311 2 1 18", "spcRawFileData");

    insertMap("1 3 6 1 4 1 311 2 1 19", "spcStructuredStorageData");

    insertMap("1 3 6 1 4 1 311 2 1 20", "spcJavaClassData (type 1)");

    insertMap("1 3 6 1 4 1 311 2 1 21", "individualCodeSigning");

    insertMap("1 3 6 1 4 1 311 2 1 22", "commercialCodeSigning");

    insertMap("1 3 6 1 4 1 311 2 1 25", "spcLink (type 2)");

    insertMap("1 3 6 1 4 1 311 2 1 26", "spcMinimalCriteriaInfo");

    insertMap("1 3 6 1 4 1 311 2 1 27", "spcFinancialCriteriaInfo");

    insertMap("1 3 6 1 4 1 311 2 1 28", "spcLink (type 3)");

    insertMap("1 3 6 1 4 1 311 2 1 29", "spcHashInfoObjID");

    insertMap("1 3 6 1 4 1 311 2 1 30", "spcSipInfoObjID");

    insertMap("1 3 6 1 4 1 311 2 2", "ctl");

    insertMap("1 3 6 1 4 1 311 2 2 1", "ctlTrustedCodesigningCAList");

    insertMap("1 3 6 1 4 1 311 2 2 2", "ctlTrustedClientAuthCAList");

    insertMap("1 3 6 1 4 1 311 2 2 3", "ctlTrustedServerAuthCAList");

    insertMap("1 3 6 1 4 1 311 3 2 1", "timestampRequest");

    insertMap("1 3 6 1 4 1 311 10 1", "certTrustList");

    insertMap("1 3 6 1 4 1 311 10 1 1", "sortedCtl");

    insertMap("1 3 6 1 4 1 311 10 2", "nextUpdateLocation");

    insertMap("1 3 6 1 4 1 311 10 3 1", "certTrustListSigning");

    insertMap("1 3 6 1 4 1 311 10 3 2", "timeStampSigning");

    insertMap("1 3 6 1 4 1 311 10 3 3", "serverGatedCrypto");

    insertMap("1 3 6 1 4 1 311 10 3 3 1", "serialized");

    insertMap("1 3 6 1 4 1 311 10 3 4", "encryptedFileSystem");

    insertMap("1 3 6 1 4 1 311 10 3 5", "whqlCrypto");

    insertMap("1 3 6 1 4 1 311 10 3 6", "nt5Crypto");

    insertMap("1 3 6 1 4 1 311 10 3 7", "oemWHQLCrypto");

    insertMap("1 3 6 1 4 1 311 10 3 8", "embeddedNTCrypto");

    insertMap("1 3 6 1 4 1 311 10 3 9", "rootListSigner");

    insertMap("1 3 6 1 4 1 311 10 3 10", "qualifiedSubordination");

    insertMap("1 3 6 1 4 1 311 10 3 11", "keyRecovery");

    insertMap("1 3 6 1 4 1 311 10 3 12", "documentSigning");

    insertMap("1 3 6 1 4 1 311 10 3 13", "lifetimeSigning");

    insertMap("1 3 6 1 4 1 311 10 3 14", "mobileDeviceSoftware");

    insertMap("1 3 6 1 4 1 311 10 3 15", "smartDisplay");

    insertMap("1 3 6 1 4 1 311 10 3 16", "cspSignature");

    insertMap("1 3 6 1 4 1 311 10 3 4 1", "efsRecovery");

    insertMap("1 3 6 1 4 1 311 10 4 1", "yesnoTrustAttr");

    insertMap("1 3 6 1 4 1 311 10 5 1", "drm");

    insertMap("1 3 6 1 4 1 311 10 5 2", "drmIndividualization");

    insertMap("1 3 6 1 4 1 311 10 6 1", "licenses");

    insertMap("1 3 6 1 4 1 311 10 6 2", "licenseServer");

    insertMap("1 3 6 1 4 1 311 10 7 1", "keyidRdn");

    insertMap("1 3 6 1 4 1 311 10 8 1", "removeCertificate");

    insertMap("1 3 6 1 4 1 311 10 9 1", "crossCertDistPoints");

    insertMap("1 3 6 1 4 1 311 10 10 1", "cmcAddAttributes");

    insertMap("1 3 6 1 4 1 311 10 11", "certPropIdPrefix");

    insertMap("1 3 6 1 4 1 311 10 11 4", "certMd5HashPropId");

    insertMap("1 3 6 1 4 1 311 10 11 20", "certKeyIdentifierPropId");

    insertMap("1 3 6 1 4 1 311 10 11 28",
        "certIssuerSerialNumberMd5HashPropId");

    insertMap("1 3 6 1 4 1 311 10 11 29", "certSubjectNameMd5HashPropId");

    insertMap("1 3 6 1 4 1 311 10 12 1", "anyApplicationPolicy");

    insertMap("1 3 6 1 4 1 311 12", "catalog");

    insertMap("1 3 6 1 4 1 311 12 1 1", "catalogList");

    insertMap("1 3 6 1 4 1 311 12 1 2", "catalogListMember");

    insertMap("1 3 6 1 4 1 311 12 2 1", "catalogNameValueObjID");

    insertMap("1 3 6 1 4 1 311 12 2 2", "catalogMemberInfoObjID");

    insertMap("1 3 6 1 4 1 311 13 1", "renewalCertificate");

    insertMap("1 3 6 1 4 1 311 13 2 1", "enrolmentNameValuePair");

    insertMap("1 3 6 1 4 1 311 13 2 2", "enrolmentCSP");

    insertMap("1 3 6 1 4 1 311 13 2 3", "osVersion");

    insertMap("1 3 6 1 4 1 311 16 4", "microsoftRecipientInfo");

    insertMap("1 3 6 1 4 1 311 17 1", "pkcs12KeyProviderNameAttr");

    insertMap("1 3 6 1 4 1 311 17 2", "localMachineKeyset");

    insertMap("1 3 6 1 4 1 311 17 3", "pkcs12ExtendedAttributes");

    insertMap("1 3 6 1 4 1 311 20 1", "autoEnrollCtlUsage");

    insertMap("1 3 6 1 4 1 311 20 2", "enrollCerttypeExtension");

    insertMap("1 3 6 1 4 1 311 20 2 1", "enrollmentAgent");

    insertMap("1 3 6 1 4 1 311 20 2 2", "smartcardLogon");

    insertMap("1 3 6 1 4 1 311 20 2 3", "universalPrincipalName");

    insertMap("1 3 6 1 4 1 311 20 3", "certManifold");

    insertMap("1 3 6 1 4 1 311 21 1", "cAKeyCertIndexPair");

    insertMap("1 3 6 1 4 1 311 21 2", "certSrvPreviousCertHash");

    insertMap("1 3 6 1 4 1 311 21 3", "crlVirtualBase");

    insertMap("1 3 6 1 4 1 311 21 4", "crlNextPublish");

    insertMap("1 3 6 1 4 1 311 21 5", "caExchange");

    insertMap("1 3 6 1 4 1 311 21 6", "keyRecovery");

    insertMap("1 3 6 1 4 1 311 21 7", "certificateTemplate");

    insertMap("1 3 6 1 4 1 311 21 9", "rdnDummySigner");

    insertMap("1 3 6 1 4 1 311 21 10", "applicationCertPolicies");

    insertMap("1 3 6 1 4 1 311 21 11", "applicationPolicyMappings");

    insertMap("1 3 6 1 4 1 311 21 12", "applicationPolicyConstraints");

    insertMap("1 3 6 1 4 1 311 21 13", "archivedKey");

    insertMap("1 3 6 1 4 1 311 21 14", "crlSelfCDP");

    insertMap("1 3 6 1 4 1 311 21 15", "requireCertChainPolicy");

    insertMap("1 3 6 1 4 1 311 21 16", "archivedKeyCertHash");

    insertMap("1 3 6 1 4 1 311 21 17", "issuedCertHash");

    insertMap("1 3 6 1 4 1 311 21 19", "dsEmailReplication");

    insertMap("1 3 6 1 4 1 311 21 20", "requestClientInfo");

    insertMap("1 3 6 1 4 1 311 21 21", "encryptedKeyHash");

    insertMap("1 3 6 1 4 1 311 21 22", "certsrvCrossCaVersion");

    insertMap("1 3 6 1 4 1 311 25 1", "ntdsReplication");

    insertMap("1 3 6 1 4 1 311 31 1", "productUpdate");

    insertMap("1 3 6 1 4 1 311 47 1 1", "systemHealth");

    insertMap("1 3 6 1 4 1 311 47 1 3", "systemHealthLoophole");

    insertMap("1 3 6 1 4 1 311 60 1 1", "rootProgramFlags");

    insertMap("1 3 6 1 4 1 311 61 1 1", "kernelModeCodeSigning");

    insertMap("1 3 6 1 4 1 311 60 2 1 1", "jurisdictionOfIncorporationL");

    insertMap("1 3 6 1 4 1 311 60 2 1 2", "jurisdictionOfIncorporationSP");

    insertMap("1 3 6 1 4 1 311 60 2 1 3", "jurisdictionOfIncorporationC");

    insertMap("1 3 6 1 4 1 311 88", "capiCom");

    insertMap("1 3 6 1 4 1 311 88 1", "capiComVersion");

    insertMap("1 3 6 1 4 1 311 88 2", "capiComAttribute");

    insertMap("1 3 6 1 4 1 311 88 2 1", "capiComDocumentName");

    insertMap("1 3 6 1 4 1 311 88 2 2", "capiComDocumentDescription");

    insertMap("1 3 6 1 4 1 311 88 3", "capiComEncryptedData");

    insertMap("1 3 6 1 4 1 311 88 3 1", "capiComEncryptedContent");

    insertMap("1 3 6 1 4 1 188 7 1 1", "ascom");

    insertMap("1 3 6 1 4 1 188 7 1 1 1", "ideaECB");

    insertMap("1 3 6 1 4 1 188 7 1 1 2", "ideaCBC");

    insertMap("1 3 6 1 4 1 188 7 1 1 3", "ideaCFB");

    insertMap("1 3 6 1 4 1 188 7 1 1 4", "ideaOFB");

    insertMap("1 3 6 1 4 1 2428 10 1 1", "UNINETT policyIdentifier");

    insertMap("1 3 6 1 4 1 2712 10", "ICE-TEL policyIdentifier");

    insertMap("1 3 6 1 4 1 2786 1 1 1", "ICE-TEL Italian policyIdentifier");

    insertMap("1 3 6 1 4 1 3029 1 1 1", "blowfishECB");

    insertMap("1 3 6 1 4 1 3029 1 1 2", "blowfishCBC");

    insertMap("1 3 6 1 4 1 3029 1 1 3", "blowfishCFB");

    insertMap("1 3 6 1 4 1 3029 1 1 4", "blowfishOFB");

    insertMap("1 3 6 1 4 1 3029 1 2 1", "elgamal");

    insertMap("1 3 6 1 4 1 3029 1 2 1 1", "elgamalWithSHA-1");

    insertMap("1 3 6 1 4 1 3029 1 2 1 2", "elgamalWithRIPEMD-160");

    insertMap("1 3 6 1 4 1 3029 3 1 1", "cryptlibPresenceCheck");

    insertMap("1 3 6 1 4 1 3029 3 1 2", "pkiBoot");

    insertMap("1 3 6 1 4 1 3029 3 1 4", "crlExtReason");

    insertMap("1 3 6 1 4 1 3029 3 1 5", "keyFeatures");

    insertMap("1 3 6 1 4 1 3029 4 1", "cryptlibContent");

    insertMap("1 3 6 1 4 1 3029 4 1 1", "cryptlibConfigData");

    insertMap("1 3 6 1 4 1 3029 4 1 2", "cryptlibUserIndex");

    insertMap("1 3 6 1 4 1 3029 4 1 3", "cryptlibUserInfo");

    insertMap("1 3 6 1 4 1 3029 4 1 4", "rtcsRequest");

    insertMap("1 3 6 1 4 1 3029 4 1 5", "rtcsResponse");

    insertMap("1 3 6 1 4 1 3029 4 1 6", "rtcsResponseExt");

    insertMap("1 3 6 1 4 1 3029 42 11172 1", "mpeg-1");

    insertMap("1 3 6 1 4 1 3029 54 11940 54",
        "TSA policy 'Anything that arrives, we sign'");

    insertMap("1 3 6 1 4 1 3029 88 89 90 90 89", "xYZZY policyIdentifier");

    insertMap("1 3 6 1 4 1 3401 8 1 1", "pgpExtension");

    insertMap("1 3 6 1 4 1 3576 7", "eciaAscX12Edi");

    insertMap("1 3 6 1 4 1 3576 7 1", "plainEDImessage");

    insertMap("1 3 6 1 4 1 3576 7 2", "signedEDImessage");

    insertMap("1 3 6 1 4 1 3576 7 5", "integrityEDImessage");

    insertMap("1 3 6 1 4 1 3576 7 65", "iaReceiptMessage");

    insertMap("1 3 6 1 4 1 3576 7 97", "iaStatusMessage");

    insertMap("1 3 6 1 4 1 3576 8", "eciaEdifact");

    insertMap("1 3 6 1 4 1 3576 9", "eciaNonEdi");

    insertMap("1 3 6 1 4 1 4146", "Globalsign");

    insertMap("1 3 6 1 4 1 4146 1", "globalsignPolicy");

    insertMap("1 3 6 1 4 1 4146 1 10", "globalsignDVPolicy");

    insertMap("1 3 6 1 4 1 4146 1 20", "globalsignOVPolicy");

    insertMap("1 3 6 1 4 1 4146 1 30", "globalsignTSAPolicy");

    insertMap("1 3 6 1 4 1 4146 1 40", "globalsignClientCertPolicy");

    insertMap("1 3 6 1 4 1 4146 1 50", "globalsignCodeSignPolicy");

    insertMap("1 3 6 1 4 1 4146 1 60", "globalsignRootSignPolicy");

    insertMap("1 3 6 1 4 1 4146 1 70", "globalsignTrustedRootPolicy");

    insertMap("1 3 6 1 4 1 4146 1 80", "globalsignEDIClientPolicy");

    insertMap("1 3 6 1 4 1 4146 1 81", "globalsignEDIServerPolicy");

    insertMap("1 3 6 1 4 1 4146 1 90", "globalsignTPMRootPolicy");

    insertMap("1 3 6 1 4 1 4146 1 95", "globalsignOCSPPolicy");

    insertMap("1 3 6 1 4 1 5309 1", "edelWebPolicy");

    insertMap("1 3 6 1 4 1 5309 1 2", "edelWebCustomerPolicy");

    insertMap("1 3 6 1 4 1 5309 1 2 1", "edelWebClepsydrePolicy");

    insertMap("1 3 6 1 4 1 5309 1 2 2", "edelWebExperimentalTSAPolicy");

    insertMap("1 3 6 1 4 1 5309 1 2 3", "edelWebOpenEvidenceTSAPolicy");

    insertMap("1 3 6 1 4 1 5472", "timeproof");

    insertMap("1 3 6 1 4 1 5472 1", "tss");

    insertMap("1 3 6 1 4 1 5472 1 1", "tss80");

    insertMap("1 3 6 1 4 1 5472 1 2", "tss380");

    insertMap("1 3 6 1 4 1 5472 1 3", "tss400");

    insertMap("1 3 6 1 4 1 5770 0 3", "secondaryPractices");

    insertMap("1 3 6 1 4 1 5770 0 4", "physicianIdentifiers");

    insertMap("1 3 6 1 4 1 6449 1 2 1 3 1", "comodoPolicy");

    insertMap("1 3 6 1 4 1 6449 1 2 2 15", "wotrustPolicy");

    insertMap("1 3 6 1 4 1 6449 1 3 5 2", "comodoCertifiedDeliveryService");

    insertMap("1 3 6 1 4 1 6449 2 1 1", "comodoTimestampingPolicy");

    insertMap("1 3 6 1 4 1 8301 3 5 1", "validityModelChain");

    insertMap("1 3 6 1 4 1 8301 3 5 2", "validityModelShell");

    insertMap("1 3 6 1 4 1 8231 1", "rolUnicoNacional");

    insertMap("1 3 6 1 4 1 11591", "gnu");

    insertMap("1 3 6 1 4 1 11591 1", "gnuRadius");

    insertMap("1 3 6 1 4 1 11591 3", "gnuRadar");

    insertMap("1 3 6 1 4 1 11591 12", "gnuDigestAlgorithm");

    insertMap("1 3 6 1 4 1 11591 12 2", "tiger");

    insertMap("1 3 6 1 4 1 11591 13", "gnuEncryptionAlgorithm");

    insertMap("1 3 6 1 4 1 11591 13 2", "serpent");

    insertMap("1 3 6 1 4 1 11591 13 2 1", "serpent128_ECB");

    insertMap("1 3 6 1 4 1 11591 13 2 2", "serpent128_CBC");

    insertMap("1 3 6 1 4 1 11591 13 2 3", "serpent128_OFB");

    insertMap("1 3 6 1 4 1 11591 13 2 4", "serpent128_CFB");

    insertMap("1 3 6 1 4 1 11591 13 2 21", "serpent192_ECB");

    insertMap("1 3 6 1 4 1 11591 13 2 22", "serpent192_CBC");

    insertMap("1 3 6 1 4 1 11591 13 2 23", "serpent192_OFB");

    insertMap("1 3 6 1 4 1 11591 13 2 24", "serpent192_CFB");

    insertMap("1 3 6 1 4 1 11591 13 2 41", "serpent256_ECB");

    insertMap("1 3 6 1 4 1 11591 13 2 42", "serpent256_CBC");

    insertMap("1 3 6 1 4 1 11591 13 2 43", "serpent256_OFB");

    insertMap("1 3 6 1 4 1 11591 13 2 44", "serpent256_CFB");

    insertMap("1 3 6 1 4 1 16334 509 1 1", "Northrop Grumman extKeyUsage?");

    insertMap("1 3 6 1 4 1 16334 509 2 1", "ngcClass1");

    insertMap("1 3 6 1 4 1 16334 509 2 2", "ngcClass2");

    insertMap("1 3 6 1 4 1 16334 509 2 3", "ngcClass3");

    insertMap("1 3 6 1 4 1 23629 1 4 2 1 1", "safenetUsageLimit");

    insertMap("1 3 6 1 4 1 23629 1 4 2 1 2", "safenetEndDate");

    insertMap("1 3 6 1 4 1 23629 1 4 2 1 3", "safenetStartDate");

    insertMap("1 3 6 1 4 1 23629 1 4 2 1 4", "safenetAdminCert");

    insertMap("1 3 6 1 4 1 23629 1 4 2 2 1", "safenetKeyDigest");

    insertMap("1 3 6 1 5 5 7", "pkix");

    insertMap("1 3 6 1 5 5 7 0 12", "attributeCert");

    insertMap("1 3 6 1 5 5 7 1", "privateExtension");

    insertMap("1 3 6 1 5 5 7 1 1", "authorityInfoAccess");

    insertMap("1 3 6 1 5 5 7 1 2", "biometricInfo");

    insertMap("1 3 6 1 5 5 7 1 3", "qcStatements");

    insertMap("1 3 6 1 5 5 7 1 4", "acAuditIdentity");

    insertMap("1 3 6 1 5 5 7 1 5", "acTargeting");

    insertMap("1 3 6 1 5 5 7 1 6", "acAaControls");

    insertMap("1 3 6 1 5 5 7 1 7", "ipAddrBlocks");

    insertMap("1 3 6 1 5 5 7 1 8", "autonomousSysIds");

    insertMap("1 3 6 1 5 5 7 1 9", "routerIdentifier");

    insertMap("1 3 6 1 5 5 7 1 10", "acProxying");

    insertMap("1 3 6 1 5 5 7 1 11", "subjectInfoAccess");

    insertMap("1 3 6 1 5 5 7 1 12", "logoType");

    insertMap("1 3 6 1 5 5 7 1 13", "wlanSSID");

    insertMap("1 3 6 1 5 5 7 2", "policyQualifierIds");

    insertMap("1 3 6 1 5 5 7 2 1", "cps");

    insertMap("1 3 6 1 5 5 7 2 2", "unotice");

    insertMap("1 3 6 1 5 5 7 2 3", "textNotice");

    insertMap("1 3 6 1 5 5 7 3", "keyPurpose");

    insertMap("1 3 6 1 5 5 7 3 1", "serverAuth");

    insertMap("1 3 6 1 5 5 7 3 2", "clientAuth");

    insertMap("1 3 6 1 5 5 7 3 3", "codeSigning");

    insertMap("1 3 6 1 5 5 7 3 4", "emailProtection");

    insertMap("1 3 6 1 5 5 7 3 5", "ipsecEndSystem");

    insertMap("1 3 6 1 5 5 7 3 6", "ipsecTunnel");

    insertMap("1 3 6 1 5 5 7 3 7", "ipsecUser");

    insertMap("1 3 6 1 5 5 7 3 8", "timeStamping");

    insertMap("1 3 6 1 5 5 7 3 9", "ocspSigning");

    insertMap("1 3 6 1 5 5 7 3 10", "dvcs");

    insertMap("1 3 6 1 5 5 7 3 11", "sbgpCertAAServerAuth");

    insertMap("1 3 6 1 5 5 7 3 13", "eapOverPPP");

    insertMap("1 3 6 1 5 5 7 3 14", "eapOverLAN");

    insertMap("1 3 6 1 5 5 7 4", "cmpInformationTypes");

    insertMap("1 3 6 1 5 5 7 4 1", "caProtEncCert");

    insertMap("1 3 6 1 5 5 7 4 2", "signKeyPairTypes");

    insertMap("1 3 6 1 5 5 7 4 3", "encKeyPairTypes");

    insertMap("1 3 6 1 5 5 7 4 4", "preferredSymmAlg");

    insertMap("1 3 6 1 5 5 7 4 5", "caKeyUpdateInfo");

    insertMap("1 3 6 1 5 5 7 4 6", "currentCRL");

    insertMap("1 3 6 1 5 5 7 4 7", "unsupportedOIDs");

    insertMap("1 3 6 1 5 5 7 4 10", "keyPairParamReq");

    insertMap("1 3 6 1 5 5 7 4 11", "keyPairParamRep");

    insertMap("1 3 6 1 5 5 7 4 12", "revPassphrase");

    insertMap("1 3 6 1 5 5 7 4 13", "implicitConfirm");

    insertMap("1 3 6 1 5 5 7 4 14", "confirmWaitTime");

    insertMap("1 3 6 1 5 5 7 4 15", "origPKIMessage");

    insertMap("1 3 6 1 5 5 7 4 16", "suppLangTags");

    insertMap("1 3 6 1 5 5 7 5", "crmfRegistration");

    insertMap("1 3 6 1 5 5 7 5 1", "regCtrl");

    insertMap("1 3 6 1 5 5 7 5 1 1", "regToken");

    insertMap("1 3 6 1 5 5 7 5 1 2", "authenticator");

    insertMap("1 3 6 1 5 5 7 5 1 3", "pkiPublicationInfo");

    insertMap("1 3 6 1 5 5 7 5 1 4", "pkiArchiveOptions");

    insertMap("1 3 6 1 5 5 7 5 1 5", "oldCertID");

    insertMap("1 3 6 1 5 5 7 5 1 6", "protocolEncrKey");

    insertMap("1 3 6 1 5 5 7 5 1 7", "altCertTemplate");

    insertMap("1 3 6 1 5 5 7 5 1 8", "wtlsTemplate");

    insertMap("1 3 6 1 5 5 7 5 2", "utf8Pairs");

    insertMap("1 3 6 1 5 5 7 5 2 1", "utf8Pairs");

    insertMap("1 3 6 1 5 5 7 5 2 2", "certReq");

    insertMap("1 3 6 1 5 5 7 6", "algorithms");

    insertMap("1 3 6 1 5 5 7 6 1", "des40");

    insertMap("1 3 6 1 5 5 7 6 2", "noSignature");

    insertMap("1 3 6 1 5 5 7 6 3", "dh-sig-hmac-sha1");

    insertMap("1 3 6 1 5 5 7 6 4", "dh-pop");

    insertMap("1 3 6 1 5 5 7 7", "cmcControls");

    insertMap("1 3 6 1 5 5 7 8", "otherNames");

    insertMap("1 3 6 1 5 5 7 8 1", "personalData");

    insertMap("1 3 6 1 5 5 7 8 2", "userGroup");

    insertMap("1 3 6 1 5 5 7 8 5", "xmppAddr");

    insertMap("1 3 6 1 5 5 7 9", "personalData");

    insertMap("1 3 6 1 5 5 7 9 1", "dateOfBirth");

    insertMap("1 3 6 1 5 5 7 9 2", "placeOfBirth");

    insertMap("1 3 6 1 5 5 7 9 3", "gender");

    insertMap("1 3 6 1 5 5 7 9 4", "countryOfCitizenship");

    insertMap("1 3 6 1 5 5 7 9 5", "countryOfResidence");

    insertMap("1 3 6 1 5 5 7 10", "attributeCertificate");

    insertMap("1 3 6 1 5 5 7 10 1", "authenticationInfo");

    insertMap("1 3 6 1 5 5 7 10 2", "accessIdentity");

    insertMap("1 3 6 1 5 5 7 10 3", "chargingIdentity");

    insertMap("1 3 6 1 5 5 7 10 4", "group");

    insertMap("1 3 6 1 5 5 7 10 5", "role");

    insertMap("1 3 6 1 5 5 7 10 6", "wlanSSID");

    insertMap("1 3 6 1 5 5 7 11", "personalData");

    insertMap("1 3 6 1 5 5 7 11 1", "pkixQCSyntax-v1");

    insertMap("1 3 6 1 5 5 7 14 2", "resourceCertificatePolicy");

    insertMap("1 3 6 1 5 5 7 20", "logo");

    insertMap("1 3 6 1 5 5 7 20 1", "logoLoyalty");

    insertMap("1 3 6 1 5 5 7 20 2", "logoBackground");

    insertMap("1 3 6 1 5 5 7 48 1", "ocsp");

    insertMap("1 3 6 1 5 5 7 48 1 1", "ocspBasic");

    insertMap("1 3 6 1 5 5 7 48 1 2", "ocspNonce");

    insertMap("1 3 6 1 5 5 7 48 1 3", "ocspCRL");

    insertMap("1 3 6 1 5 5 7 48 1 4", "ocspResponse");

    insertMap("1 3 6 1 5 5 7 48 1 5", "ocspNoCheck");

    insertMap("1 3 6 1 5 5 7 48 1 6", "ocspArchiveCutoff");

    insertMap("1 3 6 1 5 5 7 48 1 7", "ocspServiceLocator");

    insertMap("1 3 6 1 5 5 7 48 2", "caIssuers");

    insertMap("1 3 6 1 5 5 7 48 3", "timeStamping");

    insertMap("1 3 6 1 5 5 7 48 4", "dvcs");

    insertMap("1 3 6 1 5 5 7 48 5", "caRepository");

    insertMap("1 3 6 1 5 5 7 48 7", "signedObjectRepository");

    insertMap("1 3 6 1 5 5 7 48 10", "rpkiManifest");

    insertMap("1 3 6 1 5 5 7 48 11", "signedObject");

    insertMap("1 3 6 1 5 5 8 1 1", "hmacMD5");

    insertMap("1 3 6 1 5 5 8 1 2", "hmacSHA");

    insertMap("1 3 6 1 5 5 8 1 3", "hmacTiger");

    insertMap("1 3 6 1 5 5 8 2 2", "iKEIntermediate");

    insertMap("1 3 12 2 1011 7 1", "decEncryptionAlgorithm");

    insertMap("1 3 12 2 1011 7 1 2", "decDEA");

    insertMap("1 3 12 2 1011 7 2", "decHashAlgorithm");

    insertMap("1 3 12 2 1011 7 2 1", "decMD2");

    insertMap("1 3 12 2 1011 7 2 2", "decMD4");

    insertMap("1 3 12 2 1011 7 3", "decSignatureAlgorithm");

    insertMap("1 3 12 2 1011 7 3 1", "decMD2withRSA");

    insertMap("1 3 12 2 1011 7 3 2", "decMD4withRSA");

    insertMap("1 3 12 2 1011 7 3 3", "decDEAMAC");

    insertMap("1 3 14 2 26 5", "sha");

    insertMap("1 3 14 3 2 1 1", "rsa");

    insertMap("1 3 14 3 2 2", "md4WitRSA");

    insertMap("1 3 14 3 2 3", "md5WithRSA");

    insertMap("1 3 14 3 2 4", "md4WithRSAEncryption");

    insertMap("1 3 14 3 2 2 1", "sqmod-N");

    insertMap("1 3 14 3 2 3 1", "sqmod-NwithRSA");

    insertMap("1 3 14 3 2 6", "desECB");

    insertMap("1 3 14 3 2 7", "desCBC");

    insertMap("1 3 14 3 2 8", "desOFB");

    insertMap("1 3 14 3 2 9", "desCFB");

    insertMap("1 3 14 3 2 10", "desMAC");

    insertMap("1 3 14 3 2 11", "rsaSignature");

    insertMap("1 3 14 3 2 12", "dsa");

    insertMap("1 3 14 3 2 13", "dsaWithSHA");

    insertMap("1 3 14 3 2 14", "mdc2WithRSASignature");

    insertMap("1 3 14 3 2 15", "shaWithRSASignature");

    insertMap("1 3 14 3 2 16", "dhWithCommonModulus");

    insertMap("1 3 14 3 2 17", "desEDE");

    insertMap("1 3 14 3 2 18", "sha");

    insertMap("1 3 14 3 2 19", "mdc-2");

    insertMap("1 3 14 3 2 20", "dsaCommon");

    insertMap("1 3 14 3 2 21", "dsaCommonWithSHA");

    insertMap("1 3 14 3 2 22", "rsaKeyTransport");

    insertMap("1 3 14 3 2 23", "keyed-hash-seal");

    insertMap("1 3 14 3 2 24", "md2WithRSASignature");

    insertMap("1 3 14 3 2 25", "md5WithRSASignature");

    insertMap("1 3 14 3 2 26", "sha1");

    insertMap("1 3 14 3 2 27", "dsaWithSHA1");

    insertMap("1 3 14 3 2 28", "dsaWithCommonSHA1");

    insertMap("1 3 14 3 2 29", "sha-1WithRSAEncryption");

    insertMap("1 3 14 3 3 1", "simple-strong-auth-mechanism");

    insertMap("1 3 14 7 2 1 1", "ElGamal");

    insertMap("1 3 14 7 2 3 1", "md2WithRSA");

    insertMap("1 3 14 7 2 3 2", "md2WithElGamal");

    insertMap("1 3 36 1", "document");

    insertMap("1 3 36 1 1", "finalVersion");

    insertMap("1 3 36 1 2", "draft");

    insertMap("1 3 36 2", "sio");

    insertMap("1 3 36 2 1", "sedu");

    insertMap("1 3 36 3", "algorithm");

    insertMap("1 3 36 3 1", "encryptionAlgorithm");

    insertMap("1 3 36 3 1 1", "des");

    insertMap("1 3 36 3 1 1 1", "desECB_pad");

    insertMap("1 3 36 3 1 1 1 1", "desECB_ISOpad");

    insertMap("1 3 36 3 1 1 2 1", "desCBC_pad");

    insertMap("1 3 36 3 1 1 2 1 1", "desCBC_ISOpad");

    insertMap("1 3 36 3 1 3", "des_3");

    insertMap("1 3 36 3 1 3 1 1", "des_3ECB_pad");

    insertMap("1 3 36 3 1 3 1 1 1", "des_3ECB_ISOpad");

    insertMap("1 3 36 3 1 3 2 1", "des_3CBC_pad");

    insertMap("1 3 36 3 1 3 2 1 1", "des_3CBC_ISOpad");

    insertMap("1 3 36 3 1 2", "idea");

    insertMap("1 3 36 3 1 2 1", "ideaECB");

    insertMap("1 3 36 3 1 2 1 1", "ideaECB_pad");

    insertMap("1 3 36 3 1 2 1 1 1", "ideaECB_ISOpad");

    insertMap("1 3 36 3 1 2 2", "ideaCBC");

    insertMap("1 3 36 3 1 2 2 1", "ideaCBC_pad");

    insertMap("1 3 36 3 1 2 2 1 1", "ideaCBC_ISOpad");

    insertMap("1 3 36 3 1 2 3", "ideaOFB");

    insertMap("1 3 36 3 1 2 4", "ideaCFB");

    insertMap("1 3 36 3 1 4", "rsaEncryption");

    insertMap("1 3 36 3 1 4 512 17", "rsaEncryptionWithlmod512expe17");

    insertMap("1 3 36 3 1 5", "bsi-1");

    insertMap("1 3 36 3 1 5 1", "bsi_1ECB_pad");

    insertMap("1 3 36 3 1 5 2", "bsi_1CBC_pad");

    insertMap("1 3 36 3 1 5 2 1", "bsi_1CBC_PEMpad");

    insertMap("1 3 36 3 2", "hashAlgorithm");

    insertMap("1 3 36 3 2 1", "ripemd160");

    insertMap("1 3 36 3 2 2", "ripemd128");

    insertMap("1 3 36 3 2 3", "ripemd256");

    insertMap("1 3 36 3 2 4", "mdc2singleLength");

    insertMap("1 3 36 3 2 5", "mdc2doubleLength");

    insertMap("1 3 36 3 3", "signatureAlgorithm");

    insertMap("1 3 36 3 3 1", "rsaSignature");

    insertMap("1 3 36 3 3 1 1", "rsaSignatureWithsha1");

    insertMap("1 3 36 3 3 1 1 512 2", "rsaSignatureWithsha1_l512_l2");
    insertMap("1 3 36 3 3 1 1 640 2", "rsaSignatureWithsha1_l640_l2");
    insertMap("1 3 36 3 3 1 1 768 2", "rsaSignatureWithsha1_l768_l2");
    insertMap("1 3 36 3 3 1 1 896 2", "rsaSignatureWithsha1_l896_l2");
    insertMap("1 3 36 3 3 1 1 1024 2", "rsaSignatureWithsha1_l1024_l2");
    insertMap("1 3 36 3 3 1 1 512 3", "rsaSignatureWithsha1_l512_l3");
    insertMap("1 3 36 3 3 1 1 640 3", "rsaSignatureWithsha1_l640_l3");
    insertMap("1 3 36 3 3 1 1 768 3", "rsaSignatureWithsha1_l768_l3");
    insertMap("1 3 36 3 3 1 1 896 3", "rsaSignatureWithsha1_l896_l3");
    insertMap("1 3 36 3 3 1 1 1024 3", "rsaSignatureWithsha1_l1024_l3");
    insertMap("1 3 36 3 3 1 1 512 5", "rsaSignatureWithsha1_l512_l5");
    insertMap("1 3 36 3 3 1 1 640 5", "rsaSignatureWithsha1_l640_l5");
    insertMap("1 3 36 3 3 1 1 768 5", "rsaSignatureWithsha1_l768_l5");
    insertMap("1 3 36 3 3 1 1 896 5", "rsaSignatureWithsha1_l896_l5");
    insertMap("1 3 36 3 3 1 1 1024 5", "rsaSignatureWithsha1_l1024_l5");
    insertMap("1 3 36 3 3 1 1 512 9", "rsaSignatureWithsha1_l512_l9");
    insertMap("1 3 36 3 3 1 1 640 9", "rsaSignatureWithsha1_l640_l9");
    insertMap("1 3 36 3 3 1 1 768 9", "rsaSignatureWithsha1_l768_l9");
    insertMap("1 3 36 3 3 1 1 896 9", "rsaSignatureWithsha1_l896_l9");
    insertMap("1 3 36 3 3 1 1 1024 9", "rsaSignatureWithsha1_l1024_l9");
    insertMap("1 3 36 3 3 1 1 512 11", "rsaSignatureWithsha1_l512_l11");
    insertMap("1 3 36 3 3 1 1 640 11", "rsaSignatureWithsha1_l640_l11");
    insertMap("1 3 36 3 3 1 1 768 11", "rsaSignatureWithsha1_l768_l11");
    insertMap("1 3 36 3 3 1 1 896 11", "rsaSignatureWithsha1_l896_l11");
    insertMap("1 3 36 3 3 1 1 1024 11", "rsaSignatureWithsha1_l1024_l11");

    insertMap("1 3 36 3 3 1 2", "rsaSignatureWithripemd160");

    insertMap("1 3 36 3 3 1 2 512 2", "rsaSignatureWithripemd160_l512_l2");
    insertMap("1 3 36 3 3 1 2 640 2", "rsaSignatureWithripemd160_l640_l2");
    insertMap("1 3 36 3 3 1 2 768 2", "rsaSignatureWithripemd160_l768_l2");
    insertMap("1 3 36 3 3 1 2 896 2", "rsaSignatureWithripemd160_l896_l2");
    insertMap("1 3 36 3 3 1 2 1024 2", "rsaSignatureWithripemd160_l1024_l2");
    insertMap("1 3 36 3 3 1 2 512 3", "rsaSignatureWithripemd160_l512_l3");
    insertMap("1 3 36 3 3 1 2 640 3", "rsaSignatureWithripemd160_l640_l3");
    insertMap("1 3 36 3 3 1 2 768 3", "rsaSignatureWithripemd160_l768_l3");
    insertMap("1 3 36 3 3 1 2 896 3", "rsaSignatureWithripemd160_l896_l3");
    insertMap("1 3 36 3 3 1 2 1024 3", "rsaSignatureWithripemd160_l1024_l3");
    insertMap("1 3 36 3 3 1 2 512 5", "rsaSignatureWithripemd160_l512_l5");
    insertMap("1 3 36 3 3 1 2 640 5", "rsaSignatureWithripemd160_l640_l5");
    insertMap("1 3 36 3 3 1 2 768 5", "rsaSignatureWithripemd160_l768_l5");
    insertMap("1 3 36 3 3 1 2 896 5", "rsaSignatureWithripemd160_l896_l5");
    insertMap("1 3 36 3 3 1 2 1024 5", "rsaSignatureWithripemd160_l1024_l5");
    insertMap("1 3 36 3 3 1 2 512 9", "rsaSignatureWithripemd160_l512_l9");
    insertMap("1 3 36 3 3 1 2 640 9", "rsaSignatureWithripemd160_l640_l9");
    insertMap("1 3 36 3 3 1 2 768 9", "rsaSignatureWithripemd160_l768_l9");
    insertMap("1 3 36 3 3 1 2 896 9", "rsaSignatureWithripemd160_l896_l9");
    insertMap("1 3 36 3 3 1 2 1024 9", "rsaSignatureWithripemd160_l1024_l9");
    insertMap("1 3 36 3 3 1 2 512 11", "rsaSignatureWithripemd160_l512_l11");
    insertMap("1 3 36 3 3 1 2 640 11", "rsaSignatureWithripemd160_l640_l11");
    insertMap("1 3 36 3 3 1 2 768 11", "rsaSignatureWithripemd160_l768_l11");
    insertMap("1 3 36 3 3 1 2 896 11", "rsaSignatureWithripemd160_l896_l11");
    insertMap("1 3 36 3 3 1 2 1024 11",
        "rsaSignatureWithripemd160_l1024_l11");

    insertMap("1 3 36 3 3 1 3", "rsaSignatureWithrimpemd128");

    insertMap("1 3 36 3 3 1 4", "rsaSignatureWithrimpemd256");

    insertMap("1 3 36 3 3 2", "ecsieSign");

    insertMap("1 3 36 3 3 2 1", "ecsieSignWithsha1");

    insertMap("1 3 36 3 3 2 2", "ecsieSignWithripemd160");

    insertMap("1 3 36 3 3 2 3", "ecsieSignWithmd2");

    insertMap("1 3 36 3 3 2 4", "ecsieSignWithmd5");

    insertMap("1 3 36 3 3 2 8 1 1 1", "brainpoolP160r1");

    insertMap("1 3 36 3 3 2 8 1 1 2", "brainpoolP160t1");

    insertMap("1 3 36 3 3 2 8 1 1 3", "brainpoolP192r1");

    insertMap("1 3 36 3 3 2 8 1 1 4", "brainpoolP192t1");

    insertMap("1 3 36 3 3 2 8 1 1 5", "brainpoolP224r1");

    insertMap("1 3 36 3 3 2 8 1 1 6", "brainpoolP224t1");

    insertMap("1 3 36 3 3 2 8 1 1 7", "brainpoolP256r1");

    insertMap("1 3 36 3 3 2 8 1 1 8", "brainpoolP256t1");

    insertMap("1 3 36 3 3 2 8 1 1 9", "brainpoolP320r1");

    insertMap("1 3 36 3 3 2 8 1 1 10", "brainpoolP320t1");

    insertMap("1 3 36 3 3 2 8 1 1 11", "brainpoolP384r1");

    insertMap("1 3 36 3 3 2 8 1 1 12", "brainpoolP384t1");

    insertMap("1 3 36 3 3 2 8 1 1 13", "brainpoolP512r1");

    insertMap("1 3 36 3 3 2 8 1 1 14", "brainpoolP512t1");

    insertMap("1 3 36 3 4", "signatureScheme");

    insertMap("1 3 36 3 4 1", "sigS_ISO9796-1");

    insertMap("1 3 36 3 4 2", "sigS_ISO9796-2");

    insertMap("1 3 36 3 4 2 1", "sigS_ISO9796-2Withred");

    insertMap("1 3 36 3 4 2 2", "sigS_ISO9796-2Withrsa");

    insertMap("1 3 36 3 4 2 3", "sigS_ISO9796-2Withrnd");

    insertMap("1 3 36 4", "attribute");

    insertMap("1 3 36 5", "policy");

    insertMap("1 3 36 6", "api");

    insertMap("1 3 36 6 1", "manufacturer-specific_api");

    insertMap("1 3 36 6 1 1", "utimaco-api");

    insertMap("1 3 36 6 2", "functionality-specific_api");

    insertMap("1 3 36 7", "keymgmnt");

    insertMap("1 3 36 7 1", "keyagree");

    insertMap("1 3 36 7 1 1", "bsiPKE");

    insertMap("1 3 36 7 2", "keytrans");

    insertMap("1 3 36 7 2 1", "encISO9796-2Withrsa");

    insertMap("1 3 36 8 1 1", "Teletrust SigGConform policyIdentifier");

    insertMap("1 3 36 8 2 1", "directoryService");

    insertMap("1 3 36 8 3 1", "dateOfCertGen");

    insertMap("1 3 36 8 3 2", "procuration");

    insertMap("1 3 36 8 3 3", "admission");

    insertMap("1 3 36 8 3 4", "monetaryLimit");

    insertMap("1 3 36 8 3 5", "declarationOfMajority");

    insertMap("1 3 36 8 3 6", "integratedCircuitCardSerialNumber");

    insertMap("1 3 36 8 3 7", "pKReference");

    insertMap("1 3 36 8 3 8", "restriction");

    insertMap("1 3 36 8 3 9", "retrieveIfAllowed");

    insertMap("1 3 36 8 3 10", "requestedCertificate");

    insertMap("1 3 36 8 3 11", "namingAuthorities");

    insertMap("1 3 36 8 3 11 1", "rechtWirtschaftSteuern");

    insertMap("1 3 36 8 3 11 1 1", "rechtsanwaeltin");

    insertMap("1 3 36 8 3 11 1 2", "rechtsanwalt");

    insertMap("1 3 36 8 3 11 1 3", "rechtsBeistand");

    insertMap("1 3 36 8 3 11 1 4", "steuerBeraterin");

    insertMap("1 3 36 8 3 11 1 5", "steuerBerater");

    insertMap("1 3 36 8 3 11 1 6", "steuerBevollmaechtigte");

    insertMap("1 3 36 8 3 11 1 7", "steuerBevollmaechtigter");

    insertMap("1 3 36 8 3 11 1 8", "notarin");

    insertMap("1 3 36 8 3 11 1 9", "notar");

    insertMap("1 3 36 8 3 11 1 10", "notarVertreterin");

    insertMap("1 3 36 8 3 11 1 11", "notarVertreter");

    insertMap("1 3 36 8 3 11 1 12", "notariatsVerwalterin");

    insertMap("1 3 36 8 3 11 1 13", "notariatsVerwalter");

    insertMap("1 3 36 8 3 11 1 14", "wirtschaftsPrueferin");

    insertMap("1 3 36 8 3 11 1 15", "wirtschaftsPruefer");

    insertMap("1 3 36 8 3 11 1 16", "vereidigteBuchprueferin");

    insertMap("1 3 36 8 3 11 1 17", "vereidigterBuchpruefer");

    insertMap("1 3 36 8 3 11 1 18", "patentAnwaeltin");

    insertMap("1 3 36 8 3 11 1 19", "patentAnwalt");

    insertMap("1 3 36 8 3 12", "certInDirSince");

    insertMap("1 3 36 8 3 13", "certHash");

    insertMap("1 3 36 8 3 14", "nameAtBirth");

    insertMap("1 3 36 8 3 15", "additionalInformation");

    insertMap("1 3 36 8 4 1", "personalData");

    insertMap("1 3 36 8 4 8", "restriction");

    insertMap("1 3 36 8 5 1 1 1", "rsaIndicateSHA1");

    insertMap("1 3 36 8 5 1 1 2", "rsaIndicateRIPEMD160");

    insertMap("1 3 36 8 5 1 1 3", "rsaWithSHA1");

    insertMap("1 3 36 8 5 1 1 4", "rsaWithRIPEMD160");

    insertMap("1 3 36 8 5 1 2 1", "dsaExtended");

    insertMap("1 3 36 8 5 1 2 2", "dsaWithRIPEMD160");

    insertMap("1 3 36 8 6 1", "cert");

    insertMap("1 3 36 8 6 2", "certRef");

    insertMap("1 3 36 8 6 3", "attrCert");

    insertMap("1 3 36 8 6 4", "attrRef");

    insertMap("1 3 36 8 6 5", "fileName");

    insertMap("1 3 36 8 6 6", "storageTime");

    insertMap("1 3 36 8 6 7", "fileSize");

    insertMap("1 3 36 8 6 8", "location");

    insertMap("1 3 36 8 6 9", "sigNumber");

    insertMap("1 3 36 8 6 10", "autoGen");

    insertMap("1 3 36 8 7 1 1", "ptAdobeILL");

    insertMap("1 3 36 8 7 1 2", "ptAmiPro");

    insertMap("1 3 36 8 7 1 3", "ptAutoCAD");

    insertMap("1 3 36 8 7 1 4", "ptBinary");

    insertMap("1 3 36 8 7 1 5", "ptBMP");

    insertMap("1 3 36 8 7 1 6", "ptCGM");

    insertMap("1 3 36 8 7 1 7", "ptCorelCRT");

    insertMap("1 3 36 8 7 1 8", "ptCorelDRW");

    insertMap("1 3 36 8 7 1 9", "ptCorelEXC");

    insertMap("1 3 36 8 7 1 10", "ptCorelPHT");

    insertMap("1 3 36 8 7 1 11", "ptDraw");

    insertMap("1 3 36 8 7 1 12", "ptDVI");

    insertMap("1 3 36 8 7 1 13", "ptEPS");

    insertMap("1 3 36 8 7 1 14", "ptExcel");

    insertMap("1 3 36 8 7 1 15", "ptGEM");

    insertMap("1 3 36 8 7 1 16", "ptGIF");

    insertMap("1 3 36 8 7 1 17", "ptHPGL");

    insertMap("1 3 36 8 7 1 18", "ptJPEG");

    insertMap("1 3 36 8 7 1 19", "ptKodak");

    insertMap("1 3 36 8 7 1 20", "ptLaTeX");

    insertMap("1 3 36 8 7 1 21", "ptLotus");

    insertMap("1 3 36 8 7 1 22", "ptLotusPIC");

    insertMap("1 3 36 8 7 1 23", "ptMacPICT");

    insertMap("1 3 36 8 7 1 24", "ptMacWord");

    insertMap("1 3 36 8 7 1 25", "ptMSWfD");

    insertMap("1 3 36 8 7 1 26", "ptMSWord");

    insertMap("1 3 36 8 7 1 27", "ptMSWord2");

    insertMap("1 3 36 8 7 1 28", "ptMSWord6");

    insertMap("1 3 36 8 7 1 29", "ptMSWord8");

    insertMap("1 3 36 8 7 1 30", "ptPDF");

    insertMap("1 3 36 8 7 1 31", "ptPIF");

    insertMap("1 3 36 8 7 1 32", "ptPostscript");

    insertMap("1 3 36 8 7 1 33", "ptRTF");

    insertMap("1 3 36 8 7 1 34", "ptSCITEX");

    insertMap("1 3 36 8 7 1 35", "ptTAR");

    insertMap("1 3 36 8 7 1 36", "ptTarga");

    insertMap("1 3 36 8 7 1 37", "ptTeX");

    insertMap("1 3 36 8 7 1 38", "ptText");

    insertMap("1 3 36 8 7 1 39", "ptTIFF");

    insertMap("1 3 36 8 7 1 40", "ptTIFF-FC");

    insertMap("1 3 36 8 7 1 41", "ptUID");

    insertMap("1 3 36 8 7 1 42", "ptUUEncode");

    insertMap("1 3 36 8 7 1 43", "ptWMF");

    insertMap("1 3 36 8 7 1 44", "ptWordPerfect");

    insertMap("1 3 36 8 7 1 45", "ptWPGrph");

    insertMap("1 3 101 1 4", "thawte-ce");

    insertMap("1 3 101 1 4 1", "strongExtranet");

    insertMap("1 3 132 0 1", "sect163k1");

    insertMap("1 3 132 0 2", "sect163r1");

    insertMap("1 3 132 0 3", "sect239k1");

    insertMap("1 3 132 0 4", "sect113r1");

    insertMap("1 3 132 0 5", "sect113r2");

    insertMap("1 3 132 0 6", "secp112r1");

    insertMap("1 3 132 0 7", "secp112r2");

    insertMap("1 3 132 0 8", "secp160r1");

    insertMap("1 3 132 0 9", "secp160k1");

    insertMap("1 3 132 0 10", "secp256k1");

    insertMap("1 3 132 0 15", "sect163r2");

    insertMap("1 3 132 0 16", "sect283k1");

    insertMap("1 3 132 0 17", "sect283r1");

    insertMap("1 3 132 0 22", "sect131r1");

    insertMap("1 3 132 0 23", "sect131r2");

    insertMap("1 3 132 0 24", "sect193r1");

    insertMap("1 3 132 0 25", "sect193r2");

    insertMap("1 3 132 0 26", "sect233k1");

    insertMap("1 3 132 0 27", "sect233r1");

    insertMap("1 3 132 0 28", "secp128r1");

    insertMap("1 3 132 0 29", "secp128r2");

    insertMap("1 3 132 0 30", "secp160r2");

    insertMap("1 3 132 0 31", "secp192k1");

    insertMap("1 3 132 0 32", "secp224k1");

    insertMap("1 3 132 0 33", "secp224r1");

    insertMap("1 3 132 0 34", "secp384r1");

    insertMap("1 3 132 0 35", "secp521r1");

    insertMap("1 3 132 0 36", "sect409k1");

    insertMap("1 3 132 0 37", "sect409r1");

    insertMap("1 3 132 0 38", "sect571k1");

    insertMap("1 3 132 0 39", "sect571r1");

    insertMap("1 3 133 16 840 9 84", "x984");

    insertMap("1 3 133 16 840 9 84 0", "x984Module");

    insertMap("1 3 133 16 840 9 84 0 1", "x984Biometrics");

    insertMap("1 3 133 16 840 9 84 0 2", "x984CMS");

    insertMap("1 3 133 16 840 9 84 0 3", "x984Identifiers");

    insertMap("1 3 133 16 840 9 84 1", "x984Biometric");

    insertMap("1 3 133 16 840 9 84 1 0", "biometricUnknownType");

    insertMap("1 3 133 16 840 9 84 1 1", "biometricBodyOdor");

    insertMap("1 3 133 16 840 9 84 1 2", "biometricDNA");

    insertMap("1 3 133 16 840 9 84 1 3", "biometricEarShape");

    insertMap("1 3 133 16 840 9 84 1 4", "biometricFacialFeatures");

    insertMap("1 3 133 16 840 9 84 1 5", "biometricFingerImage");

    insertMap("1 3 133 16 840 9 84 1 6", "biometricFingerGeometry");

    insertMap("1 3 133 16 840 9 84 1 7", "biometricHandGeometry");

    insertMap("1 3 133 16 840 9 84 1 8", "biometricIrisFeatures");

    insertMap("1 3 133 16 840 9 84 1 9", "biometricKeystrokeDynamics");

    insertMap("1 3 133 16 840 9 84 1 10", "biometricPalm");

    insertMap("1 3 133 16 840 9 84 1 11", "biometricRetina");

    insertMap("1 3 133 16 840 9 84 1 12", "biometricSignature");

    insertMap("1 3 133 16 840 9 84 1 13", "biometricSpeechPattern");

    insertMap("1 3 133 16 840 9 84 1 14", "biometricThermalImage");

    insertMap("1 3 133 16 840 9 84 1 15", "biometricVeinPattern");

    insertMap("1 3 133 16 840 9 84 1 16", "biometricThermalFaceImage");

    insertMap("1 3 133 16 840 9 84 1 17", "biometricThermalHandImage");

    insertMap("1 3 133 16 840 9 84 1 18", "biometricLipMovement");

    insertMap("1 3 133 16 840 9 84 1 19", "biometricGait");

    insertMap("1 3 133 16 840 9 84 1", "x984ProcessingAlgorithm");

    insertMap("1 3 133 16 840 9 84 3", "x984MatchingMethod");

    insertMap("1 3 133 16 840 9 84 4", "x984FormatOwner");

    insertMap("1 3 133 16 840 9 84 4 0", "x984CbeffOwner");

    insertMap("1 3 133 16 840 9 84 4 1", "x984IbiaOwner");

    insertMap("1 3 133 16 840 9 84 4 1 1", "ibiaOwnerSAFLINK");

    insertMap("1 3 133 16 840 9 84 4 1 2", "ibiaOwnerBioscrypt");

    insertMap("1 3 133 16 840 9 84 4 1 3", "ibiaOwnerVisionics");

    insertMap("1 3 133 16 840 9 84 4 1 4",
        "ibiaOwnerInfineonTechnologiesAG");

    insertMap("1 3 133 16 840 9 84 4 1 5", "ibiaOwnerIridianTechnologies");

    insertMap("1 3 133 16 840 9 84 4 1 6", "ibiaOwnerVeridicom");

    insertMap("1 3 133 16 840 9 84 4 1 7", "ibiaOwnerCyberSIGN");

    insertMap("1 3 133 16 840 9 84 4 1 8", "ibiaOwnereCryp");

    insertMap("1 3 133 16 840 9 84 4 1 9", "ibiaOwnerFingerprintCardsAB");

    insertMap("1 3 133 16 840 9 84 4 1 10", "ibiaOwnerSecuGen");

    insertMap("1 3 133 16 840 9 84 4 1 11", "ibiaOwnerPreciseBiometric");

    insertMap("1 3 133 16 840 9 84 4 1 12", "ibiaOwnerIdentix");

    insertMap("1 3 133 16 840 9 84 4 1 13", "ibiaOwnerDERMALOG");

    insertMap("1 3 133 16 840 9 84 4 1 14", "ibiaOwnerLOGICO");

    insertMap("1 3 133 16 840 9 84 4 1 15", "ibiaOwnerNIST");

    insertMap("1 3 133 16 840 9 84 4 1 16", "ibiaOwnerA3Vision");

    insertMap("1 3 133 16 840 9 84 4 1 17", "ibiaOwnerNEC");

    insertMap("1 3 133 16 840 9 84 4 1 18", "ibiaOwnerSTMicroelectronics");

    insertMap("1 3 133 16 840 9 84 4 1", "x984X9Owner");

    insertMap("2 5 4 0", "objectClass");

    insertMap("2 5 4 1", "aliasedEntryName");

    insertMap("2 5 4 2", "knowledgeInformation");

    insertMap("2 5 4 3", "commonName");

    insertMap("2 5 4 4", "surname");

    insertMap("2 5 4 5", "serialNumber");

    insertMap("2 5 4 6", "countryName");

    insertMap("2 5 4 7", "localityName");

    insertMap("2 5 4 7 1", "collectiveLocalityName");

    insertMap("2 5 4 8", "stateOrProvinceName");

    insertMap("2 5 4 8 1", "collectiveStateOrProvinceName");

    insertMap("2 5 4 9", "streetAddress");

    insertMap("2 5 4 9 1", "collectiveStreetAddress");

    insertMap("2 5 4 10", "organizationName");

    insertMap("2 5 4 10 1", "collectiveOrganizationName");

    insertMap("2 5 4 11", "organizationalUnitName");

    insertMap("2 5 4 11 1", "collectiveOrganizationalUnitName");

    insertMap("2 5 4 12", "title");

    insertMap("2 5 4 13", "description");

    insertMap("2 5 4 14", "searchGuide");

    insertMap("2 5 4 15", "businessCategory");

    insertMap("2 5 4 16", "postalAddress");

    insertMap("2 5 4 16 1", "collectivePostalAddress");

    insertMap("2 5 4 17", "postalCode");

    insertMap("2 5 4 17 1", "collectivePostalCode");

    insertMap("2 5 4 18", "postOfficeBox");

    insertMap("2 5 4 18 1", "collectivePostOfficeBox");

    insertMap("2 5 4 19", "physicalDeliveryOfficeName");

    insertMap("2 5 4 19 1", "collectivePhysicalDeliveryOfficeName");

    insertMap("2 5 4 20", "telephoneNumber");

    insertMap("2 5 4 20 1", "collectiveTelephoneNumber");

    insertMap("2 5 4 21", "telexNumber");

    insertMap("2 5 4 21 1", "collectiveTelexNumber");

    insertMap("2 5 4 22", "teletexTerminalIdentifier");

    insertMap("2 5 4 22 1", "collectiveTeletexTerminalIdentifier");

    insertMap("2 5 4 23", "facsimileTelephoneNumber");

    insertMap("2 5 4 23 1", "collectiveFacsimileTelephoneNumber");

    insertMap("2 5 4 24", "x121Address");

    insertMap("2 5 4 25", "internationalISDNNumber");

    insertMap("2 5 4 25 1", "collectiveInternationalISDNNumber");

    insertMap("2 5 4 26", "registeredAddress");

    insertMap("2 5 4 27", "destinationIndicator");

    insertMap("2 5 4 28", "preferredDeliveryMehtod");

    insertMap("2 5 4 29", "presentationAddress");

    insertMap("2 5 4 30", "supportedApplicationContext");

    insertMap("2 5 4 31", "member");

    insertMap("2 5 4 32", "owner");

    insertMap("2 5 4 33", "roleOccupant");

    insertMap("2 5 4 34", "seeAlso");

    insertMap("2 5 4 35", "userPassword");

    insertMap("2 5 4 36", "userCertificate");

    insertMap("2 5 4 37", "caCertificate");

    insertMap("2 5 4 38", "authorityRevocationList");

    insertMap("2 5 4 39", "certificateRevocationList");

    insertMap("2 5 4 40", "crossCertificatePair");

    insertMap("2 5 4 41", "name");

    insertMap("2 5 4 42", "givenName");

    insertMap("2 5 4 43", "initials");

    insertMap("2 5 4 44", "generationQualifier");

    insertMap("2 5 4 45", "uniqueIdentifier");

    insertMap("2 5 4 46", "dnQualifier");

    insertMap("2 5 4 47", "enhancedSearchGuide");

    insertMap("2 5 4 48", "protocolInformation");

    insertMap("2 5 4 49", "distinguishedName");

    insertMap("2 5 4 50", "uniqueMember");

    insertMap("2 5 4 51", "houseIdentifier");

    insertMap("2 5 4 52", "supportedAlgorithms");

    insertMap("2 5 4 53", "deltaRevocationList");

    insertMap("2 5 4 54", "dmdName");

    insertMap("2 5 4 55", "clearance");

    insertMap("2 5 4 56", "defaultDirQop");

    insertMap("2 5 4 57", "attributeIntegrityInfo");

    insertMap("2 5 4 58", "attributeCertificate");

    insertMap("2 5 4 59", "attributeCertificateRevocationList");

    insertMap("2 5 4 60", "confKeyInfo");

    insertMap("2 5 4 61", "aACertificate");

    insertMap("2 5 4 62", "attributeDescriptorCertificate");

    insertMap("2 5 4 63", "attributeAuthorityRevocationList");

    insertMap("2 5 4 64", "familyInformation");

    insertMap("2 5 4 65", "pseudonym");

    insertMap("2 5 4 66", "communicationsService");

    insertMap("2 5 4 67", "communicationsNetwork");

    insertMap("2 5 4 68", "certificationPracticeStmt");

    insertMap("2 5 4 69", "certificatePolicy");

    insertMap("2 5 4 70", "pkiPath");

    insertMap("2 5 4 71", "privPolicy");

    insertMap("2 5 4 72", "role");

    insertMap("2 5 4 73", "delegationPath");

    insertMap("2 5 4 74", "protPrivPolicy");

    insertMap("2 5 4 75", "xMLPrivilegeInfo");

    insertMap("2 5 4 76", "xmlPrivPolicy");

    insertMap("2 5 4 82", "permission");

    insertMap("2 5 6 0", "top");

    insertMap("2 5 6 1", "alias");

    insertMap("2 5 6 2", "country");

    insertMap("2 5 6 3", "locality");

    insertMap("2 5 6 4", "organization");

    insertMap("2 5 6 5", "organizationalUnit");

    insertMap("2 5 6 6", "person");

    insertMap("2 5 6 7", "organizationalPerson");

    insertMap("2 5 6 8", "organizationalRole");

    insertMap("2 5 6 9", "groupOfNames");

    insertMap("2 5 6 10", "residentialPerson");

    insertMap("2 5 6 11", "applicationProcess");

    insertMap("2 5 6 12", "applicationEntity");

    insertMap("2 5 6 13", "dSA");

    insertMap("2 5 6 14", "device");

    insertMap("2 5 6 15", "strongAuthenticationUser");

    insertMap("2 5 6 16", "certificateAuthority");

    insertMap("2 5 6 17", "groupOfUniqueNames");

    insertMap("2 5 6 21", "pkiUser");

    insertMap("2 5 6 22", "pkiCA");

    insertMap("2 5 8 1 1", "rsa");

    insertMap("2 5 29 1", "authorityKeyIdentifier");

    insertMap("2 5 29 2", "keyAttributes");

    insertMap("2 5 29 3", "certificatePolicies");

    insertMap("2 5 29 4", "keyUsageRestriction");

    insertMap("2 5 29 5", "policyMapping");

    insertMap("2 5 29 6", "subtreesConstraint");

    insertMap("2 5 29 7", "subjectAltName");

    insertMap("2 5 29 8", "issuerAltName");

    insertMap("2 5 29 9", "subjectDirectoryAttributes");

    insertMap("2 5 29 10", "basicConstraints");

    insertMap("2 5 29 11", "nameConstraints");

    insertMap("2 5 29 12", "policyConstraints");

    insertMap("2 5 29 13", "basicConstraints");

    insertMap("2 5 29 14", "subjectKeyIdentifier");

    insertMap("2 5 29 15", "keyUsage");

    insertMap("2 5 29 16", "privateKeyUsagePeriod");

    insertMap("2 5 29 17", "subjectAltName");

    insertMap("2 5 29 18", "issuerAltName");

    insertMap("2 5 29 19", "basicConstraints");

    insertMap("2 5 29 20", "cRLNumber");

    insertMap("2 5 29 21", "cRLReason");

    insertMap("2 5 29 22", "expirationDate");

    insertMap("2 5 29 23", "instructionCode");

    insertMap("2 5 29 24", "invalidityDate");

    insertMap("2 5 29 25", "cRLDistributionPoints");

    insertMap("2 5 29 26", "issuingDistributionPoint");

    insertMap("2 5 29 27", "deltaCRLIndicator");

    insertMap("2 5 29 28", "issuingDistributionPoint");

    insertMap("2 5 29 29", "certificateIssuer");

    insertMap("2 5 29 30", "nameConstraints");

    insertMap("2 5 29 31", "cRLDistributionPoints");

    insertMap("2 5 29 32", "certificatePolicies");

    insertMap("2 5 29 32 0", "anyPolicy");

    insertMap("2 5 29 33", "policyMappings");

    insertMap("2 5 29 34", "policyConstraints");

    insertMap("2 5 29 35", "authorityKeyIdentifier");

    insertMap("2 5 29 36", "policyConstraints");

    insertMap("2 5 29 37", "extKeyUsage");

    insertMap("2 5 29 37 0", "anyExtendedKeyUsage");

    insertMap("2 5 29 38", "authorityAttributeIdentifier");

    insertMap("2 5 29 39", "roleSpecCertIdentifier");

    insertMap("2 5 29 40", "cRLStreamIdentifier");

    insertMap("2 5 29 41", "basicAttConstraints");

    insertMap("2 5 29 42", "delegatedNameConstraints");

    insertMap("2 5 29 43", "timeSpecification");

    insertMap("2 5 29 44", "cRLScope");

    insertMap("2 5 29 45", "statusReferrals");

    insertMap("2 5 29 46", "freshestCRL");

    insertMap("2 5 29 47", "orderedList");

    insertMap("2 5 29 48", "attributeDescriptor");

    insertMap("2 5 29 49", "userNotice");

    insertMap("2 5 29 50", "sOAIdentifier");

    insertMap("2 5 29 51", "baseUpdateTime");

    insertMap("2 5 29 52", "acceptableCertPolicies");

    insertMap("2 5 29 53", "deltaInfo");

    insertMap("2 5 29 54", "inhibitAnyPolicy");

    insertMap("2 5 29 55", "targetInformation");

    insertMap("2 5 29 56", "noRevAvail");

    insertMap("2 5 29 57", "acceptablePrivilegePolicies");

    insertMap("2 5 29 58", "toBeRevoked");

    insertMap("2 5 29 59", "revokedGroups");

    insertMap("2 5 29 60", "expiredCertsOnCRL");

    insertMap("2 5 29 61", "indirectIssuer");

    insertMap("2 5 29 62", "noAssertion");

    insertMap("2 5 29 63", "aAissuingDistributionPoint");

    insertMap("2 5 29 64", "issuedOnBehalfOf");

    insertMap("2 5 29 65", "singleUse");

    insertMap("2 5 29 66", "groupAC");

    insertMap("2 5 29 67", "allowedAttAss");

    insertMap("2 5 29 68", "attributeMappings");

    insertMap("2 5 29 69", "holderNameConstraints");

    insertMap("2 16 724 1 2 2 4 1", "personalDataInfo");

    insertMap("2 16 840 1 101 2 1 1 1", "sdnsSignatureAlgorithm");

    insertMap("2 16 840 1 101 2 1 1 2", "fortezzaSignatureAlgorithm");

    insertMap("2 16 840 1 101 2 1 1 3", "sdnsConfidentialityAlgorithm");

    insertMap("2 16 840 1 101 2 1 1 4", "fortezzaConfidentialityAlgorithm");

    insertMap("2 16 840 1 101 2 1 1 5", "sdnsIntegrityAlgorithm");

    insertMap("2 16 840 1 101 2 1 1 6", "fortezzaIntegrityAlgorithm");

    insertMap("2 16 840 1 101 2 1 1 7", "sdnsTokenProtectionAlgorithm");

    insertMap("2 16 840 1 101 2 1 1 8", "fortezzaTokenProtectionAlgorithm");

    insertMap("2 16 840 1 101 2 1 1 9", "sdnsKeyManagementAlgorithm");

    insertMap("2 16 840 1 101 2 1 1 10", "fortezzaKeyManagementAlgorithm");

    insertMap("2 16 840 1 101 2 1 1 11", "sdnsKMandSigAlgorithm");

    insertMap("2 16 840 1 101 2 1 1 12", "fortezzaKMandSigAlgorithm");

    insertMap("2 16 840 1 101 2 1 1 13", "suiteASignatureAlgorithm");

    insertMap("2 16 840 1 101 2 1 1 14", "suiteAConfidentialityAlgorithm");

    insertMap("2 16 840 1 101 2 1 1 15", "suiteAIntegrityAlgorithm");

    insertMap("2 16 840 1 101 2 1 1 16", "suiteATokenProtectionAlgorithm");

    insertMap("2 16 840 1 101 2 1 1 17", "suiteAKeyManagementAlgorithm");

    insertMap("2 16 840 1 101 2 1 1 18", "suiteAKMandSigAlgorithm");

    insertMap("2 16 840 1 101 2 1 1 19", "fortezzaUpdatedSigAlgorithm");

    insertMap("2 16 840 1 101 2 1 1 20", "fortezzaKMandUpdSigAlgorithms");

    insertMap("2 16 840 1 101 2 1 1 21", "fortezzaUpdatedIntegAlgorithm");

    insertMap("2 16 840 1 101 2 1 1 22", "keyExchangeAlgorithm");

    insertMap("2 16 840 1 101 2 1 1 23", "fortezzaWrap80Algorithm");

    insertMap("2 16 840 1 101 2 1 1 24", "kEAKeyEncryptionAlgorithm");

    insertMap("2 16 840 1 101 2 1 2 1", "rfc822MessageFormat");

    insertMap("2 16 840 1 101 2 1 2 2", "emptyContent");

    insertMap("2 16 840 1 101 2 1 2 3", "cspContentType");

    insertMap("2 16 840 1 101 2 1 2 42", "mspRev3ContentType");

    insertMap("2 16 840 1 101 2 1 2 48", "mspContentType");

    insertMap("2 16 840 1 101 2 1 2 49", "mspRekeyAgentProtocol");

    insertMap("2 16 840 1 101 2 1 2 50", "mspMMP");

    insertMap("2 16 840 1 101 2 1 2 66", "mspRev3-1ContentType");

    insertMap("2 16 840 1 101 2 1 2 72", "forwardedMSPMessageBodyPart");

    insertMap("2 16 840 1 101 2 1 2 73", "mspForwardedMessageParameters");

    insertMap("2 16 840 1 101 2 1 2 74", "forwardedCSPMsgBodyPart");

    insertMap("2 16 840 1 101 2 1 2 75", "cspForwardedMessageParameters");

    insertMap("2 16 840 1 101 2 1 2 76", "mspMMP2");

    insertMap("2 16 840 1 101 2 1 3 1", "sdnsSecurityPolicy");

    insertMap("2 16 840 1 101 2 1 3 2", "sdnsPRBAC");

    insertMap("2 16 840 1 101 2 1 3 3", "mosaicPRBAC");

    insertMap("2 16 840 1 101 2 1 3 10", "siSecurityPolicy");

    insertMap("2 16 840 1 101 2 1 3 10 0", "siNASP");

    insertMap("2 16 840 1 101 2 1 3 10 1", "siELCO");

    insertMap("2 16 840 1 101 2 1 3 10 2", "siTK");

    insertMap("2 16 840 1 101 2 1 3 10 3", "siDSAP");

    insertMap("2 16 840 1 101 2 1 3 10 4", "siSSSS");

    insertMap("2 16 840 1 101 2 1 3 10 5", "siDNASP");

    insertMap("2 16 840 1 101 2 1 3 10 6", "siBYEMAN");

    insertMap("2 16 840 1 101 2 1 3 10 7", "siREL-US");

    insertMap("2 16 840 1 101 2 1 3 10 8", "siREL-AUS");

    insertMap("2 16 840 1 101 2 1 3 10 9", "siREL-CAN");

    insertMap("2 16 840 1 101 2 1 3 10 10", "siREL_UK");

    insertMap("2 16 840 1 101 2 1 3 10 11", "siREL-NZ");

    insertMap("2 16 840 1 101 2 1 3 10 12", "siGeneric");

    insertMap("2 16 840 1 101 2 1 3 11", "genser");

    insertMap("2 16 840 1 101 2 1 3 11 0", "genserNations");

    insertMap("2 16 840 1 101 2 1 3 11 1", "genserComsec");

    insertMap("2 16 840 1 101 2 1 3 11 2", "genserAcquisition");

    insertMap("2 16 840 1 101 2 1 3 11 3", "genserSecurityCategories");

    insertMap("2 16 840 1 101 2 1 3 11 3 0", "genserTagSetName");

    insertMap("2 16 840 1 101 2 1 3 12", "defaultSecurityPolicy");

    insertMap("2 16 840 1 101 2 1 3 13", "capcoMarkings");

    insertMap("2 16 840 1 101 2 1 3 13 0", "capcoSecurityCategories");

    insertMap("2 16 840 1 101 2 1 3 13 0 1", "capcoTagSetName1");

    insertMap("2 16 840 1 101 2 1 3 13 0 2", "capcoTagSetName2");

    insertMap("2 16 840 1 101 2 1 3 13 0 3", "capcoTagSetName3");

    insertMap("2 16 840 1 101 2 1 3 13 0 4", "capcoTagSetName4");

    insertMap("2 16 840 1 101 2 1 5 1", "sdnsKeyManagementCertificate");

    insertMap("2 16 840 1 101 2 1 5 2", "sdnsUserSignatureCertificate");

    insertMap("2 16 840 1 101 2 1 5 3", "sdnsKMandSigCertificate");

    insertMap("2 16 840 1 101 2 1 5 4", "fortezzaKeyManagementCertificate");

    insertMap("2 16 840 1 101 2 1 5 5", "fortezzaKMandSigCertificate");

    insertMap("2 16 840 1 101 2 1 5 6", "fortezzaUserSignatureCertificate");

    insertMap("2 16 840 1 101 2 1 5 7", "fortezzaCASignatureCertificate");

    insertMap("2 16 840 1 101 2 1 5 8", "sdnsCASignatureCertificate");

    insertMap("2 16 840 1 101 2 1 5 10", "auxiliaryVector");

    insertMap("2 16 840 1 101 2 1 5 11", "mlReceiptPolicy");

    insertMap("2 16 840 1 101 2 1 5 12", "mlMembership");

    insertMap("2 16 840 1 101 2 1 5 13", "mlAdministrators");

    insertMap("2 16 840 1 101 2 1 5 14", "alid");

    insertMap("2 16 840 1 101 2 1 5 20", "janUKMs");

    insertMap("2 16 840 1 101 2 1 5 21", "febUKMs");

    insertMap("2 16 840 1 101 2 1 5 22", "marUKMs");

    insertMap("2 16 840 1 101 2 1 5 23", "aprUKMs");

    insertMap("2 16 840 1 101 2 1 5 24", "mayUKMs");

    insertMap("2 16 840 1 101 2 1 5 25", "junUKMs");

    insertMap("2 16 840 1 101 2 1 5 26", "julUKMs");

    insertMap("2 16 840 1 101 2 1 5 27", "augUKMs");

    insertMap("2 16 840 1 101 2 1 5 28", "sepUKMs");

    insertMap("2 16 840 1 101 2 1 5 29", "octUKMs");

    insertMap("2 16 840 1 101 2 1 5 30", "novUKMs");

    insertMap("2 16 840 1 101 2 1 5 31", "decUKMs");

    insertMap("2 16 840 1 101 2 1 5 40", "metaSDNSckl");

    insertMap("2 16 840 1 101 2 1 5 41", "sdnsCKL");

    insertMap("2 16 840 1 101 2 1 5 42", "metaSDNSsignatureCKL");

    insertMap("2 16 840 1 101 2 1 5 43", "sdnsSignatureCKL");

    insertMap("2 16 840 1 101 2 1 5 44", "sdnsCertificateRevocationList");

    insertMap("2 16 840 1 101 2 1 5 45",
        "fortezzaCertificateRevocationList");

    insertMap("2 16 840 1 101 2 1 5 46", "fortezzaCKL");

    insertMap("2 16 840 1 101 2 1 5 47", "alExemptedAddressProcessor");

    insertMap("2 16 840 1 101 2 1 5 48", "guard");

    insertMap("2 16 840 1 101 2 1 5 49", "algorithmsSupported");

    insertMap("2 16 840 1 101 2 1 5 50", "suiteAKeyManagementCertificate");

    insertMap("2 16 840 1 101 2 1 5 51", "suiteAKMandSigCertificate");

    insertMap("2 16 840 1 101 2 1 5 52", "suiteAUserSignatureCertificate");

    insertMap("2 16 840 1 101 2 1 5 53", "prbacInfo");

    insertMap("2 16 840 1 101 2 1 5 54", "prbacCAConstraints");

    insertMap("2 16 840 1 101 2 1 5 55", "sigOrKMPrivileges");

    insertMap("2 16 840 1 101 2 1 5 56", "commPrivileges");

    insertMap("2 16 840 1 101 2 1 5 57", "labeledAttribute");

    insertMap("2 16 840 1 101 2 1 5 58", "policyInformationFile");

    insertMap("2 16 840 1 101 2 1 5 59", "secPolicyInformationFile");

    insertMap("2 16 840 1 101 2 1 5 60", "cAClearanceConstraint");

    insertMap("2 16 840 1 101 2 1 7 1", "cspExtns");

    insertMap("2 16 840 1 101 2 1 7 1 0", "cspCsExtn");

    insertMap("2 16 840 1 101 2 1 8 1", "mISSISecurityCategories");

    insertMap("2 16 840 1 101 2 1 8 2", "standardSecurityLabelPrivileges");

    insertMap("2 16 840 1 101 2 1 10 1", "sigPrivileges");

    insertMap("2 16 840 1 101 2 1 10 2", "kmPrivileges");

    insertMap("2 16 840 1 101 2 1 10 3", "namedTagSetPrivilege");

    insertMap("2 16 840 1 101 2 1 11 1", "ukDemo");

    insertMap("2 16 840 1 101 2 1 11 2", "usDODClass2");

    insertMap("2 16 840 1 101 2 1 11 3", "usMediumPilot");

    insertMap("2 16 840 1 101 2 1 11 4", "usDODClass4");

    insertMap("2 16 840 1 101 2 1 11 5", "usDODClass3");

    insertMap("2 16 840 1 101 2 1 11 6", "usDODClass5");

    insertMap("2 16 840 1 101 2 1 12 0", "testSecurityPolicy");

    insertMap("2 16 840 1 101 2 1 12 0 1", "tsp1");

    insertMap("2 16 840 1 101 2 1 12 0 1 0", "tsp1SecurityCategories");

    insertMap("2 16 840 1 101 2 1 12 0 1 0 0", "tsp1TagSetZero");

    insertMap("2 16 840 1 101 2 1 12 0 1 0 1", "tsp1TagSetOne");

    insertMap("2 16 840 1 101 2 1 12 0 1 0 2", "tsp1TagSetTwo");

    insertMap("2 16 840 1 101 2 1 12 0 2", "tsp2");

    insertMap("2 16 840 1 101 2 1 12 0 2 0", "tsp2SecurityCategories");

    insertMap("2 16 840 1 101 2 1 12 0 2 0 0", "tsp2TagSetZero");

    insertMap("2 16 840 1 101 2 1 12 0 2 0 1", "tsp2TagSetOne");

    insertMap("2 16 840 1 101 2 1 12 0 2 0 2", "tsp2TagSetTwo");

    insertMap("2 16 840 1 101 2 1 12 0 3", "kafka");

    insertMap("2 16 840 1 101 2 1 12 0 3 0", "kafkaSecurityCategories");

    insertMap("2 16 840 1 101 2 1 12 0 3 0 1", "kafkaTagSetName1");

    insertMap("2 16 840 1 101 2 1 12 0 3 0 2", "kafkaTagSetName2");

    insertMap("2 16 840 1 101 2 1 12 0 3 0 3", "kafkaTagSetName3");

    insertMap("2 16 840 1 101 2 1 12 1 1", "tcp1");

    insertMap("2 16 840 1 101 3 1", "slabel");

    insertMap("2 16 840 1 101 3 2", "pki");

    insertMap("2 16 840 1 101 3 2 1", "NIST policyIdentifier");

    insertMap("2 16 840 1 101 3 2 1 3 1", "fbcaRudimentaryPolicy");

    insertMap("2 16 840 1 101 3 2 1 3 2", "fbcaBasicPolicy");

    insertMap("2 16 840 1 101 3 2 1 3 3", "fbcaMediumPolicy");

    insertMap("2 16 840 1 101 3 2 1 3 4", "fbcaHighPolicy");

    insertMap("2 16 840 1 101 3 2 1 48 1", "nistTestPolicy1");

    insertMap("2 16 840 1 101 3 2 1 48 2", "nistTestPolicy2");

    insertMap("2 16 840 1 101 3 2 1 48 3", "nistTestPolicy3");

    insertMap("2 16 840 1 101 3 2 1 48 4", "nistTestPolicy4");

    insertMap("2 16 840 1 101 3 2 1 48 5", "nistTestPolicy5");

    insertMap("2 16 840 1 101 3 2 1 48 6", "nistTestPolicy6");

    insertMap("2 16 840 1 101 3 2 2", "gak");

    insertMap("2 16 840 1 101 3 2 2 1", "kRAKey");

    insertMap("2 16 840 1 101 3 2 3", "extensions");

    insertMap("2 16 840 1 101 3 2 3 1", "kRTechnique");

    insertMap("2 16 840 1 101 3 2 3 2", "kRecoveryCapable");

    insertMap("2 16 840 1 101 3 2 3 3", "kR");

    insertMap("2 16 840 1 101 3 2 4", "keyRecoverySchemes");

    insertMap("2 16 840 1 101 3 2 5", "krapola");

    insertMap("2 16 840 1 101 3 3", "arpa");

    insertMap("2 16 840 1 101 3 4", "nistAlgorithm");

    insertMap("2 16 840 1 101 3 4 1", "aes");

    insertMap("2 16 840 1 101 3 4 1 1", "aes128-ECB");

    insertMap("2 16 840 1 101 3 4 1 2", "aes128-CBC");

    insertMap("2 16 840 1 101 3 4 1 3", "aes128-OFB");

    insertMap("2 16 840 1 101 3 4 1 4", "aes128-CFB");

    insertMap("2 16 840 1 101 3 4 1 5", "aes128-wrap");

    insertMap("2 16 840 1 101 3 4 1 6", "aes128-GCM");

    insertMap("2 16 840 1 101 3 4 1 7", "aes128-CCM");

    insertMap("2 16 840 1 101 3 4 1 8", "aes128-wrap-pad");

    insertMap("2 16 840 1 101 3 4 1 21", "aes192-ECB");

    insertMap("2 16 840 1 101 3 4 1 22", "aes192-CBC");

    insertMap("2 16 840 1 101 3 4 1 23", "aes192-OFB");

    insertMap("2 16 840 1 101 3 4 1 24", "aes192-CFB");

    insertMap("2 16 840 1 101 3 4 1 25", "aes192-wrap");

    insertMap("2 16 840 1 101 3 4 1 26", "aes192-GCM");

    insertMap("2 16 840 1 101 3 4 1 27", "aes192-CCM");

    insertMap("2 16 840 1 101 3 4 1 28", "aes192-wrap-pad");

    insertMap("2 16 840 1 101 3 4 1 41", "aes256-ECB");

    insertMap("2 16 840 1 101 3 4 1 42", "aes256-CBC");

    insertMap("2 16 840 1 101 3 4 1 43", "aes256-OFB");

    insertMap("2 16 840 1 101 3 4 1 44", "aes256-CFB");

    insertMap("2 16 840 1 101 3 4 1 45", "aes256-wrap");

    insertMap("2 16 840 1 101 3 4 1 46", "aes256-GCM");

    insertMap("2 16 840 1 101 3 4 1 47", "aes256-CCM");

    insertMap("2 16 840 1 101 3 4 1 48", "aes256-wrap-pad");

    insertMap("2 16 840 1 101 3 4 2", "hashAlgos");

    insertMap("2 16 840 1 101 3 4 2 1", "sha-256");

    insertMap("2 16 840 1 101 3 4 2 2", "sha-384");

    insertMap("2 16 840 1 101 3 4 2 3", "sha-512");

    insertMap("2 16 840 1 101 3 4 2 4", "sha-224");

    insertMap("2 16 840 1 101 3 4 3 1", "dsaWithSha224");

    insertMap("2 16 840 1 101 3 4 3 2", "dsaWithSha256");

    insertMap("2 16 840 1 113719 1 2 8", "novellAlgorithm");

    insertMap("2 16 840 1 113719 1 2 8 22", "desCbcIV8");

    insertMap("2 16 840 1 113719 1 2 8 23", "desCbcPadIV8");

    insertMap("2 16 840 1 113719 1 2 8 24", "desEDE2CbcIV8");

    insertMap("2 16 840 1 113719 1 2 8 25", "desEDE2CbcPadIV8");

    insertMap("2 16 840 1 113719 1 2 8 26", "desEDE3CbcIV8");

    insertMap("2 16 840 1 113719 1 2 8 27", "desEDE3CbcPadIV8");

    insertMap("2 16 840 1 113719 1 2 8 28", "rc5CbcPad");

    insertMap("2 16 840 1 113719 1 2 8 29", "md2WithRSAEncryptionBSafe1");

    insertMap("2 16 840 1 113719 1 2 8 30", "md5WithRSAEncryptionBSafe1");

    insertMap("2 16 840 1 113719 1 2 8 31", "sha1WithRSAEncryptionBSafe1");

    insertMap("2 16 840 1 113719 1 2 8 32", "lmDigest");

    insertMap("2 16 840 1 113719 1 2 8 40", "md2");

    insertMap("2 16 840 1 113719 1 2 8 50", "md5");

    insertMap("2 16 840 1 113719 1 2 8 51", "ikeHmacWithSHA1-RSA");

    insertMap("2 16 840 1 113719 1 2 8 52", "ikeHmacWithMD5-RSA");

    insertMap("2 16 840 1 113719 1 2 8 69", "rc2CbcPad");

    insertMap("2 16 840 1 113719 1 2 8 82", "sha-1");

    insertMap("2 16 840 1 113719 1 2 8 92", "rc2BSafe1Cbc");

    insertMap("2 16 840 1 113719 1 2 8 95", "md4");

    insertMap("2 16 840 1 113719 1 2 8 130", "md4Packet");

    insertMap("2 16 840 1 113719 1 2 8 131", "rsaEncryptionBsafe1");

    insertMap("2 16 840 1 113719 1 2 8 132", "nwPassword");

    insertMap("2 16 840 1 113719 1 2 8 133", "novellObfuscate-1");

    insertMap("2 16 840 1 113719 1 9", "pki");

    insertMap("2 16 840 1 113719 1 9 4", "pkiAttributeType");

    insertMap("2 16 840 1 113719 1 9 4 1", "securityAttributes");

    insertMap("2 16 840 1 113719 1 9 4 2", "relianceLimit");

    insertMap("2 16 840 1 113730 1", "cert-extension");

    insertMap("2 16 840 1 113730 1 1", "netscape-cert-type");

    insertMap("2 16 840 1 113730 1 2", "netscape-base-url");

    insertMap("2 16 840 1 113730 1 3", "netscape-revocation-url");

    insertMap("2 16 840 1 113730 1 4", "netscape-ca-revocation-url");

    insertMap("2 16 840 1 113730 1 7", "netscape-cert-renewal-url");

    insertMap("2 16 840 1 113730 1 8", "netscape-ca-policy-url");

    insertMap("2 16 840 1 113730 1 9", "HomePage-url");

    insertMap("2 16 840 1 113730 1 10", "EntityLogo");

    insertMap("2 16 840 1 113730 1 11", "UserPicture");

    insertMap("2 16 840 1 113730 1 12", "netscape-ssl-server-name");

    insertMap("2 16 840 1 113730 1 13", "netscape-comment");

    insertMap("2 16 840 1 113730 2", "data-type");

    insertMap("2 16 840 1 113730 2 1", "dataGIF");

    insertMap("2 16 840 1 113730 2 2", "dataJPEG");

    insertMap("2 16 840 1 113730 2 3", "dataURL");

    insertMap("2 16 840 1 113730 2 4", "dataHTML");

    insertMap("2 16 840 1 113730 2 5", "certSequence");

    insertMap("2 16 840 1 113730 2 6", "certURL");

    insertMap("2 16 840 1 113730 3", "directory");

    insertMap("2 16 840 1 113730 3 1", "ldapDefinitions");

    insertMap("2 16 840 1 113730 3 1 1", "carLicense");

    insertMap("2 16 840 1 113730 3 1 2", "departmentNumber");

    insertMap("2 16 840 1 113730 3 1 3", "employeeNumber");

    insertMap("2 16 840 1 113730 3 1 4", "employeeType");

    insertMap("2 16 840 1 113730 3 2 2", "inetOrgPerson");

    insertMap("2 16 840 1 113730 4 1", "serverGatedCrypto");

    insertMap("2 16 840 1 113733 1 6 3", "verisignCZAG");

    insertMap("2 16 840 1 113733 1 6 6", "verisignInBox");

    insertMap("2 16 840 1 113733 1 6 11", "verisignOnsiteJurisdictionHash");

    insertMap("2 16 840 1 113733 1 6 13", "Unknown Verisign VPN extension");

    insertMap("2 16 840 1 113733 1 6 15", "verisignServerID");

    insertMap("2 16 840 1 113733 1 7 1 1",
        "verisignCertPolicies95Qualifier1");

    insertMap("2 16 840 1 113733 1 7 1 1 1", "verisignCPSv1notice");

    insertMap("2 16 840 1 113733 1 7 1 1 2", "verisignCPSv1nsi");

    insertMap("2 16 840 1 113733 1 8 1", "verisignISSStrongCrypto");

    insertMap("2 16 840 1 113733 1", "pki");

    insertMap("2 16 840 1 113733 1 9", "pkcs7Attribute");

    insertMap("2 16 840 1 113733 1 9 2", "messageType");

    insertMap("2 16 840 1 113733 1 9 3", "pkiStatus");

    insertMap("2 16 840 1 113733 1 9 4", "failInfo");

    insertMap("2 16 840 1 113733 1 9 5", "senderNonce");

    insertMap("2 16 840 1 113733 1 9 6", "recipientNonce");

    insertMap("2 16 840 1 113733 1 9 7", "transID");

    insertMap("2 16 840 1 113733 1 9 8", "extensionReq");

    insertMap("2 16 840 1 113741 2", "intelCDSA");

    insertMap("2 16 840 1 114412 1", "digiCertNonEVCerts");

    insertMap("2 16 840 1 114412 1 1", "digiCertOVCert");

    insertMap("2 16 840 1 114412 1 2", "digiCertDVCert");

    insertMap("2 16 840 1 114412 1 11", "digiCertFederatedDeviceCert");

    insertMap("2 16 840 1 114412 1 3 0 1", "digiCertGlobalCAPolicy");

    insertMap("2 16 840 1 114412 1 3 0 2",
        "digiCertHighAssuranceEVCAPolicy");

    insertMap("2 16 840 1 114412 1 3 0 3", "digiCertGlobalRootCAPolicy");

    insertMap("2 16 840 1 114412 1 3 0 4", "digiCertAssuredIDRootCAPolicy");

    insertMap("2 16 840 1 114412 2 2", "digiCertEVCert");

    insertMap("2 16 840 1 114412 2 3", "digiCertObjectSigningCert");

    insertMap("2 16 840 1 114412 2 3 1", "digiCertCodeSigningCert");

    insertMap("2 16 840 1 114412 2 3 2", "digiCertEVCodeSigningCert");

    insertMap("2 16 840 1 114412 2 3 11", "digiCertKernelCodeSigningCert");

    insertMap("2 16 840 1 114412 2 3 21", "digiCertDocumentSigningCert");

    insertMap("2 16 840 1 114412 2 4", "digiCertClientCert");

    insertMap("2 16 840 1 114412 2 4 1 1",
        "digiCertLevel1PersonalClientCert");

    insertMap("2 16 840 1 114412 2 4 1 2",
        "digiCertLevel1EnterpriseClientCert");

    insertMap("2 16 840 1 114412 2 4 2", "digiCertLevel2ClientCert");

    insertMap("2 16 840 1 114412 2 4 3 1", "digiCertLevel3USClientCert");

    insertMap("2 16 840 1 114412 2 4 3 2", "digiCertLevel3CBPClientCert");

    insertMap("2 16 840 1 114412 2 4 4 1", "digiCertLevel4USClientCert");

    insertMap("2 16 840 1 114412 2 4 4 2", "digiCertLevel4CBPClientCert");

    insertMap("2 16 840 1 114412 2 4 5 1", "digiCertPIVHardwareCert");

    insertMap("2 16 840 1 114412 2 4 5 2", "digiCertPIVCardAuthCert");

    insertMap("2 16 840 1 114412 2 4 5 3", "digiCertPIVContentSigningCert");

    insertMap("2 16 840 1 114412 4 31", "digiCertGridClassicCert");

    insertMap("2 16 840 1 114412 4 31 5", "digiCertGridIntegratedCert");

    insertMap("2 16 840 1 114412 31 4 31 1", "digiCertGridHostCert");

    insertMap("2 23 42 0", "contentType");

    insertMap("2 23 42 0 0", "panData");

    insertMap("2 23 42 0 1", "panToken");

    insertMap("2 23 42 0 2", "panOnly");

    insertMap("2 23 42 1", "msgExt");

    insertMap("2 23 42 2", "field");

    insertMap("2 23 42 2 0", "fullName");

    insertMap("2 23 42 2 1", "givenName");

    insertMap("2 23 42 2 2", "familyName");

    insertMap("2 23 42 2 3", "birthFamilyName");

    insertMap("2 23 42 2 4", "placeName");

    insertMap("2 23 42 2 5", "identificationNumber");

    insertMap("2 23 42 2 6", "month");

    insertMap("2 23 42 2 7", "date");

    insertMap("2 23 42 2 8", "address");

    insertMap("2 23 42 2 9", "telephone");

    insertMap("2 23 42 2 10", "amount");

    insertMap("2 23 42 2 11", "accountNumber");

    insertMap("2 23 42 2 12", "passPhrase");

    insertMap("2 23 42 3", "attribute");

    insertMap("2 23 42 3 0", "cert");

    insertMap("2 23 42 3 0 0", "rootKeyThumb");

    insertMap("2 23 42 3 0 1", "additionalPolicy");

    insertMap("2 23 42 4", "algorithm");

    insertMap("2 23 42 5", "policy");

    insertMap("2 23 42 5 0", "root");

    insertMap("2 23 42 6", "module");

    insertMap("2 23 42 7", "certExt");

    insertMap("2 23 42 7 0", "hashedRootKey");

    insertMap("2 23 42 7 1", "certificateType");

    insertMap("2 23 42 7 2", "merchantData");

    insertMap("2 23 42 7 3", "cardCertRequired");

    insertMap("2 23 42 7 4", "tunneling");

    insertMap("2 23 42 7 5", "setExtensions");

    insertMap("2 23 42 7 6", "setQualifier");

    insertMap("2 23 42 8", "brand");

    insertMap("2 23 42 8 1", "IATA-ATA");

    insertMap("2 23 42 8 4", "VISA");

    insertMap("2 23 42 8 5", "MasterCard");

    insertMap("2 23 42 8 30", "Diners");

    insertMap("2 23 42 8 34", "AmericanExpress");

    insertMap("2 23 42 8 6011", "Novus");

    insertMap("2 23 42 9", "vendor");

    insertMap("2 23 42 9 0", "GlobeSet");

    insertMap("2 23 42 9 1", "IBM");

    insertMap("2 23 42 9 2", "CyberCash");

    insertMap("2 23 42 9 3", "Terisa");

    insertMap("2 23 42 9 4", "RSADSI");

    insertMap("2 23 42 9 5", "VeriFone");

    insertMap("2 23 42 9 6", "TrinTech");

    insertMap("2 23 42 9 7", "BankGate");

    insertMap("2 23 42 9 8", "GTE");

    insertMap("2 23 42 9 9", "CompuSource");

    insertMap("2 23 42 9 10", "Griffin");

    insertMap("2 23 42 9 11", "Certicom");

    insertMap("2 23 42 9 12", "OSS");

    insertMap("2 23 42 9 13", "TenthMountain");

    insertMap("2 23 42 9 14", "Antares");

    insertMap("2 23 42 9 15", "ECC");

    insertMap("2 23 42 9 16", "Maithean");

    insertMap("2 23 42 9 17", "Netscape");

    insertMap("2 23 42 9 18", "Verisign");

    insertMap("2 23 42 9 19", "BlueMoney");

    insertMap("2 23 42 9 20", "Lacerte");

    insertMap("2 23 42 9 21", "Fujitsu");

    insertMap("2 23 42 9 22", "eLab");

    insertMap("2 23 42 9 23", "Entrust");

    insertMap("2 23 42 9 24", "VIAnet");

    insertMap("2 23 42 9 25", "III");

    insertMap("2 23 42 9 26", "OpenMarket");

    insertMap("2 23 42 9 27", "Lexem");

    insertMap("2 23 42 9 28", "Intertrader");

    insertMap("2 23 42 9 29", "Persimmon");

    insertMap("2 23 42 9 30", "NABLE");

    insertMap("2 23 42 9 31", "espace-net");

    insertMap("2 23 42 9 32", "Hitachi");

    insertMap("2 23 42 9 33", "Microsoft");

    insertMap("2 23 42 9 34", "NEC");

    insertMap("2 23 42 9 35", "Mitsubishi");

    insertMap("2 23 42 9 36", "NCR");

    insertMap("2 23 42 9 37", "e-COMM");

    insertMap("2 23 42 9 38", "Gemplus");

    insertMap("2 23 42 10", "national");

    insertMap("2 23 42 10 392", "Japan");

    insertMap("2 23 133", "tCPA");

    insertMap("2 23 133 1", "tcpaSpecVersion");

    insertMap("2 23 133 2", "tcpaAttribute");

    insertMap("2 23 133 2 1", "tcpaTpmManufacturer");

    insertMap("2 23 133 2 2", "tcpaTpmModel");

    insertMap("2 23 133 2 3", "tcpaTpmVersion");

    insertMap("2 23 133 2 4", "tcpaPlatformManufacturer");

    insertMap("2 23 133 2 5", "tcpaPlatformModel");

    insertMap("2 23 133 2 6", "tcpaPlatformVersion");

    insertMap("2 23 133 2 7", "tcpaComponentManufacturer");

    insertMap("2 23 133 2 8", "tcpaComponentModel");

    insertMap("2 23 133 2 9", "tcpaComponentVersion");

    insertMap("2 23 133 2 10", "tcpaSecurityQualities");

    insertMap("2 23 133 2 11", "tcpaTpmProtectionProfile");

    insertMap("2 23 133 2 12", "tcpaTpmSecurityTarget");

    insertMap("2 23 133 2 13", "tcpaFoundationProtectionProfile");

    insertMap("2 23 133 2 14", "tcpaFoundationSecurityTarget");

    insertMap("2 23 133 2 15", "tcpaTpmIdLabel");

    insertMap("2 23 133 3", "tcpaProtocol");

    insertMap("2 23 133 3 1", "tcpaPrttTpmIdProtocol");

    insertMap("2 23 134 1 4 2 1", "postSignumRootQCA  ");

    insertMap("2 23 134 1 2 2 3", "postSignumPublicCA ");

    insertMap("2 23 134 1 2 1 8 210", "postSignumCommercialServerPolicy");

    insertMap("2 23 136 1 1 1", "mRTDSignatureData");

    insertMap("2 54 1775 2", "hashedRootKey");

    insertMap("2 54 1775 3", "certificateType");

    insertMap("2 54 1775 4", "merchantData");

    insertMap("2 54 1775 5", "cardCertRequired");

    insertMap("2 54 1775 6", "tunneling");

    insertMap("2 54 1775 7", "setQualifier");

    insertMap("2 54 1775 99", "setData");

    insertMap("1 2 40 0 17 1 22", "A-Trust EV policy");

    insertMap("1 3 6 1 4 1 34697 2 1", "AffirmTrust EV policy");

    insertMap("1 3 6 1 4 1 34697 2 2", "AffirmTrust EV policy");

    insertMap("1 3 6 1 4 1 34697 2 3", "AffirmTrust EV policy");

    insertMap("1 3 6 1 4 1 34697 2 4", "AffirmTrust EV policy");

    insertMap("2 16 578 1 26 1 3 3", "BuyPass EV policy");

    insertMap("1 3 6 1 4 1 17326 10 14 2 1 2", "Camerfirma EV policy");

    insertMap("1 3 6 1 4 1 17326 10 8 12 1 2", "Camerfirma EV policy");

    insertMap("1 3 6 1 4 1 22234 2 5 2 3 1", "CertPlus EV policy");

    insertMap("1 3 6 1 4 1 6449 1 2 1 5 1", "Comodo EV policy");

    insertMap("1 3 6 1 4 1 6334 1 100 1", "Cybertrust EV policy");

    insertMap("1 3 6 1 4 1 4788 2 202 1", "D-TRUST EV policy");

    insertMap("2 16 840 1 114412 2 1", "DigiCert EV policy");

    insertMap("2 16 528 1 1001 1 1 1 12 6 1 1 1", "DigiNotar EV policy");

    insertMap("2 16 840 1 114028 10 1 2", "Entrust EV policy");

    insertMap("1 3 6 1 4 1 14370 1 6", "GeoTrust EV policy");

    insertMap("1 3 6 1 4 1 4146 1 1", "GlobalSign EV policy");

    insertMap("2 16 840 1 114413 1 7 23 3", "GoDaddy EV policy");

    insertMap("1 3 6 1 4 1 14777 6 1 1", "Izenpe EV policy");

    insertMap("1 3 6 1 4 1 14777 6 1 2", "Izenpe EV policy");

    insertMap("1 3 6 1 4 1 782 1 2 1 8 1", "Network Solutions EV policy");

    insertMap("1 3 6 1 4 1 8024 0 2 100 1 2", "QuoVadis EV policy");

    insertMap("1 2 392 200091 100 721 1",
        "Security Communication (SECOM) EV policy");

    insertMap("2 16 840 1 114414 1 7 23 3", "Starfield EV policy");

    insertMap("1 3 6 1 4 1 23223 1 1 1", "StartCom EV policy");

    insertMap("2 16 756 1 89 1 2 1 1", "SwissSign EV policy");

    insertMap("1 3 6 1 4 1 7879 13 24 1", "T-TeleSec EV policy");

    insertMap("2 16 840 1 113733 1 7 48 1", "Thawte EV policy");

    insertMap("2 16 840 1 114404 1 1 2 4 1", "TrustWave EV policy");

    insertMap("1 3 6 1 4 1 40869 1 1 22 3", "TWCA EV policy");

    insertMap("2 16 840 1 113733 1 7 23 6", "VeriSign EV policy");

    insertMap("2 16 840 1 114171 500 9", "Wells Fargo EV policy");

  }
}
