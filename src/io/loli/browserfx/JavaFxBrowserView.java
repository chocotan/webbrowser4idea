package io.loli.browserfx;

import com.sun.javafx.application.PlatformImpl;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.Dimension;
import java.util.function.Consumer;

public class JavaFxBrowserView implements BrowserView {
    private WebView browser;
    private WebEngine webEngine;
    private String url;

    private JFXPanel jfxPanel;

    public JavaFxBrowserView() {
    }

    @Override
    public void init() {
        reload();
    }

    @Override
    public void load(String url) {
        Platform.runLater(() -> {
            webEngine.load(url);
        });
    }

    @Override
    public void reload() {
        jfxPanel = new JFXPanel();
        Platform.runLater(() -> {
            browser = new WebView();
            webEngine = browser.getEngine();
        });
    }

    @Override
    public void urlChangeCallback(Consumer<String> consumer) {
        Platform.runLater(() -> {
            webEngine.getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {
                consumer.accept(webEngine.getLocation());
            });
        });
    }

    @Override
    public JComponent getNode() {
        Platform.runLater(() -> {
            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(browser);
            Scene scene = new Scene(borderPane);
            jfxPanel.setScene(scene);
        });

        return jfxPanel;
    }
}
