package eu.trustable.rcaapp.model;

import androidx.lifecycle.ViewModel;

import org.bouncycastle.asn1.x500.X500Name;

import java.util.Map;

public class NewCaViewModel extends ViewModel {

    public String keyTypeLength;
    public int validityPeriodDays = 0;
    public X500Name x500Subject;
    public int N = 0;
    public int M = 0;

    public Map<Integer, char[]> passwordMap;


}
