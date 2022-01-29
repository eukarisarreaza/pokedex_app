package com.domain.pokedexapp.data.model

import android.util.Log
import com.domain.pokedexapp.domain.model.Pokemon
import com.example.cleanarchitecture.data.base.EntityMapper
import com.example.cleanarchitecture.data.base.ModelEntity
import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton





data class PokemonEntity(
    val id: Long?,
    val name: String?,
    val order: Long?,
    val height: Long?,
    val weight: Long?,
    val abilities: List<Ability>?= null,
    val url: String,

    @SerializedName("base_experience")
    val baseExperience: Long?,

    val forms: List<Species>? = null,

    @SerializedName("game_indices")
    val gameIndices: List<GameIndex>? = null,

    @SerializedName("held_items")
    val heldItems: List<HeldItem> ? = null,

    @SerializedName("is_default")
    val isDefault: Boolean?,

    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String?,

    val moves: List<Move> ? = null,

    @SerializedName("past_types")
    val pastTypes: JsonArray?= null,

    val species: Species?= null,
    val sprites: Sprites?= null,
    val stats: List<Stat> ? = null,
    val types: List<Type> ? = null
    ) : ModelEntity()



@Singleton
class PokemonEntityMapper @Inject constructor() : EntityMapper<Pokemon, PokemonEntity> {
    override fun mapToDomain(entity: PokemonEntity): Pokemon= Pokemon(
        abilities = entity.abilities,
        baseExperience = entity.baseExperience,
        forms = entity.forms,
        gameIndices = entity.gameIndices,
        height = entity.height,
        heldItems = entity.heldItems,
        id = entity.id,
        isDefault = entity.isDefault,
        locationAreaEncounters = entity.locationAreaEncounters,
        moves = entity.moves,
        name = entity.name,
        order = entity.order,
        pastTypes = entity.pastTypes,
        species = entity.species,
        sprites = entity.sprites,
        stats = entity.stats,
        types = entity.types,
        weight = entity.weight,
        url = entity.url,
        urlImage = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${entity.url.split("/").toList().filter { it.isNotEmpty() }.last()}.png"
    )

    override fun mapToEntity(model: Pokemon): PokemonEntity = PokemonEntity(
        abilities = model.abilities,
        baseExperience = model.baseExperience,
        forms = model.forms,
        gameIndices = model.gameIndices,
        height = model.height,
        heldItems = model.heldItems,
        id = model.id,
        isDefault = model.isDefault,
        locationAreaEncounters = model.locationAreaEncounters,
        moves = model.moves,
        name = model.name,
        order = model.order,
        pastTypes = model.pastTypes,
        species = model.species,
        sprites = model.sprites,
        stats = model.stats,
        types = model.types,
        weight = model.weight,
        url = model.url
    )
}
















data class Ability (
    val ability: Species? = null,

    @SerializedName("is_hidden")
    val isHidden: Boolean?,

    val slot: Long?
)


data class Species (
    val name: String?,
    val url: String?
)


data class GameIndex (
    @SerializedName("game_index")
    val gameIndex: Long?,

    val version: Species? = null
)


data class HeldItem (
    val item: Species? = null,

    @SerializedName("version_details")
    val versionDetails: List<VersionDetail>?
)


data class VersionDetail (
    val rarity: Long?,
    val version: Species? = null
)

data class Move (
    val move: Species? = null,

    @SerializedName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>?
)

data class VersionGroupDetail (
    @SerializedName("level_learned_at")
    val levelLearnedAt: Long?,

    @SerializedName("move_learn_method")
    val moveLearnMethod: Species? = null,

    @SerializedName("version_group")
    val versionGroup: Species? = null
)

data class GenerationV (
    @SerializedName("black-white")
    val blackWhite: Sprites? = null
)

data class GenerationIv (
    @SerializedName("diamond-pearl")
    val diamondPearl: Sprites? = null,

    @SerializedName("heartgold-soulsilver")
    val heartgoldSoulsilver: Sprites? = null,

    val platinum: Sprites? = null
)

