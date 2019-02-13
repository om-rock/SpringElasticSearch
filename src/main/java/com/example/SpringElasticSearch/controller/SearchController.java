/**
 * 
 */
package com.example.SpringElasticSearch.controller;

import static org.elasticsearch.index.query.MatchQueryBuilder.Operator.AND;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

import java.util.List;

import org.elasticsearch.common.unit.Fuzziness;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.techprimers.elastic.model.Users;

public class SearchController {
	 @GetMapping(value = "/fuzzy/{fuzzyString}")//spell mistake
	    public List<Users> givenPhraseWithType_whenUseFuzziness_thenQueryMatches(@PathVariable final String fuzzyString) {
	        final SearchQuery searchQuery = new NativeSearchQueryBuilder()
	                .withQuery(matchQuery("name", fuzzyString).operator(AND).fuzziness(Fuzziness.TWO)
	                        /*.prefixLength(3)*/).build();

	        final List<Users> userspage = elasticsearchTemplate.queryForList(searchQuery, Users.class);
//	        Page<Users> userspage = usersRepository.search(searchQuery);
	        return userspage;
	    }
}
