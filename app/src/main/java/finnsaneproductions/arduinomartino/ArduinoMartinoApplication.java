package finnsaneproductions.arduinomartino;

import android.app.Application;
import android.content.Context;

import org.acra.ACRA;
import org.acra.annotation.AcraCore;
import org.acra.annotation.AcraMailSender;
import org.acra.config.CoreConfigurationBuilder;
import org.acra.config.ToastConfigurationBuilder;
import org.acra.data.StringFormat;

@AcraCore(buildConfigClass = BuildConfig.class)

@AcraMailSender(mailTo = "finn.caitlin@yahoo.com", reportAsFile = true)
public class ArduinoMartinoApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        // The following line triggers the initialization of ACRA
        CoreConfigurationBuilder builder = new CoreConfigurationBuilder(this);
        builder.setBuildConfigClass(BuildConfig.class).setReportFormat(StringFormat.JSON);
       // builder.getPluginConfigurationBuilder(ToastConfigurationBuilder.class).setResText("Some text");
        builder.getPluginConfigurationBuilder(ToastConfigurationBuilder.class).setEnabled(true);
        ACRA.init(this, builder);    }
}
