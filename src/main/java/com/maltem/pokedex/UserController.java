package com.maltem.pokedex;

import com.maltem.pokedex.acccessor.DataRepository;
import com.maltem.pokedex.acccessor.UserRepository;
import com.maltem.pokedex.datamodels.Data;
import com.maltem.pokedex.datamodels.Login;
import com.maltem.pokedex.datamodels.Pokemon;
import com.maltem.pokedex.datamodels.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User loginUser(@RequestBody Login login) throws Exception {
        List<User> users = userRepository.findByEmailId(login.getEmailId());

        if (!users.isEmpty() && !users.get(0).getPassword().equals(login.getPassword())) {
            throw new Exception("Invalid password");
        }

        boolean status = users.get(0).isActive();

        if(status){
            throw new Exception("user is already logged in !");
        } else {
            users.get(0).setActive(true);
            User updatedData = userRepository.save(users.get(0));
            return updatedData;
        }
    }

    @RequestMapping(value = "/getPokemons", method = RequestMethod.POST)
    public List<Pokemon> getPokemons(@RequestBody Login login) {
        List<Data> dataList = dataRepository.findByEmail(login.getEmailId());

        List<Pokemon> pokemons = new ArrayList<>();
        for(Data data: dataList){
            pokemons.add(data.getPokemon());
        }

        return pokemons;
    }


    @RequestMapping(value = "/addPokemon", method = RequestMethod.POST)
    public Data addPokemon(@RequestBody Login login, @RequestBody Pokemon pokemon)  {

        Data data = new Data();
        data.setEmail(login.getEmailId());
        data.setPokemon(pokemon);

        Data savedData = dataRepository.save(data);
        return savedData;
    }

    @RequestMapping(value = "/updatePokemon", method = RequestMethod.POST)
    public Data updatePokemon(@RequestBody Login login, @RequestBody Pokemon pokemon) throws Exception {

        List<Data> dataList = dataRepository.findByEmailAndPokemon_Dex(login.getEmailId(), pokemon.getDex());

        if(dataList.size() == 0) {
            throw new Exception("pokemon doesn't exist !");
        }
        dataList.get(0).setPokemon(pokemon);
        Data updatedData = dataRepository.save(dataList.get(0));
        return updatedData;
    }

    @RequestMapping(value = "/removePokemon", method = RequestMethod.POST)
    public Data removePokemon(@RequestBody Login login, @RequestBody Pokemon pokemon) throws Exception {

        List<Data> dataList = dataRepository.findByEmailAndPokemon_Dex(login.getEmailId(), pokemon.getDex());

        if(dataList.size() == 0) {
            throw new Exception("pokemon doesn't exist !");
        }
        dataRepository.delete(dataList.get(0));
        return dataList.get(0);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public User logout(@RequestBody Login login) throws Exception {
        List<User> users = userRepository.findByEmailId(login.getEmailId());

        if(users.isEmpty()) {
            throw new Exception("user doesn't exist !");
        }

        boolean status = users.get(0).isActive();

        if(status){
            users.get(0).setActive(false);
            User updatedData = userRepository.save(users.get(0));
            return updatedData;
        } else {
            throw new Exception("user is already logged out !");
        }
    }



}
