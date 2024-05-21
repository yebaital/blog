package dev.yousef.blog.models

import io.quarkus.hibernate.reactive.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntity
import io.smallrye.mutiny.Uni
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import java.io.InvalidObjectException

@Entity
class SubcategoryEntity : PanacheEntity() {

    var name: String? = ""

    @ManyToOne
    lateinit var category: CategoryEntity

    companion object : PanacheCompanion<SubcategoryEntity> {

        fun findById(id: String): Uni<SubcategoryEntity?> {
            return find(id,"id").firstResult()
        }

        fun create(subcategory: SubcategoryEntity): Uni<Void>? {
            if (!subcategory.name.isNullOrEmpty()) {
                return SubcategoryEntity.persist(subcategory)
            } else {
                throw InvalidObjectException("Category object is invalid")
            }
        }

        fun listAllSubcategories(): Uni<List<SubcategoryEntity>> = SubcategoryEntity.listAll()
    }
}