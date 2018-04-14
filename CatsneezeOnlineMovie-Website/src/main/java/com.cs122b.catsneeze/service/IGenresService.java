package com.cs122b.catsneeze.service;

import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.pojo.Genres;

import java.util.List;

public interface IGenresService {

    ServerResponse<List<Genres>> selectAllGenres();
}
