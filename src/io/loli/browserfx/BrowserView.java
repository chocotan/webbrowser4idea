package io.loli.browserfx;


import javax.swing.*;
import java.util.function.Consumer;

public interface BrowserView {
    public void init();
    public void load(String url);
    public void reload();
    public void urlChangeCallback(Consumer<String> consumer);
    public JComponent getNode();
}
