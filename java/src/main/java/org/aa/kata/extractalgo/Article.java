package org.aa.kata.extractalgo;

import java.util.List;

public record Article(String title, List<String> tagIds, List<String> authorIds) {
}
