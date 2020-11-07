package org.overlord.server.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.FirebaseDatabase
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.io.Resource
import java.io.FileInputStream


@Configuration
class FirebaseConfiguration {
    @Value("classpath:firebase.json")
    lateinit var resourceFile: Resource

    @Primary
    @Bean
    fun firebaseAdmin() {
        val serviceAccount = FileInputStream(resourceFile.file)
        val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://pravegaherokufirebase.firebaseio.com")
                .build()


        FirebaseApp.initializeApp(options)
        val ref = FirebaseDatabase.getInstance().reference
        ref.child("spring")
                .setValueAsync(mapOf(
                        Pair("name", "shibasis"),
                        Pair("skills", mapOf(
                                Pair("dev", "Kotlin/JS"),
                                Pair("ai", "python/math")
                        )
                        )
                ))
    }

}
