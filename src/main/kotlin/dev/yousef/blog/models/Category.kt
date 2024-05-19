package dev.yousef.blog.models

import io.quarkus.hibernate.reactive.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntity
import io.smallrye.mutiny.Uni
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import java.io.InvalidObjectException

@Entity
class Category : PanacheEntity() {

    var name: String? = ""

    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL], orphanRemoval = true)
    var subcategories: MutableSet<Subcategory> = HashSet()

    companion object : PanacheCompanion<Category> {
        fun create(category: Category): Uni<Void>?  {
            if (!category.name.isNullOrEmpty()) {
                return Category.persist(category)
            } else {
                throw InvalidObjectException("Category object is invalid")
            }
        }
        fun listAllCategories(): Uni<List<Category>> = Category.listAll()
    }
}