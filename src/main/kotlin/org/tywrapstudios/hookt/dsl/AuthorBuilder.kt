package org.tywrapstudios.hookt.dsl

import org.tywrapstudios.hookt.types.Author

/**
 * Builder class for [Author].
 */
class AuthorBuilder : FormBuilder<Author> {
    /**
     * [Author.name]
     */
    var name: String? = null

    /**
     * [Author.url]
     */
    var url: String? = null

    /**
     * [Author.iconUrl]
     */
    var iconUrl: String? = null

    /**
     * [Author.proxyIconUrl]
     */
    var proxyIconUrl: String? = null

    override fun build(): Author = Author(
        name ?: throw IllegalStateException("Name for author is null"),
        url,
        iconUrl,
        proxyIconUrl,
    )
}