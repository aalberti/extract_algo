package org.aa.kata.extractalgo

class MessyAlgo {
    private val names: NameRepository = NameRepository()
    private val descriptions: DescriptionRepository = DescriptionRepository()

    fun describe(article: Article): String {
        val namesCache = names.allNames().associate { it.id to it.name }
        val descriptionsCache = descriptions.allDescriptions().associate { it.id to it.description }
        val authors = article.authorIds
            .map { id -> "${namesCache[id] ?: id}${descriptionsCache[id]?.let { " (${it})" } ?: ""}" }
            .joinToString { it }
        val tags = article.tagIds
            .map { id -> "${namesCache[id] ?: id}${descriptionsCache[id]?.let { " (${it})" } ?: ""}" }
            .joinToString { it }
        return "${article.title} is written by $authors. It talks about $tags"
    }
}

data class Article(val title: String, val tagIds: Set<String>, val authorIds: Set<String>)

class NameRepository {
    fun allNames(): Set<Name> =
        setOf(
            Name("aalberti", "Antoine Alberti"),
            Name("cyriux", "Cyrille Martraire"),
            Name("ddd", "DDD")
        )
}

data class Name(val id: String, val name: String)

class DescriptionRepository {
    fun allDescriptions(): Set<Description> =
        setOf(
            Description("aalberti", "socio-poetico anarchitect"),
            Description("cyriux", "DDD superstar"),
            Description("ddd", "Domain-Driven Design")
        )
}

data class Description(val id: String, val description: String)
