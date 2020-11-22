package org.overlord.server.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.io.Resource
import java.io.FileInputStream
import java.io.InputStream


@Configuration
class FirebaseConfiguration {
    @Value("classpath:firebase.json")
    lateinit var resourceFile: Resource

    fun credentialStream(): InputStream {
        val config = getEnv("FIREBASE_CONFIG", "")
        return if (config.isNotEmpty())
            config.byteInputStream() else
            FileInputStream(resourceFile.file)
    }

    @Primary
    @Bean
    fun firebaseAdmin(): FirebaseApp {
        val credentials = GoogleCredentials.fromStream(credentialStream())
        val options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .setDatabaseUrl("https://pravegaherokufirebase.firebaseio.com")
                .build()


        return FirebaseApp.initializeApp(options)
    }

    @Bean
    fun getFirebaseDatabase(app: FirebaseApp) = FirebaseDatabase.getInstance()

    @Bean
    fun getDatabaseReference(app: FirebaseApp, database: FirebaseDatabase) = database.reference

    @Bean
    fun getFirebaseAuth(app: FirebaseApp) = FirebaseAuth.getInstance()

//    Use redis to create message queue between this and the AI server
}
