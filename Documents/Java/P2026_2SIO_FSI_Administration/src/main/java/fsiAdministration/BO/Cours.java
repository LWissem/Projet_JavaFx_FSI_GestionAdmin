package fsiAdministration.BO;

import javafx.beans.property.SimpleStringProperty;

public class Cours {
    private int idCours;
    private SimpleStringProperty  libelleCours;
    private SimpleStringProperty descriptionCours;
    private int idSection;

    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public String getLibelleCours() {
        return libelleCours.get();
    }

    public SimpleStringProperty libelleCoursProperty() {
        return libelleCours;
    }

    public void setLibelleCours(String libelleCours) {
        this.libelleCours.set(libelleCours);
    }

    public String getDescriptionCours() {
        return descriptionCours.get();
    }

    public SimpleStringProperty descriptionCoursProperty() {
        return descriptionCours;
    }

    public void setDescriptionCours(String descriptionCours) {
        this.descriptionCours.set(descriptionCours);
    }

    public int getIdSection() {
        return idSection;
    }

    public void setIdSection(int idSection) {
        this.idSection = idSection;
    }

    public Cours(int idCours, String libelleCours, String descriptionCours, int idSection) {
        this.idCours = idCours;
        this.libelleCours = new SimpleStringProperty(libelleCours);
        this.descriptionCours = new SimpleStringProperty(descriptionCours);
        this.idSection = idSection;
    }

    public Cours(String libelleCours, String descriptionCours) {
        this.libelleCours = new SimpleStringProperty(libelleCours);
        this.descriptionCours = new SimpleStringProperty(descriptionCours);
    }
    @Override
    public String toString() {
        return getLibelleCours();
    }

}
