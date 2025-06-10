package fsiAdministration.BO;

public class Referent {
    private int idRef;
    private String nomRef;
    private String prenomRef;
    private String emaRef;

    public Referent(int idRef, String nomRef, String prenomRef, String emaRef) {
        this.idRef = idRef;
        this.nomRef = nomRef;
        this.prenomRef = prenomRef;
        this.emaRef = emaRef;
    }

    public int getIdRef() {
        return idRef;
    }

    public void setIdRef(int idRef) {
        this.idRef = idRef;
    }

    public String getNomRef() {
        return nomRef;
    }

    public void setNomRef(String nomRef) {
        this.nomRef = nomRef;
    }

    public String getPrenomRef() {
        return prenomRef;
    }

    public void setPrenomRef(String prenomRef) {
        this.prenomRef = prenomRef;
    }

    public String getEmaRef() {
        return emaRef;
    }

    public void setEmaRef(String emaRef) {
        this.emaRef = emaRef;
    }
}
