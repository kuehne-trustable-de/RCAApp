package eu.trustable.rcaapp;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.IOException;

import java.io.StringWriter;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import de.trustable.ca3s.quorumProcessor.QuorumProcessor;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RootCertificateItem extends CertificateItem {

    /**
     *
     */
    private static final long serialVersionUID = 571716732845455019L;

    private static final String TAG = "RootCertificateItem";

    @JsonProperty
    private String privKeyPEM;

    @JsonProperty
    private String signAlgo;

    @JsonProperty
    private QuorumProcessor qp;

    @JsonProperty
    private int N;

    @JsonProperty
    private int M;

    @JsonProperty
    private List<IssuedCertificateItem> issuedCertList = new ArrayList<IssuedCertificateItem>();

    @JsonProperty
    Date nextCRLUpdate;

    /**
     * allow construction by jackson
     */
    public RootCertificateItem(){}

    RootCertificateItem(X509Certificate cert, int N, int M){
        super(cert);

        this.N = N;
        this.M = M;
    }

    public RootCertificateItem(KeyPair kp, X509Certificate cert, int N, int M, Map<Integer, char[]> passwordMap, String signAlgo) throws IOException, GeneralSecurityException {

        this(cert, N, M);

        this.signAlgo = signAlgo;

        try {
            StringWriter swPem = new StringWriter();
            try (PemWriter writer = new PemWriter(swPem)) {
                writer.writeObject(new PemObject("PRIVATE KEY", kp.getPrivate().getEncoded()));
            }
            String keyAsPEM = swPem.toString();
            this.privKeyPEM = keyAsPEM;

            this.qp = new QuorumProcessor(N, keyAsPEM.getBytes(), passwordMap);

            nextCRLUpdate = new Date();

            Log.d( TAG, "Writing #" +kp.getPrivate().getEncoded().length + " private key bytes as PEM: \n" + this.privKeyPEM);
        } catch (IOException e) {
            Log.e(TAG, "problem encoding private key", e);
        } //end of catch block

    }

    public List<IssuedCertificateItem> getIssuedCertList(){
        return issuedCertList;
    }

    public void addIssuedCertList(X509Certificate cert){

        getIssuedCertList().add(new IssuedCertificateItem(cert));
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    PrivateKey getPrivateKey(Map<Integer, char[]> passwordMap) throws GeneralSecurityException {

        CryptoUtil cu = new CryptoUtil();

        byte[] pemKeyBytes = this.qp.getKey(passwordMap);
        return cu.convertPemToPrivateKey(new String(pemKeyBytes));
    }

    public String getSignAlgo() {
        return signAlgo;
    }

    public Date getNextCRLUpdate() {
        return nextCRLUpdate;
    }

    public void setNextCRLUpdate(Date nextCRLUpdate) {
        this.nextCRLUpdate = nextCRLUpdate;
    }


}
