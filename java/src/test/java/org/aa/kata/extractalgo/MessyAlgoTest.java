package org.aa.kata.extractalgo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MessyAlgoTest {
    @Test
    void name() {
        assertThat(
                new MessyAlgo().describe(
                        new Article(
                                "chit chat",
                                List.of("ddd", "TDD"),
                                List.of("cyriux", "Jessica Kerr", "aalberti")))
        ).isEqualTo("chit chat is written by Cyrille Martraire (DDD superstar), Jessica Kerr, Antoine Alberti (socio-poetico anarchitect).\n" +
                "It talks about DDD (Domain-Driven Design), TDD");
    }
}
