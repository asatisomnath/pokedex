package com.maltem.pokedex;

import com.maltem.pokedex.datamodels.Pokemon;
import com.maltem.pokedex.datamodels.Type;
import com.maltem.pokedex.utils.PokedexUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PokedexController {

	@RequestMapping(value = "/pokemons", method = RequestMethod.GET)
	public List<Pokemon> listOfPokemons() {
		List<Pokemon> pokemons = PokedexUtils.getListOfPokemons("data_pokemon.json");
		return pokemons;
	}

	@RequestMapping("/pokemons/{firstN}")
	public List<Pokemon> firstNPokemons(@PathVariable("firstN") int firstN) {
		List<Pokemon> pokemons = listOfPokemons();
		return pokemons.subList(0,firstN);
	}

	@RequestMapping(value =  "/getPokemonByName/{name}", method = RequestMethod.GET)
	public ResponseEntity<Pokemon> getPokemonByName(@PathVariable("name") String name)  {
		List<Pokemon> pokemons = listOfPokemons();

		Optional<Pokemon> collect = pokemons.stream().filter(pokemon -> pokemon.getName().equals(name)).findAny();

		if(collect.isPresent()) return new ResponseEntity<>(collect.get(),HttpStatus.CREATED);

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(value = "/getPokemonsByType/{type}", method = RequestMethod.GET)
	public ResponseEntity<List<Pokemon>> getPokemonsByType( @PathVariable("type") String typeVal) {
		List<Pokemon> pokemons = listOfPokemons();

		List<Pokemon> collect = pokemons.stream().filter(pokemon -> {
			boolean isSameType = false;

			for (Type type : pokemon.getTypes()) {
				isSameType = type.getName().equals(typeVal);
				if(isSameType) return isSameType;
			}
			return isSameType;
		}).collect(Collectors.toList());


		return new ResponseEntity<>(collect, HttpStatus.CREATED);
	}


	@RequestMapping(value =  "/getPokemonByStats/baseAttack/{val}", method = RequestMethod.GET)
	public ResponseEntity<Pokemon> getPokemonByBaseAttackStats(@PathVariable("val") String val)  {
		List<Pokemon> pokemons = listOfPokemons();

		Optional<Pokemon> collect = pokemons.stream().filter(pokemon -> pokemon.getStats().getBaseAttack().equals(Integer.parseInt(val))).findAny();

		if(collect.isPresent()) return new ResponseEntity<>(collect.get(),HttpStatus.CREATED);

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(value =  "/getPokemonByStats/baseDefense/{val}", method = RequestMethod.GET)
	public ResponseEntity<Pokemon> getPokemonByBaseDefenseStats(@PathVariable("val") String val)  {
		List<Pokemon> pokemons = listOfPokemons();

		Optional<Pokemon> collect = pokemons.stream().filter(pokemon -> pokemon.getStats().getBaseDefense().equals(Integer.parseInt(val))).findAny();

		if(collect.isPresent()) return new ResponseEntity<>(collect.get(),HttpStatus.CREATED);

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(value =  "/getPokemonByStats/baseStamina/{val}", method = RequestMethod.GET)
	public ResponseEntity<Pokemon> getPokemonByBaseStaminaStats(@PathVariable("val") String val)  {
		List<Pokemon> pokemons = listOfPokemons();

		Optional<Pokemon> collect = pokemons.stream().filter(pokemon -> pokemon.getStats().getBaseStamina().equals(Integer.parseInt(val))).findAny();

		if(collect.isPresent()) return new ResponseEntity<>(collect.get(),HttpStatus.CREATED);

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(value =  "/pokemons/sortByName", method = RequestMethod.GET)
	public List<Pokemon> sortPokemonsByName() throws FileNotFoundException {
		List<Pokemon> pokemons = listOfPokemons();

		List<Pokemon> sorted = pokemons.stream().sorted(Comparator.comparing(Pokemon::getName)).collect(Collectors.toList());

		return sorted;
	}

	@RequestMapping(value = "/pokemons/sortByType", method = RequestMethod.GET)
	public ResponseEntity<List<Pokemon>> sortPokemonsByType() {
		List<Pokemon> pokemons = listOfPokemons();

		List<Pokemon> sorted = pokemons.stream().sorted(Comparator.comparing(o -> o.getTypes().get(0).getName()))
				.collect(Collectors.toList());

		return new ResponseEntity<>(sorted,HttpStatus.CREATED);
	}

	@RequestMapping(value =  "/pokemons/sortByBaseAttack", method = RequestMethod.GET)
	public List<Pokemon> sortPokemonsByBaseAttack() throws FileNotFoundException {
		List<Pokemon> pokemons = listOfPokemons();

		List<Pokemon> sorted = pokemons.stream().sorted(Comparator.comparing(o -> o.getStats().getBaseAttack()))
				.collect(Collectors.toList());


		return sorted;
	}

	@RequestMapping(value =  "/pokemons/sortByBaseDefense", method = RequestMethod.GET)
	public List<Pokemon> sortPokemonsByBaseDefense() throws FileNotFoundException {
		List<Pokemon> pokemons = listOfPokemons();

		List<Pokemon> sorted = pokemons.stream().sorted(Comparator.comparing(o -> o.getStats().getBaseDefense()))
				.collect(Collectors.toList());


		return sorted;
	}

	@RequestMapping(value =  "/pokemons/sortByBaseStamina", method = RequestMethod.GET)
	public List<Pokemon> sortPokemonsByBaseStamina() throws FileNotFoundException {
		List<Pokemon> pokemons = listOfPokemons();

		List<Pokemon> sorted = pokemons.stream().sorted(Comparator.comparing(o -> o.getStats().getBaseStamina()))
				.collect(Collectors.toList());

		return sorted;
	}




}
