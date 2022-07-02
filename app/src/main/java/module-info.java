module wordle.clone.app.main {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    exports hoogenbj.wordle.clone;
    opens hoogenbj.wordle.clone to javafx.fxml;
}
