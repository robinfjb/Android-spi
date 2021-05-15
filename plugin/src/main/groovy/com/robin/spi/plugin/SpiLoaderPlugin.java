package com.robin.spi.plugin;

import com.android.build.gradle.BaseExtension;
import com.robin.spi.annotation.Const;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * 插件所做工作：将注解生成器生成的初始化类汇总到ServiceLoaderInit，运行时直接调用ServiceLoaderInit
 */
public class SpiLoaderPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        SpiLoaderExtension extension = project.getExtensions().create(Const.NAME, SpiLoaderExtension.class);

        SpiLoaderLogger.info("register transform");
        project.getExtensions().findByType(BaseExtension.class).registerTransform(new SpiLoaderTransform());

        project.afterEvaluate(p -> SpiLoaderLogger.setConfig(extension));
    }
}