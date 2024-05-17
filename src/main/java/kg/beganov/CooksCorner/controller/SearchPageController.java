package kg.beganov.CooksCorner.controller;

import io.swagger.v3.oas.annotations.Operation;
import kg.beganov.CooksCorner.dto.response.search.SearchResults;
import kg.beganov.CooksCorner.service.SearchPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class SearchPageController {
    SearchPageService searchPageService;
    @Operation(summary = "Search by string = userName/recipeName", description = "Returns list of users/recipes or empty list")
    @GetMapping
    public SearchResults searchByQuery(@RequestParam String query){
        return searchPageService.searchByQuery(query);
    }
}
