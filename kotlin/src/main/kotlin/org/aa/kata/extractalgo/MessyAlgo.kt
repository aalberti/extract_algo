package org.aa.kata.extractalgo

class MessyAlgo {
    private val names: NameRepository = NameRepository()
    private val descriptions: DescriptionRepository = DescriptionRepository()

    fun describe(article: Article): String {
        val describer = Describer(names, descriptions)
        val authors = describer.describeFromIds(article.authorIds)
        val tags = describer.describeFromIds(article.tagIds)
        return "${article.title} is written by $authors. It talks about $tags"
    }
}

class Describer(names: NameRepository, descriptions: DescriptionRepository) {
    private val namesCache: Map<String, String>
    private val descriptionsCache: Map<String, String>

    init {
        this.namesCache = names.allNames().associate { it.id to it.name }
        this.descriptionsCache = descriptions.allDescriptions().associate { it.id to it.description }
    }

    fun describeFromIds(
        ids: Set<String>
    ): String {
        return ids
            .map { id -> "${namesCache[id] ?: id}${descriptionsCache[id]?.let { " (${it})" } ?: ""}" }
            .joinToString { it }
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
