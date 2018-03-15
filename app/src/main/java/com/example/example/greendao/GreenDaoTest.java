package com.example.example.greendao;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.example.R;
import com.example.example.common.BaseActivity;
import com.example.example.databinding.GreendaoActBinding;
import com.example.greendao.entity.House;
import com.example.greendao.entity.Person;
import com.example.greendao.entity.PersonWithHouse;
import com.example.greendao.helper.HouseDaoHelper;
import com.example.greendao.helper.PersonDaoHelper;
import com.example.greendao.helper.PersonWithHouseDaoHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2018/1/16$ 16:53$
 * <p/>
 */
public class GreenDaoTest extends BaseActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GreendaoActBinding binding = DataBindingUtil.setContentView(this, R.layout.greendao_act);
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });
        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
        binding.query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    /**增*/
    void add(){

        List<House> houseList = new ArrayList<>();
        List<Person> personList = new ArrayList<>();
        for(int j=0;j<4;j++){
            Person person =new Person();
            person.setUsername("人"+j);
            person.setAge(18);
            personList.add(person);
        }
        PersonDaoHelper.getInstance().addAll(personList);
        for(int j=0;j<4;j++){
            House house =new House();
            house.setLocation("北京"+j);
            house.setPrice("10000");
            houseList.add(house);
        }
        HouseDaoHelper.getInstance().addAll(houseList);
        //person1选house1,house2
        //house3有person2,person3
        List<PersonWithHouse> personWithHouseList = new ArrayList<>();
        PersonWithHouse personWithHouse = new PersonWithHouse(null,personList.get(1).getId(),houseList.get(1).getId());
        personWithHouseList.add(personWithHouse);
        personWithHouse = new PersonWithHouse(null,personList.get(1).getId(),houseList.get(2).getId());
        personWithHouseList.add(personWithHouse);
        personWithHouse = new PersonWithHouse(null,personList.get(2).getId(),houseList.get(3).getId());
        personWithHouseList.add(personWithHouse);
        personWithHouse = new PersonWithHouse(null,personList.get(3).getId(),houseList.get(3).getId());
        personWithHouseList.add(personWithHouse);
        PersonWithHouseDaoHelper.getInstance().addAll(personWithHouseList);
    }
    /**删*/
    void delete(){
        PersonDaoHelper.getInstance().delete("3");
    }
    /**改*/
    void update(){
        List<Person> personList = PersonDaoHelper.getInstance().query("2");
        if(personList !=null){
            Person person = personList.get(0);
            person.setUsername("改动");
            PersonDaoHelper.getInstance().updata(person);
        }
    }
}
