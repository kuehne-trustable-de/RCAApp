package eu.trustable.rcaapp;

import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.pkcs.PKCSException;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;


public class CryptoUtilTest {

    public static final String TEST_CSR_PEM =
            "-----BEGIN NEW CERTIFICATE REQUEST-----\n" +
            "MIIDVDCCAr0CAQAweTEeMBwGA1UEAxMVd3d3Lmpvc2VwaGNoYXBtYW4uY29tMQ8w \n" +
            "DQYDVQQLEwZEZXNpZ24xFjAUBgNVBAoTDUpvc2VwaENoYXBtYW4xEjAQBgNVBAcT \n" +
            "CU1haWRzdG9uZTENMAsGA1UECBMES2VudDELMAkGA1UEBhMCR0IwgZ8wDQYJKoZI \n" +
            "hvcNAQEBBQADgY0AMIGJAoGBAOEFDpnOKRabQhDa5asDxYPnG0c/neW18e8apjOk \n" +
            "1yuGRk+3GD7YQvuhBVS1x6wkw1D2RnmnZgN1nNUK0cRK7sIvOyCh1+jgD7u46mLk \n" +
            "81j+b4YSEmYZGPLIuclyocPDm0hXayjCUqWt7z6LMIKpLym8gayEZzz9Gn97PsbP \n" +
            "kVFBAgMBAAGgggGZMBoGCisGAQQBgjcNAgMxDBYKNS4xLjI2MDAuMjB7BgorBgEE \n" +
            "AYI3AgEOMW0wazAOBgNVHQ8BAf8EBAMCBPAwRAYJKoZIhvcNAQkPBDcwNTAOBggq \n" +
            "hkiG9w0DAgICAIAwDgYIKoZIhvcNAwQCAgCAMAcGBSsOAwIHMAoGCCqGSIb3DQMH \n" +
            "MBMGA1UdJQQMMAoGCCsGAQUFBwMBMIH9BgorBgEEAYI3DQICMYHuMIHrAgEBHloA \n" +
            "TQBpAGMAcgBvAHMAbwBmAHQAIABSAFMAQQAgAFMAQwBoAGEAbgBuAGUAbAAgAEMA \n" +
            "cgB5AHAAdABvAGcAcgBhAHAAaABpAGMAIABQAHIAbwB2AGkAZABlAHIDgYkAk0kf \n" +
            "HSkr4jsEVya3mgUoyaYMO456ECNZr4Cb+WhPgexfjOO5qwOG1oDOTaKycrkc5pG+ \n" +
            "IPBQnq+4cotT8hWJQwpc+qGb8xUETpxCokhrhN5079vFXq/5dsHkmtOTwkSqSnz9 \n" +
            "yruVoxYeDQ8jI3KG3HTgxwFto8oZnm+E+Y4oshUAAAAAAAAAADANBgkqhkiG9w0B \n" +
            "AQUFAAOBgQAuAxetLzgfjBdWpjpixeVYZXuPZ+6jvZNL/9hOw7Fk5pVVXWdr8csJ \n" +
            "6JUW8QdH9KB6ZlM4yg8Df+vat1/DG6GuD2hiIR7fQ0NtPFBQmbrSm+TTBo95lwP+ \n" +
            "ZSZTusPFTLKaqValdnS9Uw+6Vq7/I4ouDA8QBIuaTFtPOp+8wEGBHQ==\n" +
            "-----END NEW CERTIFICATE REQUEST-----";

    public static final String TEST_CSR2_PEM =
            "-----BEGIN NEW CERTIFICATE REQUEST-----\n" +
            "MIIC/DCCAeQCAQAwHDEaMBgGA1UEAxMRV1MtMjAxOS1Jc3N1aW5nQ0EwggEiMA0G\n" +
            "CSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC6/GWkDeV1KrHz4ZrSGG5e/vqFDndl\n" +
            "9K9Q9cqdnF3+gZJo9oRYVASvMZ5zAJFpvZZ87KT8E6WlRUjZ4T2egap9GSsjXr5R\n" +
            "2Q/N6i5FK7pTnSSMrYBSqVWdti8yQ7+bt+mdJExbP5IVjuXWPTE1PpYzJUrfJUpV\n" +
            "JpBfGmYhzGDMTiXLrZOen0bKbytx4j4wevFWgsBdfeuZs2zchF4VYKt/SAZwTxW0\n" +
            "3BeSlB/sxN6POvHDofD/CPD1vOsz8oVCCyVTKC4RS9S8YEkELSbxI/0quMFLkXXn\n" +
            "rgVl7v+Za+7PZJ1q4ra0B2Doa2+X64LDq4YxyYdnHQzyMmm15fWnTe/dAgMBAAGg\n" +
            "gZowHQYKKwYBBAGCNw0CAzEPFg0xMC4wLjE3NzYzLjIuMHkGCSqGSIb3DQEJDjFs\n" +
            "MGowEAYJKwYBBAGCNxUBBAMCAQAwHQYDVR0OBBYEFPZBinSxew5Q6MlHG3Mz0oT+\n" +
            "sHsYMBkGCSsGAQQBgjcUAgQMHgoAUwB1AGIAQwBBMAsGA1UdDwQEAwIBhjAPBgNV\n" +
            "HRMBAf8EBTADAQH/MA0GCSqGSIb3DQEBCwUAA4IBAQAXgvm54Mzszp4UActJ5u0i\n" +
            "JIawzSpbK/tjIElugatRqPbZCoZXsGW3HWr/LWNRc8GxooG+fxWYnNLvqq4U8TLx\n" +
            "HBiISOTDGsK7C0X1LRG/Mh6uYGSlA7RW/smUyR8FHwPnwxlPDdafw3+QN0ZpkKA4\n" +
            "4rvQ4WrObPzmA/ybdK1RBXNjD3BQZjHpYV2SF9O1vDfs1mmkKezQejvnh2tPgTkJ\n" +
            "sT7E1GdqCPjpWQ1C8Kiz/PRdz/jt1D2ZeeAfJyS8G8lIwqq/w0nPlIGcKidMXKo1\n" +
            "SSD3AwieROIlyDb5ueVaxnydIAjDlO/a1VsJz/UKibH3+fjHa/grnU6MGancxKLO\n" +
            "-----END NEW CERTIFICATE REQUEST-----";

    @Test
    public void testCSRAttributeProcessing() throws OperatorCreationException, PKCSException, NoSuchAlgorithmException, IOException, NoSuchProviderException, CertificateException, InvalidKeySpecException {
        CryptoUtil cu = new CryptoUtil();

        Map<String, String> attMap = cu.explainCertificateRequestAttributes(TEST_CSR_PEM);

        for( String name: attMap.keySet()){
            System.out.println( "attr '" +  name + "' -> " + attMap.get(name));
        }
    }

    @Test
    public void testCSR2AttributeProcessing() throws OperatorCreationException, PKCSException, NoSuchAlgorithmException, IOException, NoSuchProviderException, CertificateException, InvalidKeySpecException {
        CryptoUtil cu = new CryptoUtil();

        Map<String, String> attMap = cu.explainCertificateRequestAttributes(TEST_CSR2_PEM);

        for( String name: attMap.keySet()){
            System.out.println( "attr '" +  name + "' -> '" + attMap.get(name) + "'");
        }
    }
}