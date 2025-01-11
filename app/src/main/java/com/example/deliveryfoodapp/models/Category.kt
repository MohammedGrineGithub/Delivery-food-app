package com.example.deliveryfoodapp.models

data class Category(
    var id: Int,
    var name: String,
    var items: MutableList<Item>
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "name" to name,
        "items" to items.map { it.toMap() }
    )

    companion object {
        fun fromMap(map: Map<String, Any?>) = Category(
            id = (map["id"] as Double).toInt(),
            name = map["name"] as? String ?: "",
            items = (map["items"] as? List<Map<String, Any>>)?.map {
                Item.fromMap(it)
            }?.toMutableList() ?: mutableListOf()
        )

        fun emptyCategory(): Category {
            return Category(id = 0, name = "", items = mutableListOf())
        }
    }
}