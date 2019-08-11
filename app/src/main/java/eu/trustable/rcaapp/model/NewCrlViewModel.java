package eu.trustable.rcaapp.model;

import java.util.ArrayList;
import java.util.List;

import eu.trustable.rcaapp.IssuedCertificateItem;

public class NewCrlViewModel extends CommitableViewModel  {

    public long crlValiditySeconds = 3L* 30L * 24L * 3600L;
    public List<IssuedCertificateItem> revokedCertificateList = new ArrayList<IssuedCertificateItem>();

}
