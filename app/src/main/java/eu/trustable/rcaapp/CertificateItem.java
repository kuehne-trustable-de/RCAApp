package eu.trustable.rcaapp;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CertificateItem implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 99871236486824398L;

    private static final String TAG = "CertificateItem";

    @JsonProperty
    private
    String certId;

    @JsonProperty
    private
    byte[] cert;

    @JsonProperty
    private
    String subject;

    @JsonProperty
    private
    Date validUntil;

    public CertificateItem(){}

    CertificateItem(X509Certificate cert){
        this.certId = cert.getSerialNumber().toString(16);
        this.subject = cert.getSubjectDN().getName().toString();
        this.validUntil = cert.getNotAfter();
        try {
            this.cert = cert.getEncoded();
        } catch (CertificateEncodingException e) {
            Log.e( TAG, "Certificate encoding failed", e);
        }
    }


    public String getCertId() {
        return certId;
    }

    public byte[] getCert() {
        return cert;
    }

    public String getSubject() {
        return subject;
    }

    public Date getValidUntil() {
        return validUntil;
    }
}
