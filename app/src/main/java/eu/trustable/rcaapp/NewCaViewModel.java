package eu.trustable.rcaapp;

import androidx.lifecycle.ViewModel;

import org.bouncycastle.asn1.x500.X500Name;

public class NewCaViewModel extends ViewModel {

    String keyTypeLength;
    int validityPeriodDays = 0;
    X500Name x500Subject;
    int N = 0;
    int M = 0;

}
