package io.loli.browserfx;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;


public class BrowserWindowFactory implements ToolWindowFactory {

    public BrowserWindowFactory() {

    }

    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(new Browser(new JavaFxBrowserView()),"", false);
        toolWindow.getContentManager().addContent(content);
    }


}