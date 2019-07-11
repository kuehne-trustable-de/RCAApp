package eu.trustable.rcaapp;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
class IssuedCertificateItem {

    /**
     *
     */
    private static final long serialVersionUID = 171698796484545398L;

    private static final String TAG = "PersistentModel";

    @JsonProperty
    String certId;

    @JsonProperty
    byte[] cert;

    @JsonProperty
    String subject;

    @JsonProperty
    Date validUntil;

    public IssuedCertificateItem(){}

    IssuedCertificateItem(X509Certificate cert){
        this.certId = cert.getSerialNumber().toString(16);
        this.subject = cert.getSubjectDN().getName().toString();
        this.validUntil = cert.getNotAfter();
        try {
            this.cert = cert.getEncoded();
        } catch (CertificateEncodingException e) {
            Log.e( TAG, "Certificate encoding failed", e);
        }
    }

}
