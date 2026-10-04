package extensions;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotOnFailureExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(
            ExtensionContext context) throws Exception {

        if (context.getExecutionException().isEmpty()) {
            return;
        }

        Object testInstance = context.getRequiredTestInstance();

        if (!(testInstance instanceof HasDriver hasDriver)) {
            return;
        }

        WebDriver driver = hasDriver.getDriver();

        if (driver == null) {
            return;
        }

        try {
            byte[] bytes = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);

            // 1) Salva o arquivo na pasta screenshots/
            Path directory = Paths.get("screenshots");
            Files.createDirectories(directory);

            String className =
                    context.getRequiredTestClass().getSimpleName();

            String testName =
                    context.getDisplayName()
                            .replaceAll("[^a-zA-Z0-9-_]", "_");

            Path screenshotPath =
                    directory.resolve(className + "_" + testName + ".png");

            Files.write(screenshotPath, bytes);

            // 2) Anexa a imagem no relatório do Allure
            Allure.addAttachment(
                    "Screenshot da falha",
                    "image/png",
                    new ByteArrayInputStream(bytes),
                    "png"
            );

        } catch (Exception e) {
            System.err.println(
                    "Não foi possível salvar o screenshot: "
                            + e.getMessage()
            );
        }
    }

    public interface HasDriver {
        WebDriver getDriver();
    }
}