package kg.beganov.CooksCorner.service;

import kg.beganov.CooksCorner.dto.response.search.SearchResults;

public interface SearchPageService {
    SearchResults searchByQuery(String query);
}
