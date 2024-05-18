package dev.yousef.blog.models


import io.quarkus.hibernate.reactive.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntity
import jakarta.persistence.Entity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InvalidObjectException

@Entity
class ProgrammingLanguage : PanacheEntity() {
    lateinit var name: String

    companion object : PanacheCompanion<ProgrammingLanguage> {
        suspend fun create(programmingLanguage: ProgrammingLanguage): ProgrammingLanguage =
            withContext(Dispatchers.IO) {
                if (programmingLanguage.name != null) {
                    ProgrammingLanguage.persist(programmingLanguage)
                    programmingLanguage
                } else {
                    throw InvalidObjectException("Programming Language object is invalid")
                }
            }

        //TODO: The rest of the service methods
    }

}
