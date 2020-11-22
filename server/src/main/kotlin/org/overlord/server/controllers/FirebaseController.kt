package org.overlord.server.controllers

import com.google.firebase.database.DatabaseReference
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class FirebaseController {
    @Autowired
    lateinit var ref: DatabaseReference

    @GetMapping("/firebase/{name}/{value}")
    fun testRealtimeDatabaseWrite(@PathVariable name: String, @PathVariable value: String) = Mono.create<Pair<String, String>> { callback ->
        ref.child(name)
                .setValue(value) { error, _ ->
                    if (error == null)
                        callback.success(name to value)
                    else
                        callback.error(error.toException())
                }
    }
}
