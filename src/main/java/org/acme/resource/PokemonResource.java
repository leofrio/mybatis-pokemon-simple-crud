package org.acme.resource;

import org.acme.form.PokemonUpdateForm;
import org.acme.mapper.PokemonMapper;
import org.acme.model.Pokemon;
import org.apache.ibatis.annotations.Insert;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/pokemons")
public class PokemonResource {
    @Inject
    PokemonMapper pokemonMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pokemon> getall() {
        return pokemonMapper.getAllPokemon();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Pokemon getSpecificPokemon(Long id) {
        return pokemonMapper.getSpecificPokemon(id);
    }

    @POST
    public Pokemon createPokemon(Pokemon pokemon) {
        pokemonMapper.createPokemon(pokemon);
        return pokemon;
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Integer removePokemon(@PathParam("id") Long id) {
        return pokemonMapper.removePokemon(id);
    }

    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Pokemon updatePokemon(@PathParam("id") Long id , PokemonUpdateForm form) {
        Pokemon pokemon =getSpecificPokemon(id);
        pokemon.name=form.name != null ? form.name : pokemon.name;
        pokemon.level=(form.level != null  ? form.level : pokemon.level);
        pokemon.evolves=form.evolves != null ? form.evolves : pokemon.evolves;
        pokemonMapper.updatePokemon(id,pokemon);
        return pokemon;
    }
}
