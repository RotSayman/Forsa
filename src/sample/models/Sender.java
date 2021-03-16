package sample.models;

public enum Sender {
    TAKE("T", "Забрали"), NO_TAKE("O", "Отказ"), SEND("S", "Пришёл") , NO_SEND("N", "Отправлен"), IN_CART("D", "В корзине");

    private final String code;
    private final String text;

    Sender(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public static Sender getByCode(String genderCode) {
        for (Sender g : Sender.values()) {
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
