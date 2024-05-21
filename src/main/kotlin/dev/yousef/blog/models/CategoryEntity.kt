package dev.yousef.blog.models

import io.quarkus.hibernate.reactive.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntity
import io.smallrye.mutiny.Uni
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import java.io.InvalidObjectException

@Entity
class CategoryEntity : PanacheEntity() {

    var name: String? = ""

    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL], orphanRemoval = true)
    var subcategories: MutableSet<SubcategoryEntity> = HashSet()

    companion object : PanacheCompanion<CategoryEntity> {
        fun create(category: CategoryEntity): Uni<Void>?  {
            if (!category.name.isNullOrEmpty()) {
                return CategoryEntity.persist(category)
            } else {
                throw InvalidObjectException("Category object is invalid")
            }
        }
        fun listAllCategories(): Uni<List<CategoryEntity>> = CategoryEntity.listAll()
    }
}