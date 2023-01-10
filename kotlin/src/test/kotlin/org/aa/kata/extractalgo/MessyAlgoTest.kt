package org.aa.kata.extractalgo

import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test

internal class MessyAlgoTest {
    @Test
    internal fun name() {
        assertThat(
            MessyAlgo().describe(
                Article(
                    "chit chat",
                    listOf("ddd", "TDD"),
                    listOf("cyriux", "Jessica Kerr", "aalberti")))
        ).isEqualTo("chit chat is written by Cyrille Martraire (DDD superstar), Jessica Kerr, Antoine Alberti (socio-poetico anarchitect). It talks about DDD (Domain-Driven Design), TDD")
    }
}