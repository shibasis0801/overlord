'use strict';

const android = require('@react-native-community/cli-platform-android');

module.exports = {
    commands: [ ...android.commands ],
    platforms: {
        android: {
            linkConfig: android.linkConfig,
            projectConfig: android.projectConfig,
            dependencyConfig: android.dependencyConfig,
        },
    },
    root: "./",
    project: {
        android: {
            sourceDir: './app',
            settingsGradlePath: "./settings.gradle",
            manifestPath: "./src/main/AndroidManifest.xml",
            packageName: "com.overlord.app"
        },
    },
};
