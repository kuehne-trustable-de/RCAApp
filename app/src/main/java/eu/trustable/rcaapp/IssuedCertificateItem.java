package eu.trustable.rcaapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.security.cert.X509Certificate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IssuedCertificateItem extends CertificateItem{

    /**
     *
     */
    private static final long serialVersionUID = 171698796484545398L;

    private static final String TAG = "PersistentModel";


    public IssuedCertificateItem(){}

    IssuedCertificateItem(X509Certificate cert){
        super(cert);
    }


}
