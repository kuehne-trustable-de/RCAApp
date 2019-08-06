package eu.trustable.rcaapp;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
class PersistentModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 731667732845983019L;

    private static final String TAG = "PersistentModel";

    private static final String defaultFilename = "RCA_Data.json";
    private static File storageFile;

    private static PersistentModel _instance = new PersistentModel();

    @JsonProperty
    private List<RootCertificateItem> rcList = new ArrayList<>();

    static PersistentModel getInstance(){
        return _instance;
    }


    static private ObjectMapper getMapper(){
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        return mapper;
    }

    static PersistentModel read(Context context) throws IOException {

        storageFile = new File(context.getFilesDir(), defaultFilename);

        if( storageFile.exists() && storageFile.canRead()){
            try {
                parseFile(storageFile);
            } catch (IOException e) {
                Log.e(TAG, "Problem reading file '" + storageFile.getAbsolutePath(), e);
                throw e;
            }
        }else{
            Log.d(TAG, "File '" + storageFile.getAbsolutePath() + "' not present or readable");
        }
        return _instance;
    }

    static PersistentModel parseFile(File fContent) throws IOException {
        ObjectMapper mapper = getMapper();

        _instance = mapper.readValue(fContent, PersistentModel.class);
        Log.d(TAG, "Successfully read file '" + fContent.getAbsolutePath());

        StringWriter sw = new StringWriter();
        mapper.writeValue(sw ,_instance);

        return _instance;
    }

    /**
     * allow construction by jackson
     */
    public PersistentModel(){}

    public void persist() throws IOException {

        ObjectMapper mapper = getMapper();
        mapper.writeValue(storageFile , this);
    }

    public List<RootCertificateItem> getRootCertList(){
        return rcList;
    }

    public RootCertificateItem findByCertId(String certId){
        for( RootCertificateItem rci: rcList){
            if( rci.getCertId().equals(certId)){
                return rci;
            }
        }
        return null;
    }

    public String addKeyAndCertificate(KeyPair kp, X509Certificate cert, int N, int M, Map<Integer, char[]> passwordMap) throws IOException, GeneralSecurityException {
        RootCertificateItem rci = new RootCertificateItem(kp, cert, N, M, passwordMap);
        rcList.add(rci);
        return rci.getCertId();
    }
}
