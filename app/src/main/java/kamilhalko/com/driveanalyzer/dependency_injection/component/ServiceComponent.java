package kamilhalko.com.driveanalyzer.dependency_injection.component;

import dagger.Component;
import kamilhalko.com.driveanalyzer.dependency_injection.PerService;
import kamilhalko.com.driveanalyzer.dependency_injection.module.ServiceModule;
import kamilhalko.com.driveanalyzer.services.DriveAnalyzeService;

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {
    void inject(DriveAnalyzeService service);
}
