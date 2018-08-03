package com.framgia.newyorktime.model

import com.framgia.domain.model.Movie
import com.framgia.newyorktime.base.model.ItemMapper
import com.framgia.newyorktime.base.model.ModelItem
import javax.inject.Inject

/**
 * Created: 02/08/2018
 * By: Sang
 * Description:
 */
data class MovieItem(
    var id: Int,
    var imdbId: Int?,
    var adult: Boolean,
    var backdropPath: String?,
    var posterPath: String?,
    var genres: List<GenreItem>?,
    var overview: String?,
    var popularity: Double,
    var productionCompanies: List<ProductionCompanyItem>?,
    var productionCountries: List<ProductionCountryItem>?,
    var releaseDate: String,
    var spokenLanguages: List<SpokenLanguageItem>?,
    var status: String?,
    var title: String,
    var video: Boolean,
    var voteCount: Int
) : ModelItem()

class MovieItemMapper @Inject constructor(
    private val genreItemMapper: GenreItemMapper,
    private val productionCompanyItemMapper: ProductionCompanyItemMapper,
    private val productionCountryItemMapper: ProductionCountryItemMapper,
    private val spokenLanguageItemMapper: SpokenLanguageItemMapper
) : ItemMapper<Movie, MovieItem> {

    override fun mapToPresentation(model: Movie): MovieItem =
        MovieItem(
            id = model.id,
            imdbId = model.imdbId,
            adult = model.adult,
            backdropPath = model.backdropPath,
            posterPath = model.posterPath,
            genres = model.genres?.map { genreItemMapper.mapToPresentation(it) },
            overview = model.overview,
            popularity = model.popularity,
            productionCompanies = model.productionCompanies?.map {
                productionCompanyItemMapper.mapToPresentation(it)
            },
            productionCountries = model.productionCountries?.map {
                productionCountryItemMapper.mapToPresentation(it)
            },
            releaseDate = model.releaseDate,
            spokenLanguages = model.spokenLanguages?.map {
                spokenLanguageItemMapper.mapToPresentation(it)
            },
            status = model.status,
            title = model.title,
            video = model.video,
            voteCount = model.voteCount
        )

    override fun mapToDomain(modelItem: MovieItem): Movie =
        Movie(
            id = modelItem.id,
            imdbId = modelItem.imdbId,
            adult = modelItem.adult,
            backdropPath = modelItem.backdropPath,
            posterPath = modelItem.posterPath,
            genres = modelItem.genres?.map { genreItemMapper.mapToDomain(it) },
            overview = modelItem.overview,
            popularity = modelItem.popularity,
            productionCompanies = modelItem.productionCompanies?.map {
                productionCompanyItemMapper.mapToDomain(it)
            },
            productionCountries = modelItem.productionCountries?.map {
                productionCountryItemMapper.mapToDomain(it)
            },
            releaseDate = modelItem.releaseDate,
            spokenLanguages = modelItem.spokenLanguages?.map {
                spokenLanguageItemMapper.mapToDomain(it)
            },
            status = modelItem.status,
            title = modelItem.title,
            video = modelItem.video,
            voteCount = modelItem.voteCount
        )
}
