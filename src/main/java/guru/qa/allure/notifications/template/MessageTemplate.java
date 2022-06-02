package guru.qa.allure.notifications.template;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import guru.qa.allure.notifications.exceptions.MessageBuildException;
import guru.qa.allure.notifications.template.config.TemplateConfig;
import guru.qa.allure.notifications.template.data.MessageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author kadehar
 * @since 4.0
 * Utility class for template parsing.
 */
public class MessageTemplate {
    private static final Logger LOG =
            LoggerFactory.getLogger(MessageTemplate.class);

    private final TemplateConfig templateConfig = new TemplateConfig();

    public String of(String templateFile) throws MessageBuildException {
        LOG.info("Processing template {}", templateFile);
        Template template = null;
        try {
            LOG.info("Parsing template");
            template = templateConfig.configure().getTemplate(templateFile);
        } catch (IOException ex) {
            throw new MessageBuildException(String.format("Unable to parse template %s!", templateFile), ex);
        }
        Writer writer = new StringWriter();
        try {
            LOG.info("Convert template to string");
            template.process(new MessageData().values(), writer);
        } catch (TemplateException | IOException ex) {
            throw new MessageBuildException(String.format("Unable to parse template %s!", templateFile), ex);
        }
        return writer.toString();
    }
}
