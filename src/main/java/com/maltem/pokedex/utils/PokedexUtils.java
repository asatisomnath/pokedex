package com.maltem.pokedex.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maltem.pokedex.datamodels.Pokemon;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class PokedexUtils {

    public static List<Pokemon> getListOfPokemons(String fileName) {
        List<Pokemon> pokemons = new ArrayList<>();
        File pokemonJsonFile = getFileFromResources(fileName);
        try {
            byte[] jsonData = Files.readAllBytes(pokemonJsonFile.toPath());
            ObjectMapper objectMapper = new ObjectMapper();
            pokemons = objectMapper.readValue(jsonData, new TypeReference<List<Pokemon>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pokemons;
    }

    public static File getFileFromResources(String fileName) {
        ClassLoader classLoader = PokedexUtils.class.getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }
}
