package data;

public enum Language {

    RU("Русский"),
    EN("English"),
    CN("中文"),
    DE("Deutsch"),
    ES("Español"),
    IT("Italiano");

    public final String description;

    Language(String description) {
        this.description = description;
    }
}
