package ttsw.filopl.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by T. Filo Zegarlicki on 02.09.2022
 **/

@Component
@EnableAutoConfiguration
@ConfigurationProperties("task")
public class TaskConfigurationProperties {
    private Template template;

    public TaskConfigurationProperties() {
    }

    public TaskConfigurationProperties(Template template) {
        this.template = template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public Template getTemplate() {
        return template;
    }

    public static class Template {

        @Autowired
        private boolean allowMultipleTaskFromTemplate;

        public boolean isAllowMultipleTaskFromTemplate() {
            return allowMultipleTaskFromTemplate;
        }

        public void setAllowMultipleTaskFromTemplate(boolean allowMultipleTaskFromTemplate) {
            this.allowMultipleTaskFromTemplate = allowMultipleTaskFromTemplate;
        }
    }
}
