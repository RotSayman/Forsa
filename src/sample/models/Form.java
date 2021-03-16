package sample.models;

public enum Form {
    CASH("C", "Наличный"), NON_CASH("N", "Безналичный");

    private String code;
    private String text;

    Form(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public static Form getByCode(String genderCode) {
        for (Form g : Form.values()) {
            if (g.code.equals(genderCode)) {
                return g;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
