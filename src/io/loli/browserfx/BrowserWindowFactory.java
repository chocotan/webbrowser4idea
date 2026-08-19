package io.loli.browserfx;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BrowserWindowFactory implements ToolWindowFactory {

    public BrowserWindowFactory() {

    }

    private static boolean isJavaFxAvailable() {
        try {
            Class.forName("javafx.application.Platform", false, BrowserWindowFactory.class.getClassLoader());
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        JComponent component;
        if (isJavaFxAvailable()) {
            component = new Browser(new JavaFxBrowserView());
        } else {
            JPanel unsupportedPanel = new JPanel(new BorderLayout());
            unsupportedPanel.add(new JLabel("Embedded Web Browser requires JavaFX, which is not available in this IDE runtime."), BorderLayout.CENTER);
            component = unsupportedPanel;
        }
        Content content = contentFactory.createContent(component, "", false);
        toolWindow.getContentManager().addContent(content);
    }


}