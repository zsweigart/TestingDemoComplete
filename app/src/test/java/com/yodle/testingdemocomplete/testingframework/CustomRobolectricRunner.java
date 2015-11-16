package com.yodle.testingdemocomplete.testingframework;

import com.yodle.testingdemocomplete.BuildConfig;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.lang.reflect.Method;

// https://github.com/robolectric/robolectric/issues/1810
public class CustomRobolectricRunner extends RobolectricGradleTestRunner {

    private static final int MAX_SDK_LEVEL = 21;

    private int[] sdk = {21};

    public CustomRobolectricRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override

    public Config getConfig(Method method) {

        Config config = super.getConfig(method);

        config = new Config.Implementation(sdk,

                config.manifest(),

                config.qualifiers(),

                "com.yodle.testingdemocomplete",

                config.resourceDir(),

                config.assetDir(),

                config.shadows(),

                config.application(),

                config.libraries(),

                ensureBuildConfig(config.constants()));


        return config;
    }

    private Class<?> ensureBuildConfig(Class<?> constants) {
        if (constants == Void.class) return BuildConfig.class;
        return constants;
    }

    private int ensureSdkLevel(int sdkLevel) {
        if (sdkLevel > MAX_SDK_LEVEL) return MAX_SDK_LEVEL;
        if (sdkLevel <= 0) return MAX_SDK_LEVEL;
        return sdkLevel;
    }
}
