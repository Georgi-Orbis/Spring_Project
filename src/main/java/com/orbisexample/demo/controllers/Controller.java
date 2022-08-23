package com.orbisexample.demo.controllers;

import com.orbisexample.demo.dtos.PersonDto;
import com.orbisexample.demo.entities.Car;
import com.orbisexample.demo.services.CarService;
import com.orbisexample.demo.services.PeopleService;
import com.orbisexample.demo.entities.Person;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.io.BufferedReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


@RestController
@Slf4j
public class Controller {
    private final PeopleService peopleService;
    private final CarService carService;
    private final ModelMapper modelMapper;

    private final BufferedReader bufferedReader;


    private final URL url = new URL("http://localhost:8080/people");

    @Autowired
    public Controller(PeopleService peopleService, CarService carService, ModelMapper modelMapper, BufferedReader bufferedReader) throws MalformedURLException {
        this.peopleService = peopleService;
        this.carService = carService;
        this.modelMapper = modelMapper;
        this.bufferedReader = bufferedReader;
    }

    @RequestMapping("/people/{id}")
    public PersonDto getById(@PathVariable Long id) {
        Person person = peopleService.getPersonById(id);
        return modelMapper.map(person, PersonDto.class);
    }

    @RequestMapping("/people")
    public List<PersonDto> getAllPeople(){
        List<Person> getAllPeople = peopleService.getAllPeople();
        return getAllPeople.stream()
                .map(p -> modelMapper.map(p, PersonDto.class)).toList();
    }

    @PostMapping("/people")
    public void addPerson(@Valid @RequestBody PersonDto personDto) {
        Person person = modelMapper.map(personDto, Person.class);
        peopleService.addPerson(person);
    }

    @PutMapping("/people")
    public void editPerson(@RequestBody Person person) {
        peopleService.addPerson(person);
    }

    @DeleteMapping("/people/{id}")
    public void deleteById(@PathVariable Long id) {
        peopleService.deletePersonById(id);
    }

    @GetMapping("/people/age")
    public List<Person> getAllSelectedByAge(@RequestParam Integer age1, @RequestParam Integer age2) {
        return peopleService.findAllWithAgeBetween(age1, age2);
    }

    @GetMapping("/people/first")
    public List<Person> getAllPeopleWithFirstName(@RequestParam String firstName) {
        return peopleService.getAllPeopleWithFirstName(firstName);
    }

    @GetMapping("/people/first-last")
    public List<Person> getAllWithFirstNameAndLastName(@RequestParam String firstName,
                                                       @RequestParam String lastName) {
        return peopleService.getAllPeopleWithFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping("/people/contain")
    public List<Person> getAllWithFirstNameContaining(@RequestParam String content) {
        log.info("Calling method that finds Person if his name contains (content)[{}/content] url", url);
        return peopleService.getAllPeopleWithFirstNameContaining(content);
    }

    @GetMapping("/people/id")
    public Person getPersonById(@RequestParam Long id) {
        return peopleService.getPersonById(id);
    }

    @GetMapping("/people/beforeId")
    public List<Person> getPeopleByIdBefore(@RequestParam Long id) {
        return peopleService.getAllPeopleWithIdBefore(id);
    }

    @PostMapping("/people/beforeId/{id}")
    public List<Person> postPeopleByIdBefore(@PathVariable Long id) {
        return peopleService.getAllPeopleWithIdBefore(id);
    }

    @PostMapping("/people/addAll")
    public void addAllPeople(@RequestBody List<Person> people) {
        peopleService.saveAll(people);
    }

    @PatchMapping("/people/{id}/{firstName}")
    public void patchPersonInfo(@PathVariable Long id, @PathVariable String firstName) {
        Person person = peopleService.getPersonById(id);
        person.setFirstName(firstName);
        peopleService.addPerson(person);
    }

    @DeleteMapping("/people/delete/{id}")
    public Integer deletePersonByIdIfExists(@PathVariable Long id) {
        return peopleService.deletePersonByIdIfFound(id);
    }

    @GetMapping("/people/count")
    public Integer countAllPeopleWithIdHigherThan(@RequestParam Long id) {
        return peopleService.countAllWithIdHigherThan(id);
    }

    @PostMapping("/people/sum")
    public Integer sumAllPeopleAges() {
        return peopleService.sumAllPeopleAges();
    }

    @RequestMapping("/cars")
    public List<Car> getCars() {
        return carService.getAllCars();
    }

    @RequestMapping("/cars/{id}")
    public Car getByCarId(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @PostMapping("/cars")
    public void addCar(@RequestBody Car car) {
        carService.addCar(car);
    }

    @PutMapping("/cars")
    public void editCar(@RequestBody Car car) {
        carService.addCar(car);
    }

    @DeleteMapping("/cars/{id}")
    public void deleteByCarId(@PathVariable Long id) {
        carService.deleteCarByCarId(id);
    }

    @PostMapping("/cars/owner")
    public void setOwner(@RequestParam Long personId, @RequestParam Long carId){
        carService.addOwner(personId, carId);
    }

    @PostMapping("/people/cars/{id}")
    public List<Car> getAllPersonsCars(@PathVariable Long id){
    return peopleService.getAllPersonCars(id);
    }

    @GetMapping("/cars/brand/model")
    public List<Car> getAllCarsFromSelectedBrandAndModel(@RequestParam String brand,@RequestParam String model){
    return carService.findAllCarsFromSelectedBrandAndModel(brand, model);
    }



}