data class Versions (
    @SerializedName("generation-i")
    val generationI: GenerationI? = null,

    @SerializedName("generation-ii")
    val generationIi: GenerationIi? = null,

    @SerializedName("generation-iii")
    val generationIii: GenerationIii? = null,

    @SerializedName("generation-iv")
    val generationIv: GenerationIv? = null,

    @SerializedName("generation-v")
    val generationV: GenerationV? = null,

    @SerializedName("generation-vi")
    val generationVi: Map<String, Home>?,

    @SerializedName("generation-vii")
    val generationVii: GenerationVii? = null,

    @SerializedName("generation-viii")
    val generationViii: GenerationViii? = null
)

data class Sprites (
    @SerializedName("back_default")
    val backDefault: String?,

    @SerializedName("back_female")
    val backFemale: String? = null,

    @SerializedName("back_shiny")
    val backShiny: String?,

    @SerializedName("back_shiny_female")
    val backShinyFemale: String? = null,

    @SerializedName("front_default")
    val frontDefault: String?,

    @SerializedName("front_female")
    val frontFemale: String? = null,

    @SerializedName("front_shiny")
    val frontShiny: String?,

    @SerializedName("front_shiny_female")
    val frontShinyFemale: String? = null,

    val other: Other? = null,
    val versions: Versions? = null,
    val animated: Sprites? = null
)


data class GenerationI (
    @SerializedName("red-blue")
    val redBlue: RedBlue? = null,

    val yellow: RedBlue? = null
)


data class RedBlue (
    @SerializedName("back_default")
    val backDefault: String?,

    @SerializedName("back_gray")
    val backGray: String?,

    @SerializedName("back_transparent")
    val backTransparent: String?,

    @SerializedName("front_default")
    val frontDefault: String?,

    @SerializedName("front_gray")
    val frontGray: String?,

    @SerializedName("front_transparent")
    val frontTransparent: String?
)

data class GenerationIi (
    val crystal: Crystal? = null,
    val gold: Gold? = null,
    val silver: Gold? = null
)


data class Crystal (
    @SerializedName("back_default")
    val backDefault: String?,

    @SerializedName("back_shiny")
    val backShiny: String?,

    @SerializedName("back_shiny_transparent")
    val backShinyTransparent: String?,

    @SerializedName("back_transparent")
    val backTransparent: String?,

    @SerializedName("front_default")
    val frontDefault: String?,

    @SerializedName("front_shiny")
    val frontShiny: String?,

    @SerializedName("front_shiny_transparent")
    val frontShinyTransparent: String?,

    @SerializedName("front_transparent")
    val frontTransparent: String?
)


data class Gold (
    @SerializedName("back_default")
    val backDefault: String?,

    @SerializedName("back_shiny")
    val backShiny: String?,

    @SerializedName("front_default")
    val frontDefault: String?,

    @SerializedName("front_shiny")
    val frontShiny: String?,

    @SerializedName("front_transparent")
    val frontTransparent: String? = null
)

data class GenerationIii (
    val emerald: Emerald? = null,

    @SerializedName("firered-leafgreen")
    val fireredLeafgreen: Gold? = null,

    @SerializedName("ruby-sapphire")
    val rubySapphire: Gold? = null
)


data class Emerald (
    @SerializedName("front_default")
    val frontDefault: String?,

    @SerializedName("front_shiny")
    val frontShiny: String?
)

data class Home (
    @SerializedName("front_default")
    val frontDefault: String?,

    @SerializedName("front_female")
    val frontFemale: String? = null,

    @SerializedName("front_shiny")
    val frontShiny: String?,

    @SerializedName("front_shiny_female")
    val frontShinyFemale: String? = null
)

data class GenerationVii (
    val icons: DreamWorld? = null,

    @SerializedName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: Home? = null
)

data class DreamWorld (
    @SerializedName("front_default")
    val frontDefault: String?,

    @SerializedName("front_female")
    val frontFemale: String? = null
)


data class GenerationViii (
    val icons: DreamWorld? = null
)


data class Other (
    @SerializedName("dream_world")
    val dreamWorld: DreamWorld? = null,

    val home: Home? = null,

    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork? = null
)

data class OfficialArtwork (
    @SerializedName("front_default")
    val frontDefault: String?
)

data class Stat (
    @SerializedName("base_stat")
    val baseStat: Long?,

    val effort: Long?,
    val stat: Species? = null
)

data class Type (
    val slot: Long?,
    val type: Species? = null
)

