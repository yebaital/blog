package dev.yousef.blog.models


import io.quarkus.hibernate.reactive.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntity
import io.smallrye.mutiny.Uni
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import java.io.InvalidObjectException

@Entity
class ProgrammingLanguageEntity : PanacheEntity() {
    var name: String? = ""

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    var postEntities: MutableSet<PostEntity> = HashSet()

    companion object : PanacheCompanion<ProgrammingLanguageEntity> {

        fun findById(id: String): Uni<ProgrammingLanguageEntity?> {
            return find("id", id).firstResult()
        }

        fun create(programmingLanguage: ProgrammingLanguageEntity): Uni<Void>? {
            if (!programmingLanguage.name.isNullOrBlank()) {
                return ProgrammingLanguageEntity.persist(programmingLanguage)
            } else {
                throw InvalidObjectException("Programming Language object is invalid")
            }
        }

        fun listAllProgrammingLanguages(): Uni<List<ProgrammingLanguageEntity>> =
            ProgrammingLanguageEntity.listAll()

        //TODO: The rest of the service methods
    }

}
