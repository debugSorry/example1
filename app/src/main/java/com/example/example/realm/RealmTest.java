package com.example.example.realm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.example.R;
import com.example.example.common.BaseActivity;
import com.example.example.databinding.RealmActBinding;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2018/1/15$ 9:59$
 * <p/>
 */
public class RealmTest extends BaseActivity{
    private Realm realm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RealmActBinding binding =DataBindingUtil.setContentView(this, R.layout.realm_act);
        realm  = Realm.getDefaultInstance();

        //增
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
//                addJson();
            }
        });
        //删
        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updata();
            }
        });
        binding.query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query();
            }
        });
    }
    /**增*/
    void add(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.createObject(User.class, UUID.randomUUID().toString());
//                user.setAge(18);
                user.setName("谁谁谁");
                RealmList<Dog> dogs = new RealmList<>();
                for(int i=0;i<3;i++){
                    Dog dog = realm.createObject(Dog.class);
                    dog.setAge(5);
                    dog.setFale(true);
                    dog.setName("小白"+i);
                    dogs.add(dog);
                }
                user.setDogRealmList(dogs);
                realm.copyToRealmOrUpdate(user);
            }
        });

    }
    /**增 json 和异步*/
    void addJson(){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                String json = "{id:\"123\",age:5,name:\"szh\",dogRealmList:[{name:\"hh\",age:2,isFale:false},{name:\"sss\",age:4,isFale:false}]}";
                realm.createObjectFromJson(User.class,json);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                System.out.println("success");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                System.out.println("error");
                System.out.println(error);
            }
        });
    }
    /**删*/
    void delete(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<User> result= realm.where(User.class).findAll();
                result.get(4).deleteFromRealm();//删除指定位置（第4条记录）的记录
                result.deleteFromRealm(4);//删除指定位置（第4条记录）的记录
                result.deleteFirstFromRealm(); //删除user表的第一条数据
                result.deleteLastFromRealm();//删除user表的最后一条数据
                result.deleteAllFromRealm();//删除user表的全部数据
            }
        });
    }
    /**改*/
    void updata(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Dog> result = realm.where(Dog.class).equalTo("name","hh").findAll();
                for(Dog dog : result){
                    dog.setAge(20);
                }
            }
        });
    }
    /**查*/
    void query(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<User> users = realm.where(User.class).findAllSorted("id", Sort.DESCENDING);//按照id倒序排序
                long size = users.size();//返回数据的条数
                double average = users.average("age");//返回查询结果的中age的平均值
                long sum = users.sum("age").longValue();//返回查询结果中age的总数
                long min = users.min("age").longValue();//返回查询结果中age的最小值
                long max = users.max("age").longValue();//返回查询结果中age的最大值
                System.out.println(size);
                System.out.println(average);
                System.out.println(sum);
                System.out.println(min);
                System.out.println(max);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
