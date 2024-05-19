package dev.yousef.blog.models


import io.quarkus.hibernate.reactive.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntity
import io.smallrye.mutiny.Uni
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import java.io.InvalidObjectException

@Entity
class ProgrammingLanguage : PanacheEntity() {
    var name: String? = ""

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    var posts: MutableSet<Post> = HashSet()

    companion object : PanacheCompanion<ProgrammingLanguage> {
        fun create(programmingLanguage: ProgrammingLanguage): Uni<Void>? {
            if (!programmingLanguage.name.isNullOrBlank()) {
                return ProgrammingLanguage.persist(programmingLanguage)
            } else {
                throw InvalidObjectException("Programming Language object is invalid")
            }
        }

        fun listAllProgrammingLanguages(): Uni<List<ProgrammingLanguage>> =
            ProgrammingLanguage.listAll()

        //TODO: The rest of the service methods
    }

}
