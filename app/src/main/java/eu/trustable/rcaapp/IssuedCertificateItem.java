package eu.trustable.rcaapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.security.cert.X509Certificate;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IssuedCertificateItem extends CertificateItem{

    /**
     *
     */
    private static final long serialVersionUID = 171698796484545398L;

    public static final int CRL_REASON_NOT_REVOKED = -1;
    public static final int CRL_REASON_PENDING_OFFSET = 1000;


    private static final String TAG = "IssuedCertificateItem";


    @JsonProperty
    private
    int revocationReason = CRL_REASON_NOT_REVOKED;

    @JsonProperty
    private
    Date revocationDate;

    @JsonProperty
    private
    boolean revocationPending;


    public IssuedCertificateItem(){}

    IssuedCertificateItem(X509Certificate cert){
        super(cert);
    }



    public int getRevocationReason() {
        return revocationReason;
    }

    public void setRevocationReason(int revocationReason) {
        this.revocationReason = revocationReason;
    }

    public Date getRevocationDate() {
        return revocationDate;
    }

    public void setRevocationDate(Date revocationDate) {
        this.revocationDate = revocationDate;
    }

    public boolean isRevocationPending() {
        return revocationPending;
    }

    public void setRevocationPending(boolean revocationPending) {
        this.revocationPending = revocationPending;
    }


}
