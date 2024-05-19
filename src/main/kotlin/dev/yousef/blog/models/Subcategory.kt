package dev.yousef.blog.models

import io.quarkus.hibernate.reactive.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntity
import io.smallrye.mutiny.Uni
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import java.io.InvalidObjectException

@Entity
class Subcategory : PanacheEntity() {

    var name: String? = ""

    @ManyToOne
    lateinit var category: Category

    companion object : PanacheCompanion<Subcategory> {
        fun create(subcategory: Subcategory): Uni<Void>? {
            if (!subcategory.name.isNullOrEmpty()) {
                return Subcategory.persist(subcategory)
            } else {
                throw InvalidObjectException("Category object is invalid")
            }
        }

        fun listAllCategories(): Uni<List<Subcategory>> = Subcategory.listAll()
    }
}