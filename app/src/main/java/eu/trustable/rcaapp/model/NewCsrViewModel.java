package eu.trustable.rcaapp.model;

import androidx.lifecycle.ViewModel;

import org.bouncycastle.asn1.x500.X500Name;

import java.util.HashMap;

import eu.trustable.rcaapp.RootCertificateItem;

public class NewCsrViewModel extends CommitableViewModel  {

    public String csrPEM;

}
