package eu.trustable.rcaapp.model;

import androidx.lifecycle.ViewModel;

import java.util.HashMap;

import eu.trustable.rcaapp.RootCertificateItem;

public class CommitableViewModel extends ViewModel {

    public String issuingCertId;
    public RootCertificateItem issuingCert;
    public HashMap<Integer, char[]> passwordMap = new HashMap<Integer, char[]>();

}
