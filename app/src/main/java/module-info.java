module wordle.clone.app.main {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    exports hoogenbj.wordle.lookalike;
    opens hoogenbj.wordle.lookalike to javafx.fxml;
}
