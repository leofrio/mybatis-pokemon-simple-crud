package org.acme.mapper;

import org.acme.form.PokemonUpdateForm;
import org.acme.model.Pokemon;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface PokemonMapper {
    @Select("SELECT p.id as id, p.p_name as name, p.p_level as level,p.evolves FROM pokemons p  order by p.id")
    List<Pokemon> getAllPokemon();

    @Select("SELECT id,p_name as name ,p_level as level,evolves FROM pokemons WHERE id = #{id}")
    Pokemon getSpecificPokemon(Long id);

    @Insert("insert into pokemons ( p_name, p_level, evolves) VALUES (#{pokemon.name},#{pokemon.level},#{pokemon.evolves})")
    @SelectKey(statement = {"select max(id) from pokemons"}, keyProperty = "pokemon.id", keyColumn = "id", before = true, resultType = Long.class)
    void createPokemon(@Param("pokemon") Pokemon pokemon);

    @Delete("delete from pokemons where id = #{id}")
    Integer removePokemon(@Param("id") Long id );
    @Update("update pokemons set p_name = #{form.name}, p_level = #{form.level},evolves = #{form.evolves} where id= #{id}")
    void updatePokemon(@Param("id") Long id,@Param("form") Pokemon form);
}
