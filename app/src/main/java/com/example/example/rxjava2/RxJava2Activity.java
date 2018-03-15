package com.example.example.rxjava2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import com.example.example.R;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxAdapterView;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import com.jakewharton.rxbinding2.widget.RxTextView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/10$ 15:46$
 * <p/>
 */
public class RxJava2Activity extends Activity{
    List<String> list = new ArrayList<>();
    Retrofit retrofit =RetrofitClient.crete();
    Subscription mSubscription;
    private Button btn_longClick;
    private ListView listview;
    private CheckBox checkBox;
    private EditText editText;
    private Button countDown;
    Observer<String> observer = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {
            System.out.println("onSubscribe");
        }

        @Override
        public void onNext(String s) {
            System.out.println("接收到了数据 "+s);
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("Error");
        }

        @Override
        public void onComplete() {
            System.out.println("onComplete");
        }
    };
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2);
        for(int i=0;i<10;i++){
            list.add("hello"+i);
        }
        btn_longClick=findViewById(R.id.btn_longClick);
        listview=findViewById(R.id.listview);
        checkBox=findViewById(R.id.checkBox);
        editText=findViewById(R.id.editText);
        countDown=findViewById(R.id.countDown);
        longClick();
        RxAdapter();
        RxCompoundButton();
        debounce();
        countDown();

    }
    /**创建一个Observable并自动为你调用onNext( )发射数据*/
    public void just(View view){
        Observable<String> observable = Observable.just("hello1","hello2","hello3");
        observable.subscribe(observer);
    }
    /**遍历集合，发送每个item。相当于多次回调onNext()方法
     * 所有Collection接口的实现类都可以作为Iterable对象直接传入fromIterable()
     * */
    public void fromIterable(View view){

        Observable<String> observable = Observable.fromIterable((Iterable<String>)list);
        observable.subscribe(observer);
    }
    /**当观察者订阅时，才创建Observable，并且针对每个观察者创建都是一个新的Observable*/
    public void defer(View view){
        Observable<String> observable =Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                return Observable.just("hello1","hello2","hello3");
            }
        });
        observable.subscribe(observer);
    }
    /**按固定时间间隔发射整数序列的Observable，可用作定时器。即按照固定2秒一次调用onNext()方法*/
    public void interval(View view) throws InterruptedException {
        Observable<Long> observable = Observable.interval(2, TimeUnit.SECONDS);
        observable.subscribe(new Observer<Long>(){

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                System.out.println(System.currentTimeMillis()+"");
                System.out.println(aLong+"---");
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    /**第一个参数为起始值，第二个为发送的个数，如果为0则不发送，负数则抛异常。上述表示发射1到20的数*/
    public void range(View view){
        Observable<Integer> observable =  Observable.range(5,10);
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer+"");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    /**一个给定的延迟后发射一个特殊的值*/
    public void timer(View view){
        Observable<Long> observable =Observable.timer(2,TimeUnit.SECONDS);
        observable.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe--"+System.currentTimeMillis()+"");
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("onNext--"+System.currentTimeMillis()+"");
                System.out.println(aLong+"");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    /**延迟数据发送*/
    public void delay(View view){
        Observable.just("1","2","3","4").delay(3,TimeUnit.SECONDS).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(System.currentTimeMillis());
                System.out.println(s);
            }
        });
    }
    /**该Observable的事件可以重复调用*/
    public void repeat(View view){
        Observable<Integer> observable=Observable.just(123).repeat(5);
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer+"");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    /**Consumer中的accept()方法接收一个来自Observable的单个值*/
    public void Consumer(View view){
      Observable.just("hello1","hello2","hello3").subscribe(new Consumer<String>() {
          @Override
          public void accept(String s) throws Exception {
              System.out.println(s);
          }
      });
    }
    /**把原来的Observable对象转换成另一个Observable对象*/
    public void map(View view){
        Observable<Integer> observable = Observable.just("hello1").map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return s.length();
            }
        });
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer+"");
            }
        });
    }
    /**发送的数据是集合，flatmap()重新生成一个Observable对象，并把数据转换成Observer想要的数据形式*/
    public void flatmap(View view){
        Observable<Object> observable = Observable.just(list).flatMap(new Function<List<String>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(List<String> strings) throws Exception {
                return Observable.fromIterable(strings);
            }
        });
        observable.map(new Function<Object, String>() {
            @Override
            public String apply(Object o) throws Exception {
                return o.toString();
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }
    /**返回true则表示数据满足条件，返回false则表示数据需要被过滤*/
    public void filter(View view){
        Observable<Object> observable = Observable.just(list).flatMap(new Function<List<String>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(List<String> strings) throws Exception {
                return Observable.fromIterable(strings);
            }
        });
        observable.filter(new Predicate<Object>() {
            @Override
            public boolean test(Object o) throws Exception {
                return o.equals("hello2");
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o.toString());
            }
        });
    }
    /**
     * take ：取前n个数据
     takeLast：取后n个数据
     first 只发送第一个数据
     last 只发送最后一个数据
     skip() 跳过前n个数据发送后面的数据
     skipLast() 跳过最后n个数据，发送前面的数据
     */
    /**输出最多指定数量的结果*/
    public void take(View view){
        Observable.just(list).flatMap(new Function<List<String>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(List<String> strings) throws Exception {
                return Observable.fromIterable(strings);
            }
        }).take(3).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o.toString());
            }
        });
    }
    /**允许我们在每次输出一个元素之前做一些额外的事情*/
    public void doOnNext(View view){
        Observable.just(list).flatMap(new Function<List<String>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(List<String> strings) throws Exception {
                return Observable.fromIterable(strings);
            }
        }).doOnNext(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println("准备工作");
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o.toString());
            }
        });
    }
    /**合并观察对象*/
    public void merge(View view){
        List<String> list1=new ArrayList<>();
        List<String> list2=new ArrayList<>();
        for(int i=0;i<5;i++){
            list1.add("list1--"+i);
            list2.add("list2--"+i);
        }
        Observable observable1=Observable.fromIterable(list1);
        Observable observable2=Observable.fromIterable(list2);
        Observable.merge(observable1,observable2).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o.toString());
            }
        });
    }
    /**合并多个观察对象的数据。并且允许 Func2（）函数重新发送合并后的数据*/
    public void zip(View view){
        List<String> list1=new ArrayList<>();
        List<String> list2=new ArrayList<>();
        for(int i=0;i<5;i++){
            list1.add("list1--"+i);
            list2.add("list2--"+i);
        }
        Observable observable1=Observable.fromIterable(list1);
        Observable observable2=Observable.fromIterable(list2);
        Observable observable3=Observable.zip(observable1, observable2, new BiFunction<String,String,String>() {
            @Override
            public String apply(String s, String s2) throws Exception {
                return s+s2;
            }
        });
        observable3.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o.toString());
            }
        });
    }
    /**累加器*/
    public void scan(View view){
        Observable.just(1,2,3,4,5).scan(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                return integer+integer2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer+"");
            }
        });
    }
    /**elementAt 发送数据序列中第n个数据 ，序列号从0开始*/
    public void elementAt(View view){
        Observable.just("1","2","3","4").elementAt(4,"2222").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }
    /**数据序列的开头插入指定的项 */
    public void startWith(View view){
        Observable.just("1","2","3","4").startWith(Observable.fromIterable(list)).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }
    /**把n个数据打成一个list包，然后跳过第skip个数据*/
    public void buffer(View view){
        Observable.fromIterable(list).buffer(2,1).subscribe(new Consumer<List<String>>() {
            @Override
            public void accept(List<String> strings) throws Exception {
                System.out.println(strings.toString());
            }
        });
    }
    /**线程切换*/
    public void Scheduler(View view){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                System.out.println("当前发送线程："+Thread.currentThread().getName());
                e.onNext(1);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("当前接收线程："+Thread.currentThread().getName());
                        System.out.println(integer);
                    }
                });
    }
    /**Retrofit网络请求*/
    public void Retrofit(View view){
        String appkey="5ef0e980d64e55f70bc727c1eafe0f9a";
        Api api=retrofit.create(Api.class);
        Observable<AllCity> observable=api.getAllCity(appkey);
        observable.subscribeOn(Schedulers.io())
                .flatMap(new Function<AllCity, ObservableSource<City>>() {
                    @Override
                    public ObservableSource<City> apply(AllCity allCity) throws Exception {
                        return Observable.fromIterable(allCity.getResult());
                    }
                })
                .filter(new Predicate<City>() {
                    @Override
                    public boolean test(City city) throws Exception {
                        String id = city.getId();
                        if(Integer.parseInt(id)< 5){
                            return true;
                        }
                        return false;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<City>() {
                    @Override
                    public void accept(City city) throws Exception {
                        System.out.println(city.getId()+city.getDistrict());
                    }
                });
    }
    /**切断连接，确切地讲是将Observer(观察者)切断，不再接收来自被观察者的事件，而被观察者的事件却仍在继续执行*/
    public void Disposable(View view){
        final Disposable[] disposable = new Disposable[1];
        Observable.just("hello").repeat().subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("Disposable");
                disposable[0] =d;
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
                disposable[0].dispose();
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Throwable");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
    /**处理Backpressure的策略--Error*/
    public void FlowableError(View view){
        Flowable<String> flowable=Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                for(int i=0;i<129;i++){
                    System.out.println("发送"+"hello"+i);
                    e.onNext("hello"+i);
                }
            }
        }, BackpressureStrategy.ERROR);
        flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("Subscription");
                s.request(1);
            }

            @Override
            public void onNext(String s) {
                System.out.println("接收"+s);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("Throwable");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
    /**处理Backpressure的策略--Drop*/
    public void FlowableDropStart(View view){
        Flowable<String> flowable=Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                for(int i=0;i<500;i++){
//                    System.out.println("发送"+"hello"+i);
                    e.onNext("hello"+i);
                }
            }
        },BackpressureStrategy.DROP);
        flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("Subscription");
                mSubscription = s;
                s.request(50);
            }

            @Override
            public void onNext(String s) {
                System.out.println("接收"+s);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("Throwable");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
    public void FlowableDropConsume(View view){
        mSubscription.request(50);
    }
    /**button按钮防抖操作，防连续点击, 在一段时间内，只取第一个事件，然后其他事件都丢弃*/
    public void throttleFirst(View view){
        RxView.clicks(view).throttleFirst(3,TimeUnit.SECONDS).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("Disposable");
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext");
                System.out.println(o.toString());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
    /**distinct过滤重复的数字,  distinctUntilChanged()  过滤连续重复的数据*/
    public void distinct(View view){
        List<String> list =new ArrayList<>();
        list.add("1");
        list.add("3");
        list.add("2");
        list.add("2");
        list.add("3");
        list.add("3");
        list.add("4");
        list.add("5");
        Observable.fromIterable(list).distinct().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }
    /**按钮的长按监听*/
    public void longClick(){
        RxView.longClicks(btn_longClick).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o.toString());
            }
        });
    }
    /**listView 的点击事件、长按事件处理*/
    public void RxAdapter(){
        List<String> list = new ArrayList<>() ;
        for ( int i = 0 ; i < 40 ; i++ ){
            list.add( "sss" + i ) ;
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1 );
        adapter.addAll(list);

        listview.setAdapter( adapter );
        RxAdapterView.itemClicks(listview).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("click"+integer);
            }
        });
        RxAdapterView.itemLongClicks(listview).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("longClick"+integer);
            }
        });
    }
    /**勾选监听*/
    public void RxCompoundButton(){
        RxCompoundButton.checkedChanges(checkBox).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                System.out.println(aBoolean);
            }
        });
    }
    /**关键词联想功能 。debounce()在一定的时间内没有操作就会发送事件*/
    public void debounce(){
        RxTextView.textChanges(editText).debounce(600,TimeUnit.MILLISECONDS).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence charSequence) throws Exception {
                System.out.println(charSequence+"");
            }
        });
    }
    /**接口合并concat,对缓存进行检查，如：内存缓存、本地缓存、网络，那一层有数据立即返回*/
    public void concat(View view){
        final String[] data ={null,"2","3"};
       Observable<Object> observable1=Observable.create(new ObservableOnSubscribe<Object>() {
           @Override
           public void subscribe(ObservableEmitter<Object> e) throws Exception {
              if(data[0]!=null){
                  e.onNext("hello1");
              }
              e.onComplete();
           }
       });
        Observable<Object> observable2=Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                if(data[1]!=null){
                    e.onNext("hello2");
                }
                e.onComplete();
            }
        });
        Observable<Object> observable3=Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                if(data[2]!=null){
                    e.onNext("hello3");
                }
                e.onComplete();
            }
        });
        Observable.concat(observable1,observable2,observable3).first(2).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o.toString());
            }
        });
    }
    /**验证码倒计时*/
    public void countDown(){
        RxView.clicks(countDown).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                final long count =3;
                Observable.interval(0,1,TimeUnit.SECONDS).take(count+1).map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return count-aLong;
                    }
                }).doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        countDown.setEnabled(false);
                        countDown.setTextColor(Color.GRAY);
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        countDown.setText(aLong+"秒");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        countDown.setEnabled(true);
                        countDown.setTextColor(Color.BLACK);
                        countDown.setText("发送验证码");
                    }
                });
            }
        });
    }
}
