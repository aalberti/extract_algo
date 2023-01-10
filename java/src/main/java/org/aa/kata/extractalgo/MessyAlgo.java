package org.aa.kata.extractalgo;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;

public class MessyAlgo {
    private final NameRepository names = new NameRepository();
    private final DescriptionRepository descriptions = new DescriptionRepository();

    public String describe(Article article) {
        Map<String, Name> namesCache = names.allNames().stream().collect(toMap(Name::id, identity()));
        Map<String, Description> descriptionsCache = descriptions.allDescriptions().stream().collect(toMap(Description::id, identity()));
        String authors = article.authorIds().stream()
                .map(id -> String.format("%s%s",
                        namesCache.getOrDefault(id, new Name(id, id)).name(),
                        Optional.ofNullable(descriptionsCache.get(id))
                                .map(Description::description)
                                .map(description -> String.format(" (%s)", description))
                                .orElse("")))
                .collect(joining(", "));
        String tags = article.tagIds().stream()
                .map(id -> String.format("%s%s",
                        namesCache.getOrDefault(id, new Name(id, id)).name(),
                        Optional.ofNullable(descriptionsCache.get(id))
                                .map(Description::description)
                                .map(description -> String.format(" (%s)", description))
                                .orElse("")))
                .collect(joining(", "));
        return article.title() + " is written by " + authors + ".\n" +
                "It talks about " + tags;
    }

    private static class NameRepository {
        public Set<Name> allNames() {
            return Set.of(
                    new Name("aalberti", "Antoine Alberti"),
                    new Name("cyriux", "Cyrille Martraire"),
                    new Name("ddd", "DDD"));
        }
    }

    private record Name(String id, String name) {
    }

    private static class DescriptionRepository {
        public Set<Description> allDescriptions() {
            return Set.of(
                    new Description("aalberti", "socio-poetico anarchitect"),
                    new Description("cyriux", "DDD superstar"),
                    new Description("ddd", "Domain-Driven Design"));
        }
    }

    private record Description(String id, String description) {
    }
}
