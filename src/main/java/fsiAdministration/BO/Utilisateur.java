package fsiAdministration.BO;

public class Utilisateur {

    private int idUtilisateur;
    private int idSection;
    private String loginUtilisateur;
    private String mdpUtilisateur;

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getMdpUtilisateur() {
        return mdpUtilisateur;
    }

    public void setMdpUtilisateur(String mdpUtilisateur) {
        this.mdpUtilisateur = mdpUtilisateur;
    }

    public String getLoginUtilisateur() {
        return loginUtilisateur;
    }

    public void setLoginUtilisateur(String loginUtilisateur) {
        this.loginUtilisateur = loginUtilisateur;
    }

    public int getIdSection() {
        return idSection;
    }

    public void setIdSection(int idSection) {
        this.idSection = idSection;
    }

    public Utilisateur(int idUtilisateur, String loginUtilisateur, String mdpUtilisateur) {
        this.idUtilisateur = idUtilisateur;
        this.idSection = idSection;
        this.loginUtilisateur = loginUtilisateur;
        this.mdpUtilisateur = mdpUtilisateur;
    }
}
