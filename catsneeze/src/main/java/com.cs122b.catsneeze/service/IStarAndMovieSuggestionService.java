package com.cs122b.catsneeze.service;

import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.vo.SuggestionVo;

import java.util.List;

public interface IStarAndMovieSuggestionService {
    ServerResponse<List<SuggestionVo>> getSuggestionsFromStarAndMovie(String query);
}
