package eu.trustable.rcaapp;

import android.graphics.Bitmap;
import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.zxing.WriterException;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.security.KeyPair;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
class RootCertificateItem implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 571716732845455019L;

    private static final String TAG = "PersistentModel";

    @JsonProperty
    String certId;

    @JsonProperty
    byte[] cert;

    @JsonProperty
    String privKeyPEM;

    @JsonProperty
    String subject;

    @JsonProperty
    Date validUntil;

    @JsonProperty
    int N;

    @JsonProperty
    int M;

    @JsonProperty
    private List<IssuedCertificateItem> issuedCertList = new ArrayList<IssuedCertificateItem>();

    /**
     * allow construction by jackson
     */
    public RootCertificateItem(){}

    RootCertificateItem(X509Certificate cert, int N, int M){
        this.certId = cert.getSerialNumber().toString(16);
        this.subject = cert.getSubjectDN().getName().toString();
        this.validUntil = cert.getNotAfter();
        try {
            this.cert = cert.getEncoded();
        } catch (CertificateEncodingException e) {
            Log.e( TAG, "Certificate encoding failed", e);
        }
        this.N = N;
        this.M = M;
    }

    public RootCertificateItem(KeyPair kp, X509Certificate cert, int N, int M) throws IOException {
        this(cert, N, M);

        try {
            StringWriter swPem = new StringWriter();
            try (PemWriter writer = new PemWriter(swPem)) {
                writer.writeObject(new PemObject("PRIVATE KEY", kp.getPrivate().getEncoded()));
            }
            this.privKeyPEM = swPem.toString();
            Log.d( TAG, "Writing #" +kp.getPrivate().getEncoded().length + " private key bytes as PEM: \n" + this.privKeyPEM);
        } catch (IOException e) {
            Log.e(TAG, "problem encoding private key", e);
        } //end of catch block

    }

    public List<IssuedCertificateItem> getIssuedCertList(){
        return issuedCertList;
    }

    public void addIssuedCertList(X509Certificate cert){

        issuedCertList.add(new IssuedCertificateItem(cert));
    }


}
